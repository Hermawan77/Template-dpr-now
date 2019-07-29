package com.example.template_dpr_now;

class AgendaItem {
    private String mTanggal;
    private String mJam;
    private String mJudul;
    private String mDeskripsi;

    public AgendaItem(String tanggal, String jam, String judul, String deskripsi){
        mTanggal = tanggal;
        mJam = jam;
        mJudul = judul;
        mDeskripsi = deskripsi;
    }

    public String getTanggal (){
        return mTanggal;
    }

    public String getJam (){
        return mJam;
    }

    public String getJudul (){
        return mJudul;
    }

    public String getDeskripsi (){
        return mDeskripsi;
    }
}
