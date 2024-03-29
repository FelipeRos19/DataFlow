package dev.feliperos.core.commands.string.set;

import dev.feliperos.DataFlow;
import dev.feliperos.core.base.arguments.Offset;
import dev.feliperos.core.builder.WriteCommandBuilder;
import dev.feliperos.core.exceptions.InvalidArgumentException;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidValueException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/setrange/">SetRange</a> do Redis.
 *
 * @see dev.feliperos.core.builder.WriteCommandBuilder
 * @see dev.feliperos.core.base.arguments.Offset
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024.
 * @since 1.0
 * @version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
public class SetRange extends WriteCommandBuilder<SetRange, Long> implements Offset<SetRange> {
    private String key;
    private String value;
    private int offset = -1;

    /**
     * Utilizado para definir o valor da Chave de inserção.
     *
     * @param key chave de inserção.
     * @return T objeto em construção.
     */
    @Override
    public SetRange setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Utilizado para definir o Valor de inserção.
     *
     * @param value valor de inserção.
     * @return T objeto em construção.
     */
    @Override
    public SetRange setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Utilizado para definir o Offset de uma Chave.
     *
     * @return T objeto em construção.
     */
    @Override
    public SetRange setOffset(int value) {
        this.offset = value;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional <Long>} retorna o resultado do Comando.
     */
    @Override
    public Optional<Long> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            if (this.value == null || this.value.isEmpty())
                throw new InvalidValueException();

            if (this.offset == -1)
                throw new InvalidArgumentException();

            if (DataFlow.isDebug())
                DataFlow.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return Optional.of(jedis.setrange(this.key, this.offset, this.value));
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
    public SetRange build() {
        return new SetRange(this.key, this.value, this.offset);
    }
}
