package dev.feliperos.core.commands.string;

import dev.feliperos.DataFlow;
import dev.feliperos.core.builder.WriteCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidValueException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/append/">Append</a> do Redis.
 *
 * @see dev.feliperos.core.builder.WriteCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 23/03/2024
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class Append extends WriteCommandBuilder<Append, Long> {
    private String key;
    private String value;

    /**
     * Utilizado para definir o valor da Chave de inserção.
     *
     * @param key chave de inserção.
     * @return T objeto em construção.
     */
    @Override
    public Append setKey(String key) {
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
    public Append setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional} retorna o resultado do Comando.
     */
    @Override
    public Optional<Long> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            if (this.value == null || this.value.isEmpty())
                throw new InvalidValueException();

            long result = jedis.append(this.key, this.value);
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
    public Append build() {
        return null;
    }
}
