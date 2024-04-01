package dev.feliperos.core.base.structs;

/**
 * Representação da Estrutura de definir Valor dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 30/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Value<T> {

    /**
     * Utilizado para definir o Valor de inserção.
     *
     * @param value valor de inserção.
     * @return T objeto em construção.
     */
    T setValue(String value);
}