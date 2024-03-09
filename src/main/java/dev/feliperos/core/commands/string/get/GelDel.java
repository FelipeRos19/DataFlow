package dev.feliperos.core.commands.string.get;

import dev.feliperos.DataFlow;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.utils.Messages;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/getdel/">GetDel</a> do Redis.
 *
 * @see ReadCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 03/03/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
public class GelDel extends ReadCommandBuilder<GelDel, String> {
    private String key;

    private GelDel(String key) {
        this.key = key;
    }

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public GelDel setKey(String key) {
        this.key = key;
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

            String result = jedis.getDel(this.key);
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
    public GelDel build() {
        return new GelDel(this.key);
    }
}
