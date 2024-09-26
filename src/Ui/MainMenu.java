package Ui;

import Models.*;
import Services.*;
import Models.Enum.Status;
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
    private QuoteServiceImpl quoteService;
    private static final Logger logger = Logger.getLogger(MainMenu.class.getName());

    public MainMenu() {
        this.customerService = new CustomerServiceImpl();
        this.projectService = new ProjectServiceImpl();
        this.materialService = new MaterialServiceImpl();
        this.laborService = new LaborServiceImpl();
        this.quoteService = new QuoteServiceImpl();
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
                    devisManu(prompt);
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
        Customer customer = customerService.getCustomerByName(customerName);
            if (customer != null) {
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

            logger.info("Material added: " + materialName );

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
            String hoursWorkedStr = Validator.validateInput("Entrez le nombre d'heures travaillées : ", InputType.DOUBLE);
            int hoursWorked = Integer.parseInt(hoursWorkedStr);
            String productivityFactorStr = Validator.validateInput("Entrez le facteur de productivité : ", InputType.DOUBLE);
            double productivityFactor = Double.parseDouble(productivityFactorStr);

            Labor labor = new Labor(laborType, vatRate, projectId, hourlyRate, hoursWorked, productivityFactor);
            laborService.addLabor(labor);
            totalCost += labor.getTotalCost();

            logger.info("Labor added: " + laborType );

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
        } else {
            System.out.println("Projet ajouté avec succès avec le coût total : " + totalCost);
        }

        projectService.updateProject(project);
        logger.info("Project updated: " + project.getProjectName());    }

    private void displayProjects() {
        List<Project> projects = projectService.getProjectByStatus();
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

    public void devisManu(Scanner prompt){
        System.out.println("--- Devi Menu ---");
        System.out.println("1. Crée un devis");
        System.out.println("2. Annuler un devis");
        System.out.println("3. Accepter un devis");
        System.out.println("4. Go back");
        int option = prompt.nextInt();
        switch(option){
            case 1:
                generateQuote();
                break;
            case 2:
                denyQuote(prompt);
                break;
            case 3:
                acceptQuote(prompt);
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid option");
        }
    }

    private void generateQuote() {
        System.out.println("--- Génération de Devis ---");

        // Prompt for project name
        String projectName = Validator.validateInput("Entrez le nom du projet : ", InputType.STRING);

        // Retrieve the project
        Project project = projectService.getProjectByName(projectName);
        if (project == null) {
            System.out.println("Projet non trouvé avec le nom : " + projectName);
            return;
        }

        // Get materials and labors for the project
        List<Material> materials = materialService.getMaterialById(project.getId());
        List<Labor> labors = laborService.getLaborById(project.getId());

        // Calculate costs
        double[] materialCosts = materialService.calculateMaterialCosts(materials);
        double[] laborCosts = laborService.calculateLaborCosts(labors);
        double totalCostBeforeMargin = materialCosts[0] + laborCosts[0];
        double profitMargin = project.getProfitMargin();

        double totalCostWithVat = materialCosts[1] + laborCosts[1];

        // Calculate total cost with margin and include VAT
        double totalCostWithMargin = totalCostWithVat +(  totalCostWithVat * (profitMargin / 100));
        System.out.println("totalCostWithMarginBeforeVAT");



        // Retrieve customer details
        Customer customer = customerService.getCustomerByProjectId(project.getCustomerId());

        // Display the formatted result
        System.out.println("--- Résultat du Calcul ---");
        System.out.println("Nom du projet : " + project.getProjectName());
        System.out.println("Client : " + customer.getName());
        System.out.println("Adresse du chantier : " + customer.getAddress());

        System.out.println("\n--- Détail des Coûts ---");

        // Display Material Costs
        System.out.println("1. Matériaux :");
        for (Material material : materials) {
            System.out.printf("- %s : %.2f € (quantité : %.2f, coût unitaire : %.2f €, qualité : %.2f, transport : %.2f €)%n",
                    material.getName(), material.getTotalCost(), material.getQuantity(),
                    material.getUnitCost(), material.getQualityCoefficient(), material.getTransportCost());
        }
        System.out.printf("**Coût total des matériaux avant TVA : %.2f €**%n", materialCosts[0]);
        System.out.printf("**Coût total des matériaux avec TVA : %.2f €**%n", materialCosts[1]);

        // Display Labor Costs
        System.out.println("2. Main-d'œuvre :");
        for (Labor labor : labors) {
            System.out.printf("- %s : %.2f € (taux horaire : %.2f €/h, heures travaillées : %2f h, productivité : %.2f)%n",
                    labor.getName(), labor.getTotalCost(), labor.getHourlyRate(),
                    labor.getWorkHours(), labor.getWorkerProductivity());
        }
        System.out.printf("**Coût total de la main-d'œuvre avant TVA : %.2f €**%n", laborCosts[0]);
        System.out.printf("**Coût total de la main-d'œuvre avec TVA  : %.2f €**%n", laborCosts[1]);

        // Display Total Costs including VAT
        System.out.printf("3. Coût total avant marge : %.2f€%n", totalCostWithVat);
        System.out.println("4. Marge bénéficiaire : " + profitMargin +"%");
        System.out.printf("**Coût total final du projet (avec TVA) : %.2f €**%n", totalCostWithMargin);

        // Prompt for quote details
        String emissionDate = Validator.validateInput("Entrez la date d'émission du devis (format : jj/mm/aaaa) : ", InputType.STRING);
        String validityDate = Validator.validateInput("Entrez la date de validité du devis (format : jj/mm/aaaa) : ", InputType.STRING);
        String saveQuote = Validator.validateInput("Souhaitez-vous enregistrer le devis ? (y/n) : ", InputType.STRING);

        // Save quote if user confirms
        if (saveQuote.equalsIgnoreCase("y")) {
            quoteService.addQuote(project.getId(), emissionDate, validityDate, totalCostWithMargin);
            System.out.println("Devis enregistré avec succès !");
            logger.info("Quote saved for project: " + project.getProjectName());
        } else {
            System.out.println("Devis non enregistré.");
        }
    }

    private void denyQuote(Scanner prompt) {
        System.out.println("--- Recherche de Devis à Rejeter ---");
        String projectName = Validator.validateInput("Entrez le nom du projet pour trouver le devis : ", InputType.STRING);
        Project project = projectService.getProjectByName(projectName);

        if (project  == null) {
            System.out.println("Devis non trouvé pour le projet : " + projectName);
            return;
        }


        System.out.println("Devis trouvé pour le projet : " + projectName);
        prompt.nextLine();
        System.out.println("Voulez-vous vraiment rejeter ce devis ? (y/n) : ");
        String response = prompt.nextLine();
        if (response.equalsIgnoreCase("y")) {
            quoteService.updateQuoteStatus(project.getId(), false);
            project.setProjectStatus(Status.CANCELLED);
            projectService.updateProject(project);
            System.out.println("Le devis a été rejeté avec succès.");
        } else {
            System.out.println("Action annulée. Le devis n'a pas été rejeté.");
        }
    }

    private void acceptQuote(Scanner prompt) {
        System.out.println("--- Recherche de Devis à Accepter ---");
        String projectName = Validator.validateInput("Entrez le nom du projet pour trouver le devis : ", InputType.STRING);
        Project project = projectService.getProjectByName(projectName);

        if (project  == null) {
            System.out.println("Devis non trouvé pour le projet : " + projectName);
            return;
        }


        System.out.println("Devis trouvé pour le projet : " + projectName);
        prompt.nextLine();
        System.out.println("Voulez-vous vraiment accepter ce devis ? (y/n) : ");
        String response = prompt.nextLine();
        if (response.equalsIgnoreCase("y")) {
            quoteService.updateQuoteStatus(project.getId(), true);
            project.setProjectStatus(Status.DONE);
            projectService.updateProject(project);
            System.out.println("Le devis a été rejeté avec succès.");
        } else {
            System.out.println("Action annulée. Le devis n'a pas été rejeté.");
        }
    }



}
