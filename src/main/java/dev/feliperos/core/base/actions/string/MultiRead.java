package dev.feliperos.core.base.actions.string;

import dev.feliperos.core.base.structs.MultiKey;

/**
 * Representação da Ação de Leitura Múltipla dentro do Redis.
 *
 * @see dev.feliperos.core.base.actions.string.Read
 * @see dev.feliperos.core.base.structs.MultiKey
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024
 * @since 1.0
 * @version 1.1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiRead<T> extends Read<T>, MultiKey<T> {
}
