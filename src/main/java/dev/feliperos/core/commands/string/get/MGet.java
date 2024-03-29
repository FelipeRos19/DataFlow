package dev.feliperos.core.commands.string.get;

import dev.feliperos.DataFlow;
import dev.feliperos.core.base.actions.MultiRead;
import dev.feliperos.core.builder.ReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/commands/mget/">MGet</a> do Redis.
 *
 * @see dev.feliperos.core.builder.ReadCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 28/03/2024.
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class MGet extends ReadCommandBuilder<MGet, List<String>> implements MultiRead<MGet> {
    @Getter
    private List<String> keys = new ArrayList<>();

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public MGet setKey(String key) {
        this.keys.add(key);
        return this;
    }

    /**
     * Utilizado para definir o valor das Chaves de Pesquisa.
     *
     * @param keys chaves de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public MGet setKeys(String... keys) {
        this.keys.addAll(List.of(keys));
        return this;
    }

    /**
     * Utilizado para executar os Comandos no Redis.
     *
     * @return {@link Optional <String>} retorna o resultado do Comando.
     */
    @Override
    public Optional<List<String>> execute() {
        try (Jedis jedis = DataFlow.getJedis().getResource()) {
            if (this.keys == null || this.keys.isEmpty())
                throw new InvalidKeyException();

            List<String> result = jedis.mget(this.keys.toArray(new String[this.keys.size()]));
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
    public MGet build() {
        return new MGet(this.keys);
    }
}
