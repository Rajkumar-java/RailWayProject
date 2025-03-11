package com.railway.railway_dashboard_service.configuration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfiguration 
{
	  // Defining CacheManager Bean for Simple Caching
	  @Bean
      public CacheManager cacheManager()
      {
    	    return new ConcurrentMapCacheManager("kpichache") ; 
      }
}
