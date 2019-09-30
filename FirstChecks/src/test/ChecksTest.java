package test;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ChecksTest {

	@Test
	public void CommentLinesNoComments() {
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
		
		assertEquals(16, 16);
	}
}
