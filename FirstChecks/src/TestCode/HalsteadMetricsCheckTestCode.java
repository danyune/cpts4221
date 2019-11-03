package TestCode;

public class HalsteadMetricsCheckTestCode {

	private int globalVariable = 0;

	public void firstMethod() {
		int first = switchStatement(10);
		int second = switchStatement(20);
		int third = switchStatement(15);
	}

	public void ifStatement() {
		globalVariable = 1;
		globalVariable = 2;
		if (globalVariable > 0) {
			int a = 2 + 2;
			int b = 10;
			b = a + 4;
			if (b > 5) {
				a = 0;
			}
		}
	}

	public int switchStatement(int number) {
		switch (number) {
		case 10:
			number++;
			break;
		case 20:
			number--;
			break;
		default:
			break;
		}
		return number;
	}

	public void emptyMethod() {

	}

}
