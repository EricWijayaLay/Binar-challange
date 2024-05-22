package c2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.Optional;

public class cetakStruk {

    public static void cetak(Binarfund binarfund) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("struk.txt"))) {
            writer.write("========================================\n");
            writer.write("Terima kasih telah memesan di Binarfund!\n");
            writer.write("Berikut adalah rincian pesanan anda :\n\n");

            // Mengekstrak nilai Map dari Optional
            Optional<Map<String, Pesanan>> optionalPesananMap = binarfund.getPesananMap();
            if (optionalPesananMap.isPresent()) {
                Map<String, Pesanan> pesananMap = optionalPesananMap.get();
                // Mengurutkan rincian pesanan berdasarkan jumlah pesanan dari terbesar ke terkecil
                pesananMap.values().stream()
                        .sorted(Comparator.comparingInt(Pesanan::getJumlah).reversed())
                        .forEach(pesanan -> {
                            try {
                                writer.write(String.format("%-32s x%-5d%n", pesanan.getMenu().getNama(), pesanan.getJumlah()));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
            }

            // Menggunakan getTotalHarga() yang mengembalikan Optional
            Optional<Double> optionalTotalHarga = binarfund.getTotalHarga();
            if (optionalTotalHarga.isPresent()) {
                double totalHarga = optionalTotalHarga.get();
                writer.write("------------------------------------+\n");
                writer.write(String.format("%-28s Rp%-18.0f%n", "Total:", totalHarga));
            }

            writer.write("\n========================================\n");
            writer.write("Simpan struk sebagai bukti transaksi\n");
            writer.write("Have a great day ^_^\n");
            writer.write("========================================\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

