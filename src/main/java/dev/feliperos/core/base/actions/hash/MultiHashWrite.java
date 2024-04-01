package dev.feliperos.core.base.actions.hash;

import dev.feliperos.core.base.structs.MultiField;
import dev.feliperos.core.base.structs.MultiKey;
import dev.feliperos.core.base.structs.MultiValue;

/**
 * Representação da Ação de Escrita Múltipla dentro do Redis.
 *
 * @see dev.feliperos.core.base.actions.hash.HashWrite
 * @see dev.feliperos.core.base.structs.MultiKey
 * @see dev.feliperos.core.base.structs.MultiField
 * @see dev.feliperos.core.base.structs.MultiValue
 *
 * @author Felipe, Felipe Ros. Created on 01/04/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa
 */
public interface MultiHashWrite<T> extends HashWrite<T>, MultiKey<T>, MultiField<T>, MultiValue<T> {
}
