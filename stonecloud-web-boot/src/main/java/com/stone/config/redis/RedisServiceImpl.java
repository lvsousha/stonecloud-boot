package com.stone.config.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {

  private static final String SUFFIX_SEPARATOR = "_";

  @Resource
  private RedisTemplate<String, Object> redisTemplate;

  private <T> T convertInstanceOfObject(Object o, Class<T> clazz) {
    if (o == null) {
      return null;
    }
    if (!clazz.isAssignableFrom(o.getClass())) {
      throw new RuntimeException(
          String.format("[Redis Error] except type %s but %s", clazz.getName(), o.getClass()));
    }
    return clazz.cast(o);
  }

  private <T> void validateSetOperate(RedisKey key, T t) {
    if (t == null) {
      throw new RuntimeException("[Redis Error] value must not be null");
    }
    if (!key.getClazz().isAssignableFrom(t.getClass())) {
      throw new RuntimeException(
          String.format("[Redis Error] value type not match,except type %s but %s", key.getClazz(),
              t.getClass()));
    }
  }


  @Override
  public <T> T get(RedisKey key) {
    Object obj = redisTemplate.opsForValue().get(key.toString());
    return convertInstanceOfObject(obj, key.getClazz());
  }

  @Override
  public <T> void set(RedisKey key, T t) {
    validateSetOperate(key, t);
    redisTemplate.opsForValue().set(key.toString(), t);
  }

  @Override
  public <T> void set(RedisKey key, T t, long timeout, TimeUnit unit) {
    validateSetOperate(key, t);
    redisTemplate.opsForValue().set(key.toString(), t, timeout, unit);
  }

  @Override
  public <T> T hGet(RedisKey key, String hashKey) {
    Object obj = redisTemplate.opsForHash().get(key.toString(), hashKey);
    return convertInstanceOfObject(obj, key.getClazz());
  }

  @Override
  public <T> void hSet(RedisKey key, String hashKey, T t) {
    validateSetOperate(key, t);
    redisTemplate.opsForHash().put(key.toString(), hashKey, t);
  }

  @Override
  public <T> void delete(RedisKey key) {
    redisTemplate.delete(key.toString());
  }

  @Override
  public <T> void hDelete(RedisKey key, String hashKey) {
    redisTemplate.opsForHash().delete(key.toString(), hashKey);
  }

  @Override
  public <T> T get(RedisKey key, String keySuffix) {
    String realKey = key.toString() + SUFFIX_SEPARATOR + keySuffix;
    Object obj = redisTemplate.opsForValue().get(realKey);
    return convertInstanceOfObject(obj, key.getClazz());
  }


  @Override
  public <T> void set(RedisKey key, String keySuffix, T t, long timeout, TimeUnit unit) {
    validateSetOperate(key, t);
    String realKey = key.toString() + SUFFIX_SEPARATOR + keySuffix;
    redisTemplate.opsForValue().set(realKey, t, timeout, unit);
  }

  @Override
  public <T> boolean setIfAbsent(RedisKey key, String keySuffix, T t, long timeout, TimeUnit unit) {
    validateSetOperate(key, t);
    String realKey = key.toString() + SUFFIX_SEPARATOR + keySuffix;
    boolean res = redisTemplate.opsForValue().setIfAbsent(realKey, t);
    if (res) {
      redisTemplate.expire(realKey, timeout, unit);
    }
    return res;
  }

  @Override
  public <T> void delete(RedisKey key, String keySuffix) {
    String realKey = key.toString() + SUFFIX_SEPARATOR + keySuffix;
    redisTemplate.delete(realKey);
  }
}
