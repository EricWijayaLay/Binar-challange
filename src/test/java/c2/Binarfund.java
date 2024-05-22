package c2;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Binarfund{
    private Map<String, Pesanan> pesananMap;
    private double totalHarga;
    public Binarfund() {//inialisasi
        this.pesananMap = new HashMap<>();
        this.totalHarga = 0;
    }

    public void tambahPesanan(Menu menu, int jumlah) {//method utk menambah pesana ke hashmap
        String namaMenu = menu.getNama();
        if (pesananMap.containsKey(namaMenu)) {
            Pesanan pesanan = pesananMap.get(namaMenu);
            pesananMap.put(namaMenu, new Pesanan(menu, pesanan.getJumlah() + jumlah));
        } else {
            pesananMap.put(namaMenu, new Pesanan(menu, jumlah));
        }
        totalHarga += menu.getHarga() * jumlah;
    }

    //optional class
    public Optional<Map<String, Pesanan>> getPesananMap() {
        return Optional.ofNullable(pesananMap);
    }

    public Optional<Double> getTotalHarga() {
        return Optional.ofNullable(totalHarga);
    }


    public static void main(String[] args) {
        boolean r = true;
        Scanner input = new Scanner(System.in);
        Binarfund binarfund = new Binarfund();

        Menu[] daftarMenu = {//array menu
                new Menu("Nasi Goreng", 15000),
                new Menu("Mie Goreng", 13000),
                new Menu("Nasi + Ayam", 18000),
                new Menu("Es Teh Manis", 3000),
                new Menu("Es Jeruk", 5000)
        };
//        //cth anymatch
//        boolean adaAyamBakar = Arrays.stream(daftarMenu)
//                .anyMatch(menu -> menu.getNama().equalsIgnoreCase("mie goreng"));
//        if (adaAyamBakar) {
//            System.out.println("Menu Ayam Bakar tersedia!");
//        } else {
//            System.out.println("Menu Ayam Bakar tidak tersedia.");
//        }


        while (r) {
            System.out.println("Silahkan pilih menu :");
            //lambda
            IntStream.range(0, daftarMenu.length)
                    .forEach(i -> System.out.printf("%d. %-15s | %7.0f%n", i + 1, daftarMenu[i].getNama(), daftarMenu[i].getHarga()));
            System.out.println("Pilih menu :");
            int i=0;
            try {
                i = input.nextInt();
                if (i < 1 || i > daftarMenu.length) {//ketika inputan diluar range
                    System.out.println("Menu tidak tersedia");
                    continue;
                }
            } catch (InputMismatchException e) {
                System.out.println("Inputan harus berupa angka");
                input.next(); // Membersihkan buffer
                continue;
            }

            int q = 0;
            do {
                try {
                    System.out.println("Masukkan jumlah :");
                    q = input.nextInt();
                    if (q <= 0) {//ketika inputan kurang dari atau sama dengan 0
                        System.out.println("Inputkan jumlah dengan benar");
                        continue; // input ulang
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Inputan harus berupa angka");
                    input.next();
                    continue; // input ulang
                }
                break; // Keluar dari loop saat input valid
            } while (true); // Loop terus sampai mendapat input valid

            binarfund.tambahPesanan(daftarMenu[i - 1], q);//memanggil method tambahpesanan


            String pesan;
            boolean validInput = false;
            while (!validInput) {
                System.out.println("Apakah anda ingin memesan lagi?(y/n)");
                pesan = input.next();
                if (pesan.equalsIgnoreCase("n")) {
                    r=false;//keluar dari while(r)
                }
                try {
                    if (!pesan.equalsIgnoreCase("y") && !pesan.equalsIgnoreCase("n")) {
                        throw new Exception("Inputan harus 'y' atau 'n'");//jika inputan bukan y/n
                    }
                    validInput = true;
                } catch (Exception e) {
                    System.out.println("Terjadi kesalahan : " + e.getMessage());
                }
            }
        }
        //stream ordering jumlah pesanan
        List<Pesanan> sortedPesanan = binarfund.getPesananMap()
                .map(map -> map.values().stream()
                        .sorted(Comparator.comparingInt(Pesanan::getJumlah).reversed())
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());

        System.out.println("========================================");
        System.out.println("Terima kasih telah memesan di Binarfund!\n");
        System.out.println("Berikut adalah rincian pesanan anda :\n");
        for (Pesanan pesanan : sortedPesanan) {
            System.out.printf("%-32s x%-5d%n", pesanan.getMenu().getNama(), pesanan.getJumlah());
        }
        System.out.println("------------------------------------+");
        System.out.printf("%-28s Rp%-18.0f%n", "Total:", binarfund.totalHarga);
        System.out.println("\n========================================");
        System.out.println("Simpan struk sebagai bukti transaksi");
        System.out.println("Have a great day ^_^");
        System.out.println("========================================");
        cetakStruk.cetak(binarfund);
    }
}
//lombok
//unit testing
//RDB & ORM
