package main;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.*;

public class LocalMethodsCheck extends AbstractCheck {
	private int localMethods = 0;

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
		localMethods = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		String catchMsg = "Number of local method references: ";
		log(ast.getLineNo(), catchMsg + localMethods);
	}

	@Override
	public void visitToken(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
		
		if (objBlock.getChildCount() > 0) {
			// Global operands
			DetailAST child = objBlock.getFirstChild();

			while (child != null) {
				if (ast.getType() == TokenTypes.METHOD_CALL) {
					if ((!ast.branchContains(TokenTypes.DOT)) && ast.getType() == TokenTypes.LITERAL_THIS) {
						localMethods++;
					}
				}
				child = child.getNextSibling();
			}
		}

		Arrays.stream(tokenTypes()).forEach(x -> localMethods += countTokens(objBlock, x));
	}

	private int[] tokenTypes() {
		return new int[] {TokenTypes.METHOD_CALL};
	}

	private int countTokens(DetailAST ast, int tokenTypes) {
		// If the class has anything in it first
		if (ast.getChildCount() > 0) {
			int count = 0;
			//count += ast.getChildCount(tokenTypes);

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
}
