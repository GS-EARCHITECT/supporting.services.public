package main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages ={"type_mgmt","category_mgmt","resource_mgmt"})
@EnableJpaRepositories(basePackages = {"type_mgmt","category_mgmt","resource_mgmt"})
@ComponentScan({"type_mgmt","category_mgmt","resource_mgmt"})
public class Resource_Mgmt_Main extends SpringBootServletInitializer  
{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(Resource_Mgmt_Main.class);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(Resource_Mgmt_Main.class, args);
	}
}