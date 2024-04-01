package dev.feliperos.core.base.actions.string;

import dev.feliperos.core.base.structs.MultiKey;
import dev.feliperos.core.base.structs.MultiValue;

/**
 * Representação da Ação de Escrita Múltipla dentro do Redis.
 *
 * @see dev.feliperos.core.base.actions.string.Write
 * @see dev.feliperos.core.base.structs.MultiKey
 * @see dev.feliperos.core.base.structs.MultiValue
 *
 * @author Felipe, Felipe Ros. Creadted on 29/03/2024
 * @since 1.0
 * @version 1.1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiWrite<T> extends Write<T>, MultiKey<T>, MultiValue<T> {
}
