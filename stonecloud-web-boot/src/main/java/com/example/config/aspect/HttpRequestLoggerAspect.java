package com.example.config.aspect;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

@Component
@Aspect
public class HttpRequestLoggerAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequestLoggerAspect.class);

	@Around("@annotation(requestMapping)")
	public Object disPlayRequestInfo(ProceedingJoinPoint point, RequestMapping requestMapping) throws Throwable {
		return doLog(point, LOGGER);
	}

	private static final String CRLF = "\r\n";

	protected Object doLog(ProceedingJoinPoint point, Logger logger) throws Throwable {
		String className = null;
		String methodName = null;
		Method method = null;
		String paramLog = null;
		try {
			Signature signature = point.getSignature();
			logger.info(signature.getClass().getName());
			Object[] args = point.getArgs();
			MethodSignature methodSignature = null;
			boolean hasResult = false;
			if (signature instanceof MethodSignature) {
				methodSignature = (MethodSignature) signature;
				method = methodSignature.getMethod();
				methodName = method.getName();
				className = method.getDeclaringClass().getName();
				hasResult = methodSignature.getReturnType() != Void.TYPE
						&& methodSignature.getReturnType() != Void.class;
			} else {
				return point.proceed();
			}

			long startTime = System.currentTimeMillis();
			RequestMethodInfo requestInfo = getRequestInfo(methodSignature, args, logger);

			paramLog = getLogMessage(requestInfo);
			if (requestInfo.isDoInfoLog()) {
				logger.info(paramLog);
			}

			Object resultObject = null;
			if (hasResult) {
				resultObject = point.proceed();
			} else {
				point.proceed();
			}

			long endTime = System.currentTimeMillis();
			long spendTime = endTime - startTime;
			String timeMessage = className + "." + methodName + " spend time " + spendTime + " ms";
			if (spendTime < 10) {
				logger.debug(timeMessage);
			} else if (spendTime < 50) {
				logger.info(timeMessage);
			} else {
				logger.error(timeMessage);
			}
			// logger.info(className + "." + methodName + " return value:" +
			// JSONObject.toJSONString(result));
			return resultObject;
		} catch (Throwable e) {
			if (paramLog != null) {
				logger.error("执行如下操作失败，方法以及参数为：===============================");
				logger.error(paramLog);
				logger.error("具体错误如下：：===============================");
				logger.error("========", e);
			} else if (className != null && methodName != null) {
				logger.error("Invoke method:" + className + "." + methodName + " error");
			} else {
				logger.error("Invoke method error");
			}
			throw e;

		}

	}

	private String getLogMessage(RequestMethodInfo requestInfo) {
		StringBuilder sb = new StringBuilder();
		if (requestInfo != null) {
			sb.append("method:" + requestInfo.getClassName() + "." + requestInfo.getMethodString() + CRLF);
			if (requestInfo.getRequestMapping() != null) {
				sb.append("  requestMapping:" + requestInfo.getRequestMapping() + CRLF);
			}
			Map<String, String> otherParams = requestInfo.getOtherParams();
			if (otherParams != null) {
				sb.append("  params =============================================" + CRLF);
				for (Map.Entry<String, String> paramEntry : otherParams.entrySet()) {
					sb.append("  key: [" + paramEntry.getKey() + "], value :[" + paramEntry.getValue() + "]" + CRLF);
				}
			}
			Map<String, String> requestParams = requestInfo.getRequestParams();
			if (requestParams != null) {
				sb.append(" httpRequestParams ====================================" + CRLF);
				for (Map.Entry<String, String> paramEntry : requestParams.entrySet()) {
					sb.append("  key: [" + paramEntry.getKey() + "], value :[" + paramEntry.getValue() + "]" + CRLF);
				}
			}
			Map<String, String> requestHeaderParams = requestInfo.getRequestHeaderParams();
			if (requestHeaderParams != null) {
				sb.append(" requestHeaderParams ====================================" + CRLF);
				for (Map.Entry<String, String> paramEntry : requestHeaderParams.entrySet()) {
					sb.append("  key: [" + paramEntry.getKey() + "], value :[" + paramEntry.getValue() + "]" + CRLF);
				}
			}
		}
		return sb.toString();
	}

	private RequestMethodInfo getRequestInfo(MethodSignature methodSignature, Object[] args, Logger logger) {
		RequestMethodInfo requestInfo = new RequestMethodInfo();
		Class<?>[] parameterTypes = methodSignature.getParameterTypes();
		Method method = methodSignature.getMethod();
		String className = method.getDeclaringClass().getName();
		String methodName = method.getName();
		Class<?> clazz = method.getDeclaringClass();
		RequestMapping clazzRequestMapping = clazz.getAnnotation(RequestMapping.class);
		StringBuilder requestMappingStr = new StringBuilder();
		if (clazzRequestMapping != null) {
			requestMappingStr.append(Joiner.on("|").join(clazzRequestMapping.value()));
		}
		RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
		if (requestMapping != null && requestMapping.value() != null) {
			String[] mappingStrArray = requestMapping.value();
			for (String mapping : mappingStrArray) {
				if (mapping != null) {
					mapping = mapping.startsWith("/") ? mapping : "/" + mapping;
					requestMappingStr.append(mapping);
				}
			}
			requestInfo.setRequestMapping(requestMappingStr.toString());
		}
		requestInfo.setClassName(className);
		requestInfo.setMethodName(methodName);
		LocalVariableTableParameterNameDiscoverer paramNameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
		String[] paramNames = paramNameDiscoverer.getParameterNames(methodSignature.getMethod());
		try {
			StringBuilder methodSb = new StringBuilder();
			methodSb.append(methodName + "(");
			if (parameterTypes != null && parameterTypes.length > 0) {
				Map<String, String> paramMap = Maps.newHashMap();
				int paramLength = parameterTypes.length;
				for (int i = 0; i < paramLength; i++) {
					Class<?> type = parameterTypes[i];
					Object arg = args[i];
					if (arg == null) {
						continue;
					}
					String paramName = null;
					if (paramNames != null) {
						paramName = paramNames[i];
					}
					if (paramName == null) {
						paramName = "arg" + i;
					}
					methodSb.append(type.getSimpleName() + " " + paramName);
					if (i < paramLength - 1) {
						methodSb.append(", ");
					}
					Package argPackage = arg.getClass().getPackage();
					String value = null;
					if (arg instanceof HttpServletRequest) {
						Map<String, String> requestParamMap = Maps.newHashMap();
						HttpServletRequest request = (HttpServletRequest) arg;
						Enumeration<String> requestParamNames = request.getParameterNames();
						requestInfo.setRequestParams(requestParamMap);
						while (requestParamNames.hasMoreElements()) {
							String requestParamName = requestParamNames.nextElement();
							String requestParamValue = request.getParameter(requestParamName);
							requestParamMap.put(requestParamName, requestParamValue);
						}
						Map<String, String> requestHeaderNamesMap = Maps.newHashMap();
						Enumeration<String> requestHeaderNames = request.getHeaderNames();
						requestInfo.setRequestHeaderParams(requestHeaderNamesMap);
						while (requestHeaderNames.hasMoreElements()) {
							String requestHeadName = requestHeaderNames.nextElement();
							String requestHeadValue = request.getHeader(requestHeadName);
							requestHeaderNamesMap.put(requestHeadName, requestHeadValue);
						}
					} else if (arg instanceof HttpServletResponse) {
						continue;
					} else if (arg instanceof String) {
						value = (String) arg;
					} else if (arg instanceof MultipartFile) {
						continue;
					} else if (argPackage != null && argPackage.getName().contains("org.springframework")) {
						continue;
					} else {
						value = JSONObject.toJSONString(arg);
					}
					paramMap.put(paramName, value);
					requestInfo.setOtherParams(paramMap);
				}
			}
			methodSb.append(")");
			requestInfo.setMethodString(methodSb.toString());
			return requestInfo;
		} catch (Throwable t) {
			throw new RuntimeException(
					"logger " + className + "." + methodName + ";args:" + Arrays.toString(args) + "error", t);
		}
	}
}