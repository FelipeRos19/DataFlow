package dev.feliperos.core.commands.string.get;

import dev.feliperos.RedisPulse;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.utils.Messages;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/getrange/">GetRange</a> do Redis.
 *
 * @see ReadCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 03/03/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
public class GetRange extends ReadCommandBuilder<GetRange, String> {
    private String key;
    private long start;
    private long end;

    private GetRange(String key, long start, long end) {
        this.key = key;
        this.start = start;
        this.end = end;
    }

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public GetRange setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Utilizado para definir a distância do retorno da pesquisa.
     *
     * @param start valor inicial
     * @param end valor final
     * @return T objeto em construção.
     */
    public GetRange setRange(long start, long end) {
        this.start = start;
        this.end = end;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional <String>} retorna o resultado do Comando.
     */
    @Override
    public Optional<String> execute() {
        try (Jedis jedis = RedisPulse.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            String result = jedis.getrange(this.key, this.start, this.end);
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
    public GetRange build() {
        return new GetRange(this.key, this.start, this.end);
    }
}
