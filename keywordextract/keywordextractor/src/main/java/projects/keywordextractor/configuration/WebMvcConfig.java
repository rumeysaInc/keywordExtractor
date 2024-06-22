package projects.keywordextractor.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4200") // Bu kısımda istemcilerin erişim izni alacağı origin'leri belirtirsiniz
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS"); // İzin verilen HTTP metotlarını belirtirsiniz
    }
}
