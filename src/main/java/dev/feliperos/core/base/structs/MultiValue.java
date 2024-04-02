package dev.feliperos.core.base.structs;

/**
 * Representação da Estrutura de definir Múltiplos Valores dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Creadted on 01/04/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiValue<T> {

    /**
     * Utilizando para definir os valores de inserção.
     *
     * @param values valores de inserção.
     * @return T objeto em construção.
     */
    T setValues(String... values);
}
