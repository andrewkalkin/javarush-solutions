package com.javarush.task.task37.task3708.retrievers;

import com.javarush.task.task37.task3708.cache.LRUCache;
import com.javarush.task.task37.task3708.storage.Storage;

/**
 * Created by kalinnikov_al on 06.06.2017.
 */
public class CachingProxyRetriever implements Retriever {

    LRUCache cache;
    OriginalRetriever retriever;

    public CachingProxyRetriever(Storage storage) {
        this.retriever = new OriginalRetriever(storage);
        cache = new LRUCache(16);
    }

    @Override
    public Object retrieve(long id) {

        Object obj = cache.find(id);

        if (obj == null) {
            obj = retriever.retrieve(id);
            cache.set(id, obj);
        }

        return obj;
    }
}
