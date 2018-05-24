package org.cloudfoundry.samples.music;

import java.util.Date;
import java.text.SimpleDateFormat;

import org.cloudfoundry.samples.music.config.SpringApplicationContextInitializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTask
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).
                initializers(new SpringApplicationContextInitializer())
                .application()
                .run(args);
    }

    @Bean
	public CommandLineRunner commandLineRunner() {
		return strings ->
				System.out.println("Executed at :" + 
				      new SimpleDateFormat().format(new Date()));
	}
}