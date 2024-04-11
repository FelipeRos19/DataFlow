package dev.feliperos.core.builder;

import dev.feliperos.core.base.actions.hash.HashRead;
import dev.feliperos.core.base.structs.Build;
import dev.feliperos.core.base.structs.Execute;

/**
 * Construtor abstrato de um comando Hash de Leitura no Redis.
 *
 * @see dev.feliperos.core.base.actions.hash.HashRead
 * @see dev.feliperos.core.base.structs.Execute
 * @see dev.feliperos.core.base.structs.Build
 *
 * @param <K> classe do comando.
 * @param <T> tipo de retorno da execução.
 */
public abstract class HashReadCommandBuilder<K, T> implements HashRead<K>, Execute<T>, Build<K> {
}