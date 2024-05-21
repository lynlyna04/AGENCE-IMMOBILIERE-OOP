package pack;

import java.util.Scanner;

public class Maison extends Propriete {
    private int nbEtage;
    private double superficieTerrain;

    public Maison(int ID, double superficie, double prix, String localisation, String description, int nbEtage,
            double superficieTerrain) {
        super(ID, TypePropriete.MAISON, superficie, prix, localisation, description);
        this.nbEtage = nbEtage;
        this.superficieTerrain = superficieTerrain;
    }

    public int getNbEtage() {
        return nbEtage;
    }

    public void setNbEtage(int nbEtage) {
        this.nbEtage = nbEtage;
    }

    public double getSuperficieTerrain() {
        return superficieTerrain;
    }

    public void setSuperficieTerrain(double superficieTerrain) {
        this.superficieTerrain = superficieTerrain;
    }

    // Static method to create an Appartement object by taking input from the user
    public static Maison createMaisonFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Maison details:");
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
        System.out.print("Nombre d'etage: ");
        int nbEtage = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("superficie du terrain ");
        int superficieTerrain = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        scanner.close();

        return new Maison(id, superficie, prix, localisation, description, nbEtage, superficieTerrain);
    }
}