package main;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperatorsCheck extends AbstractCheck {
	private static final String CATCH_MSG = "GENERIC CATCH MESSAGE: ";
	private int operators = 0;

	@Override
	public int[] getRequiredTokens() {
		return new int[0];
	}

	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return new int[0];
	}

	@Override
	public int[] getDefaultTokens() {
		return new int[] { TokenTypes.ELIST };
	}

	@Override
	public void finishTree(DetailAST ast) {
		log(0, CATCH_MSG + operators);
	}

	@Override
	public void visitToken(DetailAST ast) {
	    DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);
	    DetailAST block2 = objBlock.findFirstToken(TokenTypes.METHOD_DEF);
		operators += block2.getChildCount(TokenTypes.ELIST);
	}

}
