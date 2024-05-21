package pack;
import java.util.Scanner;

public class Appartement extends Propriete {
    private int etage;
    private int nbPieces;

    public Appartement(int ID, double superficie, double prix, String localisation, String description, int etage, int nbPieces) {
        super(ID, TypePropriete.APPARTEMENT, superficie, prix, localisation, description);
        this.etage = etage;
        this.nbPieces = nbPieces;
    }

    public int getEtage() {
        return etage;
    }

    public void setEtage(int etage) {
        this.etage = etage;
    }

    public int getNbPieces() {
        return nbPieces;
    }

    public void setNbPieces(int nbPieces) {
        this.nbPieces = nbPieces;
    }
    
 // Static method to create an Appartement object by taking input from the user
    public static Appartement createAppartementFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Appartement details:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Superficie: ");
        double superficie = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Prix: ");
        double prix = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Localisation: ");
        String localisation = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Étage: ");
        int etage = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Nombre de pièces: ");
        int nbPieces = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        scanner.close();

        return new Appartement(id, superficie, prix, localisation, description, etage, nbPieces);
    }
    
}