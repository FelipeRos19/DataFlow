package dev.feliperos.core.commands.hash;

import dev.feliperos.DataFlow;
import dev.feliperos.core.builder.HashWriteCommandBuilder;
import dev.feliperos.core.exceptions.InvalidFieldException;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidValueException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/hset/">HSet</a> do Redis.
 *
 * @see dev.feliperos.core.builder.HashWriteCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 03/04/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class HSet extends HashWriteCommandBuilder<HSet, Boolean> {
    private String key;
    private String field;
    private String value;

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public HSet setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Utilizado para definir o campo de inserção.
     *
     * @param field campo de inserção.
     * @return T objeto em construção.
     */
    @Override
    public HSet setField(String field) {
        this.field = field;
        return this;
    }

    /**
     * Utilizado para definir o Valor de inserção.
     *
     * @param value valor de inserção.
     * @return T objeto em construção.
     */
    @Override
    public HSet setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional} retorna o resultado do Comando.
     */
    @Override
    public Optional<Boolean> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            if (this.field == null || this.field.isEmpty())
                throw new InvalidFieldException();

            if (this.value == null || this.value.isEmpty())
                throw new InvalidValueException();

            boolean result = jedis.hset(this.key, this.field, this.value) > 0;
            if (DataFlow.isDebug())
                DataFlow.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return Optional.of(result);
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
    public HSet build() {
        return new HSet(this.key, this.field, this.value);
    }
}
