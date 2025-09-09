package Ass1;
import java.util.Scanner;

public class Menu {
    private Scanner input;

    public Menu(Scanner input) {
        this.input = input;
    }

    public int displayMainMenu() {//main menu display all department
        System.out.println("\n===== Hospital Management Menu =====");
        System.out.println("1. Doctors");
        System.out.println("2. Patients");
        System.out.println("3. Staff");
        System.out.println("4. Medicine");
        System.out.println("5. Laboratories");
        System.out.println("6. Facilities");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");

        int choice = Integer.parseInt(input.nextLine());//make choice is integer;
        while(choice < 1 || choice > 7) {//if the choice is not between 1 to 7
        	System.out.println("Invalid number!");
        	System.out.print("Enter your choice: ");
        	choice = Integer.parseInt(input.nextLine());
        }
       
        return choice;
    }

}
