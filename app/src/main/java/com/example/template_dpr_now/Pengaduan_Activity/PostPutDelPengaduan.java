package com.example.template_dpr_now.Pengaduan_Activity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostPutDelPengaduan {
    @SerializedName("id")
    private String id_pengaduan;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("no_telepon")
    @Expose
    private String no_telepon;
    @SerializedName("isi_aduan")
    @Expose
    private String isi_aduan;
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Pengaduan mPengaduan;
    @SerializedName("message")
    String message;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }

    public String getIsi_aduan() {
        return isi_aduan;
    }

    public void setIsi_aduan(String isi_aduan) {
        this.isi_aduan = isi_aduan;
    }

    public Pengaduan getmPengaduan() {
        return mPengaduan;
    }

    public void setmPengaduan(Pengaduan mPengaduan) {
        this.mPengaduan = mPengaduan;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Pengaduan getPengaduan() {
        return mPengaduan;
    }
    public void setPengaduan(Pengaduan pengaduan) {
        mPengaduan = pengaduan;
    }
    public String getId_pengaduan() {
        return id_pengaduan;
    }

    public void setId_pengaduan(String id_pengaduan) {
        this.id_pengaduan = id_pengaduan;
    }
    public PostPutDelPengaduan(){}

    public PostPutDelPengaduan(String id, String nama, String email, String nomor, String isi) {
        this.id_pengaduan = id;
        this.nama = nama;
        this.email = email;
        this.no_telepon = nomor;
        this.isi_aduan = isi;
    }
}
