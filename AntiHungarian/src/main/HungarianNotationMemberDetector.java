package main;

import java.util.regex.Pattern;

public class HungarianNotationMemberDetector {
	private Pattern pattern = Pattern.compile("m[A-Z0-9].*");

	public boolean detectsNotation(String variableName) {
		return pattern.matcher(variableName).matches();
	}
}