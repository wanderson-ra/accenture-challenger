package br.com.accenture.challenger;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class ChallengeApplication extends SpringBootServletInitializer {

	private static Class<ChallengeApplication> applicationClass = ChallengeApplication.class;

	public static void main(String[] args) {
		Locale.setDefault(new Locale( "pt", "BR" ));
		SpringApplication.run(ChallengeApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}

}
