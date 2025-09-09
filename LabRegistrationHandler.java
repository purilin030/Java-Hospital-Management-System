package Ass1;
import java.util.Scanner;

public class LabRegistrationHandler {
	 private Scanner input;
	 private Lab[] labs;
	 private int count;

	    public LabRegistrationHandler(Scanner input, Lab labs[], int count) {
	        this.input = input;
	        this.labs = labs;
	        this.count = count;
	    }

	    public Lab registerNewLab() {
	        System.out.println("Register New Lab:");
	        String labName;
	        while (true) {
	            labName = prompt("Enter Name: ");
	            if (isDuplicate(labName)) {
	                System.out.println("Duplicate Name. Please enter a different one.");
	            } else {
	                break;
	            }
	        }
	        int cost = promptInt("Enter Cost: ");
	        System.out.println();

	        return new Lab(labName, cost);
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

	    private int promptInt(String message) {
	        int num = -1;
	        while (true) {
	            try {
	                System.out.print(message);
	                num = Integer.parseInt(input.nextLine());
	                break;
	            } catch (NumberFormatException e) {
	                System.out.println("Invalid number. Try again.");
	            }
	        }
	        return num;
	    }
	    
	    private boolean isDuplicate(String name) {
	        for (int i = 0; i < count; i++) {
	            if (labs[i].getLab().equalsIgnoreCase(name)) {
	                return true;
	            }
	        }
	        return false;
	    }
	}