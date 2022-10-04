package spring.ch03.di_sample.used;

import org.springframework.stereotype.Component;

@Component("Evening")
public class EveningGreet implements Greet{

	@Override
	public void greeting() {
		System.out.println("Good evening.");
	}
}
