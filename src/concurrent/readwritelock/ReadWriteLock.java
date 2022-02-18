package concurrent.readwritelock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by luque_ruby on 2022/2/18.
 */
public class ReadWriteLock {
    public static void main(String[] args) {
        //普通未加锁
//        MyCache myCache = new MyCache();
//        for (int i = 0; i < 5; i++) {
//            final int temp = i;
//            new Thread(()->{myCache.put(temp + "", temp + "");}, String.valueOf(i)).start();
//        }
//
//        for (int i = 0; i < 5; i++) {
//            final int temp = i;
//            new Thread(()->{myCache.get(temp + "");}, String.valueOf(i)).start();
//        }

        //读写锁
        MyCacheLock myCacheLock = new MyCacheLock();
        for (int i = 0; i < 50; i++) {
            final int temp = i;
            new Thread(()->{myCacheLock.put(temp + "", temp + "");}, String.valueOf(i)).start();
        }

        for (int i = 0; i < 50; i++) {
            final int temp = i;
            new Thread(()->{myCacheLock.get(temp + "");}, String.valueOf(i)).start();
        }
    }
}

/**
 * 自定义缓存
 */
class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) {
        System.out.println("MyCache " + Thread.currentThread().getName() + "写入" + key);
        map.put(key, value);
        System.out.println("MyCache " + Thread.currentThread().getName() + "写入OK");

    }

    public void get(String key) {
        System.out.println("MyCache " + Thread.currentThread().getName() + "读取" + key);
        Object o = map.get(key);
        System.out.println("MyCache " + Thread.currentThread().getName() + "读取OK");

    }
}

class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    /** 读写锁，更加细粒度的控制*/
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    /** 写入的时候只希望同时只有一个线程写*/
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();

        try {
            System.out.println("MyCacheLock " + Thread.currentThread().getName() + "写入" + key);
            map.put(key, value);
            System.out.println("MyCacheLock " + Thread.currentThread().getName() + "写入OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }

    }

    /** 读，希望所有人都可以读*/
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println("MyCacheLock " + Thread.currentThread().getName() + "读取" + key);
            Object o = map.get(key);
            System.out.println("MyCacheLock " + Thread.currentThread().getName() + "读取OK");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }

    }
}
