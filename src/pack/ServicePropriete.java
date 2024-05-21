package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ServicePropriete {
    private List<Propriete> proprietes;

    public ServicePropriete() {
        this.proprietes = new ArrayList<>();
    }

    public List<Propriete> getToutesLesProprietes() {
        return proprietes;
    }

    public Propriete getProprieteParId(int id) {
        return proprietes.stream()
                .filter(p -> p.getID() == id)
                .findFirst()
                .orElse(null);
    }

    public void ajouterPropriete(Propriete propriete) {
        proprietes.add(propriete);
    }

    public void modifierPropriete(Propriete propriete) {
        Propriete proprieteExistante = getProprieteParId(propriete.getID());
        if (proprieteExistante != null) {
            proprieteExistante.setType(propriete.getType());
            proprieteExistante.setSuperficie(propriete.getSuperficie());
            proprieteExistante.setPrix(propriete.getPrix());
            proprieteExistante.setLocalisation(propriete.getLocalisation());
            proprieteExistante.setDescription(propriete.getDescription());
        }
    }

    public void supprimerPropriete(Propriete propriete) {
        proprietes.remove(propriete);
    }

    public List<Propriete> rechercherProprietes(CritereRecherchePropriete criteres) {
        return proprietes.stream()
                .filter(p -> p.getPrix() >= criteres.getPrixMin() && p.getPrix() <= criteres.getPrixMax())
                .filter(p -> criteres.getType() == null || p.getType() == criteres.getType())
                .filter(p -> criteres.getLocalisation() == null || p.getLocalisation().contains(criteres.getLocalisation()))
                .collect(Collectors.toList());
    }
}