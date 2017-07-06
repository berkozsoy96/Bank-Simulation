package banka;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banka {
    
    static List<Musteri> musteriler = new ArrayList<>();
    
    public static void dosyayaKaydet() throws IOException{
        File file = new File("musteriler.txt");
        if (!file.exists()) {
                file.createNewFile();
            }
        
        FileWriter fileWriter = new FileWriter(file, false);
        BufferedWriter bWriter = new BufferedWriter(fileWriter);
        for(int i=0 ; i<musteriler.size() ; i++){
            bWriter.write(musteriler.get(i).ad + " " + musteriler.get(i).soyad + " " 
            + musteriler.get(i).hesapNo + " " + musteriler.get(i).sifre + " " 
            + musteriler.get(i).bloke + " " + musteriler.get(i).tl.miktar + " "
            + musteriler.get(i).dolar.miktar + " " + musteriler.get(i).euro.miktar);
            bWriter.newLine();

        }
        bWriter.close();
        fileWriter.close();
    }
    
    public static void dosyadanOku() throws FileNotFoundException, IOException{
        File file = new File("musteriler.txt");
        if (!file.exists()) {
                file.createNewFile();
            }
        FileReader fileReader = new FileReader(file);
        String line;
        
        BufferedReader br = new BufferedReader(fileReader);
        
        while ((line = br.readLine()) != null) {
            
            Musteri gecici = new Musteri();
            String[] a = line.split(" ");
            
            gecici.ad = a[0];
            gecici.soyad = a[1];
            gecici.hesapNo = Integer.parseInt(a[2]);
            gecici.sifre = Integer.parseInt(a[3]);
            gecici.bloke = Boolean.parseBoolean(a[4]);
            gecici.tl.miktar = Integer.parseInt(a[5]);
            gecici.dolar.miktar = Integer.parseInt(a[6]);
            gecici.euro.miktar = Integer.parseInt(a[7]);
            
            musteriler.add(gecici);
        }
        br.close();
    }
    
    public static int girisYap() throws IOException{
        System.out.print("Hesap Numaranızı Giriniz: ");
        int secilenMusteriİndis = -1;
        Scanner input = new Scanner(System.in);
        int num = input.nextInt();
        int i;
        for(i=0 ; i<musteriler.size();i++){
            if(num == musteriler.get(i).hesapNo && !musteriler.get(i).bloke){
                System.out.print("Şifreniz:");
                if(musteriler.get(i).sifre == input.nextInt()){
                    System.out.println("Giriş Başarılı.");
                    secilenMusteriİndis = i;
                    System.out.println("Hosgeldiniz "+ musteriler.get(i).ad);
                }
                else{
                    int j;
                    for(j=0; j<2 ; j++){
                        System.out.print("Şifreyi Hatalı Girdiniz! Tekrar Giriniz: ");
                        if(musteriler.get(i).sifre == input.nextInt()){
                            System.out.println("Giriş Başarılı.");
                            secilenMusteriİndis = i;
                            System.out.println("Hosgeldiniz "+ musteriler.get(i).ad);
                            break;
                        }
                    }
                    if(j==2){
                        System.out.println("Şifrenizi 3 kere yanlış girdiniz Hesabını Bloke olmuştur."
                                + "Hesabı Açtırmak için Banka Yöneticisi ile Konuşun.");
                        musteriler.get(i).bloke= true;
                        dosyayaKaydet();
                    }

                }
                break;
            }
        }
        if(i==musteriler.size()){
            System.out.println("Hesabınız Bloke Olmuştur veya Böyle Bir Hesap Bulunmamaktadır.");
        }
        return secilenMusteriİndis;
    }
    
    public static void yeniHesap() throws IOException{
        Scanner input = new Scanner(System.in);
        Musteri musteri = new Musteri();
        System.out.print("Adınızı Giriniz: ");
        musteri.ad = input.next();
        System.out.print("Soyadınızı Giriniz: ");
        musteri.soyad = input.next();
        musteriler.add(musteri);
        dosyayaKaydet();
    }
    
    public static void paraCek(int secilenMusteriİndis){
        System.out.println("Hangi Hesaptan Para Çekmek İstersiniz: ");
        System.out.println("1. TL");
        System.out.println("2. Dolar");
        System.out.println("3. Euro");
        System.out.print("Seçiniz: ");
        Scanner input = new Scanner(System.in);
        int sec = input.nextInt();

        if(sec == 1){
            if(musteriler.get(secilenMusteriİndis).tl.miktar == 0){
                System.out.println("Hesabınızda para bulunmamaktadır.");
            }
            else{
                System.out.println("TL hesabınızda bulunan miktar: "+ musteriler.get(secilenMusteriİndis).tl.miktar+" "+ musteriler.get(secilenMusteriİndis).tl.cins);
                System.out.print("Ne kadar çekmek istersiniz: ");
                int miktar = input.nextInt();
                if(miktar<=musteriler.get(secilenMusteriİndis).tl.miktar){
                    musteriler.get(secilenMusteriİndis).tl.miktar-=miktar;
                    System.out.println("Güle Güle Harcayınız.");
                }else{
                    System.out.println("O kadar paranız bulunmamaktadır.");
                }
            }
        }if(sec == 2){
            if(musteriler.get(secilenMusteriİndis).dolar.miktar == 0){
                System.out.println("Hesabınızda para bulunmamaktadır.");
            }
            else{
                System.out.println("TL hesabınızda bulunan miktar: "+ musteriler.get(secilenMusteriİndis).dolar.miktar+" "+ musteriler.get(secilenMusteriİndis).dolar.cins);
                System.out.print("Ne kadar çekmek istersiniz: ");
                int miktar = input.nextInt();
                if(miktar<=musteriler.get(secilenMusteriİndis).dolar.miktar){
                    musteriler.get(secilenMusteriİndis).dolar.miktar-=miktar;
                    System.out.println("Güle Güle Harcayınız.");
                }else{
                    System.out.println("O kadar paranız bulunmamaktadır.");
                }
            }
        }if(sec == 3){
            if(musteriler.get(secilenMusteriİndis).euro.miktar == 0){
                System.out.println("Hesabınızda para bulunmamaktadır.");
            }
            else{
                System.out.println("TL hesabınızda bulunan miktar: "+ musteriler.get(secilenMusteriİndis).euro.miktar +" "+ musteriler.get(secilenMusteriİndis).euro.cins);
                System.out.print("Ne kadar çekmek istersiniz: ");
                int miktar = input.nextInt();
                if(miktar<=musteriler.get(secilenMusteriİndis).euro.miktar){
                    musteriler.get(secilenMusteriİndis).euro.miktar-=miktar;
                    System.out.println("Güle Güle Harcayınız.");
                }else{
                    System.out.println("O kadar paranız bulunmamaktadır.");
                }
            }
        }
    }
    
    public static void paraYatır(int secilenMusteriİndis){
        System.out.println("Hangi Hesaba Yatırmak İstersiniz");
                    System.out.println("1. TL");
                    System.out.println("2. Dolar");
                    System.out.println("3. Euro");
                    Scanner input = new Scanner(System.in);
                    int sec = input.nextInt();
                    
                    if(sec==1){
                        System.out.print("Ne kadar yatırmak istersiniz: ");
                        int miktar = input.nextInt();
                        musteriler.get(secilenMusteriİndis).tl.miktar+=miktar;
                    }if(sec==2){
                        System.out.print("Ne kadar yatırmak istersiniz: ");
                        int miktar = input.nextInt();
                        musteriler.get(secilenMusteriİndis).dolar.miktar+=miktar;
                    }if(sec==3){
                        System.out.print("Ne kadar yatırmak istersiniz: ");
                        int miktar = input.nextInt();
                        musteriler.get(secilenMusteriİndis).euro.miktar+=miktar;
                    }
    }
    
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        boolean cıkıs = false;
        boolean cıkıs2=true;
        int secilenMusteriİndis = -1;
        
        while(!cıkıs){
            dosyadanOku();
            
            System.out.println("1. Yeni Hesap");
            System.out.println("2. Giriş");
            System.out.println("Diğer. Çıkış");
            System.out.print("Seçiminiz : ");
            int sayi = input.nextInt();
            
            switch(sayi){
                case 1:
                    yeniHesap();
                    break;
                case 2:
                    secilenMusteriİndis = girisYap();
                    cıkıs=true;
                    cıkıs2=false;
                    break;
                default:
                    System.out.println("Çıkış Yapılmıştır.");
                    cıkıs=true;
                    cıkıs2=true;
                    break;
            }
            
        }
        
        while(!cıkıs2){
            System.out.println("1. Para Çekme");
            System.out.println("2. Para Yatırma");
            System.out.println("3. Şifre Değiştirme");
            System.out.println("4. Kullanıcı Bilgileri");
            System.out.println("Diğer. Çıkış");

            System.out.print("Seçiminiz : ");
            int sayi = input.nextInt();

            switch(sayi){
                case 1:
                    paraCek(secilenMusteriİndis);
                    break;
                case 2:
                    paraYatır(secilenMusteriİndis);
                    break;
                case 3:
                    System.out.println("Yeni Şifrenizi Giriniz: ");
                    int yeniSifre = input.nextInt();
                    musteriler.get(secilenMusteriİndis).sifreGuncelle(yeniSifre);
                    System.out.println("Sifreniz Başarıyla Güncellendi.");
                    dosyayaKaydet();
                    break;
                case 4:
                    System.out.println("İsim: " + musteriler.get(secilenMusteriİndis).ad);
                    System.out.println("Soyisim: " + musteriler.get(secilenMusteriİndis).soyad);
                    System.out.println("Hesap Numarası: " + musteriler.get(secilenMusteriİndis).hesapNo);
                    break;
                default:
                    System.out.println("Çıkış Yapılmıştır.");
                    return;
            }
            
        }
        
    }
    
}
