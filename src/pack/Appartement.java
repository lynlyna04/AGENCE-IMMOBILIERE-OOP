package pack;
import java.util.Scanner;

public class Appartement extends Propriete {

    public Appartement(int ID, TypePropriete type, double superficie, double prix, String localisation, String description) {
        super(ID, type, superficie, prix, localisation, description);
    }

    @Override
    public String toString() {
        return "ID: " + getID() + ", Type: " + getType() + ", Superficie: " + getSuperficie() + "m², Prix: " + getPrix() + "€, Localisation: " + getLocalisation() + ", Description: " + getDescription();
    }

    // Static method to create an Appartement object by taking input from the user
    public static Appartement createAppartementFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Appartement details:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Type (STUDIO/F2/F3...): ");
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

        // Normally, you would also link the property to an agent, but for simplicity, we'll leave it out here

        return new Appartement(id, type, superficie, prix, localisation, description);
    }
}
