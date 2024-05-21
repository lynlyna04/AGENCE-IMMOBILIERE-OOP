package pack;

public abstract class Propriete {
    private int ID;
    private TypePropriete type;
    private double superficie;
    private double prix;
    private String localisation;
    private String description;
    private Agent agentRespo;

    public Propriete(int ID, TypePropriete type, double superficie, double prix, String localisation, String description) {
        this.ID = ID;
        this.type = type;
        this.superficie = superficie;
        this.prix = prix;
        this.localisation = localisation;
        this.description = description;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public TypePropriete getType() {
        return type;
    }

    public void setType(TypePropriete type) {
        this.type = type;
    }

    
    public double getSuperficie() {
            return superficie;
    }
    
    public void setSuperficie(double superficie) {
            this.superficie = superficie;
    }
    
    public double getPrix() {
            return prix;
    }
    
    public void setPrix(double prix) {
            this.prix = prix;
    }
    
    public String getLocalisation() {
            return localisation;
    }
    
    public void setLocalisation(String localisation) {
            this.localisation = localisation;
    }
    
    public String getDescription() {
            return description;
    }
    
    public void setDescription(String description) {
            this.description = description;
    }
    
    public Agent getAgentRespo() {
            return agentRespo;
    }
    
    public void setAgentRespo(Agent agentRespo) {
            this.agentRespo = agentRespo;
    }
}