package dev.feliperos.core.base.structs;

import java.util.Optional;

/**
 * Representação da Ação de execução de um comando no Redis.
 *
 * @author Felipe, Felipe Ros. Created on 01/03/2024
 * @since 1.0
 * @version 1.0
 */
public interface Execute<T> {

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional<String>} retorna o resultado do Comando.
     */
    Optional<T> execute();
}
