package main;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.*;

public class LinesOfCommentsCheck extends AbstractCheck {
	
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
		return new int[] { TokenTypes.COMMENT_CONTENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END};
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.COMMENT_CONTENT, TokenTypes.BLOCK_COMMENT_BEGIN, TokenTypes.BLOCK_COMMENT_END};
	}

	@Override
	public void beginTree(DetailAST ast) {
		commentLines = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		
		String catchMsg = "Lines of Comments: ";
		log(ast.getLineNo(), catchMsg + commentLines);
	}

	@Override
	public void visitToken(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.COMMENT_CONTENT);
		Arrays.stream(tokenTypes()).forEach(x -> commentLines += countTokens(objBlock, x));
	}

	private int[] tokenTypes() {
		return new int[] {TokenTypes.COMMENT_CONTENT, TokenTypes.BLOCK_COMMENT_BEGIN };
	}

	private int countTokens(DetailAST ast, int tokenTypes) {
	
		if (tokenTypes == TokenTypes.BLOCK_COMMENT_BEGIN) {
			int lines = 0;
			boolean commentBlock = true;
			
			while(commentBlock) {
				if(tokenTypes != TokenTypes.BLOCK_COMMENT_END) {
					ast = ast.getNextSibling();
					lines++;
				}
				else {
					commentBlock = false;
				}
			}
			
			return lines;
		}
		else {
			return 1;
		}
	}
}
