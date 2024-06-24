package projects.keywordextractor.security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import projects.keywordextractor.security.JWTGenerator;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JWTGeneratorTest {

    @Test
    public void testGenerateToken() {
        // Arrange
        JWTGenerator jwtGenerator = new JWTGenerator();
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", "password");

        // Act
        String token = jwtGenerator.generateToken(authentication);

        // Assert
        assertNotNull(token);
        assertTrue(token.length() > 0); // Tokenin boş olmadığını kontrol ediyoruz
        assertTrue(token.contains(".")); // JWT formatına uygun olup olmadığını kontrol ediyoruz
    }
}
