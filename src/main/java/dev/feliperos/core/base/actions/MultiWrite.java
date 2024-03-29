package dev.feliperos.core.base.actions;

/**
 * Representação da Ação de Escrita Múltipla dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Creadted on 29/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiWrite<T> {

    /**
     * Utilizado para definir os valores das Chaves de inserção.
     *
     * @param keys chaves de pesquisa.
     * @return T objeto em construção.
     */
    T setKeys(String... keys);

    /**
     * Utilizado para definir os valores de inserção.
     *
     * @param values valores de inserção.
     * @return T objeto em construção.
     */
    T setValues(String... values);
}
