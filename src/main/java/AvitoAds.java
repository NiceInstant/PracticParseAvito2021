import java.util.ArrayList;
import java.util.List;

public class AvitoAds {

    private String nameAds;
    private int priceAds;

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
