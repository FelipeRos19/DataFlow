package dev.feliperos.core.builder;

import dev.feliperos.core.base.actions.string.Write;
import dev.feliperos.core.base.structs.Build;
import dev.feliperos.core.base.structs.Execute;

/**
 * Construtor abstrato de um comando de escrita no Redis.
 *
 * @see dev.feliperos.core.base.actions.string.Write
 * @see dev.feliperos.core.base.structs.Execute
 * @see dev.feliperos.core.base.structs.Build
 *
 * @author Felipe, Felipe Ros. Created on 04/03/2024.
 * @since 1.0
 * @version 1.1.0
 *
 * @param <K> classe de comando.
 * @param <T> tipo de retorno da execução.
 */
public abstract class WriteCommandBuilder<K, T> implements Write<K>, Execute<T>, Build<K> {
}
