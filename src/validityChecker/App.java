package validityChecker;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		for(int i = 0; i < 2; i++) {
			Scanner myObj = new Scanner(System.in);  // Create a Scanner object
		    System.out.println("\n\n\nWelcome to validity checker! \n");
		    System.out.println("Type");
		    System.out.println("1 for Null checker");
		    System.out.println("2 for Personal number checker");
		    System.out.println("3 for Car registry number checker");
		    System.out.println("\nPlease enter number:");
		    
		    String checkerChoice = myObj.nextLine();  // Read user input
		    System.out.println("ValidityChecker choice: " + checkerChoice);  // Output user input
		    int checkerChoiceInt = Integer.parseInt(checkerChoice); 
		    
		    switch (checkerChoiceInt) {
		      case 1:
		        System.out.println("Null Checker was choosen");
				ValidityChecker checkNull = new CheckNull();
				String inputString1 = getInputString(myObj);
				System.out.println("The input is " + checkNull.validityCheck(inputString1));	
		        break;
		      case 2:
		        System.out.println("Personal Number Checker was choosen");
				ValidityChecker checkPersonalNumber = new CheckPersonalNumber();
				String inputString2 = getInputString(myObj);
				System.out.println("The input is " + checkPersonalNumber.validityCheck(inputString2));
		        break;
		      case 3:
		        System.out.println("Car registry number Checker was choosen");
				ValidityChecker checkCarRegistryNumber = new CheckCarRegistryNumber();
				String inputString3 = getInputString(myObj);
				System.out.println("The input is " + checkCarRegistryNumber.validityCheck(inputString3));						
		        break;
		    }
		}
	}
	
	public static String getInputString(Scanner myObj) {
		System.out.println("Please enter input");
		String inputString = myObj.nextLine(); 
		return inputString;
	}
}
