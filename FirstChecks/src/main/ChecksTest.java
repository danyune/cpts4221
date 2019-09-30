package main;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ChecksTest {

	@Test
	public void CommentLinesAllTypesTest() {
		String str = 
				"// Should be 8 comments\r\n" + 
				"public class DummyClass {\r\n" + 
				"\r\n" + 
				"	// Please work\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		int num1 = 0;\r\n" + 
				"		num1 = 5;\r\n" + 
				"		/*\r\n" + 
				"		 * Comment\r\n" + 
				"		 */\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	// The best comment\r\n" + 
				"	public void merthod() {\r\n" + 
				"		String yo = \"boi\";\r\n" + 
				"		yo = \"gurl\";\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"/*\r\n" + 
				" * hello \r\n" + 
				" * ewlafj\r\n" + 
				" * faefe\r\n" + 
				" * ef\r\n" + 
				" * aefeaf\r\n" + 
				" * d\r\n" + 
				" */\r\n" + 
				"\r\n" + 
				"//dsafe\r\n" + 
				"\r\n" + 
				"// sduhfie\r\n";
		
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();
		// need to run str through the check
		assertEquals(16, LinesOfCommentsCheck.getSingleComment() + LinesOfCommentsCheck.getMultiComment());
	}
	
	@Test
	public void CommentLinesNoCommentsTest() {
		String str = 
				"public class DummyClass {\r\n" + 
				"\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"		int num1 = 0;\r\n" + 
				"		num1 = 5;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public void merthod() {\r\n" + 
				"		String yo = \"boi\";\r\n" + 
				"		yo = \"gurl\";\r\n" + 
				"	}\r\n" + 
				"}\r\n";
		
		LinesOfCommentsCheck test = new LinesOfCommentsCheck();
		// need to run str through the check
		assertEquals(0, LinesOfCommentsCheck.getSingleComment() + LinesOfCommentsCheck.getMultiComment());
	}
	
	@Test
	public void TotalCommentsAllTypesTest() {
		String str = 
				"// Should be 8 comments\r\n" + 
				"public class DummyClass {\r\n" + 
				"\r\n" + 
				"	// Please work\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"		// TODO Auto-generated method stub\r\n" + 
				"		int num1 = 0;\r\n" + 
				"		num1 = 5;\r\n" + 
				"		/*\r\n" + 
				"		 * Comment\r\n" + 
				"		 */\r\n" + 
				"		\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	// The best comment\r\n" + 
				"	public void merthod() {\r\n" + 
				"		String yo = \"boi\";\r\n" + 
				"		yo = \"gurl\";\r\n" + 
				"	}\r\n" + 
				"}\r\n" + 
				"/*\r\n" + 
				" * hello \r\n" + 
				" * ewlafj\r\n" + 
				" * faefe\r\n" + 
				" * ef\r\n" + 
				" * aefeaf\r\n" + 
				" * d\r\n" + 
				" */\r\n" + 
				"\r\n" + 
				"//dsafe\r\n" + 
				"\r\n" + 
				"// sduhfie\r\n";
		
		TotalCommentsCheck test = new TotalCommentsCheck();
		// need to run str through the check
		assertEquals(8, test.getCommentLines());
	}
	
	@Test
	public void TotalCommentsNoCommentsTest() {
		String str = 
				"public class DummyClass {\r\n" + 
				"\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"		int num1 = 0;\r\n" + 
				"		num1 = 5;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public void merthod() {\r\n" + 
				"		String yo = \"boi\";\r\n" + 
				"		yo = \"gurl\";\r\n" + 
				"	}\r\n" + 
				"}\r\n";
		
		TotalCommentsCheck test = new TotalCommentsCheck();
		// need to run str through the check
		assertEquals(0, test.getCommentLines());
	}
	
	@Test
	public void VariableCountTest() {
		String str = 
				"public class DummyClass {\r\n" + 
				"\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"		int num1 = 0;\r\n" + 
				"		num1 = 5;\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public void merthod() {\r\n" + 
				"		String yo = \"boi\";\r\n" + 
				"		yo = \"gurl\";\r\n" + 
				"	}\r\n" + 
				"}\r\n";
		
		VariablesCheck test = new VariablesCheck();
		// need to run str through the check
		assertEquals(8, test.getVariablesCount());
	}
	
	@Test
	public void VariableCountNoVarsTest() {
		String str = 
				"public class DummyClass {\r\n" + 
				"\r\n" + 
				"	public static void main(String[] args) {\r\n" + 
				"		System.out.print(\"Holla\");\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"	public void merthod() {\r\n" + 
				"		System.out.print(\"Yo\");\r\n" + 
				"	}\r\n" + 
				"}";
		
		VariablesCheck test = new VariablesCheck();
		// need to run str through the check
		assertEquals(0, test.getVariablesCount());
	}
}

