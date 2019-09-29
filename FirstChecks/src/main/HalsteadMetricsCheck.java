package main;

import java.math.BigInteger;
import java.util.*;

import com.puppycrawl.tools.checkstyle.api.*;

public class HalsteadMetricsCheck extends AbstractCheck {
	private int operatorsCount = 0;
	private int operandsCount = 0;
	private int uniqueOperators = 0;
	private int uniqueOperands = 0;
	private int cycTotal = 0;
	
	private int testValue = 0;

	// CC stuff
	/** The current value. */
	private BigInteger currentValue = BigInteger.ONE;
	/** Stack of values - all but the current value. */
	private final Deque<BigInteger> valueStack = new ArrayDeque<>();

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
		operandsCount = 0;
		uniqueOperators = 0;
		uniqueOperands = 0;
		cycTotal = 0;
		testValue = 0;
	}

	@Override
	public void leaveToken(DetailAST ast) {

		switch (ast.getType()) {
		case TokenTypes.CTOR_DEF:
		case TokenTypes.METHOD_DEF:
		case TokenTypes.INSTANCE_INIT:
		case TokenTypes.STATIC_INIT:
			leaveMethodDef(ast);
			break;
		default:
			break;
		}
	}

	@Override
	public void finishTree(DetailAST ast) {

		// Sum of total operators and operands
		int halsteadLength = operatorsCount + operandsCount;

		// Unique number of operators and operands
		int halsteadVocabulary = uniqueOperators + uniqueOperands;

		// Program length (N) times log_2 halsteadVocabulary
		double halsteadVolume = halsteadLength * (Math.log(halsteadVocabulary) / Math.log(2));

		// D = (uniqueOperators / 2) * (operandsCount / uniqueOperands)
		double halsteadDifficulty = (((double) uniqueOperators / 2)
				* (double) (operandsCount / (double) uniqueOperands));

		// halsteadVolume * halsteadDifficulty
		double halsteadEffort = halsteadDifficulty * halsteadVolume;

		log(ast.getLineNo(), "Number of Operators: " + operatorsCount);
		log(ast.getLineNo(), "Number of Operands: " + operandsCount);
		log(ast.getLineNo(), "Halstead Length: " + halsteadLength);
		log(ast.getLineNo(), "Halstead Vocabulary: " + halsteadVocabulary);
		log(ast.getLineNo(), "Halstead Difficulty: " + halsteadDifficulty);
		log(ast.getLineNo(), "Halstead Volume: " + halsteadVolume);
		log(ast.getLineNo(), "Halstead Effort: " + halsteadEffort);
		log(ast.getLineNo(), "CYC: " + cycTotal);
		log(ast.getLineNo(), "Test Value: " + testValue);
	}

	/**
	 * Hook called when visiting a token. Will not be called the method definition
	 * tokens.
	 *
	 * @param ast the token being visited
	 */
	private void visitTokenHook(DetailAST ast) {
		if (ast.getType() != TokenTypes.LITERAL_SWITCH) {
			incrementCurrentValue(BigInteger.ONE);
		}
	}

	private void leaveMethodDef(DetailAST ast) {
		cycTotal += currentValue.intValue();
	}

	/**
	 * Increments the current value by a specified amount.
	 *
	 * @param amount the amount to increment by
	 */
	private void incrementCurrentValue(BigInteger amount) {
		currentValue = currentValue.add(amount);
	}

	/** Push the current value on the stack. */
	private void pushValue() {
		valueStack.push(currentValue);
		currentValue = BigInteger.ONE;
	}

	/**
	 * Pops a value off the stack and makes it the current value.
	 */
	private void popValue() {
		currentValue = valueStack.pop();
	}

	/** Process the start of the method definition. */
	private void visitMethodDef() {
		pushValue();
	}

	@Override
	public void visitToken(DetailAST ast) {
		switch (ast.getType()) {
		case TokenTypes.CTOR_DEF:
		case TokenTypes.METHOD_DEF:
		case TokenTypes.INSTANCE_INIT:
		case TokenTypes.STATIC_INIT:
			testValue++;
			visitMethodDef();
			break;
		default:
			visitTokenHook(ast);
		}

		DetailAST objBlock = ast.findFirstToken(TokenTypes.OBJBLOCK);

		for (int n : operatorTokens()) {
			if (objBlock.branchContains(n)) {
				uniqueOperators++;
			}
		}
		for (int n : operandTokens()) {
			if (objBlock.branchContains(n)) {
				uniqueOperands++;
			}
		}

		if (objBlock.getChildCount() > 0) {
			// Global operands
			DetailAST child = objBlock.getFirstChild();
			while (child != null) {
				if (child.getType() == TokenTypes.VARIABLE_DEF) {
					operandsCount++;
					increaseCounts(child);
				}
				if (child.getType() == TokenTypes.METHOD_DEF) {
					if (child.getChildCount(TokenTypes.SLIST) > 0) {
						// Add one for the open curly bracket for the statement list
						operatorsCount++;
						increaseCounts(child.findFirstToken(TokenTypes.SLIST));
					}
				}
				child = child.getNextSibling();
			}
		}
	}

	private int[] operatorTokens() {
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
				TokenTypes.INC, TokenTypes.POST_INC, TokenTypes.DEC, TokenTypes.POST_DEC };
	}

	private int[] operandTokens() {
		return new int[] { TokenTypes.NUM_DOUBLE, TokenTypes.NUM_FLOAT, TokenTypes.NUM_INT, TokenTypes.NUM_LONG,
				TokenTypes.IDENT };
	}

	private void increaseCounts(DetailAST ast) {
		//variableCount += ast.getChildCount(TokenTypes.VARIABLE_DEF);
		operatorsCount += countOperators(ast);
		operandsCount += countOperands(ast);
	}

	private int countOperators(DetailAST ast) {
		if (ast.getChildCount() > 0) {
			int count = 0;

			for (int n : operatorTokens()) {
				count += ast.getChildCount(n);
			}

			// Find first child, assuming first method
			DetailAST child = ast.getFirstChild();

			// Keep checking each method until we have no more
			while (child != null) {
				count += countOperators(child);
				child = child.getNextSibling();
			}
			return count;

			// Means class/interface didn't have nothin
		} else {
			return 0;
		}
	}

	private int countOperands(DetailAST ast) {
		if (ast.getChildCount() > 0) {
			int count = 0;

			for (int n : operandTokens()) {
				count += ast.getChildCount(n);
			}

			DetailAST child = ast.getFirstChild();

			while (child != null) {
				count += countOperands(child);
				child = child.getNextSibling();
			}
			return count;
		} else {
			return 0;
		}
	}
}
