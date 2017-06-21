package com.javarush.task.task33.task3310.strategy;

/**
 * Created by kalinnikov_al on 04.06.2017.
 */
public class FileStorageStrategy implements StorageStrategy {

    static final int DEFAULT_INITIAL_CAPACITY = 16;
    static final long DEFAULT_BUCKET_SIZE_LIMIT = 10000;
    FileBucket[] table = new FileBucket[DEFAULT_INITIAL_CAPACITY];
    int size;
    private long bucketSizeLimit = DEFAULT_BUCKET_SIZE_LIMIT;
    long maxBucketSize=0;

    public long getBucketSizeLimit() {
        return bucketSizeLimit;
    }

    public void setBucketSizeLimit(long bucketSizeLimit) {
        this.bucketSizeLimit = bucketSizeLimit;
    }

    @Override
    public boolean containsKey(Long key) {
        return getEntry(key) != null;
    }

    @Override
    public boolean containsValue(String value) {
        if (value == null)
            return false;

        for (FileBucket table : table) {
            if (table == null) continue;
            for (Entry e = table.getEntry(); e != null; e = e.next)
                if (value.equals(e.value))
                    return true;
        }
        return false;
    }

    @Override
    public void put(Long key, String value) {
        addEntry(hash(key), key, value, indexFor(hash(key),table.length));
    }

    @Override
    public Long getKey(String value) {
        if (value == null)
            return 0L;

        for (FileBucket table : table) {
            if (table == null) continue;
            for (Entry e = table.getEntry(); e != null; e = e.next)
                if (value.equals(e.value))
                    return e.key;
        }
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null == getEntry(key) ? null : getEntry(key).getValue();
    }

    int hash(Long k){
        return k.hashCode();
    }

    int indexFor(int hash, int length){
        return (length - 1) & hash;
    }

    Entry getEntry(Long key){
        if (size == 0)
            return null;

        int hash = (key == null) ? 0 : hash(key);

        Entry e = table[indexFor(hash, table.length)].getEntry();

        if (e != null)
            for (;e != null; e = e.next) {
                Object k;
                if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k))))
                    return e;
            }
        return null;
    }

    void resize(int newCapacity) {

        if (maxBucketSize > bucketSizeLimit ) {
            FileBucket[] newTable = new FileBucket[newCapacity];
            transfer(newTable);
            table = newTable;
            maxBucketSize = 0;
        }

    }

    void transfer(FileBucket[] newTable){
        int newCapacity = newTable.length;
        Entry e;
        for (FileBucket fb : table) {
            e = fb.getEntry();
            fb.remove();
            fb = null;
            while(null != e) {
                Entry next = e.next;
                int i = indexFor(e.hash, newCapacity);

                if (newTable[i] == null) {
                    newTable[i] = new FileBucket();
                    e.next = null;
                    newTable[i].putEntry(e);
                } else {
                    e.next = newTable[i].getEntry();
                    newTable[i].putEntry(e);
                }
                if (newTable[i].getFileSize() > maxBucketSize) maxBucketSize = newTable[i].getFileSize();
                e = next;
            }

        }
    }

    void addEntry(int hash, Long key, String value, int bucketIndex){
        if ((maxBucketSize > bucketSizeLimit) && (null != table[bucketIndex])) {
            resize(2 * table.length);
            hash = (null != key) ? hash(key) : 0;
            bucketIndex = indexFor(hash, table.length);
        }
        createEntry(hash, key, value, bucketIndex);
    }

    void createEntry(int hash, Long key, String value, int bucketIndex){
        if (table[bucketIndex] == null) {
            table[bucketIndex] = new FileBucket();
            table[bucketIndex].putEntry(new Entry(hash, key, value, null));
        } else {
            Entry e = table[bucketIndex].getEntry();
            table[bucketIndex].putEntry(new Entry(hash, key, value, e));
        }
        size++;
    }
}
