package validityChecker;

import java.util.logging.Logger;

public class CheckPersonalNumber implements ValidityChecker {

	@Override
	public boolean validityCheck(String str) {
		
		/*
		 * A valid Swedish personal number must
		 * 	- be of length 12.
		 * 	- be within the years 1900 to 2099.
		 * 	- have a valid month and day.
		 *  - the last digit must coincide with Luhn's algorithm.
		 * 
		 */
		
		// Create logger.
		Logger julLogger = java.util.logging.Logger.getLogger(CheckPersonalNumber.class.getName());
		
		// Check if input is null.
		if (str == null){
			julLogger.info("Input not correct; Personal Number was null.");
			return false;
		}
		
		// Check that input is of length 12.
		boolean isLength12 = checkLengthIs12(str);
		if (!isLength12) {
			julLogger.info("Input not correct; Personal Number not 12 in length.");
			return false;
		}

		// Check that all input characters are UnicodeDigits.
		boolean isAsciiDigits = checkAsciiDigits(str);
		if (!isAsciiDigits) {
			julLogger.info("Input not correct; Personal Number contain none-Ascii digits.");
			return false;
		}
		
		// Check that the dates are reasonable.
		boolean datesAreReasonable = checkDatesReasonable(str);
		if (!datesAreReasonable) {
			julLogger.info("Input not correct; Personal Number contain invalid dates.");
			return false;
		}
		
		
		// Check last digit.
		int personalNumberSum = getPersonalNumberSum(str);
		int intRemainder2 = performModuloOperations(personalNumberSum);		
		boolean isLastDigitCorrect = checkLastDigit(str, intRemainder2);
		if (!isLastDigitCorrect) {
			julLogger.info("Input not correct; Last digit of Personal Number was not correct.");
			return false;
		}
		
		// All checks passed, valid Swedish personal number.
		return true;
		
	}	
	
	// Check length.
	public boolean checkLengthIs12(String str) {
		return str.length() == 12;
	}
	
	// Check String contains only ASCII digits.
	public boolean checkAsciiDigits(String str) {
		for (int i = 0; i < str.length(); i++){
		    if(!isAsciiDigit(str.charAt(i))) {
		    	return false;
		    }
		}
		return true;
	}
	
	// Check character is ASCII digit.
	private static boolean isAsciiDigit(char c) {
	    return (c >= '0' && c <= '9');
	}
	
	// Check that the dates are reasonable.
	public boolean checkDatesReasonable(String str) {
		
		// Check that the first two is 19 or 20.
		String oneTwoChar = str.substring(0, 2);
		int oneTwoInt = Integer.parseInt(oneTwoChar);
		if (oneTwoInt < 19 || oneTwoInt > 20) {
			return false;
		}
		
		// Year 1900 - 2099 is considered valid. In 79 Years I will have to fix this code.
		
		// Checks valid month
		String fiveSixChar = str.substring(4, 6);
		int fiveSixInt = Integer.parseInt(fiveSixChar);
		if (fiveSixInt < 1 || fiveSixInt > 12) {
			return false;
		}
		
		// February.
		String sevenEightChar = str.substring(6, 8);
		int sevenEightInt = Integer.parseInt(sevenEightChar);
		if (fiveSixInt == 2) {
			
			// Leap years have an extra day in February.
			int yearInt = Integer.parseInt(str.substring(0, 4));
			if (yearInt % 4 == 0 && yearInt != 1900) {
				if (sevenEightInt < 1 || sevenEightInt > 29) {
					return false;
				}
			}
			else {
				if (sevenEightInt < 1 || sevenEightInt > 28) {
					return false;
				}
			}
				
		}
		
		// These months have 30 days.
		else if (fiveSixInt == 4 || fiveSixInt == 6 || fiveSixInt == 9 || fiveSixInt == 11) {
			if (sevenEightInt < 1 || sevenEightInt > 30) {
				return false;
			}
		}
		
		// The rest have 31 days.
		else {
			if (sevenEightInt < 1 || sevenEightInt > 31) {
				return false;
			}			
		}
		return true;
	}
	
	// Calculates personal number-sum, part one of the Luhn algorithm.
	public int getPersonalNumberSum(String str) {
		int sum = 0;		
		for (int i = 2; i < str.length() - 1; i++){
			
			char c = str.charAt(i);
			int intValue = Character.getNumericValue(c);

			if (i % 2 == 0) {
				intValue = intValue * 2;
			}
				
			if (intValue > 9) {
				intValue = intValue % 10 + 1;
			}
			sum = sum + intValue;
		}
		return sum;
	}
	
	// Performs modulo operations on personal number-sum, part two of the Luhn algorithm.
	public int performModuloOperations(int intSum) {
		
		int intRemainder = intSum % 10;
		int intDifference = 10 - intRemainder;
		int intRemainder2 = intDifference % 10;
		
		return intRemainder2;
	}

	// Checks if the return of the Luhn algorithm agrees with last digit.
	private boolean checkLastDigit(String str, int intRemainder2) {
		char lastChar = str.charAt(str.length() - 1);
		int lastCharInt = Character.getNumericValue(lastChar);
		
		return lastCharInt == intRemainder2;
	}
}
