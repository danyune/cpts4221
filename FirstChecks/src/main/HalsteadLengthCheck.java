package main;

import java.util.*;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadLengthCheck extends AbstractCheck {
	private int operandCount = 0;
	private int operatorsCount = 0;

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
		operandCount = 0;
		operatorsCount = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		String catchMsg = "Number of Operators and Operands: ";
		int total = operatorsCount + operandCount;
		log(ast.getLineNo(), catchMsg + total);
	}

	@Override
	public void visitToken(DetailAST ast) {

		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
		Arrays.stream(tokenTypes()).forEach(x -> operatorsCount += countTokens(objBlock, x));
		
		if (objBlock.getChildCount() > 0) {
			// Global operands
			DetailAST child = objBlock.getFirstChild();
			while (child != null) {
				if (child.getType() == TokenTypes.VARIABLE_DEF) {
					operandCount += countGoodSection(child);
				}
				if (child.getType() == TokenTypes.METHOD_DEF) {
					// operandCount++;
					operandCount += countGoodSection(child.findFirstToken(TokenTypes.SLIST));
				}
				child = child.getNextSibling();
			}
		}
	}

	private int countGoodSection(DetailAST ast) {
		if (ast.getChildCount() > 0) {
			int total = 0;

			for (int n : operandArray()) {
				if (ast.getType() != TokenTypes.DOT) {
					total += ast.getChildCount(n);
				}
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

	private int countTokens(DetailAST ast, int tokenTypes) {
		// If the class has anything in it first
		if (ast.getChildCount() > 0) {
			int count = 0;
			count += ast.getChildCount(tokenTypes);

			// Find first child, assuming first method
			DetailAST child = ast.getFirstChild();

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

	private int[] tokenTypes() {
		return new int[] { TokenTypes.ASSIGN, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR,
				TokenTypes.MOD };
	}

	private int[] operandArray() {
		return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
				TokenTypes.IDENT };
	}
}
