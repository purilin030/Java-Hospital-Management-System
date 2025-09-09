package Ass1;
import java.util.Scanner;

public class PatientRegistrationHandler {
    private Scanner input;
    private Patient[] patients;
    private int count;

    public PatientRegistrationHandler(Scanner input, Patient[] patients, int count) {
        this.input = input;
        this.patients = patients;
        this.count = count;
    }

    public Patient registerNewPatient() {
        System.out.println("Register New Patient:");
        String id;
        while (true) {
            id = prompt("Enter Patient ID: ");
            if (!id.matches("^P\\d{3}$")) {
                System.out.println("Invalid format. Use format D###, e.g., P001.");
                continue;
             }
            if (isDuplicate(id)) {
                System.out.println("Duplicate ID. Please enter a different one.");
            } else {
                break;
            }
        }
        String name = prompt("Enter Name: ");
        String disease = prompt("Enter Disease: ");
        String sex = prompt("Enter Sex: ");
        String admitStatus = prompt("Enter Admit Status: ");
        int age = promptInt("Enter Age: ");
        System.out.println();

        return new Patient(id, name, disease, sex, admitStatus, age);
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
            if (patients[i].getId().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }
}
