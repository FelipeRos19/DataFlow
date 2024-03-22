package dev.feliperos.core.commands.string.get;

import dev.feliperos.DataFlow;
import dev.feliperos.core.base.actions.Persistence;
import dev.feliperos.core.base.actions.Time;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidTimeTypeException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.GetExParams;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/getex/">GetEx</a> do Redis.
 *
 * @see dev.feliperos.core.builder.ReadCommandBuilder
 * @see dev.feliperos.core.base.actions.Time
 * @see dev.feliperos.core.base.actions.Persistence
 *
 * @author Felipe, Felipe Ros. Created on 04/03/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class GetEx extends ReadCommandBuilder<GetEx, String> implements Time<GetEx>, Persistence<GetEx> {
    private String key;
    private GetExParams params;

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public GetEx setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * Utilizado para definir o tempo de expiração específico em segundos.
     *
     * @param seconds tempo de expiração.
     * @return T objeto em construção.
     */
    @Override
    public GetEx setSeconds(long seconds) {
        this.params.ex(seconds);
        return this;
    }

    /**
     * Utilizado para definir o tempo de expiração específico em milissegundos.
     *
     * @param milliseconds tempo de expiração.
     * @return T objeto em construção.
     */
    @Override
    public GetEx setMilliseconds(long milliseconds) {
        this.params.px(milliseconds);
        return this;
    }

    /**
     * Utilizado para definir o tempo de expiração específico em unix com segundos.
     *
     * @param unixSeconds tempo de expiração.
     * @return T objeto em construção.
     */
    @Override
    public GetEx setUnixSeconds(long unixSeconds) {
        this.params.exAt(unixSeconds);
        return this;
    }

    /**
     * Utilizado para definir o tempo de expiração específico em unix com milissegundos.
     *
     * @param unixMilliseconds tempo de expiração.
     * @return T objeto em construção.
     */
    @Override
    public GetEx setUnixMilliseconds(long unixMilliseconds) {
        this.params.pxAt(unixMilliseconds);
        return this;
    }

    /**
     * Utilizado para remover o tempo de expiração de uma chave.
     *
     * @return T objeto em construção.
     */
    @Override
    public GetEx setPersistence() {
        this.params.persist();
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional<String>} retorna o resultado do Comando.
     */
    @Override
    public Optional<String> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            if (this.params == null)
                throw new InvalidTimeTypeException();

            String result = jedis.getEx(this.key, this.params);
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
    public GetEx build() {
        return new GetEx(this.key, this.params);
    }
}
