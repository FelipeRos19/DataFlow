package dev.feliperos.core.base.actions;

/**
 * Representação da Ação de Tempo em uma Escrita dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 03/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Time<T> {

    /**
     * Utilizado para definir o tempo de expiração específico em segundos.
     *
     * @param seconds tempo de expiração.
     * @return T objeto em construção.
     */
    T setSeconds(long seconds);

    /**
     * Utilizado para definir o tempo de expiração específico em milissegundos.
     *
     * @param milliseconds tempo de expiração.
     * @return T objeto em construção.
     */
    T setMilliseconds(long milliseconds);

    /**
     * Utilizado para definir o tempo de expiração específico em unix com segundos.
     *
     * @param unixSeconds tempo de expiração.
     * @return T objeto em construção.
     */
    T setUnixSeconds(long unixSeconds);

    /**
     * Utilizado para definir o tempo de expiração específico em unix com milissegundos.
     *
     * @param unixMilliseconds tempo de expiração.
     * @return T objeto em construção.
     */
    T setUnixMilliseconds(long unixMilliseconds);
}
