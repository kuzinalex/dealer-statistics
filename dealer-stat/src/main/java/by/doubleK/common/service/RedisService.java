package by.doubleK.common.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    public void addCodeToRedis(String key, String value) {
        redisTemplate.opsForValue().set(key, value, 1, TimeUnit.DAYS);
    }


    public boolean checkCodeFromRedis(String key, String value) {

        return value.equals(redisTemplate.opsForValue().get(key));
    }


    public String getValueFromRedis(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
