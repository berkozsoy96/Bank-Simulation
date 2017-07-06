package banka;

public class Musteri {
    String ad;
    String soyad;
    int hesapNo;
    static int sayac = 1000;
    int sifre;
    Para tl = new Para("TL",100);
    Para euro = new Para("EUR",100);
    Para dolar = new Para("USD",100);
    boolean bloke = false;
    
    Musteri(){
        this.hesapNo = sayac;
        sayac++;
        sifre = 1111;
    }
    
    void sifreGuncelle(int yeniSifre){
        this.sifre=yeniSifre;
    }
}
