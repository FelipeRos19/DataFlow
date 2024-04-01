package dev.feliperos.core.base.structs;

/**
 * Representação da Estrutura de definir Chave dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 30/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Key<T> {

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    T setKey(String key);
}
