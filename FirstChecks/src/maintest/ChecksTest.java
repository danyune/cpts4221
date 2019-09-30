package maintest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.HalsteadMetricsCheck;

class ChecksTest {

	@Test
	void testTokenArrays() {
		HalsteadMetricsCheck hmc = new HalsteadMetricsCheck();
		assertEquals(hmc.getDefaultTokens().length, 2);
		assertEquals(hmc.getAcceptableTokens().length, 2);
		assertEquals(hmc.getRequiredTokens().length, 0);
	}

}
