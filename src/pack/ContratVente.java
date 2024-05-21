package pack;

import java.util.ArrayList;
import java.util.List;

public class ContratVente implements Contrat {
    private double prixVente;
    private Client client;
    private List<Contrat> contrats;

    public ContratVente(double prixVente, Client client) {
        this.prixVente = prixVente;
        this.client = client;
        this.contrats = new ArrayList<>();
    }

    public double getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public void genererContrat() {
        ContratVente nouveauContrat = new ContratVente(this.prixVente, this.client);
        contrats.add(nouveauContrat);
    }

    @Override
    public void ajoutContrat(Contrat contrat) {
        contrats.add(contrat);
    }
}

