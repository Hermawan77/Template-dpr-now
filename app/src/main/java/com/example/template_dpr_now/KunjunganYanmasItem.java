package com.example.template_dpr_now;

public class KunjunganYanmasItem {
    private String mImage;
    private String mTanggal;
    private String mJudul;

    public KunjunganYanmasItem(String image, String tanggal, String judul){
        mImage = image;
        mTanggal = tanggal;
        mJudul = judul;
    }

    public String getImage() {
        return mImage;
    }

    public String getTanggal() {
        return mTanggal;
    }

    public String getJudul() {
        return mJudul;
    }
}
