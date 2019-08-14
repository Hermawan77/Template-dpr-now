package com.example.template_dpr_now.Majalah_Activity;

public class MajalahItem {
    private String mImage;
    private String mEdisi;
    private String mTahun;
    private String mFile;

    public MajalahItem(String image, String edisi, String tahun, String file){
        mImage = image;
        mEdisi = edisi;
        mTahun = tahun;
        mFile = file;
    }

    public String getImage() {
        return mImage;
    }

    public String getEdisi() {
        return mEdisi;
    }

    public String getTahun() {
        return mTahun;
    }

    public String getFile() {
        return mFile;
    }

}
