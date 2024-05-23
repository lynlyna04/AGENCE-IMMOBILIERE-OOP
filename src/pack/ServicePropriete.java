package pack;

import java.util.List;

public class ServicePropriete {
    private List<Propriete> proprietes;

    public ServicePropriete(List<Propriete> proprietes) {
        this.proprietes = proprietes;
    }

    public Propriete getProprieteParId(int id) {
        for (Propriete propriete : proprietes) {
            if (propriete.getID() == id) {
                return propriete;
            }
        }
        return null; // Or throw an exception
    }

    public void ajoutPropriete(Propriete propriete) {
        proprietes.add(propriete);
    }

    public void suppPropriete(Propriete propriete) {
        proprietes.remove(propriete);
    }
}
