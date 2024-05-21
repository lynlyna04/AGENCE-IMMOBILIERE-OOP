package pack;

import java.util.Date;

public class Vente extends Transaction {
    private ContratVente contrat;

    public Vente(int ID, Date date, Propriete propriete, Client client) {
        super(ID, date, propriete, TypeTransaction.VENTE, client);
    }

    public ContratVente getContrat() {
        return contrat;
    }

    public void setContrat(ContratVente contrat) {
        this.contrat = contrat;
    }

    public void genererContrat() {
        double prixVente = getPropriete().getPrix();
        Client client = getClient();
        contrat = new ContratVente(prixVente, client);
    }

    public void enregistrerPaiement() {
        Paiement paiement = new Paiement(getPropriete().getPrix(), this);
        ajoutPaiement(paiement);
    }


    
}