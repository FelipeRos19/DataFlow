package dev.feliperos.core.base.actions.hash;

import dev.feliperos.core.base.structs.Field;
import dev.feliperos.core.base.structs.Key;
import dev.feliperos.core.base.structs.Value;

/**
 * Representação da Ação de Escrita em uma Hash dentro do Redis.
 *
 * @see dev.feliperos.core.base.structs.Key
 * @see dev.feliperos.core.base.structs.Field
 * @see dev.feliperos.core.base.structs.Value
 *
 * @author Felipe, Felipe Ros. Created on 01/03/2024
 * @since 1.0
 * @version 1.1.0
 *
 * @param <T> Classe que implementa.
 */
public interface HashWrite<T> extends Key<T>, Field<T>, Value<T> {
}
