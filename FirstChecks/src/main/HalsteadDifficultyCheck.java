package main;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadDifficultyCheck extends AbstractCheck {
	private int uniqueOperators = 0;
	private int totalOperands = 0;
	private Set<String> variableSet = new HashSet<String>();

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public int[] getAcceptableTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void beginTree(DetailAST ast) {
		uniqueOperators = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		String catchMsg = "Number of Unique Operators and Operands: ";
		log(ast.getLineNo(), catchMsg + uniqueOperators);
	}

	// half of the unique operators multiplied by the total number of
	// operands, divided by the number of distinct operator
	@Override
	public void visitToken(DetailAST ast) {

		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

	}
}
