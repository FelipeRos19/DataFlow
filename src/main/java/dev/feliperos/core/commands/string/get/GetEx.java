package dev.feliperos.core.commands.string.get;

import dev.feliperos.RedisPulse;
import dev.feliperos.core.base.actions.Time;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidTimeException;
import dev.feliperos.core.exceptions.InvalidTimeTypeException;
import dev.feliperos.utils.Messages;
import dev.feliperos.utils.TimeProcessor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Protocol;

import java.util.Optional;

public class GetEx extends ReadCommandBuilder<GetEx, String> implements Time<GetEx> {
    private String key;
    private long time;
    private Protocol.Keyword type;

    private GetEx(String key, long time, Protocol.Keyword timeParam) {
        this.key = key;
        this.time = time;
        this.type = timeParam;
    }

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
        this.time = seconds;
        this.type = Protocol.Keyword.EX;
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
        this.time = milliseconds;
        this.type = Protocol.Keyword.PX;
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
        this.time = unixSeconds;
        this.type = Protocol.Keyword.EXAT;
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
        this.time = unixMilliseconds;
        this.type = Protocol.Keyword.PXAT;
        return this;
    }

    /**
     * Utilizado para remover o tempo de expiração de uma chave.
     *
     * @return T objeto em construção.
     */
    @Override
    public GetEx setPersistence() {
        this.time = 0;
        this.type = Protocol.Keyword.PERSIST;
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional<String>} retorna o resultado do Comando.
     */
    @Override
    public Optional<String> execute() {
        try (Jedis jedis = RedisPulse.getJedis().getResource()) {
            if (this.key == null || this.key.isEmpty())
                throw new InvalidKeyException();

            if (this.type == null)
                throw new InvalidTimeTypeException();

            if (this.time == 0 && !this.type.equals(Protocol.Keyword.PERSIST))
                throw new InvalidTimeException();

            String result = jedis.getEx(this.key, TimeProcessor.processGetExTimeUnit(this.type, this.time));
            if (RedisPulse.isDebug())
                RedisPulse.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return (result != null) ? Optional.of(result) : Optional.empty();
        } catch (Exception exception) {
            RedisPulse.getLogger().error(Messages.getExecutedMessage(this.getClass()), exception);
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
        return new GetEx(this.key, this.time, this.type);
    }
}
