
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

    public void setNameAds(String nameAds) {
        this.nameAds = nameAds;
    }

    public void setPriceAds(int priceAds) {
        this.priceAds = priceAds;
    }

    public String getNameAds() { return nameAds; }

    public int getPriceAds() {
        return priceAds;
    }

    public  void display()
    {
        System.out.println("Марка Авто: "+nameAds+"  Цена : "+priceAds);

    }
}
