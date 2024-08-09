package org.raddan.newspaper.config.counter;

/**
 * @author Alexander Dudkin
 */
public interface Counter {
    void increment(String key);
    long getCount(String key);
}
