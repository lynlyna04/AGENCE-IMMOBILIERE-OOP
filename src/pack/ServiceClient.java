package pack;

import java.util.List;

public class ServiceClient {
    private List<Client> clients;

    public ServiceClient(List<Client> clients) {
        this.clients = clients;
    }

    public Client getClientParId(int id) {
        for (Client client : clients) {
            if (client.getID() == id) {
                return client;
            }
        }
        return null; // Or throw an exception
    }

    public void ajoutClient(Client client) {
        clients.add(client);
    }

    public void suppClient(Client client) {
        clients.remove(client);
    }
}
