package com.example.template_dpr_now.Pelayananmasyarakat_Activity;

public class PelayananmasyakatItem {
    private String mImage;
    private String mTanggal;
    private String mJudul;

    public PelayananmasyakatItem(String image, String tanggal, String judul){
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
