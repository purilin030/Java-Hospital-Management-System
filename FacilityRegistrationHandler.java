package Ass1;
import java.util.Scanner;
 
public class FacilityRegistrationHandler {
	private Scanner input;
	private facility[] facility;
	private int count;

    public FacilityRegistrationHandler(Scanner input, facility[] facility, int count) {
        this.input = input;
        this.facility = facility;
        this.count = count;
    }

    public facility registerNewFacility() {
        System.out.println("Register New Facility:");
        String name = prompt("Enter Facility Name: ");
        if(isDuplicate(name)) {
        	System.out.println("Duplicate Name. Registration cancelled");
        	return null;
        }
        System.out.println();
        return new facility(name);
    }

    private String prompt(String message) {
        String inputStr;
        do {
            System.out.print(message);
            inputStr = input.nextLine();
            if (inputStr.trim().isEmpty()) System.out.println("Input cannot be empty.");
        } while (inputStr.trim().isEmpty());
        return inputStr;
    }
    
    private boolean isDuplicate(String name) {
        for (int i = 0; i < count; i++) {
            if (facility[i].getFacility().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
