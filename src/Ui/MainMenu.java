package Ui;

import Models.Customer;
import Models.Project;
import Models.Labor;
import Models.Material;
import Services.CustomerServiceImpl;
import Services.LaborServiceImpl;
import Services.MaterialServiceImpl;
import Services.ProjectServiceImpl;
import Enum.Status;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainMenu {
    private CustomerServiceImpl customerService;
    private ProjectServiceImpl projectService;
    private MaterialServiceImpl materialService;
    private LaborServiceImpl laborService;
    private static final Logger logger = Logger.getLogger(MainMenu.class.getName());

    public MainMenu() {
        this.customerService = new CustomerServiceImpl();
        this.projectService = new ProjectServiceImpl();
        this.materialService = new MaterialServiceImpl();
        this.laborService = new LaborServiceImpl();
    }

    public void mainMenu() {
        Scanner prompt = new Scanner(System.in);
        while (true) {
            System.out.println("=== Menu Principal ===");
            System.out.println("1. Créer un nouveau projet");
            System.out.println("2. Afficher les projets existants");
            System.out.println("3. Calculer le coût d'un projet");
            System.out.println("4. Quitter");
            System.out.print("Choisissez une option : ");

            int option = prompt.nextInt();
            prompt.nextLine();

            switch (option) {
                case 1:
                    createNewProject(prompt);
                    break;
                case 2:
                    displayProjects();
                    break;
                case 3:
                    System.out.println("Fonctionnalité non implémentée.");
                    break;
                case 4:
                    logger.info("Application terminated by user.");
                    System.out.println("Au revoir!");
                    return;
                default:
                    logger.warning("Invalid option selected: " + option);
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
    }

    private void createNewProject(Scanner prompt) {
        System.out.println("--- Recherche de client ---");
        System.out.println("Souhaitez-vous chercher un client existant ou en ajouter un nouveau ?");
        System.out.println("1. Chercher un client existant");
        System.out.println("2. Ajouter un nouveau client");
        System.out.print("Choisissez une option : ");

        int choice = prompt.nextInt();
        prompt.nextLine();
        Customer selectedCustomer = null;

        if (choice == 1) {
            selectedCustomer = searchExistingCustomer(prompt);
        } else if (choice == 2) {
            selectedCustomer = addNewCustomer(prompt);
        } else {
            logger.warning("Invalid option for customer selection: " + choice);
            System.out.println("Option invalide.");
            return;
        }

        if (selectedCustomer != null) {
            System.out.println("--- Création d'un Nouveau Projet ---");
            System.out.print("Entrez le nom du projet : ");
            String projectName = prompt.nextLine();

            Project project = new Project(0, projectName, 0, 0, Status.ONGOING, selectedCustomer.getId());
            projectService.addProject(project);
            addMaterialsAndLabor(prompt, project);

            logger.info("New project created: " + projectName + " for customer: " + selectedCustomer.getName());
        }
    }

    private Customer searchExistingCustomer(Scanner prompt) {
        System.out.print("Entrez le nom du client : ");
        String customerName = prompt.nextLine();
        List<Customer> customers = customerService.getAllCustomers();

        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                System.out.println("Client trouvé !");
                System.out.println("Nom : " + customer.getName());
                System.out.println("Adresse : " + customer.getAddress());
                System.out.println("Numéro de téléphone : " + customer.getPhone());
                System.out.print("Souhaitez-vous continuer avec ce client ? (y/n) : ");
                if (prompt.nextLine().equalsIgnoreCase("y")) {
                    logger.info("Existing customer selected: " + customer.getName());
                    return customer;
                }
            }
        }
        logger.warning("Customer not found: " + customerName);
        System.out.println("Client non trouvé.");
        return null;
    }

    private Customer addNewCustomer(Scanner prompt) {
        System.out.print("Entrez le nom du client : ");
        String name = prompt.nextLine();
        System.out.print("Entrez l'adresse du client : ");
        String address = prompt.nextLine();
        System.out.print("Entrez le numéro de téléphone du client : ");
        String phone = prompt.nextLine();
        System.out.print("Le client est-il professionnel ? (true/false) : ");
        boolean isProfessional = prompt.nextBoolean();
        prompt.nextLine();

        Customer newCustomer = new Customer(0, name, address, phone, isProfessional);
        customerService.addCustomer(newCustomer);
        logger.info("New customer added: " + name);
        System.out.println("Client ajouté avec succès !");
        return newCustomer;
    }

    private void addMaterialsAndLabor(Scanner prompt, Project project) {
        double totalCost = 0.0;

        int projectId = project.getId();


        while (true) {
            System.out.println("--- Ajout des matériaux ---");
            System.out.print("Entrez le nom du matériau : ");
            String materialName = prompt.nextLine();
            System.out.println("Entrez le taux de TVA du matériau : ");
            double vatRate = prompt.nextDouble();
            System.out.print("Entrez la quantité de ce matériau : ");
            double quantity = prompt.nextDouble();
            System.out.print("Entrez le coût unitaire de ce matériau : ");
            double unitCost = prompt.nextDouble();
            System.out.print("Entrez le coût de transport de ce matériau : ");
            double transportCost = prompt.nextDouble();
            System.out.print("Entrez le coefficient de qualité du matériau : ");
            double qualityCoefficient = prompt.nextDouble();
            prompt.nextLine();

            Material material = new Material(materialName, vatRate, projectId, quantity, unitCost, transportCost, qualityCoefficient);
            materialService.addMaterial(material);
            totalCost += material.getTotalCost();

            logger.info("Material added: " + materialName + " | Total cost: " + totalCost);

            System.out.print("Voulez-vous ajouter un autre matériau ? (y/n) : ");
            if (!prompt.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }

        while (true) {
            System.out.println("--- Ajout de la main-d'œuvre ---");
            System.out.print("Entrez le type de main-d'œuvre : ");
            String laborType = prompt.nextLine();
            System.out.println("Entrez le taux de TVA du matériau : ");
            double vatRate = prompt.nextDouble();
            System.out.print("Entrez le taux horaire de cette main-d'œuvre : ");
            double hourlyRate = prompt.nextDouble();
            System.out.print("Entrez le nombre d'heures travaillées : ");
            int hoursWorked = prompt.nextInt();
            System.out.print("Entrez le facteur de productivité : ");
            double productivityFactor = prompt.nextDouble();
            prompt.nextLine();

            Labor labor = new Labor(laborType, vatRate, projectId, hourlyRate, hoursWorked, productivityFactor);
            laborService.addLabor(labor);
            totalCost += labor.getTotalCost();

            logger.info("Labor added: " + laborType + " | Total cost: " + totalCost);

            System.out.print("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ");
            if (!prompt.nextLine().equalsIgnoreCase("y")) {
                break;
            }
        }

        project.setTotalCost(totalCost);
        projectService.addProject(project);

        System.out.println("Projet ajouté avec succès avec le coût total : " + totalCost);
    }

    private void displayProjects() {
        List<Project> projects = projectService.getAllProjects();
        if (projects.isEmpty()) {
            System.out.println("Aucun projet trouvé.");
            return;
        }
        System.out.println("--- Projets existants ---");
        for (Project project : projects) {
            System.out.println(project);
        }
        logger.info("Displayed all projects.");
    }
}
