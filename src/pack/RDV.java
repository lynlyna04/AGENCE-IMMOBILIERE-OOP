package pack;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.Serializable;

public class RDV implements Serializable{
    private int id;
    private LocalDateTime dateHeure;
    private Propriete propriete;
    private Client client;
    private Agent agent;
    private TypeRDV typeR;

    public RDV(int id, LocalDateTime dateHeure, Propriete propriete, Client client, Agent agent, TypeRDV typeR) {
        this.id = id;
        this.dateHeure = dateHeure;
        this.propriete = propriete;
        this.client = client;
        this.agent = agent;
        this.typeR = typeR;
    }
    public RDV() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public TypeRDV getTypeR() {
        return typeR;
    }

    public void setTypeR(TypeRDV typeR) {
        this.typeR = typeR;
    }

    public void rdvPlanifier(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
        this.typeR = TypeRDV.PLANIFIE;
    }

    public void rdvAnnuler() {
        this.typeR = TypeRDV.ANNULE;
    }

    public void rdvRealiser() {
        this.typeR = TypeRDV.REALISE;
    }

    // Static method to create an RDV object by taking input from the user
    public static RDV createRDVFromInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter RDV details:");
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        // Assuming you want to take LocalDateTime input in ISO format (YYYY-MM-DDTHH:MM)
        System.out.print("Date and Time (YYYY-MM-DDTHH:MM): ");
        String dateTimeStr = scanner.nextLine();
        LocalDateTime dateHeure = LocalDateTime.parse(dateTimeStr);

        // Assuming Propriete, Client, and Agent objects are created separately
        // You can include logic here to create them from input if needed
        Propriete propriete = null; // Create Propriete object
        Client client = null; // Create Client object
        Agent agent = null; // Create Agent object

        // Assuming TypeRDV is chosen from a predefined list
        System.out.print("Type de RDV (PLANIFIE/ANNULE/REALISE): ");
        TypeRDV typeR = TypeRDV.valueOf(scanner.nextLine().toUpperCase());

        return new RDV(id, dateHeure, propriete, client, agent, typeR);
    }

    @Override
    public String toString() {
        return "RDV [ID=" + id + ", DateHeure=" + dateHeure + ", Propriete=" + propriete + ", Client=" + client 
                + ", Agent=" + agent + ", Type=" + typeR + "]";
    }
}
