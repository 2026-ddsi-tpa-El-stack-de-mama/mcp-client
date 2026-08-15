package org.example.mcpclient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * La property "headers" bajo spring.ai.mcp.client.streamable-http.connections.*
 * NO existe en Spring AI 2.0.0 (ver McpStreamableHttpClientProperties / documentacion oficial:
 * solo soporta "url" y "endpoint"). Por eso el Authorization que poniamos en application.yml
 * se ignoraba en silencio y el mcp-server rechazaba la conexion con 403.
 *
 * La forma soportada de inyectar headers por default es exponer un bean WebClient.Builder:
 * la auto-configuracion del cliente MCP webflux lo usa como "template" (lo clona por cada
 * conexion configurada), asi que cualquier defaultHeader que le pongamos aca viaja en todas
 * las requests hacia el mcp-server.
 */
@Configuration
public class McpWebClientConfig {

  @Bean
  WebClient.Builder webClientBuilder(@Value("${MCP_SERVER_BEARER_TOKEN}") String bearerToken) {
    return WebClient.builder()
        .defaultHeader("Authorization", "Bearer " + bearerToken);
  }
}