package pack;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class main {
    private static List<Client> clients = new ArrayList<>();
    private static List<Agent> agents = new ArrayList<>();
    private static List<RDV> rdvs = new ArrayList<>();
    private static List<Propriete> proprietes = new ArrayList<>();
    private static List<Transaction> transactions = new ArrayList<>();

    private static ServicePropriete servicePropriete = new ServicePropriete(proprietes);
    private static ServiceClient serviceClient = new ServiceClient(clients);

    public static void main(String[] args) {
    	loadData(); // Load existing data from file
        Scanner scanner = new Scanner(System.in);
        boolean continueInput = true;
        while (continueInput) {
            System.out.print("Do you want to add, modify, delete, or display (afficher) a Client, Agent, RDV, Propriete, or Transaction? (add/modify/delete/afficher): ");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "add":
                    handleAdd(scanner);
                    break;
                case "modify":
                    handleModify(scanner);
                    break;
                case "delete":
                    handleDelete(scanner);
                    break;
                case "afficher":
                    handleAfficher(scanner);
                    break;
                default:
                    System.out.println("Invalid action. Please enter 'add', 'modify', 'delete', or 'afficher'.");
                    break;
            }

            System.out.print("Do you want to perform another action? (yes/no): ");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("yes")) {
                continueInput = false;
            }
        }
        scanner.close();
        
        saveData(); // Save data to file before exiting
    }

    private static void handleAdd(Scanner scanner) {
        System.out.print("Do you want to add a Client, an Agent, an RDV, a Propriete, or a Transaction? (client/agent/rdv/propriete/transaction): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "client":
                Client client = Client.createClientFromInput();
                clients.add(client);
                break;
            case "agent":
                Agent agent = Agent.createAgentFromInput();
                agents.add(agent);
                break;
            case "rdv":
                RDV rdv = RDV.createRDVFromInput();
                rdvs.add(rdv);
                break;
            case "propriete":
                System.out.print("Is it an Appartement or a Maison? (appartement/maison): ");
                String typePropriete = scanner.nextLine().trim().toLowerCase();
                if (typePropriete.equals("appartement")) {
                    Appartement appartement = Appartement.createAppartementFromInput();
                    proprietes.add(appartement);
                } else if (typePropriete.equals("maison")) {
                    Maison maison = Maison.createMaisonFromInput();
                    proprietes.add(maison);
                } else {
                    System.out.println("Invalid choice. Please enter 'appartement' or 'maison'.");
                }
                break;
            case "transaction":
                Transaction transaction = Transaction.createTransactionFromInput(servicePropriete, serviceClient);
                transactions.add(transaction);
                break;
            default:
                System.out.println("Invalid choice. Please enter 'client', 'agent', 'rdv', 'propriete', or 'transaction'.");
                break;
        }
    }

    private static void handleModify(Scanner scanner) {
        System.out.print("Do you want to modify a Client, an Agent, an RDV, a Propriete, or a Transaction? (client/agent/rdv/propriete/transaction): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "client":
                modifyClient(scanner);
                break;
            case "agent":
                modifyAgent(scanner);
                break;
            case "rdv":
                modifyRDV(scanner);
                break;
            case "propriete":
                modifyPropriete(scanner);
                break;
            case "transaction":
                modifyTransaction(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter 'client', 'agent', 'rdv', 'propriete', or 'transaction'.");
                break;
        }
    }

    private static void handleDelete(Scanner scanner) {
        System.out.print("Do you want to delete a Client, an Agent, an RDV, a Propriete, or a Transaction? (client/agent/rdv/propriete/transaction): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "client":
                deleteClient(scanner);
                break;
            case "agent":
                deleteAgent(scanner);
                break;
            case "rdv":
                deleteRDV(scanner);
                break;
            case "propriete":
                deletePropriete(scanner);
                break;
            case "transaction":
                deleteTransaction(scanner);
                break;
            default:
                System.out.println("Invalid choice. Please enter 'client', 'agent', 'rdv', 'propriete', or 'transaction'.");
                break;
        }
    }

    private static void handleAfficher(Scanner scanner) {
        System.out.print("Do you want to afficher Clients, Agents, RDVs, Proprietes, or Transactions? (clients/agents/rdvs/proprietes/transactions): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        switch (choice) {
            case "clients":
                afficherClients();
                break;
            case "agents":
                afficherAgents();
                break;
            case "rdvs":
                afficherRDVs();
                break;
            case "proprietes":
                afficherProprietes();
                break;
            case "transactions":
                afficherTransactions();
                break;
            default:
                System.out.println("Invalid choice. Please enter 'clients', 'agents', 'rdvs', 'proprietes', or 'transactions'.");
                break;
        }
    }
    private static void modifyClient(Scanner scanner) {
        System.out.print("Enter the ID of the Client to modify: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Client client : clients) {
            if (client.getID() == id) {
                System.out.println("Enter new details for the Client (leave blank to keep current value):");

                System.out.print("Nom: ");
                String nom = scanner.nextLine();
                if (!nom.isEmpty()) client.setNom(nom);

                System.out.print("Prenom: ");
                String prenom = scanner.nextLine();
                if (!prenom.isEmpty()) client.setPrenom(prenom);

                System.out.print("Adresse: ");
                String adresse = scanner.nextLine();
                if (!adresse.isEmpty()) client.setAdresse(adresse);

                System.out.print("Telephone: ");
                String telephone = scanner.nextLine();
                if (!telephone.isEmpty()) client.setTelephone(telephone);

                System.out.print("Email: ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) client.setEmail(email);

                System.out.print("Type de client (ACHETEUR/LOCATAIRE): ");
                String typeClient = scanner.nextLine();
                if (!typeClient.isEmpty()) client.setTypeC(TypeClient.valueOf(typeClient.toUpperCase()));

                break;
            }
        }
    }

    private static void modifyAgent(Scanner scanner) {
        System.out.print("Enter the ID of the Agent to modify: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Agent agent : agents) {
            if (agent.getID() == id) {
                System.out.println("Enter new details for the Agent (leave blank to keep current value):");

                System.out.print("Nom: ");
                String nom = scanner.nextLine();
                if (!nom.isEmpty()) agent.setNom(nom);

                System.out.print("Prenom: ");
                String prenom = scanner.nextLine();
                if (!prenom.isEmpty()) agent.setPrenom(prenom);

                System.out.print("Adresse: ");
                String adresse = scanner.nextLine();
                if (!adresse.isEmpty()) agent.setAdresse(adresse);

                System.out.print("Telephone: ");
                String telephone = scanner.nextLine();
                if (!telephone.isEmpty()) agent.setTelephone(telephone);

                System.out.print("Email: ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) agent.setEmail(email);

                break;
            }
        }
    }

    private static void modifyRDV(Scanner scanner) {
        System.out.print("Enter the ID of the RDV to modify: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (RDV rdv : rdvs) {
            if (rdv.getId() == id) {
                System.out.println("Enter new details for the RDV (leave blank to keep current value):");

                System.out.print("Date and Time (YYYY-MM-DDTHH:MM): ");
                String dateTimeStr = scanner.nextLine();
                if (!dateTimeStr.isEmpty()) rdv.setDateHeure(LocalDateTime.parse(dateTimeStr));

                // Assuming Propriete, Client, and Agent objects are created separately
                // You can include logic here to update them from input if needed

                System.out.print("Type de RDV (PLANIFIE/ANNULE/REALISE): ");
                String typeRDV = scanner.nextLine();
                if (!typeRDV.isEmpty()) rdv.setTypeR(TypeRDV.valueOf(typeRDV.toUpperCase()));

                break;
            }
        }
    }

    private static void modifyPropriete(Scanner scanner) {
        System.out.print("Enter the ID of the Propriete to modify: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Propriete propriete : proprietes) {
            if (propriete.getID() == id) {
                System.out.println("Enter new details for the Propriete (leave blank to keep current value):");

                System.out.print("Superficie: ");
                String superficieStr = scanner.nextLine();
                if (!superficieStr.isEmpty()) propriete.setSuperficie(Double.parseDouble(superficieStr));

                System.out.print("Prix: ");
                String prixStr = scanner.nextLine();
                if (!prixStr.isEmpty()) propriete.setPrix(Double.parseDouble(prixStr));

                System.out.print("Localisation: ");
                String localisation = scanner.nextLine();
                if (!localisation.isEmpty()) propriete.setLocalisation(localisation);

                System.out.print("Description: ");
                String description = scanner.nextLine();
                if (!description.isEmpty()) propriete.setDescription(description);

                // Assuming Agent is updated separately if needed

                break;
            }
        }
    }

    private static void modifyTransaction(Scanner scanner) {
        System.out.print("Enter the ID of the Transaction to modify: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        for (Transaction transaction : transactions) {
            if (transaction.getID() == id) {
                System.out.println("Enter new details for the Transaction (leave blank to keep current value):");

                System.out.print("Date (yyyy-MM-dd): ");
                String dateString = scanner.nextLine();
                if (!dateString.isEmpty()) {
                    try {
                        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
                        transaction.setDate(date);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }

                // Assuming Propriete and Client objects are updated separately if needed

                System.out.print("Type de transaction: ");
                String typeTransaction = scanner.nextLine();
                if (!typeTransaction.isEmpty()) transaction.setType(TypeTransaction.valueOf(typeTransaction.toUpperCase()));

                break;
            }
        }
    }

    private static void deleteClient(Scanner scanner) {
        System.out.print("Enter the ID of the Client to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        clients.removeIf(client -> client.getID() == id);
    }

    private static void deleteAgent(Scanner scanner) {
        System.out.print("Enter the ID of the Agent to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        agents.removeIf(agent -> agent.getID() == id);
    }

    private static void deleteRDV(Scanner scanner) {
        System.out.print("Enter the ID of the RDV to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        rdvs.removeIf(rdv -> rdv.getId() == id);
    }

    private static void deletePropriete(Scanner scanner) {
        System.out.print("Enter the ID of the Propriete to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        proprietes.removeIf(propriete -> propriete.getID() == id);
    }

    private static void deleteTransaction(Scanner scanner) {
        System.out.print("Enter the ID of the Transaction to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        transactions.removeIf(transaction -> transaction.getID() == id);
    }

    private static void afficherClients() {
        System.out.println("List of Clients:");
        for (Client client : clients) {
            System.out.println(client + "\n");
        }
    }

    private static void afficherAgents() {
        System.out.println("List of Agents:");
        for (Agent agent : agents) {
            System.out.println(agent + "\n");
        }
    }

    private static void afficherRDVs() {
        System.out.println("List of RDVs:");
        for (RDV rdv : rdvs) {
            System.out.println(rdv + "\n");
        }
    }

    private static void afficherProprietes() {
        System.out.println("List of Proprietes:");
        for (Propriete propriete : proprietes) {
            System.out.println(propriete + "\n");
        }
    }

    private static void afficherTransactions() {
        System.out.println("List of Transactions:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction + "\n");
        }
    }
    
    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.dat"))) {
            oos.writeObject(clients);
            oos.writeObject(agents);
            oos.writeObject(rdvs);
            oos.writeObject(transactions);
            oos.writeObject(proprietes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.dat"))) {
            clients = (List<Client>) ois.readObject();
            agents = (List<Agent>) ois.readObject();
            rdvs = (List<RDV>) ois.readObject();
            transactions = (List<Transaction>) ois.readObject();
            proprietes = (List<Propriete>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
