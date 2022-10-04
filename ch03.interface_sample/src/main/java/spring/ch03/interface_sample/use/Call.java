package spring.ch03.interface_sample.use;

import spring.ch03.interface_sample.used.Calculator;
//import spring.ch03.interface_sample.used.AddCalc;
import spring.ch03.interface_sample.used.SubCalc;

public class Call {
	public static void main(String[] args) {
//		Calculator calculator = new AddCalc();
		Calculator calculator = new SubCalc();
		Integer result = calculator.calc(10, 5);
		System.out.println("result="+result);
	}
}
