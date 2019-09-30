/*
 * Here we can see an example of the poltergeist anti-pattern.
 * The class "PoltergeistClass" has no function except to invoke the creation of the WantedClass, and is therefore completely unnecessary
 *
 */
public class PoltergeistAntiPattern {
	
	public static void main(String[] args) {
		
		PoltergeistClass polt = new PoltergeistClass();
		
		WantedClass wanted = polt.CreateClass("TotallyUniqueUserName", "hunter2");
		
		System.out.println(wanted);
	}
	
	static class PoltergeistClass {
		
		public PoltergeistClass() {
		}
		
		public WantedClass CreateClass(String user, String pass) {
			
			WantedClass temp = new WantedClass(user, pass);
			return temp;
		}
	}
	
	static class WantedClass {
		private String userName;
		private String password;
		
		public WantedClass(String user, String pass) {
			
			userName = user;
			password = pass;
		}
		
		@Override
		public String toString() {
			return String.format(userName + ": " + password);
		}
	}
}
