package dev.feliperos.core.base.structs;

/**
 * Representação da Estrutura de Construção do Comando.
 *
 * @param <T> classe que implementa
 */
public interface Build<T> {

    /**
     * Constrói o Comando e retorna configurado.
     *
     * @return comando construído.
     */
    T build();
}
