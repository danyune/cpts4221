package main;

import com.puppycrawl.tools.checkstyle.api.*;

public class MethodCalls extends AbstractCheck {
	private int localMethods = 0;
	private int externalMethods = 0;

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public boolean isCommentNodesRequired() {
		return true;
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
		externalMethods = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		log(ast.getLineNo(), "Local Methods: " + localMethods);
		log(ast.getLineNo(), "External Methods: " + externalMethods);
	}

	@Override
	public void visitToken(DetailAST ast) {

		while (ast != null) {
			if (ast.getChildCount() > 0) {
				// Global operands
				DetailAST child = ast.getFirstChild();
				while (child != null) {
					countTokens(child);
					child = child.getNextSibling();
				}
			}
			ast = ast.getNextSibling();
		}
	}

	private void countTokens(DetailAST ast) {

		if (ast.getChildCount() > 0) {
			if (ast.getType() == TokenTypes.METHOD_CALL) {
				if (ast.getChildCount(TokenTypes.DOT) > 0) {
					if (ast.findFirstToken(TokenTypes.DOT).branchContains(TokenTypes.LITERAL_THIS)) {
						localMethods++;
					} else {
						externalMethods++;
					}

				} else {
					localMethods++;
				}
			}

			// Find first child, assuming first method
			DetailAST child = ast.getFirstChild();

			// Keep checking each method until we have no more
			while (child != null) {
				countTokens(child);
				child = child.getNextSibling();
			}
		}
	}
}