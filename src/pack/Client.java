package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client extends Personne {
    private TypeClient typeC;
    private List<Propriete> preferences;
    private List<Transaction> transactions;

    public Client(int ID, String nom, String prenom, String adresse, String telephone, String email, TypeClient typeC) {
        super(ID, nom, prenom, adresse, telephone, email);
        this.typeC = typeC;
        this.preferences = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public TypeClient getTypeC() {
        return typeC;
    }

    public void setTypeC(TypeClient typeC) {
        this.typeC = typeC;
    }

    public List<Propriete> getPreferences() {
        return preferences;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void ajoutPreference(Propriete propriete) {
        preferences.add(propriete);
    }

    public void suppPreference(Propriete propriete) {
        preferences.remove(propriete);
    }

    public void ajoutTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void suppTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    // Static method to create a Client object by taking input from the user
    public static Client createClientFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter client details:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Nom: ");
        String nom = scanner.nextLine();
        System.out.print("Prenom: ");
        String prenom = scanner.nextLine();
        System.out.print("Adresse: ");
        String adresse = scanner.nextLine();
        System.out.print("Telephone: ");
        String telephone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Type de client (ACHETEUR/LOCATAIRE): ");
        TypeClient typeC = TypeClient.valueOf(scanner.nextLine().toUpperCase());

        scanner.close();

        return new Client(id, nom, prenom, adresse, telephone, email, typeC);
    }
}