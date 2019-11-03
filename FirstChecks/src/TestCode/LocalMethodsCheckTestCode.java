package TestCode;

public class LocalMethodsCheckTestCode {

	public LocalMethodsCheckTestCode() {
		int x = this.addTwo(2, 2);
		int y = addTwo(3, 3);
	}

	public int addTwo(int a, int b) {
		return a + b;
	}
}
