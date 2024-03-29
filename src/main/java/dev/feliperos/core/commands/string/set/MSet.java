package dev.feliperos.core.commands.string.set;

import dev.feliperos.DataFlow;
import dev.feliperos.core.base.actions.MultiWrite;
import dev.feliperos.core.builder.WriteCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidPairArgumentException;
import dev.feliperos.core.exceptions.InvalidResultException;
import dev.feliperos.core.exceptions.InvalidValueException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/mset/">MSet</a> do Redis.
 *
 * @see dev.feliperos.core.builder.WriteCommandBuilder
 * @see dev.feliperos.core.base.actions.MultiWrite
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024;
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class MSet extends WriteCommandBuilder<MSet, Boolean> implements MultiWrite<MSet> {
    private List<String> keys = new ArrayList<>();
    private List<String> values = new ArrayList<>();

    /**
     * Utilizado para definir o valor da Chave de inserção.
     *
     * @param key chave de inserção.
     * @return T objeto em construção.
     */
    @Override
    public MSet setKey(String key) {
        this.keys.add(key);
        return this;
    }

    /**
     * Utilizado para definir o Valor de inserção.
     *
     * @param value valor de inserção.
     * @return T objeto em construção.
     */
    @Override
    public MSet setValue(String value) {
        this.values.add(value);
        return this;
    }

    /**
     * Utilizado para definir os valores das Chaves de inserção.
     *
     * @param keys chaves de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public MSet setKeys(String... keys) {
        this.keys.addAll(List.of(keys));
        return this;
    }

    /**
     * Utilizado para definir os valores de inserção.
     *
     * @param values valores de inserção.
     * @return T objeto em construção.
     */
    @Override
    public MSet setValues(String... values) {
        this.values.addAll(List.of(values));
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional <Boolean>} retorna o resultado do Comando.
     */
    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.keys == null || this.keys.isEmpty())
                throw new InvalidKeyException();

            if (this.values == null || this.values.isEmpty())
                throw new InvalidValueException();

            if (this.keys.size() != this.values.size())
                throw new InvalidPairArgumentException();

            List<String> entries = new ArrayList<>();
            for (int i = 0; i < this.keys.size(); i++) {
                entries.add(this.keys.get(i));
                entries.add(this.values.get(i));
            }

            String result = jedis.mset(entries.toArray(new String[this.keys.size()]));
            if (DataFlow.isDebug())
                DataFlow.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            if (result == null)
                throw new InvalidResultException();

            return (result.equals("ok")) ? Optional.of(true) : Optional.empty();
        } catch (Exception exception) {
            DataFlow.getLogger().error(Messages.getErrorMessage(this.getClass()), exception);
            return Optional.empty();
        }
    }

    /**
     * Constrói o Comando e retorna configurado.
     *
     * @return comando construído.
     */
    @Override
    public MSet build() {
        return new MSet(this.keys, this.values);
    }
}
