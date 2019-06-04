package com.zhangrui.data.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @Author: YSTen
 * @Date: Created at 2019-06-04-17:36
 * @Description:
 * @Modified: By
 */
@Configuration
@EnableCaching
@Slf4j
public class RedisCacheConfig extends CachingConfigurerSupport {

	@Bean
	public CacheManager cacheManager(RedisTemplate redisTemplate) {
		RedisCacheManager rcm = new RedisCacheManager(redisTemplate);
		// 设置缓存默认过期时间（全局的）
		rcm.setDefaultExpiration(1800);
		return rcm;
	}

	@Bean
	public RedisTemplate<String, String> redisTemplate(
		RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	@Override
	public CacheErrorHandler errorHandler() {
		CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
			@Override
			public void handleCacheGetError(RuntimeException e, Cache cache, Object key) {
				log.warn("Method:handleCachePutError get key:{}", key);
				if (e instanceof JedisConnectionException || e instanceof RedisConnectionFailureException) {
					log.warn("Method:handleCacheGetError redis has lose connection:", e);
					return;
				}
				throw e;
			}

			@Override
			public void handleCachePutError(RuntimeException e, Cache cache, Object key, Object value) {
				log.warn("Method:handleCachePutError put key:{}", key);
				if (e instanceof JedisConnectionException || e instanceof RedisConnectionFailureException) {
					log.warn("Method:handleCachePutError redis has lose connection:", e);
					return;
				}
				throw e;
			}

			@Override
			public void handleCacheEvictError(RuntimeException e, Cache cache, Object key) {
				log.warn("Method:handleCacheEvictError clear key:{}", key);
				if (e instanceof JedisConnectionException || e instanceof RedisConnectionFailureException) {
					log.warn("Method:handleCacheEvictError redis has lose connection:", e);
					return;
				}
				throw e;
			}

			@Override
			public void handleCacheClearError(RuntimeException e, Cache cache) {
				if (e instanceof JedisConnectionException || e instanceof RedisConnectionFailureException) {
					log.warn("Method:handleCacheClearError redis has lose connection:", e);
					return;
				}
				throw e;
			}
		};
		return cacheErrorHandler;
	}
}
