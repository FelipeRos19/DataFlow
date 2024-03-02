package dev.feliperos.core.base.actions;

/**
 * Representação da Ação de Escrita dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 25/2/2024
 * @since 1.0
 * @version 1.0
 */
public interface Write<T> {

    /**
     * Utilizado para definir o valor da Chave de inserção.
     *
     * @param key chave de inserção.
     * @return T objeto em construção.
     */
    T setKey(String key);

    /**
     * Utilizado para definir o Valor de inserção.
     *
     * @param value valor de inserção.
     * @return T objeto em construção.
     */
    T setValue(String value);
}
