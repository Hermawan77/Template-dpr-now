package com.example.template_dpr_now.fragment;

public class F_K1_Item {
    private String mNama;
    private String mNip;
    private String mJabatan;

    public F_K1_Item(String nama, String nip, String jabatan) {
        mNama = nama;
        mNip = nip;
        mJabatan = jabatan;
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
