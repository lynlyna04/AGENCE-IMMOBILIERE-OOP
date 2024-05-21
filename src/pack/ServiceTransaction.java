package pack;

import java.util.ArrayList;
import java.util.List;

public class ServiceTransaction {
    private List<Transaction> transactions;

    public ServiceTransaction() {
        this.transactions = new ArrayList<>();
    }

    public List<Transaction> getToutesLesTransactions() {
        return transactions;
    }

    public Transaction getTransactionParId(int id) {
        return transactions.stream()
                .filter(t -> t.getID() == id)
                .findFirst()
                .orElse(null);
    }

    public void ajouterTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public void modifierTransaction(Transaction transaction) {
        Transaction transactionExistante = getTransactionParId(transaction.getID());
        if (transactionExistante != null) {
            transactionExistante.setDate(transaction.getDate());
            transactionExistante.setPropriete(transaction.getPropriete());
            transactionExistante.setType(transaction.getType());
            transactionExistante.setClient(transaction.getClient());
        }
    }

    public void supprimerTransaction(Transaction transaction) {
        transactions.remove(transaction);
    }

    public void ajouterPaiement(Paiement paiement) {
        Transaction transaction = paiement.getTransaction();
        transaction.ajoutPaiement(paiement);
    }

    public void modifierPaiement(Paiement paiement) {
        Transaction transaction = paiement.getTransaction();
        List<Paiement> paiements = transaction.getPaiements();
        paiements.stream()
                .filter(p -> p.getID() == paiement.getID())
                .findFirst()
                .ifPresent(p -> {
                    p.setMontant(paiement.getMontant());
                    p.marquePayer();
                });
    }

    public void supprimerPaiement(Paiement paiement) {
        Transaction transaction = paiement.getTransaction();
        transaction.suppPaiement(paiement);
    }
}