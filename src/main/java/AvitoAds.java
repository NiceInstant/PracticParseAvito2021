public class AvitoAds {

    private String nameAds;
    private int priceAds;

    AvitoAds(String nameAds, int priceAds)
    {
        this.nameAds= nameAds;
        this.priceAds = priceAds;
    }

    public String getNameAds() {
        return nameAds;
    }

    public int getPriceAds() {
        return priceAds;
    }

    public  void display()
    {
        System.out.println("Марка выто : "+nameAds+"\tЦена : "+priceAds);
    }
}
