package Ass1;
import java.util.Scanner;

public class DoctorRegistrationHandler {
    private Scanner input;
	private Doctor[] doctor;
	private int count;

    public DoctorRegistrationHandler(Scanner input, Doctor[] doctor, int count) {
        this.input = input;
        this.doctor = doctor;
        this.count = count;
    }
    
    public Doctor registerNewDoctor() {
        System.out.println("Register New Doctor: ");
        String id;
        while (true) {
            id = prompt("Enter Doctor ID: ");
            if (!id.matches("^D\\d{3}$")) {
                System.out.println("Invalid format. Use format D###, e.g., D001.");
                continue;
            }
            if (isDuplicate(id)) {
                System.out.println("Duplicate ID. Please enter a different one." + "\n");
            } else {
                break;
            }
        }
       String name = prompt("Enter name: ");
       String specialist = prompt("Enter Specialist: ");
       String worktime = prompt("Enter worktime: ");
       String qualification = prompt("Enter qualification: ");
       int salary = promptInt("Enter salary: ");
       System.out.println();
       
       return new Doctor(id, name, specialist, worktime, qualification, salary);
    }
    
    private String prompt(String message) {
    	String inputStr;
    	
    	do {
    		System.out.print(message);
            inputStr = input.nextLine();
            if (inputStr.trim().isEmpty()) System.out.println("Input cannot be empty.");
    	} while(inputStr.trim().isEmpty());
    	
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
    
    private boolean isDuplicate(String id) {
        for (int i = 0; i < count; i++) {
            if (doctor[i].getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
    
}