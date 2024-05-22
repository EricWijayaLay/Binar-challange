package c2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

public class BinarfundTest {
    private Binarfund binarfund;
    private Menu menu;

    @BeforeEach
    public void setUp() {
        binarfund = new Binarfund();
        menu = new Menu("Nasi Goreng", 15000);
    }

    @AfterEach
    public void tearDown() {
        binarfund = null;
        menu = null;
    }

    @Test
    public void testTambahPesanan_Positive() {
        int jumlah = 2;

        binarfund.tambahPesanan(menu, jumlah);//apakah bisa tambah menu
        Map<String, Pesanan> pesananMap = binarfund.getPesananMap().orElse(null);
        assertNotNull(pesananMap);//tidak null
        assertTrue(pesananMap.containsKey("Nasi Goreng"));//apakah menu ada
        Pesanan pesanan = pesananMap.get("Nasi Goreng");
        assertNotNull(pesanan);
        assertEquals(menu, pesanan.getMenu());//apakah atribut benar
        assertEquals(jumlah, pesanan.getJumlah());

        // Test total harga
        double hargaExpected = menu.getHarga() * jumlah;
        Optional<Double> totalHargaOptional = binarfund.getTotalHarga();
        assertTrue(totalHargaOptional.isPresent());
        assertEquals(hargaExpected, totalHargaOptional.get());
    }

    @Test//neg test : ketika skenario salah dilakukan, sehinnga hasil test harus failed
    public void testTambahPesanan_Negative() {//ketika inputan jumlah adl negatif
        int jumlah = -2;
        binarfund.tambahPesanan(menu, jumlah);
        assertFalse(binarfund.getPesananMap().isPresent());// Pengecekan pesananMap
        assertFalse(binarfund.getTotalHarga().isPresent());// Pengecekan total harga
    }

}

