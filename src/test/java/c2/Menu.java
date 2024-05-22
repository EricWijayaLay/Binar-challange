package c2;
//import lombok.Data;
//@Data
public class Menu {//class utk menu (nama dan harga menu)
    //encapsulation, hanya bisa diakses di dalam class
    private String nama;
    private double harga;

    public Menu(String nama, double harga) {//constructor
        this.nama = nama;
        this.harga = harga;
    }

    public String getNama() {//getter, me return ketika method dipanggil
        return nama;
    }

    public double getHarga() {
        return harga;
    }

}
