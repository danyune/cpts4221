package main;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadVocabularyCheck extends AbstractCheck {
	private int uniqueCount = 0;
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
		uniqueCount = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		String catchMsg = "Number of Unique Operators and Operands: ";
		log(ast.getLineNo(), catchMsg + uniqueCount);
	}

	@Override
	public void visitToken(DetailAST ast) {

		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

		if (objBlock.getChildCount() > 0) {

			for (int n : operandArray()) {
				if (objBlock.branchContains(n)) {
					uniqueCount++;
				}
			}
			
			for (int n : operatorsArray()) {
				if (objBlock.branchContains(n)) {
					uniqueCount++;
				}
			}

			getVariableNames(objBlock);
			uniqueCount += variableSet.size();
		}

	}

	private void getVariableNames(DetailAST ast) {
		if (ast.getChildCount() > 0) {
			if (ast.getType() == TokenTypes.VARIABLE_DEF) {
				variableSet.add(ast.findFirstToken(TokenTypes.IDENT).getText());
//				uniqueCount++;
			}
			DetailAST child = ast.getFirstChild();

			while (child != null) {
				getVariableNames(child);
				child = child.getNextSibling();
			}
		}
	}

	private int[] operatorsArray() {
		return new int[] { TokenTypes.ASSIGN, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR,
				TokenTypes.MOD };
	}

	// Not including identity here
	private int[] operandArray() {
		return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG };
	}
}
