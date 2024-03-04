package dev.feliperos.core.commands.string.get;

import dev.feliperos.RedisPulse;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.utils.Messages;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/get/">Get</a> do Redis.
 *
 * @see dev.feliperos.core.builder.ReadCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 1/3/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
public class Get extends ReadCommandBuilder<Get, String> {
    private String key;

    private Get(String key) {
        this.key = key;
    }

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public Get setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional<String>} retorna o resultado do Comando.
     */
    @Override
    public Optional<String> execute(){
        try (Jedis jedis = RedisPulse.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            String result = jedis.get(this.key);
            if (RedisPulse.isDebug())
                RedisPulse.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return (result != null) ? Optional.of(result) : Optional.empty();
        } catch (Exception exception) {
            RedisPulse.getLogger().error(Messages.getErrorMessage(this.getClass()), exception);
            return Optional.empty();
        }
    }

    /**
     * Constrói o Comando e retorna configurado.
     *
     * @return comando construído.
     */
    @Override
    public Get build() {
        return new Get(this.key);
    }
}
