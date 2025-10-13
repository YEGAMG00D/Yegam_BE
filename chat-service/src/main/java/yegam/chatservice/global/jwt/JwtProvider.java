package yegam.chatservice.global.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import yegam.chatservice.global.exception.CustomException;
import yegam.chatservice.global.exception.GlobalErrorCode;

import java.security.Key;
import java.util.Base64;

@Slf4j
@Component
public class JwtProvider {

  private final Key key;

  public JwtProvider(@Value("${spring.jwt.secret}") String secretKey) {
    byte[] keyBytes = Base64.getDecoder().decode(secretKey);
    this.key = Keys.hmacShaKeyFor(keyBytes);
  }

  // JWT 유효성 검증
  public boolean validateToken(String token) {
    try {
      Jwts.parserBuilder()
          .setSigningKey(key)
          .build()
          .parseClaimsJws(token);
      return true;
    } catch (ExpiredJwtException e) {
      throw new CustomException(GlobalErrorCode.JWT_EXPIRED);
    } catch (JwtException | IllegalArgumentException e) {
      throw new CustomException(GlobalErrorCode.JWT_INVALID);
    }
  }

  // JWT 에서 사용자 ID 추출
  public String extractUserId(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }

  // JWT 에서 이메일 추출
  public String extractEmail(String token) {
    return Jwts.parserBuilder()
        .setSigningKey(key)
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("email", String.class);
  }
}
