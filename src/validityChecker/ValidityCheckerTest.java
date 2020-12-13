package validityChecker;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ValidityCheckerTest {
	
	ValidityChecker checkNull = new CheckNull();
	ValidityChecker checkPersonalNumber = new CheckPersonalNumber();
	ValidityChecker checkCarRegistryNumber = new CheckCarRegistryNumber();
	
	// ----- checkNull -----
	@Test
	void testCheckNull1() {
		assertTrue(checkNull.validityCheck(null));
	}
	
	@Test
	void testCheckNull2() {
		assertFalse(checkNull.validityCheck("hello"));
	}

	// ----- checkPersonalNumber -----
	
	// true
	
	@Test
	void testCheckPersonalNumberCorrect() {
		assertTrue(checkPersonalNumber.validityCheck("198804258013"));
	}
	
	@Test
	void testCheckPersonalNumberCorrect3() {
		
		ArrayList<Integer> myCheckerArray = new ArrayList<Integer>();
		myCheckerArray.add(2);
		myCheckerArray.add(4);
	}
	
	@Test
	void testCheckPersonalNumberValidLeapYear() {
		String myString = "198804258013";
		ArrayList<ValidityChecker> myCheckerArray = new ArrayList<ValidityChecker>();
//		myCheckerArray.add(checkNull);
		myCheckerArray.add(checkPersonalNumber);
		
		for(int i = 0; i < myCheckerArray.size(); i++) {
			assertTrue(myCheckerArray.get(i).validityCheck(myString));
		}
		assertFalse(checkNull.validityCheck(myString));
		assertTrue(checkPersonalNumber.validityCheck(myString));
	}
	
	// false
	
	@Test
	void testCheckPersonalNumberWrong() {
		assertFalse(checkPersonalNumber.validityCheck("198705078018"));
	}
	
	@Test
	void testCheckPersonalNumberWrongTooLong() {
		assertFalse(checkPersonalNumber.validityCheck("2005070715182"));
	}
	
	@Test
	void testCheckPersonalNumberNullInput() {
		assertFalse(checkPersonalNumber.validityCheck(null));
	}
	
	@Test
	void testCheckPersonalNumberTooLargeYear() {
		assertFalse(checkPersonalNumber.validityCheck("219602078017"));
	}
	
	@Test
	void testCheckPersonalNumberTooLargeMonth() {
		assertFalse(checkPersonalNumber.validityCheck("198513078014"));
	}
	
	@Test
	void testCheckPersonalNumberTooLargeDay() {
		assertFalse(checkPersonalNumber.validityCheck("199402308018"));
	}
	
	@Test
	void testCheckPersonalNumberTooSmallYear() {
		assertFalse(checkPersonalNumber.validityCheck("159508073015"));
	}
	
	@Test
	void testCheckPersonalNumberTooSmallMonth() {
		assertFalse(checkPersonalNumber.validityCheck("197500072019"));
	}
	
	@Test
	void testCheckPersonalNumberTooSmallDay() {
		assertFalse(checkPersonalNumber.validityCheck("198502003014"));
	}
	
	@Test
	void testCheckPersonalNumberLeapYear() {
		assertFalse(checkPersonalNumber.validityCheck("190002291110"));
	}
	
	// ----- checkCarRegistryNumber -----
	
	// true

	@Test
	void testCarRegistryNumberCorrect() {
		assertTrue(checkCarRegistryNumber.validityCheck("ABT542"));
	}
	
	@Test
	void testCarRegistryNumberLastCharLetter() {
		assertTrue(checkCarRegistryNumber.validityCheck("MLD54H"));
	}
	
	// false
	
	@Test
	void testCarRegistryNumberThreeZeros() {
		assertFalse(checkCarRegistryNumber.validityCheck("ABT000"));
	}
	
	@Test
	void testCarRegistryNumberWrong() {
		assertFalse(checkCarRegistryNumber.validityCheck("A6H999"));
	}
	
	@Test
	void testCarRegistryNumberIncorrectLength() {
		assertFalse(checkCarRegistryNumber.validityCheck("AJH9599"));
	}
	
	@Test
	void testCarRegistryNumberIncorrectLength2() {
		assertFalse(checkCarRegistryNumber.validityCheck("AJH99"));
	}
	
	@Test
	void testCarRegistryNumberLowercase() {
		assertFalse(checkCarRegistryNumber.validityCheck("ApH999"));
	}
	
	@Test
	void testCarRegistryNumberProhibited() {
		assertFalse(checkCarRegistryNumber.validityCheck("WWW999"));
	}
	
	@Test
	void testCarRegistryNumberProhibited2() {
		assertFalse(checkCarRegistryNumber.validityCheck("MLB999"));
	}
	
}

