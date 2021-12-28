import java.util.InputMismatchException;
import java.sql.*;
import com.mysql.cj.protocol.Resultset;
import java.util.Scanner;

public class transaksi extends barang {

    static Connection conn;
    String urll = "jdbc:mysql://localhost:3306/javaminimarket";

    Scanner inputan = new Scanner (System.in);

    public transaksi(int hargabrg, int jumlahbrg) {
        super(hargabrg, jumlahbrg);
    }

    @Override
    public int subtotal() {  
        subttl = hargabrg*jumlahbrg;
        //System.out.println("\nSubtotal : " + subttl);
        return subttl;
    }

    @Override
    public int diskon() { 
        if(subttl>100000 && subttl<=200000){
            diskonn = 5000;
        }
        else if (subttl>200000 && subttl<=500000){
            diskonn = 10000;
        }
        else if (subttl>500000 && subttl<=750000){
            diskonn = 20000;
        }
        else if (subttl>750000){
            diskonn = 50000;
        }
        else {diskonn = 0;}
        //System.out.println("Diskon yang didapat : " + diskonn);
        return diskonn;
    }

    @Override
    public int totalharga() {  
        total = subttl - diskonn;
        //System.out.println("\nTotal : " + total);
        return total;    
    }

    @Override
    public void tampil(){

        System.out.println("Nomor transaksi : " + notrans + "\n");
        System.out.println("Kasir : " + kasirrr);
        System.out.println("\nNama barang : " + namabrg);
        System.out.println("Ukuran : " + ukurn[ukur]);
        System.out.println("Jumlah barang : " + jumlahbrg);
        System.out.println("\nSubtotal : " + subttl);
        System.out.println("Diskon yang didapat : " + diskonn);
        System.out.println("\nTotal harga: " + total);

    }

    public void view()throws SQLException{   //vieeewwww
    
        System.out.println("Daftar isi seluruh minimarket");
        String sql = "SELECT * FROM minimarket";
        conn = DriverManager.getConnection(urll,"root","");
        Statement statement = conn.createStatement();
		ResultSet result = statement.executeQuery(sql);
        
        while (result.next())
        {
            System.out.print("\nNo transaksi\t: ");
            System.out.print(result.getInt("notransaksi"));
            
            System.out.print("\nKasir\t\t: ");
            System.out.print(result.getString("kasir"));

            System.out.print("\nNama barang\t: ");
            System.out.print(result.getString("namabarang"));
            
            System.out.print("\nUkuran barang\t: ");
            System.out.print(result.getString("ukuranbarang"));

            System.out.print("\nHarga\t\t: Rp.");
            System.out.print(result.getInt("harga"));
            
            System.out.print("\nJumlah barang\t: ");
            System.out.print(result.getInt("jumlah"));

            System.out.print("\nTotal bayar\t: Rp.");
            System.out.println(result.getInt("totalbayar"));

        }
    }


    public void save() throws SQLException{
        try{
            // notransaksi();
            kasir();        
            namabarang();
            ukuran();
            hargabarang();
            jumlah();
            subtotal();
            diskon();
            totalharga();
            tampil();
    
            String sql = "INSERT INTO minimarket (notransaksi, kasir, namabarang, ukuranbarang, harga, jumlah, totalbayar) VALUES (0,'"+kasirrr+"','"+namabrg+"','"+ukurannya+"','"+hargabrg+"' ,'"+jumlahbrg+"' ,'"+total+"' )";

            conn = DriverManager.getConnection(urll,"root","");
            Statement statement = conn.createStatement();
            statement.execute(sql);
            System.out.println("Berhasil input data!!");
        }
        
        catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Error!! masukkan indeks ukuran dengan benar");
        }

        catch (InputMismatchException e){
            System.err.println("Input pilihan dengan angka saja");
        }
        
    }

    public void delete() throws SQLException{
        view();
        try{
            System.out.print("Masukkan nomor transaksi yang akan dihapus : ");
            int keyword = inputan.nextInt();

            String sql = "DELETE FROM minimarket WHERE notransaksi = "+ keyword;

            conn = DriverManager.getConnection(urll,"root","");
	        Statement statement = conn.createStatement();

            if(statement.executeUpdate(sql) > 0){
	            System.out.println("Berhasil menghapus data minimarket (Nomor "+keyword+")");
	        }
        }
        catch(SQLException e){
	        System.out.println("Terjadi kesalahan dalam menghapus data");
	    }
        catch(Exception e){
            System.out.println("masukan data yang benar");
        }
    }


    public void search() throws SQLException{
        System.out.print("Masukkan Nama barang yang ingin dilihat : ");    
		String keyword = inputan.nextLine();
		
		String sql = "SELECT * FROM minimarket WHERE namabarang LIKE '%"+keyword+"%'";
		conn = DriverManager.getConnection(urll,"root","");
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql); 

        while (result.next()){
            System.out.print("\nNo transaksi\t: ");
            System.out.print(result.getInt("notransaksi"));
            System.out.print("\nKasir\t\t: ");
            System.out.print(result.getString("kasir"));
            System.out.print("\nNama barang\t: ");
            System.out.print(result.getString("namabarang"));
            System.out.print("\nUkuran barang\t: ");
            System.out.print(result.getString("ukuranbarang"));
            System.out.print("\nHarga\t\t: ");
            System.out.print(result.getInt("harga"));
            System.out.print("\nJumlah\t\t: ");
            System.out.print(result.getInt("jumlah"));
            System.out.print("\nTotal bayar\t: ");
            System.out.println(result.getInt("totalbayar"));
        }
        
    }


    public void update() throws SQLException{
        view();
        try{
            System.out.print("\nMasukkan nomor transaksi barang yang hendak diubah: ");
            Integer keywordd = inputan.nextInt();

            String sql = "SELECT * FROM minimarket WHERE notransaksi = " +keywordd;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if (result.next())
            {
                System.out.print("\nNama baru \t: ");
                String namabarubrg = inputan.next();
                
                System.out.print("\n");
                sql = "UPDATE minimarket SET namabarang='"+namabarubrg+"' WHERE notransaksi='"+keywordd+"'";
            
                // conn = DriverManager.getConnection(urll,"root","");
                // Statement statementt = conn.createStatement();
                // statementt.execute(sql);
                // System.out.println("Berhasil memperbaharui data transaksi (Nomor "+keywordd+")");

                if(statement.executeUpdate(sql) > 0)
                {
                    System.out.println("Berhasil memperbaharui data transaksi (Nomor "+keywordd+")");
                }
            }
            statement.close();
        }

            catch (SQLException e){
                System.err.println("Terjadi kesalahan dalam mengedit data transaksi");
                System.err.println(e.getMessage());
            }
        
    }

}
