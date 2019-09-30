package main;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.*;

public class TotalCommentsCheck extends AbstractCheck {
	
	private int commentLines = 0;

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
		return new int[] { TokenTypes.COMMENT_CONTENT};
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT };
	}

	@Override
	public void beginTree(DetailAST ast) {
		setCommentLines(0);
	}

	@Override
	public void finishTree(DetailAST ast) {
		
		String catchMsg = "Total Comments: ";
		log(ast.getLineNo(), catchMsg + getCommentLines());
	}

	@Override
	public void visitToken(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.COMMENT_CONTENT);
		Arrays.stream(tokenTypes()).forEach(x -> setCommentLines(getCommentLines() + countTokens(objBlock, x)));
	}

	private int[] tokenTypes() {
		return new int[] {TokenTypes.COMMENT_CONTENT};
	}

	private int countTokens(DetailAST ast, int tokenTypes) {

		return 1;
	}

	public int getCommentLines() {
		return commentLines;
	}

	public int setCommentLines(int commentLines) {
		this.commentLines = commentLines;
		return commentLines;
	}
}
