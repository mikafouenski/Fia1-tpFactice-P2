package temp;

import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import temp.ATestWithInterface;
import temp.IConversion;

public class ATestWithInterfaceTest {

	@Mocked
	IConversion c;
	private ATestWithInterface a;

	@Before
	public void init() {
		a = new ATestWithInterface();
		a.setC(c);
	}

	@Test
	public void ATester_Test1() {
		new Expectations() {
			{
				a.convertit(0.0, "C2F");
				result = 32.0;
			}
		};
		double val = a.convertit(0.0, "C2F");
		double expected = 32.0;

		Assert.assertEquals(expected, val, 0);
	}

	@Test
	public void ATester_Test2() {
		new Expectations() {
			{
				a.convertit(32.0, "F2C");
				result = 0.0;
			}
		};
		double val = a.convertit(32.0, "F2C");
		double expected = 0.0;

		Assert.assertEquals(expected, val, 0);
	}

	@Test
	public void ATester_Test3() {
		new Expectations() {
			{
				a.convertit(37.0, "C2F");
				result = 98.6;
			}
		};
		double val = a.convertit(37.0, "C2F");
		double expected = 98.6;

		Assert.assertEquals(expected, val, 0);
	}

	@Test
	public void ATester_Test4() {
		new Expectations() {
			{
				a.convertit(98.6, "F2C");
				result = 37.0;
			}
		};
		double val = a.convertit(98.6, "F2C");
		double expected = 37.0;

		Assert.assertEquals(expected, val, 0);
	}

}
