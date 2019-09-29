package main;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Deque;

import com.puppycrawl.tools.checkstyle.FileStatefulCheck;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

@FileStatefulCheck

public class CyclomaticComplexity extends AbstractCheck {

	/**
	 * A key is pointing to the warning message text in "messages.properties" file.
	 */
	public static final String MSG_KEY = "cyclomaticComplexity";

	/** The initial current value. */
	private static final BigInteger INITIAL_VALUE = BigInteger.ONE;

	/** Default allowed complexity. */
	private static final int DEFAULT_COMPLEXITY_VALUE = 10;

	/** Stack of values - all but the current value. */
	private final Deque<BigInteger> valueStack = new ArrayDeque<>();

	/** Whether to treat the whole switch block as a single decision point. */
	private boolean switchBlockAsSingleDecisionPoint;

	/** The current value. */
	private BigInteger currentValue = INITIAL_VALUE;

	/** Threshold to report violation for. */
	private int max = DEFAULT_COMPLEXITY_VALUE;
	
	private int total = 0;

	/**
	 * Sets whether to treat the whole switch block as a single decision point.
	 * 
	 * @param switchBlockAsSingleDecisionPoint whether to treat the whole switch
	 *                                         block as a single decision point.
	 */
	public void setSwitchBlockAsSingleDecisionPoint(boolean switchBlockAsSingleDecisionPoint) {

		this.switchBlockAsSingleDecisionPoint = switchBlockAsSingleDecisionPoint;
	}

	/**
	 * Set the maximum threshold allowed.
	 *
	 * @param max the maximum threshold
	 */
	public final void setMax(int max) {

		this.max = max;
	}

	@Override
	public int[] getDefaultTokens() {

		return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT,
				TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
				TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION,
				TokenTypes.LAND, TokenTypes.LOR, };

	}

	@Override
	public int[] getAcceptableTokens() {

		return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT, TokenTypes.STATIC_INIT,
				TokenTypes.LITERAL_WHILE, TokenTypes.LITERAL_DO, TokenTypes.LITERAL_FOR, TokenTypes.LITERAL_IF,
				TokenTypes.LITERAL_SWITCH, TokenTypes.LITERAL_CASE, TokenTypes.LITERAL_CATCH, TokenTypes.QUESTION,
				TokenTypes.LAND, TokenTypes.LOR, };

	}

	@Override
	public final int[] getRequiredTokens() {

		return new int[] { TokenTypes.CTOR_DEF, TokenTypes.METHOD_DEF, TokenTypes.INSTANCE_INIT,
				TokenTypes.STATIC_INIT, };

	}

	@Override
	public void visitToken(DetailAST ast) {

		switch (ast.getType()) {
		case TokenTypes.CTOR_DEF:
		case TokenTypes.METHOD_DEF:
		case TokenTypes.INSTANCE_INIT:
		case TokenTypes.STATIC_INIT:
			visitMethodDef();
			break;
		default:
			visitTokenHook(ast);
		}
	}
	
	@Override
	public void beginTree(DetailAST ast) {
		total = 0;
	}
	
	public int getTotal() {
		return total;
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

	/**
	 * Hook called when visiting a token. Will not be called the method definition
	 * tokens.
	 *
	 * @param ast the token being visited
	 */
	private void visitTokenHook(DetailAST ast) {
		if (switchBlockAsSingleDecisionPoint) {
			if (ast.getType() != TokenTypes.LITERAL_CASE) {
				incrementCurrentValue(BigInteger.ONE);
			}
		} else if (ast.getType() != TokenTypes.LITERAL_SWITCH) {
			incrementCurrentValue(BigInteger.ONE);
		}
	}

	/**
	 * Process the end of a method definition.
	 *
	 * @param ast the token representing the method definition
	 */
	private void leaveMethodDef(DetailAST ast) {
		final BigInteger bigIntegerMax = BigInteger.valueOf(max);
		if (currentValue.compareTo(bigIntegerMax) > 0) {
			log(ast, MSG_KEY, currentValue, bigIntegerMax);

			popValue();
		}
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
		currentValue = INITIAL_VALUE;
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
}