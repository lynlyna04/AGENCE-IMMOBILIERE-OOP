package pack;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContratLocation implements Contrat {
    private double loyer;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Client client;
    private List<Contrat> contrats;

    public ContratLocation(double loyer, LocalDate dateDebut, LocalDate dateFin, Client client) {
        this.loyer = loyer;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.client = client;
        this.contrats = new ArrayList<>();
    }

    public double getLoyer() {
        return loyer;
    }

    public void setLoyer(double loyer) {
        this.loyer = loyer;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void genererContrat() {
        ContratLocation nouveauContrat = new ContratLocation(this.loyer, this.dateDebut, this.dateFin, this.client);
        contrats.add(nouveauContrat);
        // Logique pour générer un contrat de location
    }

    @Override
    public void ajoutContrat(Contrat contrat) {
        contrats.add(contrat);
        // Logique pour ajouter un contrat
    }
}
