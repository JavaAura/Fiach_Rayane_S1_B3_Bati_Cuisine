package Ui;

import java.util.Scanner;

public class MainMenu {
    public void mainMenu(){
        while(true){
            System.out.println("=== Main Menu ===");
            System.out.println("1. Create new project");
            System.out.println("2. View existent projects");
            System.out.println("3. Calculate project cost");
            System.out.println("4. Exit");

            Scanner prompt = new Scanner(System.in);
            int option = prompt.nextInt();
            prompt.nextLine();

            
            switch (option){
                case 1:
                    // Create new project
                    break;
                case 2:
                    // View existent projects
                    break;
                case 3:
                    // Calculate project cost
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
}
