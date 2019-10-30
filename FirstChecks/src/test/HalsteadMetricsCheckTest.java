package test;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import com.puppycrawl.tools.checkstyle.*;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import main.HalsteadMetricsCheck;

class HalsteadMetricsCheckTest extends AbstractModuleTestSupport {
	// Check if the elements in the arrays are ok
	@Test
	public void arraySizesTest() {
		HalsteadMetricsCheck halsteadMetricsCheck = new HalsteadMetricsCheck();
		assertEquals(2, halsteadMetricsCheck.getAcceptableTokens().length);
		assertEquals(0, halsteadMetricsCheck.getRequiredTokens().length);
		assertEquals(2, halsteadMetricsCheck.getDefaultTokens().length);
	}

	// Check if the beginTree is called properly
	@Test
	public void beginTreeCallTest() throws Exception {
		DetailAST ast = new DetailAST();
		HalsteadMetricsCheck halsteadMetricsCheck = mock(HalsteadMetricsCheck.class);
		doNothing().when(halsteadMetricsCheck).beginTree(isA(DetailAST.class));
		halsteadMetricsCheck.beginTree(ast);
		Mockito.verify(halsteadMetricsCheck, times(1)).beginTree(ast);
	}

	// Check if the visitToken is called properly
	@Test
	public void visitTokenCallTest() throws Exception {
		DetailAST ast = new DetailAST();
		HalsteadMetricsCheck halsteadMetricsCheck = mock(HalsteadMetricsCheck.class);
		doNothing().when(halsteadMetricsCheck).visitToken(isA(DetailAST.class));
		halsteadMetricsCheck.visitToken(ast);
		Mockito.verify(halsteadMetricsCheck, times(1)).visitToken(ast);
	}

	// Check if the finishTree is called properly
	@Test
	public void finishTreeCallTest() throws Exception {
		DetailAST ast = new DetailAST();
		HalsteadMetricsCheck halsteadMetricsCheck = mock(HalsteadMetricsCheck.class);
		doNothing().when(halsteadMetricsCheck).finishTree(isA(DetailAST.class));
		halsteadMetricsCheck.finishTree(ast);
		Mockito.verify(halsteadMetricsCheck, times(1)).finishTree(ast);
	}

	// Actually test the check
	@Test
	public void halsteadMetricsTest() throws Exception {

		// Test the actual running of the check
		DefaultConfiguration dc = createModuleConfig(HalsteadMetricsCheck.class);
		String fileToTest = getPackageLocation() + "HalsteadMetricsCheckTestCode.java";
		String[] results = { 
				"1: Halstead Difficulty: 144.5",
				"1: Halstead Effort: 31935.370623545947",
				"1: Halstead Length: 53",
				"1: Halstead Vocabulary: 18",
				"1: Halstead Volume: 221.00602507644254",
				"1: Maintainability Index: 55.30630965643668",
				"1: Number of Operands: 17",
				"1: Number of Operators: 36"
				};
		verify(dc, fileToTest, results);
	}

	@Override
	protected String getPackageLocation() {

		// Replace TestCode with the directory or package the code to test is at
		String packageAndFileLocation = "\\src\\TestCode\\";
		try {
			String filePath = new java.io.File(".").getCanonicalPath() + packageAndFileLocation;
			return filePath;
		} catch (IOException e) {
			return null;
		}
	}
}
