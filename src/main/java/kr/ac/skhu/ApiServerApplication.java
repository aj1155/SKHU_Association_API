package kr.ac.skhu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
//@PropertySource({ "classpath:DB.yml" })
public class ApiServerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		/*
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(ApiServerApplication.class);
		SpringApplication springApplication = springApplicationBuilder.build();
		springApplication.run(args);
		*/
		SpringApplication.run(ApiServerApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiServerApplication.class);
	}
}
