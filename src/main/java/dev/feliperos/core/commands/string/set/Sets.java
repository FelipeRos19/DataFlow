package dev.feliperos.core.commands.string.set;

import dev.feliperos.DataFlow;
import dev.feliperos.core.base.arguments.KeyState;
import dev.feliperos.core.base.arguments.Time;
import dev.feliperos.core.builder.WriteCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.core.exceptions.InvalidValueException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/set/">Set</a> do Redis.
 *
 * @see dev.feliperos.core.builder.WriteCommandBuilder
 * @see Time
 *
 * @author Felipe, Felipe Ros. Created on 22/03/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class Sets extends WriteCommandBuilder<Sets, String> implements Time<Sets>, KeyState<Sets> {
    private String key;
    private String value;
    private SetParams params = new SetParams();
    private boolean isGet;

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public Sets setKey(String key) {
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
    public Sets setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * Utilizado para definir o tempo de expiração específico em segundos.
     *
     * @param seconds tempo de expiração.
     * @return T objeto em construção.
     */
    @Override
    public Sets setSeconds(long seconds) {
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
    public Sets setMilliseconds(long milliseconds) {
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
    public Sets setUnixSeconds(long unixSeconds) {
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
    public Sets setUnixMilliseconds(long unixMilliseconds) {
        this.params.pxAt(unixMilliseconds);
        return this;
    }

    /**
     * Utilizado para manter o tempo de expiração da Chave.
     *
     * @return T objeto em construção.
     */
    public Sets keepTTL() {
        this.params.keepTtl();
        return this;
    }

    /**
     * Utilizado para definir o estado de inserção apenas para quando a Chave não existir.
     *
     * @return T objeto em construção.
     */
    @Override
    public Sets setNX() {
        this.params.nx();
        return this;
    }

    /**
     * Utilizado para definir o estado de inserção apenas para quando a Chave existir.
     *
     * @return T objeto em construção.
     */
    @Override
    public Sets setXX() {
        this.params.xx();
        return this;
    }

    /**
     * Utilizado retornar o Valor antigo da Chave.
     *
     * @return T objeto em construção.
     */
    public Sets get() {
        this.isGet = true;
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

            if (this.value == null || this.value.isEmpty())
                throw new InvalidValueException();

            String result = (this.isGet) ? jedis.setGet(this.key, this.value, this.params) : jedis.set(this.key, this.value, this.params);
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
    public Sets build() {
        return new Sets(this.key, this.value, this.params, this.isGet);
    }
}
