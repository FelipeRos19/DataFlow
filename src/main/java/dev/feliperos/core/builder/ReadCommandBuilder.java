package dev.feliperos.core.builder;

import dev.feliperos.core.base.structs.Execute;
import dev.feliperos.core.base.actions.string.Read;

/**
 * Construtor abstrato de um comando de leitura no Redis.
 *
 * @see Read
 * @see Execute
 *
 * @author Felipe, Felipe Ros. Created on 01/03/2024.
 * @since 1.0
 * @version 1.0
 *
 * @param <K> classe do comando.
 * @param <T> tipo de retorno da execução.
 */
public abstract class ReadCommandBuilder<K, T> implements Read<K>, Execute<T> {

    /**
     * Constrói o Comando e retorna configurado.
     *
     * @return comando construído.
     */
    public abstract K build();
}
