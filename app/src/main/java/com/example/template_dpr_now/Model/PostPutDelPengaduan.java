package com.example.template_dpr_now.Model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelPengaduan {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    Pengaduan mPengaduan;
    @SerializedName("message")
    String message;
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
}
