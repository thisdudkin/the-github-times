package org.raddan.newspaper.config.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Alexander Dudkin
 */
@Service
public class RedisCounter implements Counter {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void increment(String key) {
        redisTemplate.opsForValue().increment(key);
    }

    @Override
    public long getCount(String key) {
        Long count = (Long) redisTemplate.opsForValue().get(key);
        return count != null ? count : 0;
    }

}
