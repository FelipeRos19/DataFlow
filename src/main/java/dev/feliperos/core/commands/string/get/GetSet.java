package dev.feliperos.core.commands.string.get;

import dev.feliperos.DataFlow;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.builder.WriteCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidValueException;
import dev.feliperos.utils.Messages;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/getset/">GetSet</a> do Redis.
 *
 * @see ReadCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 04/03/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
public class GetSet extends WriteCommandBuilder<GetSet, String> {
    private String key;
    private String value;

    private GetSet(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * Utilizado para definir o valor da Chave de inserção.
     *
     * @param key chave de inserção.
     * @return T objeto em construção.
     */
    @Override
    public GetSet setKey(String key) {
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
    public GetSet setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional <String>} retorna o resultado do Comando.
     */
    @Override
    public Optional<String> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            if (this.value == null || this.value.isEmpty())
                throw new InvalidValueException();

            String result = jedis.getSet(this.key, this.value);
            if (DataFlow.isDebug())
                DataFlow.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return (result != null) ? Optional.of(result) : Optional.empty();
        } catch (Exception exception) {
            DataFlow.getLogger().error(Messages.getExecutedMessage(this.getClass()), exception);
            return Optional.empty();
        }
    }

    /**
     * Constrói o Comando e retorna configurado.
     *
     * @return comando construído.
     */
    @Override
    public GetSet build() {
        return new GetSet(this.key, this.value);
    }
}
