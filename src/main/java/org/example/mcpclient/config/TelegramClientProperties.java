package org.example.mcpclient.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app")
public class TelegramClientProperties {

  @NotNull private Telegram telegram = new Telegram();
  @NotNull private McpServer mcpServer = new McpServer();

  public Telegram getTelegram() { return telegram; }
  public void setTelegram(Telegram telegram) { this.telegram = telegram; }
  public McpServer getMcpServer() { return mcpServer; }
  public void setMcpServer(McpServer mcpServer) { this.mcpServer = mcpServer; }

  public static class Telegram {
    @NotBlank private String botToken;
    @NotBlank private String botUsername;
    public String getBotToken() { return botToken; }
    public void setBotToken(String botToken) { this.botToken = botToken; }
    public String getBotUsername() { return botUsername; }
    public void setBotUsername(String botUsername) { this.botUsername = botUsername; }
  }

  public static class McpServer {
    @NotBlank private String bearerToken;
    public String getBearerToken() { return bearerToken; }
    public void setBearerToken(String bearerToken) { this.bearerToken = bearerToken; }
  }
}