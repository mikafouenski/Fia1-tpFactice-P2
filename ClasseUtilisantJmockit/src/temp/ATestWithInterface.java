package temp;

public class ATestWithInterface {
	private IConversion c;

	public double convertit(double temp, String sens) {
		if (sens.equals("F2C"))
			return c.convF2C(temp);
		else if (sens.equals("C2F"))
			return c.convC2F(temp);
		else
			return 0.0;
	}

	public IConversion getC() {
		return c;
	}

	public void setC(IConversion c) {
		this.c = c;
	}
}
