package com.example.template_dpr_now.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostPutDelAkun {
    @SerializedName("id")
    private String id_akun;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("password")
    @Expose
    private String password;

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
        return password;
    }

    public void setEmail(String email) {
        this.password = email;
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
    public String getid_akun() {
        return id_akun;
    }

    public void setid_akun(String id_akun) {
        this.id_akun = id_akun;
    }
    public PostPutDelAkun(){}

    public PostPutDelAkun(String id, String nama, String email) {
        this.id_akun = id;
        this.nama = nama;
        this.password = email;

    }

    
    @Override
    public String toString(){
        return
                "SemuaItem{" +
                        "nama = '" + nama + '\'' +
                        ",id = '" + id_akun+ '\'' +
                        ",password = '" + password + '\'' +
                        "}";
    }
}
