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
import Utils.Validator;
import Utils.Enums.InputType;

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

            String option = Validator.validateInput("Choisissez une option : ", InputType.OPTION, 1, 4);

            switch (Integer.parseInt(option)) {
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
        String choice = Validator.validateInput("Choisissez une option : ", InputType.OPTION, 1, 2);

        Customer selectedCustomer = null;

        if (choice.equals("1")) {
            selectedCustomer = searchExistingCustomer(prompt);
        } else if (choice.equals("2")) {
            selectedCustomer = addNewCustomer(prompt);
        } else {
            logger.warning("Invalid option for customer selection: " + choice);
            System.out.println("Option invalide.");
            return;
        }

        if (selectedCustomer != null) {
            System.out.println("--- Création d'un Nouveau Projet ---");
            String projectName = Validator.validateInput("Entrez le nom du projet : ", InputType.STRING);
            Project project = new Project(projectName, 0, 0, Status.ONGOING, selectedCustomer.getId());
            projectService.addProject(project);
            addMaterialsAndLabor(prompt, project);

            logger.info("New project created: " + projectName + " for customer: " + selectedCustomer.getName());
        }
    }

    private Customer searchExistingCustomer(Scanner prompt) {
        String customerName = Validator.validateInput("Entrez le nom du client : ", InputType.STRING);
        List<Customer> customers = customerService.getAllCustomers();

        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(customerName)) {
                System.out.println("Client trouvé !");
                System.out.println("Nom : " + customer.getName());
                System.out.println("Adresse : " + customer.getAddress());
                System.out.println("Numéro de téléphone : " + customer.getPhone());
                String confirm = Validator.validateInput("Souhaitez-vous continuer avec ce client ? (y/n) : ", InputType.STRING);
                if (confirm.equalsIgnoreCase("y")) {
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
        String name = Validator.validateInput("Entrez le nom du client : ", InputType.STRING);
        String address = Validator.validateInput("Entrez l'adresse du client : ", InputType.STRING);
        String phone = Validator.validateInput("Entrez le numéro de téléphone du client : ", InputType.STRING);
        String isProfessionalStr = Validator.validateInput("Le client est-il professionnel ? (true/false) : ", InputType.STRING);
        boolean isProfessional = Boolean.parseBoolean(isProfessionalStr);

        Customer newCustomer = new Customer(name, address, phone, isProfessional);
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
            String materialName = Validator.validateInput("Entrez le nom du matériau : ", InputType.STRING);
            String vatRateStr = Validator.validateInput("Entrez le taux de TVA du matériau : ", InputType.DOUBLE);
            double vatRate = Double.parseDouble(vatRateStr);
            String quantityStr = Validator.validateInput("Entrez la quantité de ce matériau : ", InputType.DOUBLE);
            double quantity = Double.parseDouble(quantityStr);
            String unitCostStr = Validator.validateInput("Entrez le coût unitaire de ce matériau : ", InputType.DOUBLE);
            double unitCost = Double.parseDouble(unitCostStr);
            String transportCostStr = Validator.validateInput("Entrez le coût de transport de ce matériau : ", InputType.DOUBLE);
            double transportCost = Double.parseDouble(transportCostStr);
            String qualityCoefficientStr = Validator.validateInput("Entrez le coefficient de qualité du matériau : ", InputType.DOUBLE);
            double qualityCoefficient = Double.parseDouble(qualityCoefficientStr);

            Material material = new Material(materialName, vatRate, projectId, quantity, unitCost, transportCost, qualityCoefficient);
            materialService.addMaterial(material);
            totalCost += material.getTotalCost();

            logger.info("Material added: " + materialName + " | Total cost: " + totalCost);

            String addMore = Validator.validateInput("Voulez-vous ajouter un autre matériau ? (y/n) : ", InputType.STRING);
            if (!addMore.equalsIgnoreCase("y")) {
                break;
            }
        }

        while (true) {
            System.out.println("--- Ajout de la main-d'œuvre ---");
            String laborType = Validator.validateInput("Entrez le type de main-d'œuvre : ", InputType.STRING);
            String vatRateStr = Validator.validateInput("Entrez le taux de TVA du matériau : ", InputType.DOUBLE);
            double vatRate = Double.parseDouble(vatRateStr);
            String hourlyRateStr = Validator.validateInput("Entrez le taux horaire de cette main-d'œuvre : ", InputType.DOUBLE);
            double hourlyRate = Double.parseDouble(hourlyRateStr);
            String hoursWorkedStr = Validator.validateInput("Entrez le nombre d'heures travaillées : ", InputType.INTEGER);
            int hoursWorked = Integer.parseInt(hoursWorkedStr);
            String productivityFactorStr = Validator.validateInput("Entrez le facteur de productivité : ", InputType.DOUBLE);
            double productivityFactor = Double.parseDouble(productivityFactorStr);

            Labor labor = new Labor(laborType, vatRate, projectId, hourlyRate, hoursWorked, productivityFactor);
            laborService.addLabor(labor);
            totalCost += labor.getTotalCost();

            logger.info("Labor added: " + laborType + " | Total cost: " + totalCost);

            String addMore = Validator.validateInput("Voulez-vous ajouter un autre type de main-d'œuvre ? (y/n) : ", InputType.STRING);
            if (!addMore.equalsIgnoreCase("y")) {
                break;
            }
        }

        project.setTotalCost(totalCost);

        String marginStr = Validator.validateInput("Souhaitez-vous ajouter une marge bénéficiaire ? (y/n) : ", InputType.STRING);
        if (marginStr.equalsIgnoreCase("y")) {
            String profitMarginStr = Validator.validateInput("Entrez le pourcentage de marge bénéficiaire : ", InputType.DOUBLE);
            double profitMargin = Double.parseDouble(profitMarginStr);
            project.setProfitMargin(profitMargin);

            double finalCost = totalCost + (totalCost * (profitMargin / 100));
            project.setTotalCost(finalCost);
            System.out.println("Coût total final avec marge bénéficiaire : " + finalCost);
        } else {
            System.out.println("Projet ajouté avec succès avec le coût total : " + totalCost);
        }

        projectService.updateProject(project);
        logger.info("Project updated: " + project.getProjectName());    }

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
