package dev.feliperos.configuration;

import lombok.Getter;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * Classe de Construção do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 25/2/2024
 * @version 1.0
 */
@Getter
public class RedisConfiguration {
    private final JedisPool jedis;

    public RedisConfiguration(RedisConfig config) {
        this.jedis = new JedisPool(

        );
    }

    /**
     * Construção da Pool de Conexões do Redis.
     *
     * @param maxConnections número de conexões da Pool.
     * @return {@link JedisPoolConfig} objeto de configuração da Pool.
     */
    private JedisPoolConfig buildPoolConfig(int maxConnections) {
        final JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxConnections);
        poolConfig.setMaxIdle(128);
        poolConfig.setMinIdle(16);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setMinEvictableIdleDuration(Duration.ofSeconds(60));
        poolConfig.setTimeBetweenEvictionRuns(Duration.ofSeconds(30));
        poolConfig.setNumTestsPerEvictionRun(3);
        poolConfig.setBlockWhenExhausted(true);
        return poolConfig;
    }
}
