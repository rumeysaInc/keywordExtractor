package projects.keywordextractor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import projects.keywordextractor.configuration.WebMvcConfig;

@SpringBootApplication
@Import(WebMvcConfig.class)
public class KeywordextractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeywordextractorApplication.class, args);
	}

}
