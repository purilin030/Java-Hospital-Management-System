package Ass1;
import java.util.Scanner;

public class MedicineRegistrationHandler {
	private Scanner input;
    private Medicine[] medicines;
    private int count;

    public MedicineRegistrationHandler(Scanner input, Medicine[] medicines, int count) {
        this.input = input;
        this.medicines = medicines;
        this.count = count;
    }

    public Medicine registerNewMedicine() {
        System.out.println("Register New Medicine:");
        String name;
        while (true) {
            name = prompt("Enter Name: ");
            if (isDuplicate(name)) {
                System.out.println("Duplicate Name. Please enter a different one.");
            } else {
                break;
            }
        }
        String manufacturer = prompt("Enter Manufacturer: ");
        String expiryDate = prompt("Enter Expiry Date (YYYY-MM-DD): ");
        int cost = promptInt("Enter Cost: ");
        int countValue = promptInt("Enter Quantity: ");
        System.out.println();

        return new Medicine(name, manufacturer, expiryDate, cost, countValue);
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
            if (medicines[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
