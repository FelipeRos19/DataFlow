package dev.feliperos;

import dev.feliperos.configuration.RedisConfig;
import dev.feliperos.configuration.RedisConfiguration;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPool;

/**
 * Classe de Inicialização da Biblioteca.
 *
 * @author Felipe, Felipe Ros. Created on 25/2/2024
 * @since 1.0
 * @version 1.0
 */
public class DataFlow {
    @Getter
    private static JedisPool jedis;
    @Getter
    private static boolean debug;
    @Getter
    private static Logger logger;

    public static void init(RedisConfig redisConfig) {
        jedis = new RedisConfiguration(redisConfig).getJedis();
        debug = redisConfig.isDebug();
        logger = LoggerFactory.getLogger("| " + redisConfig.getPrefix() + " (Redis)");
        logger.info("DataFlow has initialized!");
    }
}