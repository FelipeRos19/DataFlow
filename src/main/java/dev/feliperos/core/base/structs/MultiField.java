package dev.feliperos.core.base.structs;

/**
 * Representação da Estrutura de definir Campo dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Creadted on 30/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiField<T> {

    /**
     * Utilizado para definir o valor dos Campos.
     *
     * @param fields
     * @return
     */
    T setFields(String... fields);
}
