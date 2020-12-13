package validityChecker;

import java.util.Arrays;
import java.util.logging.Logger;

public class CheckCarRegistryNumber implements ValidityChecker{

	@Override
	public boolean validityCheck(String str) {
		/*
		 * A valid Swedish car registry number must be
		 * 	- Not null.
		 * 	- 6 Character in length.
		 * 	- Have a certain format.
		 * and must not
		 * 	- Contain a prohibited character combination
		 * 	- Have I, Q or V.
		 * 	- Triple zeros.
		 * 
		 */
		
		// Create logger.
		Logger julLogger = java.util.logging.Logger.getLogger(CheckCarRegistryNumber.class.getName());
		
		// Check if input is null.
		if (str == null){
			julLogger.info("Input not correct; Car registry number was null.");
			return false;
		}
		
		// Check that input is of length 6.
		boolean isLength6 = checkLengthIs6(str);
		if (!isLength6) {
			julLogger.info("Input not correct; Car registry number not 6 in length.");
			return false;
		}
	
		// Check that format of input.
		boolean carRegistryNumberFormatIsCorrect = checkCarRegistryNumberFormat(str);
		if (!carRegistryNumberFormatIsCorrect) {
			julLogger.info("Input not correct; Car registry number did not have the correct format.");
			return false;
		}	
		
		// Check if containing prohibited character combination.
		boolean carRegistryNumberProhibited = checkCarRegistryNumberProhibited(str);
		if (carRegistryNumberProhibited){
			julLogger.info("Input not correct; Car registry number contained a prohibited character combination.");
			return false;
		}
		
		// The characters I, Q and V aren't allowed.
		boolean carRegistryNumberContainIQV = checkCarRegistryNumberContainIQV(str);
		if (carRegistryNumberContainIQV){
			julLogger.info("Input not correct; Car registry number contained a prohibited character I or Q or V.");
			return false;
		}
		
		// Three zeros are not allowed. 00Letter is allowed, strange perhaps that 00O is allowed...
		boolean carRegistryNumberContainThreeZeros = checkCarRegistryNumberContainThreeZeros(str);
		if (carRegistryNumberContainThreeZeros){
			julLogger.info("Input not correct; Car registry number contained three zeros.");
			return false;
		}
		
		return true;
	}
	
	public boolean checkCarRegistryNumberContainThreeZeros(String str) {
		String lastThreeString = str.substring(3, 6);
		
		return lastThreeString.equals("000");
	}
	
	
	
	public boolean checkCarRegistryNumberContainIQV(String str) {
		
		for (int i = 0; i < 6; i++){
		    if(charIsIQV(str.charAt(i))) {
		    	return true;
		    }
		}
		return false;
	}
	
	public boolean charIsIQV(char c) {
		return (c == 'I' || c == 'Q' || c == 'V');
	}
	
	public boolean checkCarRegistryNumberProhibited(String str) {
		
		String[] notAllowed = {"APA", "ARG", "ASS", "BAJ", "BSS", "CUC", "CUK", "DUM", "ETA", "ETT",
				 "FAG", "FAN", "FEG", "FEL", "FEM", "FES", "FET", "FNL", "FUC", "FUK", "FUL", "GAM",
				 "GAY", "GEJ", "GEY", "GHB", "GUD", "GYN", "HAT", "HBT", "HKH", "HOR", "HOT", "KGB",
				 "KKK", "KUC", "KUF", "KUG", "KUK", "KYK", "LAM", "LAT", "LEM", "LOJ", "LSD", "LUS",
				 "MAD", "MAO", "MEN", "MES", "MLB", "MUS", "NAZ", "NRP", "NSF", "NYP", "OND", "OOO",
				 "ORM", "PAJ", "PKK", "PLO", "PMS", "PUB", "RAP", "RAS", "ROM", "RPS", "RUS", "SEG",
				 "SEX", "SJU", "SOS", "SPY", "SUG", "SUP", "SUR", "TBC", "TOA", "TOK", "TRE", "TYP",
				 "UFO", "USA", "WAM", "WAR", "WWW", "XTC", "XTZ", "XXL", "XXX", "ZEX", "ZOG", "ZPY",
				 "ZUG", "ZUP", "ZOO"};
		
		String firstThreeString = str.substring(0, 3);
		
		return Arrays.asList(notAllowed).contains(firstThreeString);
	}
	
	public boolean isAsciiDigit(char c) {
	    return (c >= '0' && c <= '9');
	}
	
	public boolean isUppercaseAsciiLetter(char c) {
	    return (c >= 'A' && c <= 'Z');
	}
	
	public boolean checkCarRegistryNumberFormat(String str) {
		
		for (int i = 0; i < 3; i++){
		    if(!isUppercaseAsciiLetter(str.charAt(i))) {
		    	return false;
		    }
		}
		
		for (int i = 3; i < 5; i++){
		    if(!isAsciiDigit(str.charAt(i))) {
		    	return false;
		    }
		}
		
		// Since 2019, the last car registry character may be a digit or capital letter.
		boolean lastCharIsAsciiDigit = isAsciiDigit(str.charAt(5));
		boolean lastCharIsUppercaseAscii = isUppercaseAsciiLetter(str.charAt(5));
				
		if (!lastCharIsAsciiDigit && !lastCharIsUppercaseAscii) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkLengthIs6(String str) {
		return str.length() == 6;
	}
}

