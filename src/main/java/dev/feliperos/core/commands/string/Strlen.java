package dev.feliperos.core.commands.string;

import dev.feliperos.DataFlow;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/strlen/">Strlen</a> do Redis.
 *
 * @see dev.feliperos.core.builder.ReadCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class Strlen extends ReadCommandBuilder<Strlen, Long> {
    private String key;

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public Strlen setKey(String key) {
        this.key = key;
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

            if (DataFlow.isDebug())
                DataFlow.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return Optional.of(jedis.strlen(this.key));
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
    public Strlen build() {
        return new Strlen(this.key);
    }
}
