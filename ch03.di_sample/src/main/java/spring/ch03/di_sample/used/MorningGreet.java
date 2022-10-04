package spring.ch03.di_sample.used;

import org.springframework.stereotype.Component;

@Component("Morning")
public class MorningGreet implements Greet{
	
	@Override
	public void greeting() {
		System.out.println("Good morning.");
	}
}
