package dev.feliperos.core.builder;

import dev.feliperos.core.base.actions.string.Write;
import dev.feliperos.core.base.structs.Execute;

/**
 * Construtor abstrato de um comando de escrita no Redis.
 *
 * @see Write
 * @see Execute
 *
 * @author Felipe, Felipe Ros. Created on 04/03/2024.
 * @since 1.0
 * @version 1.0
 *
 * @param <K> classe de comando.
 * @param <T> tipo de retorno da execução.
 */
public abstract class WriteCommandBuilder<K, T> implements Write<K>, Execute<T> {

    /**
     * Constrói o Comando e retorna configurado.
     *
     * @return comando construído.
     */
    public abstract K build();
}
