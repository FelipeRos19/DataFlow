package dev.feliperos.core.base.actions;

/**
 * Representação a Ação de Persistir uma Chave dentro do Redis.
 *
 * @author Felipe, Felipe Ros. Creadted on 28/03/2024
 * @since 1.0
 * @version 1.0
 *
 * @param <T> Classe que implementa.
 */
public interface Persistence<T> {

    /**
     * Utilizado para remover o tempo de expiração de uma chave.
     *
     * @return T objeto em construção.
     */
    T setPersistence();
}
