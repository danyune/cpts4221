package TestCode;

public class HalsteadMetricsCheckTestCode {

	public static void main(String[] args) {
		int a = 2 + 2;
		int b = a + 3;
		if (b > 5) {
			a = 0;
		}

		switch (b) {
		case 1:
			b++;
			break;
		case 2:
			b--;
			break;
		default:
			b = 0;
		}
	}

}
