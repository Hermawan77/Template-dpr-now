package com.example.template_dpr_now.Undangundang_Activity;

public class UndangUndangItem {
    private String mNomor;
    private String mTentang;
    private String mDoc;

    public UndangUndangItem(String nomor, String tentang, String file){
        mNomor = nomor;
        mTentang = tentang;
        mDoc = file;
    }

    public String getTentang() {
        return mTentang;
    }

    public String getNomor() {
        return mNomor;
    }

    public String getDoc() {
        return mDoc;
    }
}
