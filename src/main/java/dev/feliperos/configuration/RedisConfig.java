package dev.feliperos.configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * Objeto de Configuração do Redis.
 *
 * @author Felipe, Felipe Ros. Created on 25/2/2024
 * @since 1.0
 * @version 1.0
 */
@Getter
@Setter
public class RedisConfig {
    private String prefix;
    private boolean debug;
    private String host;
    private int port;
    private String user;
    private String password;
    private int connections;

    /**
     * Construtor da Configuração do Redis.
     *
     * @param prefix prefixo de log.
     * @param host endereço de conexão.
     * @param port porta da conexão.
     * @param user usuário da conexão.
     * @param password senha da conexão.
     * @param connections número de conexões.
     */
    public RedisConfig(String prefix, String host, int port, String user, String password, int connections) {
        this.prefix = prefix;
        this.debug = false;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.connections = connections;
    }

    /**
     * Construtor da Configuração do Redis.
     *
     * @param prefix prefixo de log.
     * @param debug estado de depuração.
     * @param host endereço de conexão.
     * @param port porta da conexão.
     * @param user usuário da conexão.
     * @param password senha da conexão.
     * @param connections número de conexões.
     */
    public RedisConfig(String prefix, boolean debug, String host, int port, String user, String password, int connections) {
        this.prefix = prefix;
        this.debug = debug;
        this.host = host;
        this.port = port;
        this.user = user;
        this.password = password;
        this.connections = connections;
    }
}
