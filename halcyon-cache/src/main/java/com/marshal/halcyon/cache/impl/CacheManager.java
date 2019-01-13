package com.marshal.halcyon.cache.impl;

import com.marshal.halcyon.cache.ICache;
import com.marshal.halcyon.cache.ICacheManager;
import com.marshal.halcyon.core.listener.ContextRefreshedListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auth: Marshal
 * @date: 2019/1/13
 * @desc:
 */
@Component
public class CacheManager implements ICacheManager, ContextRefreshedListener {


    private HashMap<String, ICache> cacheMap = new HashMap<>();
    private List<ICache> caches;

    @Override
    public <T> ICache<T> getCache(String name) {
        return null;
    }

    @Override
    public void addCache(ICache<?> cache) {

    }

    @Override
    public List<ICache> getCaches() {
        return null;
    }

    @Override
    public void contextInitialized(ApplicationContext applicationContext) {
        Map<String, ICache> cacheBeans = applicationContext.getBeansOfType(ICache.class);
        if (cacheBeans != null) {
            cacheBeans.forEach((k, v) -> {
                if (caches == null || !caches.contains(v)) {
                    if (StringUtils.isEmpty(v.getName())) {
                        throw new RuntimeException(v + " cacheName is empty");
                    }
//                    addCache(v);
                    v.init();
                }
            });
        }
    }
}
