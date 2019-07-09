package com.example.template_dpr_now;

public class Pilihann {
    int id;
    String name, email, date, time, essai, pilihan;
    double phone;

    public Pilihann(int id, String name, String email, String date, String time, String essai, String pilihan, double phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.date = date;
        this.time = time;
        this.essai = essai;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
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

    public double getPhone() {
        return phone;
    }
}
