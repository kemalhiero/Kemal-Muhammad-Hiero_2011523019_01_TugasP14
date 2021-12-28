import java.util.Scanner;
import java.sql.*;
// import com.mysql.cj.protocol.Resultset;


public class program {
    
    public static void main(String[] args) throws SQLException {
        
        Connection conn;
        String url = "jdbc:mysql://localhost:3306/javaminimarket";
        
        int pilihann;
        Scanner terimaInput = new Scanner (System.in);
        boolean menu=true;
        
        transaksi trs = new transaksi(0, 0);

        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,"root","");
			System.out.println("Class Driver ditemukan");
			

            while (menu){
                System.out.println("\n------------------");
                System.out.println("    Minimarket");
                System.out.println("------------------");
                System.out.println("1. Lihat Data");
                System.out.println("2. Beli barang(tambah data)");
                System.out.println("3. Ubah nama barang(update data)");
                System.out.println("4. Hapus Data");
                System.out.println("5. Cari Data");
                System.out.println("6. Selesai\n");

                System.out.print("\nPilihan anda (1/2/3/4/5/6): ");
                pilihann = terimaInput.nextInt();

                switch (pilihann) {
                    case 1:
                        trs.view();
                        break;
                    case 2:
                        trs.save();
                        break;
                    case 3:
                        trs.update();
                        break;
                    case 4:
                        trs.delete();
                        break;
                    case 5:
                        trs.search();
                        break;
                    case 6:
                        menu = false;
                        break;
                
                    default:
                        System.err.println("\nInput tidak ditemukan\nSilakan pilih [1-6]");
                        break;
                }
            }

        }
        catch(ClassNotFoundException ex) {
            System.err.println("Driver Error");
            System.exit(0);
        }
        catch(SQLException e){
            System.err.println("Tidak berhasil koneksi");
            System.err.println(e.getMessage());
        }
                    

        // trs.notransaksi();
        // trs.kasir();        
        // trs.namabarang();
        // trs.ukuran();
        // trs.hargabarang();
        // trs.jumlah();
        // trs.subtotal();
        // trs.diskon();
        // trs.totalharga();
        // System.out.println("---------------------------------");
        // trs.tampil();

        
        System.out.println("\nSelesai\n");

    }
}
