package com.marshal.halcyon.cache;

import java.util.List;

public interface ICacheManager {
    /**
     * 根据 name 获取 ICache.
     *
     * @param name cache name
     * @param <T>  cache 元素类型
     * @return ICache, may be null
     */
    <T> ICache<T> getCache(String name);

    /**
     * 注册 cache.
     * <p>
     * 根据 cache 的 name 注册.
     *
     * @param cache cache
     */
    void addCache(ICache<?> cache);

    /**
     * 获取所有缓存.
     *
     * @return 缓存列表
     */
    List<ICache> getCaches();
}
