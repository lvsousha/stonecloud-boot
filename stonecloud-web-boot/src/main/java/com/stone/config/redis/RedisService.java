package com.stone.config.redis;

import java.util.concurrent.TimeUnit;

public interface RedisService {
  <T> T get(RedisKey key);

  <T> void delete(RedisKey key);

  <T> void set(RedisKey key, T t);

  <T> void set(RedisKey key, T t, long timeout, TimeUnit unit);

  <T> T hGet(RedisKey key, String hashKey);

  <T> void hSet(RedisKey key, String hashKey, T t);

  <T> void hDelete(RedisKey key, String hashKey);

  <T> void delete(RedisKey key, String keySuffix);

  <T> T get(RedisKey key, String keySuffix);

  /**
   * 用于处理可自动过期的key
   * 
   * @param key
   * @param keySuffix
   * @param t
   * @param timeout
   * @param unit
   */
  <T> void set(RedisKey key, String keySuffix, T t, long timeout, TimeUnit unit);


  /**
   * 用于处理可自动过期的key
   * 
   * @param key
   * @param keySuffix
   * @param t
   * @param timeout
   * @param unit
   */
  <T> boolean setIfAbsent(RedisKey key, String keySuffix, T t, long timeout, TimeUnit unit);
}
