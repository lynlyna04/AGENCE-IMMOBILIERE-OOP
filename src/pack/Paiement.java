package pack;

import java.util.Scanner;

public class Paiement {
    private int ID;
    private double montant;
    private boolean paye;
    private static int nextID = 1; 
    private Transaction transaction;

    public Paiement(double montant, Transaction transaction) {
        
		this.ID = nextID++; // Assign the next available ID and increment the counter
        this.montant = montant;
        this.paye = false;
        this.transaction = transaction;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public boolean isPaye() {
        return paye;
    }

    public void marquePayer() {
        this.paye = true;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public static Paiement createPaiementFromInput(ServiceTransaction serviceTransaction) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne
        System.out.print("Montant: ");
        double montant = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Transaction (ID): ");
        int transactionID = scanner.nextInt();
        scanner.nextLine();
        Transaction transaction = serviceTransaction.getTransactionParId(transactionID);
        return new Paiement(id, transaction);
    }
}
