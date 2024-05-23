package pack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class Transaction implements Serializable{
    private int ID;
    private Date date;
    private Propriete propriete;
    private List<Paiement> paiements;
    private TypeTransaction type;
    private Client client;

    public Transaction(int ID, Date date, Propriete propriete, TypeTransaction type, Client client) {
        this.ID = ID;
        this.date = date;
        this.propriete = propriete;
        this.paiements = new ArrayList<>();
        this.type = type;
        this.client = client;
    }
    
    public Transaction() {}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Propriete getPropriete() {
        return propriete;
    }

    public void setPropriete(Propriete propriete) {
        this.propriete = propriete;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public TypeTransaction getType() {
        return type;
    }

    public void setType(TypeTransaction type) {
        this.type = type;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void ajoutPaiement(Paiement paiement) {
        paiements.add(paiement);
    }

    public void suppPaiement(Paiement paiement) {
        paiements.remove(paiement);
    }

    public static Transaction createTransactionFromInput(ServicePropriete servicePropriete, ServiceClient serviceClient) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Date (yyyy-MM-dd): ");
        String dateString = scanner.nextLine();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.print("Propriété (ID): ");
        int proprieteID = scanner.nextInt();
        scanner.nextLine();
        Propriete propriete = servicePropriete.getProprieteParId(proprieteID);
        System.out.print("Type de transaction: ");
        String typeString = scanner.nextLine();
        TypeTransaction type = TypeTransaction.valueOf(typeString.toUpperCase());
        System.out.print("Client (ID): ");
        int clientID = scanner.nextInt();
        scanner.nextLine();
        Client client = serviceClient.getClientParId(clientID);
        return new Transaction(id, date, propriete, type, client);
    }

    @Override
    public String toString() {
        return "Transaction ID: " + ID + ", Date: " + date + ", Propriété: " + propriete + ", Type: " + type + ", Client: " + client;
    }
}
