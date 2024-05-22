package c2;

//import lombok.Data;
//@Data
public class Pesanan {//class utk menghitung harga, menyimpan menu yg diinputkan
    private Menu menu;
    private int jumlah;
    public Pesanan(Menu menu, int jumlah) {//constructor yang memanggil class Menu
        this.menu = menu;
        this.jumlah = jumlah;
    }
    public Menu getMenu() {
        return menu;
    }
    public int getJumlah() {
        return jumlah;
    }
    public double getTotalHarga() {//constructor menghitung harga
        return menu.getHarga() * jumlah;
    }
}
