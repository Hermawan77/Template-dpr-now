package com.example.template_dpr_now;

public class Aspirasii {
    int id;
    String name, email, date, time, essai, pilihan, phone;

    public Aspirasii(int id, String name, String email, String phone, String date, String time, String essai, String pilihan) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.date = date;
        this.time = time;
        this.essai = essai;
        this.pilihan = pilihan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone (String phone){
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getEssai() {
        return essai;
    }

    public String getPilihan() {
        return pilihan;
    }
}
