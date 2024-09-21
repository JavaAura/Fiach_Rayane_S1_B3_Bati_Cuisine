package Ui;

import Models.Customer;
import Services.CustomerServiceImpl;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private CustomerServiceImpl customerService;

    public MainMenu() {
        this.customerService = new CustomerServiceImpl(); // Initialize customerService
    }

    public void mainMenu() {
        Scanner prompt = new Scanner(System.in);
        while (true) {
            System.out.println("=== Main Menu ===");
            System.out.println("1. Create a new project");
            System.out.println("2. Display existing projects");
            System.out.println("3. Calculate project cost");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int option = prompt.nextInt();
            prompt.nextLine();

            switch (option) {
                case 1:
                    createProject(prompt);
                    break;
                case 2:
                    // Display existing projects
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

    private void createProject(Scanner prompt) {
        System.out.println("--- Client Search ---");
        System.out.println("Do you want to search for an existing client or add a new one?");
        System.out.println("1. Search for an existing client");
        System.out.println("2. Add a new client");
        System.out.print("Choose an option: ");
        int option = prompt.nextInt();
        prompt.nextLine();

        switch (option) {
            case 1:
                searchExistingClient(prompt);
                break;
            case 2:
                addNewClient(prompt);
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;
        }
    }

    private void searchExistingClient(Scanner prompt) {
        System.out.println("--- Search for Existing Client ---");
        System.out.print("Enter the client's name: ");
        String clientName = prompt.nextLine();
        List<Customer> customers = customerService.getAllCustomers();
        Customer foundCustomer = null;

        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(clientName)) {
                foundCustomer = customer;
                break;
            }
        }

        if (foundCustomer != null) {
            System.out.println("Client found!");
            System.out.println("Name: " + foundCustomer.getName());
            System.out.println("Address: " + foundCustomer.getAddress());
            System.out.println("Phone number: " + foundCustomer.getPhone());
            System.out.print("Do you want to continue with this client? (y/n): ");
            String continueWithClient = prompt.nextLine();
            if (continueWithClient.equalsIgnoreCase("y")) {
                // Continue with the found customer
            } else {
                // Handle the case where the user does not want to continue with the found customer
            }
        } else {
            System.out.println("Client not found.");
        }
    }

    private void addNewClient(Scanner prompt) {
        System.out.println("--- Add a New Client ---");
        System.out.print("Enter the client's name: ");
        String name = prompt.nextLine();
        System.out.print("Enter the client's address: ");
        String address = prompt.nextLine();
        System.out.print("Enter the client's phone number: ");
        String phone = prompt.nextLine();
        System.out.print("Is the client a professional? (true/false): ");
        String isProfessionalInput = prompt.nextLine();
        boolean isProfessional = Boolean.parseBoolean(isProfessionalInput);

        Customer newCustomer = new Customer(name, address, phone, isProfessional);
        newCustomer.setName(name);
        newCustomer.setAddress(address);
        newCustomer.setPhone(phone);
        newCustomer.setProfessional(isProfessional);

        customerService.addCustomer(newCustomer);
        System.out.println("New client added successfully!");
    }
}