package dev.feliperos.core.base.actions.hash;

import dev.feliperos.core.base.structs.MultiField;
import dev.feliperos.core.base.structs.MultiKey;

/**
 * Representação da Ação de Leitura Múltipla dentro do Redis.
 *
 * @see dev.feliperos.core.base.actions.hash.HashRead
 * @see dev.feliperos.core.base.structs.MultiKey
 * @see dev.feliperos.core.base.structs.MultiField
 *
 * @author Felipe, Felipe Ros. Created on 01/04/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiHashRead<T> extends HashRead<T>, MultiKey<T>, MultiField<T> {
}
