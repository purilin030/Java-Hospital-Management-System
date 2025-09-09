package Ass1;
import java.util.Scanner;
public class SubMenu {
	private Scanner input;

    public SubMenu(Scanner input) {
        this.input = input;
    }
    
	public int displaySubMenu(String category) {// submenu is to display and select the function
		
		System.out.println("\n---" + category + "Menu ---");
		System.out.println("1. Add New");
	    System.out.println("2. Show List");
	    System.out.println("3. Searching");
	    System.out.println("4. Deleting");
	    System.out.print("Enter your choice: ");
	    int choice = Integer.parseInt(input.nextLine());
	    System.out.println();
	        
	    while(choice < 1 || choice > 4) {//make sure between 1 - 2
	       System.out.println("Invalid number!");
	       System.out.print("Enter your choice: ");
	       choice = Integer.parseInt(input.nextLine());
	       System.out.println();
	    }
	    return choice;
	}
}
