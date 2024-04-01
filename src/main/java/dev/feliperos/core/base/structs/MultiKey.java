package dev.feliperos.core.base.structs;

/**
 * Representação da Estrutura de definir Múltiplas Chaves dentro do Redis.
 *
 * @author Felipe, Felipe ROS. Created on 30/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface MultiKey<T> {

    /**
     * Utilizado para definir o valor das Chaves de Pesquisa.
     *
     * @param keys chaves de pesquisa.
     * @return T objeto em construção.
     */
    T setKeys(String... keys);
}
