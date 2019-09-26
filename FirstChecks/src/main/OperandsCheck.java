package main;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperandsCheck extends AbstractCheck {
	private int operandCount = 0;

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
		return new int[] { TokenTypes.EXPR };
	}

	@Override
	public void beginTree(DetailAST ast) {
		operandCount = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		String catchMsg = "Number of Operands: ";
		log(ast.getLineNo(), catchMsg + operandCount);
	}

	@Override
	public void visitToken(DetailAST ast) {
		Arrays.stream(tokenTypes()).forEach(x -> operandCount += countTokens(ast, x));
	}

	private int[] tokenTypes() {
		return new int[] { TokenTypes.EXPR, TokenTypes.VARIABLE_DEF };
	}

	private int[] operandArray() {
		return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
				TokenTypes.IDENT };
	}

	private int countTokens(DetailAST ast, int tokenTypes) {
		// If the class has anything in it first
		if (ast.getChildCount() > 0) {
			int count = 0;

			// Find first child, assuming first method
			DetailAST child = ast.getFirstChild();

			// If this child is a variable def AND contains assign OR if it is an EXPR
			for (int asdf : tokenTypes()) {
				
			}
			for (int n : operandArray()) {
				if (child.getType() == n) {
					count++;
				}
			}

			// Keep checking each method until we have no more
			while (child != null) {
				count += countTokens(child, tokenTypes);
				child = child.getNextSibling();
			}
			return count;

			// Means class/interface didn't have nothin
		} else {
			return 0;
		}
	}
}
