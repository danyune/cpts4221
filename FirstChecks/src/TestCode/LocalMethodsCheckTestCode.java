package TestCode;

import java.io.IOException;

public class LocalMethodsCheckTestCode {
	public static void main(String[] args) throws IOException {
		int x = addTwo(2, 2);
		int y = addTwo(3, 3);
	}

	public static int addTwo(int a, int b) {
		return a + b;
	}
}
