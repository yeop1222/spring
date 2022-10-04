package spring.ch03.interface_sample.used;

public class AddCalc implements Calculator{

	@Override
	public Integer calc(Integer x, Integer y) {
		return x+y;
	}
}
