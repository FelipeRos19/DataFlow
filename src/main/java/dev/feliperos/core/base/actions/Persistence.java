package dev.feliperos.core.base.actions;

public interface Persistence<T> {

    /**
     * Utilizado para remover o tempo de expiração de uma chave.
     *
     * @return T objeto em construção.
     */
    T setPersistence();
}
