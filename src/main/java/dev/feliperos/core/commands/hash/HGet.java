package dev.feliperos.core.commands.hash;

import dev.feliperos.DataFlow;
import dev.feliperos.core.builder.HashReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidFieldException;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/docs/latest/commands/hget/">HGet</a>.
 *
 * @author Felipe, Felipe Ros. Created on 21/04/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class HGet extends HashReadCommandBuilder<HGet, String> {
    private String key;
    private String field;

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public HGet setKey(String key) {
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
    public HGet setField(String field) {
        this.field = field;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional} retorna o resultado do Comando.
     */
    @Override
    public Optional<String> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            if (this.field == null || this.field.isEmpty())
                throw new InvalidFieldException();

            String result = jedis.hget(this.key, this.field);
            if (DataFlow.isDebug())
                DataFlow.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return (result != null) ? Optional.of(result) : Optional.empty();
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
    public HGet build() {
        return new HGet(this.key, this.field);
    }
}


