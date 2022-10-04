package spring.ch03.interface_sample.used;

public class SubCalc implements Calculator{

	@Override
	public Integer calc(Integer x, Integer y) {
		return x-y;
	}
}
