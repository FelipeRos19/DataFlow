package dev.feliperos.core.base.structs;

/**
 * Representação da Estrutura de definir Campo dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 01/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Field<T> {

    /**
     * Utilizado para definir o campo de inserção.
     *
     * @param field campo de inserção.
     * @return T objeto em construção.
     */
    T setField(String field);
}
