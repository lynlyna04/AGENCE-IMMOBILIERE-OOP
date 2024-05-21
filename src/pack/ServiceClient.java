package pack;

import java.util.ArrayList;
import java.util.List;

public class ServiceClient {
    private List<Client> clients;

    public ServiceClient() {
        this.clients = new ArrayList<>();
    }

    public List<Client> getTousLesClients() {
        return clients;
    }

    public Client getClientParId(int id) {
        return clients.stream()
                .filter(c -> c.getID() == id)
                .findFirst()
                .orElse(null);
    }

    public void ajouterClient(Client client) {
        clients.add(client);
    }

    public void modifierClient(Client client) {
        Client clientExistant = getClientParId(client.getID());
        if (clientExistant != null) {
            clientExistant.setNom(client.getNom());
            clientExistant.setPrenom(client.getPrenom());
            clientExistant.setAdresse(client.getAdresse());
            clientExistant.setTelephone(client.getTelephone());
            clientExistant.setEmail(client.getEmail());
            clientExistant.setTypeC(client.getTypeC());
        }
    }

    public void supprimerClient(Client client) {
        clients.remove(client);
    }
}
