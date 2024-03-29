package dev.feliperos.core.base.arguments;

/**
 * Representação da Ação do Estado de Existir da Chave dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024.
 * @since 1.0
 * @version 1.1.0
 *
 * @param <T> Classe que implementa.
 */
public interface KeyState<T> {

    /**
     * Utilizado para definir o estado de inserção apenas para quando a Chave não existir.
     *
     * @return T objeto em construção.
     */
    T setNX();

    /**
     * Utilizado para definir o estado de inserção apenas para quando a Chave existir.
     *
     * @return T objeto em construção.
     */
    T setXX();
}
