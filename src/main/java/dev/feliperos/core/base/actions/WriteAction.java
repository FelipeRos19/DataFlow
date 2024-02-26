package dev.feliperos.core.base.actions;

/*
 * @author Felipe, Felipe Ros. Created on 25/2/2024
 */
public interface WriteAction<V> {
    String getKey();

    V getValue();
}
