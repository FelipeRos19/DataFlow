package dev.feliperos.core.base.actions.string;

import dev.feliperos.core.base.structs.Key;
import dev.feliperos.core.base.structs.Value;

/**
 * Representação da Ação de Escrita dentro do Redis.
 *
 * @see dev.feliperos.core.base.structs.Key
 * @see dev.feliperos.core.base.structs.Value
 *
 * @author Felipe, Felipe Ros. Created on 25/2/2024
 * @since 1.0
 * @version 1.1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Write<T> extends Key<T>, Value<T> {
}
