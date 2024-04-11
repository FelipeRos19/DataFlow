package dev.feliperos.core.commands.hash;


import dev.feliperos.DataFlow;
import dev.feliperos.core.base.structs.MultiField;
import dev.feliperos.core.builder.HashReadCommandBuilder;
import dev.feliperos.core.exceptions.InvalidFieldException;
import dev.feliperos.core.exceptions.InvalidKeyException;
import dev.feliperos.utils.Messages;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Implementação do Comando <a href="https://redis.io/docs/latest/commands/hdel/">HDel</a> do Redis.
 *
 * @see dev.feliperos.core.builder.HashReadCommandBuilder
 *
 * @author Felipe, Felipe Ros. Created on 11/04/2024
 * @since 1.0
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class HDel extends HashReadCommandBuilder<HDel, Long> implements MultiField<HDel> {
    private String key;
    private List<String> fields;

    /**
     * Utilizado para definir o valor da Chave de pesquisa.
     *
     * @param key chave de pesquisa.
     * @return T objeto em construção.
     */
    @Override
    public HDel setKey(String key) {
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
    public HDel setField(String field) {
        this.fields.add(field);
        return this;
    }

    /**
     * Utilizado para definir o valor dos Campos.
     *
     * @param fields campos de inserção
     * @return T objeto em construção.
     */
    @Override
    public HDel setFields(String... fields) {
        this.fields.addAll(Arrays.stream(fields).toList());
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

            if (this.fields == null || this.fields.isEmpty())
                throw new InvalidFieldException();

            long result = jedis.hdel(this.key, this.fields.toArray(new String[this.fields.size()]));
            if (DataFlow.isDebug())
                DataFlow.getLogger().info(Messages.getExecutedMessage(this.getClass()));

            return Optional.of(result);
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
    public HDel build() {
        return new HDel(this.key, this.fields);
    }
}
