package pack;

import java.time.LocalDate;
import java.util.Date;

public class Location extends Transaction {
    private ContratLocation contrat;

    public Location(int ID, Date date, Propriete propriete, Client client) {
        super(ID, date, propriete, TypeTransaction.LOCATION, client);
    }

    public ContratLocation getContrat() {
        return contrat;
    }

    public void setContrat(ContratLocation contrat) {
        this.contrat = contrat;
    }

    public void genererContrat() {
        double loyer = getPropriete().getPrix() * 0.1; // Exemple : 10% du prix de la propriété
        LocalDate dateDebut = LocalDate.now();
        LocalDate dateFin = dateDebut.plusYears(1); // Exemple : contrat d'un an
        Client client = getClient();
        contrat = new ContratLocation(loyer, dateDebut, dateFin, client);
    }

    public void enregistrerPaiement() {
        double montantLoyer = contrat.getLoyer();
      
        Paiement paiement = new Paiement(montantLoyer, this);
        ajoutPaiement(paiement);
    }
}
