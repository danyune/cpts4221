package main;

import java.util.*;

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
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void beginTree(DetailAST ast) {
		operandCount = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		String catchMsg = "Solo Number of Operands: ";
		log(ast.getLineNo(), catchMsg + operandCount);
	}

	@Override
	public void visitToken(DetailAST ast) {

		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

		if (objBlock.getChildCount() > 0) {
			// Global operands
			DetailAST child = objBlock.getFirstChild();
			while (child != null) {
				if (child.getType() == TokenTypes.VARIABLE_DEF) {
					operandCount += countTokens(child);
				}
				if (child.getType() == TokenTypes.METHOD_DEF) {
					// operandCount++;
					operandCount += countTokens(child.findFirstToken(TokenTypes.SLIST));
				}
				child = child.getNextSibling();
			}
		}
	}

	private int[] operandArray() {
		return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
				TokenTypes.IDENT };
	}

	private int countTokens(DetailAST ast) {
		if (ast.getChildCount() > 0) {
			int total = 0;

			for (int n : operandArray()) {
				total += ast.getChildCount(n);
			}

			DetailAST child = ast.getFirstChild();

			while (child != null) {
				total += countTokens(child);
				child = child.getNextSibling();
			}
			return total;
		} else {
			return 0;
		}
	}

}
