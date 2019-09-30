package main;

import com.puppycrawl.tools.checkstyle.api.*;

public class CommentsCheck extends AbstractCheck {
	private int singleComment = 0;
	private int multiComment = 0;

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
		singleComment = 0;
		multiComment = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		log(ast.getLineNo(), "Single Comments: " + singleComment);
		log(ast.getLineNo(), "Block Comments: " + multiComment);
	}

	@Override
	public void visitToken(DetailAST ast) {
		if (ast.findFirstToken(TokenTypes.BLOCK_COMMENT_BEGIN) != null) {
			// To account for the /*
			multiComment++;
			multiComment += ast.findFirstToken(TokenTypes.BLOCK_COMMENT_END).getLineNo() - ast.getLineNo();
		}

		if (ast.findFirstToken(TokenTypes.SINGLE_LINE_COMMENT) != null) {
			singleComment++;
		}

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
			if (ast.getType() == TokenTypes.SINGLE_LINE_COMMENT) {
				singleComment++;
			}

			if (ast.getType() == TokenTypes.BLOCK_COMMENT_BEGIN) {
				// To account for the /*
				multiComment++;
				multiComment += ast.findFirstToken(TokenTypes.BLOCK_COMMENT_END).getLineNo() - ast.getLineNo();
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
