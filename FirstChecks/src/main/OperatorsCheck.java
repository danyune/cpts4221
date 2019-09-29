package main;

import java.util.Arrays;

import com.puppycrawl.tools.checkstyle.api.*;

public class OperatorsCheck extends AbstractCheck {
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
		operatorsCount = 0;
	}

	@Override
	public void finishTree(DetailAST ast) {
		String catchMsg = "Solo Number of Operators: ";
		log(ast.getLineNo(), catchMsg + operatorsCount);
	}

	@Override
	public void visitToken(DetailAST ast) {
		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

		if (objBlock.getChildCount() > 0) {
			// Global operands
			DetailAST child = objBlock.getFirstChild();
			while (child != null) {
				if (child.getType() == TokenTypes.VARIABLE_DEF) {
					operatorsCount += countTokens(child);
				}
				if (child.getType() == TokenTypes.METHOD_DEF) {
					if (child.getChildCount(TokenTypes.SLIST) > 0) {
						operatorsCount++;
						operatorsCount += countTokens(child.findFirstToken(TokenTypes.SLIST));
					}
				}
				child = child.getNextSibling();
			}
		}
	}

	private int[] tokenTypes() {
		return new int[] { TokenTypes.ASSIGN, TokenTypes.PLUS, TokenTypes.MINUS, TokenTypes.DIV, TokenTypes.STAR,
				TokenTypes.MOD, TokenTypes.LCURLY, TokenTypes.RCURLY, TokenTypes.SLIST, TokenTypes.RPAREN,
				TokenTypes.LPAREN, TokenTypes.LITERAL_WHILE, TokenTypes.DO_WHILE, TokenTypes.LITERAL_FOR,
				TokenTypes.METHOD_CALL, TokenTypes.DOT, TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE,
				TokenTypes.LITERAL_IF, TokenTypes.LITERAL_ELSE, TokenTypes.LITERAL_RETURN, TokenTypes.LITERAL_BREAK,
				TokenTypes.COMMA, TokenTypes.RBRACK, TokenTypes.ARRAY_DECLARATOR, TokenTypes.ARRAY_INIT,
				TokenTypes.SEMI, TokenTypes.BOR, TokenTypes.BOR_ASSIGN, TokenTypes.BAND_ASSIGN, TokenTypes.BAND,
				TokenTypes.BSR_ASSIGN, TokenTypes.BSR, TokenTypes.BXOR_ASSIGN, TokenTypes.BXOR, TokenTypes.DIV_ASSIGN,
				TokenTypes.MINUS_ASSIGN, TokenTypes.MOD_ASSIGN, TokenTypes.PLUS_ASSIGN, TokenTypes.SL,
				TokenTypes.SL_ASSIGN, TokenTypes.BNOT, TokenTypes.SR_ASSIGN, TokenTypes.SR, TokenTypes.STAR_ASSIGN,
				TokenTypes.INC, TokenTypes.POST_INC, TokenTypes.DEC, TokenTypes.POST_DEC, TokenTypes.LITERAL_DEFAULT };
	}

	private int countTokens(DetailAST ast) {

		if (ast.getChildCount() > 0) {
			int count = 0;

			for (int n : tokenTypes()) {
				count += ast.getChildCount(n);
			}

			// Find first child, assuming first method
			DetailAST child = ast.getFirstChild();

			// Keep checking each method until we have no more
			while (child != null) {
				count += countTokens(child);
				child = child.getNextSibling();
			}
			return count;

			// Means class/interface didn't have nothin
		} else {
			return 0;
		}
	}
}
