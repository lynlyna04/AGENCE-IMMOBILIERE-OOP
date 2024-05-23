package pack;

import java.util.Scanner;

public class Maison extends Propriete {
    private int numRooms;

    public Maison(int ID, TypePropriete type, double superficie, double prix, String localisation, String description, int numRooms) {
        super(ID, type, superficie, prix, localisation, description);
        this.numRooms = numRooms;
    }

    public int getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(int numRooms) {
        this.numRooms = numRooms;
    }

    @Override
    public String toString() {
        return "ID: " + getID() + ", Type: " + getType() + ", Superficie: " + getSuperficie() + "m², Prix: " + getPrix() + "€, Localisation: " + getLocalisation() + ", Description: " + getDescription() + ", Number of Rooms: " + numRooms;
    }

    // Static method to create a Maison object by taking input from the user
    public static Maison createMaisonFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Maison details:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Type (MAISON): ");
        TypePropriete type = TypePropriete.valueOf(scanner.nextLine().toUpperCase());
        System.out.print("Superficie (in m²): ");
        double superficie = scanner.nextDouble();
        System.out.print("Prix: ");
        double prix = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character
        System.out.print("Localisation: ");
        String localisation = scanner.nextLine();
        System.out.print("Description: ");
        String description = scanner.nextLine();
        System.out.print("Number of Rooms: ");
        int numRooms = scanner.nextInt();

        return new Maison(id, type, superficie, prix, localisation, description, numRooms);
    }
}
