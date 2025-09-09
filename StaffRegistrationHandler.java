package Ass1;
import java.util.Scanner;

public class StaffRegistrationHandler {
	private Scanner input;
    private Staff[] staff;
    private int count;

    public StaffRegistrationHandler(Scanner input, Staff[] staff, int count) {
        this.input = input;
        this.staff = staff;
        this.count = count;
    }

    public Staff registerNewStaff() {
        System.out.println("Register New Staff:");
        String id;
        while (true) {
            id = prompt("Enter Staff ID: ");
            if (!id.matches("^S\\d{3}$")) {//formatting "S001"
                System.out.println("Invalid format. Use format D###, e.g., S001.");
                continue;
             }
            if (isDuplicate(id)) {
                System.out.println("Duplicate ID. Please enter a different one.");
            } else {
                break;
            }
        }
        String name = prompt("Enter Name: ");
        String designation = prompt("Enter Designation: ");
        String sex = prompt("Enter Sex: ");
        int salary = promptInt("Enter Salary: ");
        System.out.println();

        return new Staff(id, name, designation, sex, salary);
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

    private boolean isDuplicate(String id) {
        for (int i = 0; i < count; i++) {
            if (staff[i].getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}


