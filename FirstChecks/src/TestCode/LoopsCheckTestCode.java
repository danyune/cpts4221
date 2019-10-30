package TestCode;

public class LoopsCheckTestCode {

	public static void main(String[] args) {
		
	}
	
	public static void forLoop()
	{
		for (int i = 0; i < 1; i++)
		{
			System.out.println("Just existing for loop");
		}
	}
	
	public static void whileLoop()
	{
		while (true)
		{
			System.out.println("Just existing while loop");
			break;
		}
	}

	public static void doWhileLoop()
	{
		do {
			System.out.println("Just existing do while loop");
			break;
		} while (true);
	}
}
