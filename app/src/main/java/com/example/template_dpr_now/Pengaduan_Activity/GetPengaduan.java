package com.example.template_dpr_now.Pengaduan_Activity;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class GetPengaduan {

    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Pengaduan> listDataPengaduan;
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
    public List<Pengaduan> getListDataPengaduan() {
        return listDataPengaduan;
    }
    public void setListDataPengaduan(List<Pengaduan> listDataPengaduan) {
        this.listDataPengaduan = listDataPengaduan;
    }

}
