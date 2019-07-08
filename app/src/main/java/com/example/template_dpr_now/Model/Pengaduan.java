package com.example.template_dpr_now.Model;

import com.google.gson.annotations.SerializedName;

public class Pengaduan {
    @SerializedName("id")
    private String id_pengaduan;
    @SerializedName("nama")
    private String nama;
    @SerializedName("email")
    private String email;
    @SerializedName("nomor")
    private String nomor;
    @SerializedName("isi")
    private String isi_aduan;


    public Pengaduan(){}

    public Pengaduan(String id, String nama, String email, String nomor, String isi) {
        this.id_pengaduan = id;
        this.nama = nama;
        this.email = email;
        this.nomor = nomor;
        this.isi_aduan = isi;
    }

}
