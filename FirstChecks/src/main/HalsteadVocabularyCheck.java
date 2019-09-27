package main;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadVocabularyCheck extends AbstractCheck {
	private int uniqueCount = 0;
	private Set<String> variableList = new HashSet<String>();

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
		uniqueCount += countTokens(objBlock);
	}

	private int[] operatorsArray() {
		return new int[] { TokenTypes.ASSIGN, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR,
				TokenTypes.MOD };
	}

	private int[] operandArray() {
		return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
				TokenTypes.IDENT };
	}

	private int countTokens(DetailAST ast) {
		// If the class has anything in it first
		if (ast.getChildCount() > 0) {
			DetailAST child;
			int count = 0;

			if (ast.getChildCount(TokenTypes.VARIABLE_DEF) > 0) {
				child = ast.findFirstToken(TokenTypes.VARIABLE_DEF);
				if (child.getChildCount(TokenTypes.ASSIGN) > 0) {
					count += countGoodSection(child);
				}
			}

			if (ast.getChildCount(TokenTypes.EXPR) > 0) {
				child = ast.findFirstToken(TokenTypes.EXPR);
				while (child != null) {
					if (child.getChildCount(TokenTypes.ASSIGN) > 0) {
						count += countGoodSection(child);
					}
					child = child.getNextSibling();
				}
			}

			// Find first child, assuming first method
			child = ast.getFirstChild();

			// Keep checking each method until we have no more
			while (child != null) {
				count += countTokens(child);
				child = child.getNextSibling();
			}
			return count;

			// Means class/interface didn't have nothin
		} else {
			return 0;
		}
	}

	private int countGoodSection(DetailAST ast) {
		if (ast.getChildCount() > 0) {
			int total = 0;
			for (int n : operandArray()) {
				total += ast.getChildCount(n);
			}

			DetailAST child = ast.getFirstChild();

			while (child != null) {
				total += countGoodSection(child);
				child = child.getNextSibling();
			}
			return total;
		} else {
			return 0;
		}
	}
}
