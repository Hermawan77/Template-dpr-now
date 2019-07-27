package com.example.template_dpr_now.fragment;

public class F_K1_Item {
    private String mImageUrl;
    private String mNama;
    private String mNip;
    private String mJabatan;

    public F_K1_Item(String imageUrl, String nama, String nip, String jabatan) {
        mImageUrl = imageUrl;
        mNama = nama;
        mNip = nip;
        mJabatan = jabatan;
    }


    public String getImageUrl(){
        return mImageUrl;
    }

    public String getNama() {
        return mNama;
    }

    public String getNip() {
        return mNip;
    }

    public String getJabatan() {
        return mJabatan;
    }

}
