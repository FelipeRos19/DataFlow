package dev.feliperos.core.base.actions.string;

/**
 * Representação da Ação de Leitura dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 29/02/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Read<T> {

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    T setKey(String key);
}
