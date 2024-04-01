package dev.feliperos.core.base.actions.hash;

import dev.feliperos.core.base.structs.Field;
import dev.feliperos.core.base.structs.Key;

/**
 * Representação de uma Ação de Leitura em uma Hash dentro do Redis.
 *
 * @see dev.feliperos.core.base.structs.Key
 * @see dev.feliperos.core.base.structs.Field
 *
 * @author Felipe, Felipe Ros. Created on 03/03/2024
 * @since 1.0
 * @version 1.1.0
 *
 * @param <T> Classe que implementa.
 */
public interface HashRead<T> extends Key<T>, Field<T> {
}
