package dev.feliperos.core.base.actions;

/**
 * Representação de uma Ação de Escrita em uma Hash dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 03/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface HashRead<T> {

    /**
     * Utilizado para definir o valor da Chave de inserção.
     *
     * @param key chave de inserção.
     * @return T objeto em construção.
     */
    T setKey(String key);

    /**
     * Utilizado para definir o campo de inserção.
     *
     * @param field campo de inserção.
     * @return T objeto em construção.
     */
    T setField(String field);
}
