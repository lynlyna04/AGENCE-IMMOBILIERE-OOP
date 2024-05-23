package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class Agent extends Personne implements Serializable {
    private List<Propriete> proprietesGerees;

    public Agent(int ID, String nom, String prenom, String adresse, String telephone, String email) {
        super(ID, nom, prenom, adresse, telephone, email);
        this.proprietesGerees = new ArrayList<>();
    }
    
    public Agent() {}

    public List<Propriete> getProprietesGerees() {
        return proprietesGerees;
    }

    public void ajoutPropriete(Propriete propriete) {
        proprietesGerees.add(propriete);
        propriete.setAgentRespo(this);
    }

    public void suppPropriete(Propriete propriete) {
        proprietesGerees.remove(propriete);
        propriete.setAgentRespo(null);
    }
    
    @Override
    public String toString() {
        return "ID: " + this.getID() + ", Nom: " + this.getNom() + ", Prénom: " + this.getPrenom() + ", Adresse: " + this.getAdresse() + ", Téléphone: " + this.getTelephone() + ", Email: " + this.getEmail();
    }
    
    // Static method to create an Agent object by taking input from the user
    public static Agent createAgentFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Agent details:");
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

        return new Agent(id, nom, prenom, adresse, telephone, email);
    }
}
