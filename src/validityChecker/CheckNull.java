package validityChecker;

import java.util.logging.Logger;

public class CheckNull implements ValidityChecker{

	@Override
	public boolean validityCheck(String str) {
		
		Logger julLogger = java.util.logging.Logger.getLogger(CheckNull.class.getName());
		
		if (str != null) {
			julLogger.info("Input not correct; String was not null.");
			
			return false;
		}
		
		return true;
	}
}
