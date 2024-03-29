package dev.feliperos.core.base.actions;

/**
 * Representação da Ação de Leitura Múltipla dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiRead<T> {

    /**
     * Utilizado para definir o valor das Chaves de Pesquisa.
     *
     * @param keys chaves de pesquisa.
     * @return T objeto em construção.
     */
    T setKeys(String... keys);
}
