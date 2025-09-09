package Ass1;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
    	
    	// Initialize data
        Initialization init = new Initialization();

        // Create Scanner for user input
        Scanner input = new Scanner(System.in);

        // Create Menu and SubMenu objects
        Menu menu = new Menu(input);
        SubMenu submenu = new SubMenu(input);

        // Create Controller and pass dependencies
        Controller controller = new Controller(input, init);

        // Run the system
        controller.run(menu, submenu);

        // Close Scanner when done
        input.close();

    }

}
