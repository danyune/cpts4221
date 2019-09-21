package main;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperatorsCheck extends AbstractCheck {
	private int operators = 0;

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

//	@Override
//	public int[] getAcceptableTokens() {
//		// TODO Auto-generated method stub
//		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
//	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.CLASS_DEF, TokenTypes.INTERFACE_DEF };
	}

	@Override
	public void beginTree(DetailAST ast) {
		operators = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		// Edit what you want the warning area to display
		String catchMsg = "PLACEHOLDER: ";
		log(ast.getLineNo(), catchMsg + operators);
	}

	@Override
	public void visitToken(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
		
		// Put whatever checks here, not sure how to make it a list
		operators += countTokens(objBlock, TokenTypes.ASSIGN);
		operators += countTokens(objBlock, TokenTypes.PLUS);
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
			
		// Means class didn't have nothin
		} else {
			return 0;
		}
	}
}
