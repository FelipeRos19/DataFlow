package dev.feliperos.core.base.arguments;

/**
 * Representação da Argumento de Offset em um Comando do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Offset<T> {

    /**
     * Utilizado para definir o Offset de uma Chave.
     *
     * @return T objeto em construção.
     */
    T setOffset(int value);
}
