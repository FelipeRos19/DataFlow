package dev.feliperos.core.base.actions;

/**
 * Representação da Ação de Leitura em uma Hash dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 01/03/2024
 * @since 1.0
 * @version 1.0
 */
public interface HashRead {

    /**
     * Utilizado para pegar a Chave de pesquisa do Hash.
     *
     * @return {@link String} retorna a Chave de pesquisa do Hash.
     */
    String getKey();

    /**
     * Utilizado para pegar o Campo de pesquisa no Hash.
     *
     * @return {@link String} retorna o Campo de pesquisa do Hash.
     */
    String getField();
}
