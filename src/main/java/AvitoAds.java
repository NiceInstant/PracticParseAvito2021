import java.util.Objects;

public class AvitoAds {

    private String nameAds;
    private int priceAds;

    public AvitoAds(String nameAds, int priceAds)
    {
        this.nameAds = nameAds;
        this.priceAds = priceAds;
    }
    @Override
    public String toString(){

        return "Марка Авто: "+nameAds+"  Цена : "+priceAds+"\n";
    }
 /*   @Override
    public boolean equals(Object o) {
        // 1
        if (this == o) {
            return true;
        }

        // 2
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        // 3
        AvitoAds avitoAds = (AvitoAds) o;
        return priceAds == avitoAds.priceAds  &&
                nameAds.equals(avitoAds.nameAds);
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvitoAds avitoAds = (AvitoAds) o;
        return priceAds == avitoAds.priceAds && Objects.equals(nameAds, avitoAds.nameAds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameAds, priceAds);
    }

    public String getNameAds() { return nameAds; }

    public int getPriceAds() {
        return priceAds;
    }

}