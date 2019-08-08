package com.example.template_dpr_now.Berita_Activity;

class Berita_Item {
    private String mTanggal;
    private String mKategori;
    private String mImageUrl;
    private String mJudul;
    private String mIsi;

    public Berita_Item(String Tanggal, String Kategori, String ImageUrl, String Judul, String Isi) {
        mTanggal = Tanggal;
        mKategori = Kategori;
        mImageUrl = ImageUrl;
        mJudul = Judul;
        mIsi = Isi;
    }


    public String getTanggal() {
        return mTanggal;
    }

    public String getKategori() {
        return mKategori;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public String getJudul() {
        return mJudul;
    }

    public String getIsi() {
        return mIsi;
    }

}
