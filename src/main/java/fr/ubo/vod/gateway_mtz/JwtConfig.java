package fr.ubo.vod.gateway_mtz;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
public class JwtConfig {

  @Value("${jwt.secret}")
  private String secret;

  @Bean
  public ReactiveJwtDecoder jwtDecoder() {
    byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
    SecretKeySpec key = new SecretKeySpec(keyBytes, "HMACSHA256");
    return NimbusReactiveJwtDecoder
            .withSecretKey(key)
            .macAlgorithm(MacAlgorithm.HS256)  // ← force explicitement HS256
            .build();
  }
}