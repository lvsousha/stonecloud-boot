package com.stone.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import com.stone.CompositePropertySourceFactory;
import com.stone.service.RedisService;
import com.stone.service.impl.RedisServiceImpl;
import lombok.Data;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@EnableCaching
@PropertySource(value = {"classpath:config/${spring.profiles.active}/redis.yml"},factory = CompositePropertySourceFactory.class)
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisConfig {
  
  private Integer database;
  private String host;
  private Integer port;
  private String password;
  private String timeout;
  @Bean
  @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
  public JedisPoolConfig getJedisPoolConfig(){
      JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
      return jedisPoolConfig;
  }
  
  @Bean
  public RedisStandaloneConfiguration getRedisStandaloneConfiguration(){
    RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
    RedisPassword redisPassword = RedisPassword.of(password);
    redisStandaloneConfiguration.setDatabase(database);
    redisStandaloneConfiguration.setPassword(redisPassword);
    redisStandaloneConfiguration.setHostName(host);
    redisStandaloneConfiguration.setPort(port);
      return redisStandaloneConfiguration;
  }
  
  @Bean
  public JedisConnectionFactory getConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration = getRedisStandaloneConfiguration();
    //获得默认的连接池构造
    //这里需要注意的是，edisConnectionFactoryJ对于Standalone模式的没有（RedisStandaloneConfiguration，JedisPoolConfig）的构造函数，对此
    //我们用JedisClientConfiguration接口的builder方法实例化一个构造器，还得类型转换
    JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpcf = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
    //修改我们的连接池配置
    jpcf.poolConfig(getJedisPoolConfig());
    //通过构造器来构造jedis客户端配置
    JedisClientConfiguration jedisClientConfiguration = jpcf.build();

    return new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration);
  }
  
  @Bean
  public RedisTemplate<String, Object> getRedisTemplate(JedisConnectionFactory factory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setConnectionFactory(factory);
    GenericJackson2JsonRedisSerializer genericJackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
    redisTemplate.setValueSerializer(genericJackson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer(genericJackson2JsonRedisSerializer);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.afterPropertiesSet();
    return redisTemplate;
  }

  @Bean
  public StringRedisTemplate getStringRedisTemplate(RedisConnectionFactory factory) {
    StringRedisTemplate stringTemplate = new StringRedisTemplate();
    stringTemplate.setConnectionFactory(factory);
    RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    stringTemplate.setKeySerializer(redisSerializer);
    stringTemplate.setHashKeySerializer(redisSerializer);
    stringTemplate.setValueSerializer(redisSerializer);
    return stringTemplate;
  }
  
  @Bean
  public RedisService redisService(RedisTemplate<String, Object> redisTemplate) {
    RedisService redisService = new RedisServiceImpl(redisTemplate);
    return redisService;
  }
}
