package pack;

public class CritereRecherchePropriete {
    private double prixMin;
    private double prixMax;
    private TypePropriete type;
    private String localisation;

    public CritereRecherchePropriete(double prixMin, double prixMax, TypePropriete type, String localisation) {
        this.prixMin = prixMin;
        this.prixMax = prixMax;
        this.type = type;
        this.localisation = localisation;
    }

    public double getPrixMin() {
        return prixMin;
    }

    public void setPrixMin(double prixMin) {
        this.prixMin = prixMin;
    }

    public double getPrixMax() {
        return prixMax;
    }

    public void setPrixMax(double prixMax) {
        this.prixMax = prixMax;
    }

    public TypePropriete getType() {
        return type;
    }

    public void setType(TypePropriete type) {
        this.type = type;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}