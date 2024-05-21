package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
//import java.time.LocalDateTime;

public class ServiceRDV {
    private List<RDV> rdvs;

    public ServiceRDV() {
        this.rdvs = new ArrayList<>();
    }

    public List<RDV> getTousRDV() {
        return rdvs;
    }

    public RDV getRDVParId(int id) {
        return rdvs.stream()
                .filter(rdv -> rdv.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void ajouterRDV(RDV rdv) {
        rdvs.add(rdv);
    }

    public void modifierRDV(RDV rdv) {
        RDV rdvExistant = getRDVParId(rdv.getId());
        if (rdvExistant != null) {
            rdvExistant.setDateHeure(rdv.getDateHeure());
            rdvExistant.setPropriete(rdv.getPropriete());
            rdvExistant.setClient(rdv.getClient());
            rdvExistant.setAgent(rdv.getAgent());
            rdvExistant.setTypeR(rdv.getTypeR());
        }
    }

    public void supprimerRDV(RDV rdv) {
        rdvs.remove(rdv);
    }
    
    public List<RDV> getRDVPropriete(Propriete propriete) {
        return rdvs.stream()
                .filter(rdv -> rdv.getPropriete().equals(propriete))
                .collect(Collectors.toList());
    }
    
    public List<RDV> getRDVClient(Client client) {
        return rdvs.stream()
                .filter(rdv -> rdv.getClient().equals(client))
                .collect(Collectors.toList());
    }
    
    public List<RDV> getRDVAgent(Agent agent) {
        return rdvs.stream()
                .filter(rdv -> rdv.getAgent().equals(agent))
                .collect(Collectors.toList());
    }
}
