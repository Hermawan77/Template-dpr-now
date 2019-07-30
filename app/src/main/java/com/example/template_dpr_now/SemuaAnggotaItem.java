package com.example.template_dpr_now;

public class SemuaAnggotaItem {
    private String mNama;
    private String mFraksi;
    private String mDapil;
    private String mImageUrl;

    public SemuaAnggotaItem(String namaanggota, String fraksi, String dapil, String imageurl){
        mNama = namaanggota;
        mFraksi = fraksi;
        mDapil = dapil;
        mImageUrl = imageurl;
    }


    public String getNamaAnggota() {
        return mNama;
    }

    public String getFraksi() {
        return mFraksi;
    }

    public String getDapil() {
        return mDapil;
    }

    public String getImageUrl() {
        return mImageUrl;
    }
}