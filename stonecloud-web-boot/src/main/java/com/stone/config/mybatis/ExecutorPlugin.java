package com.stone.config.mybatis;

import java.util.Properties;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import lombok.extern.slf4j.Slf4j;

@Intercepts({ @Signature(type = Executor.class, 
							method = "query", 
							args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class }
						) 
			})
@Slf4j
public class ExecutorPlugin implements Interceptor {
	
	public Object intercept(Invocation invocation) throws Throwable {
		log.info("intercept");
		log.info(invocation.getTarget().getClass().toString());
		Object[] args = invocation.getArgs();
		log.info(((BoundSql)args[5]).getSql());
		return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
	}
}
