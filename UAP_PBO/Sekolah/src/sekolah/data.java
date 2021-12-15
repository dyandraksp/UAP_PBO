/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sekolah;

/**
 *
 * @author Sntn_Prnwr
 */
public class data {
    private int nik;
    private String nama;
    private String jenis_kelamin;
    private String jabatan;
    private String status;
    private int no_telp;
    private String alamat;

    public data(int nik, String nama, String jenis_kelamin, String jabatan, String status, int no_telp, String alamat) {
        this.nik = nik;
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.jabatan = jabatan;
        this.status = status;
        this.no_telp = no_telp;
        this.alamat = alamat;
    }

    public int getNik() {
        return nik;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getStatus() {
        return status;
    }

    public int getNo_telp() {
        return no_telp;
    }

    public String getAlamat() {
        return alamat;
    }
    
            
}
