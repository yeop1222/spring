package spring.ch03.di_sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import spring.ch03.di_sample.used.Greet;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args)
				.getBean(Application.class).execute();
	}

	@Autowired
	@Qualifier("Morning")
	Greet greet;
	
	private void execute() {
		greet.greeting();
	}
}
