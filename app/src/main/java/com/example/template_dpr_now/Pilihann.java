package com.example.template_dpr_now;

public class Pilihann {
    int id;
    String name, email, date, time, essai, pilihan;
    double phone;

    public Pilihann(int id, String name, String email, double phone, String date, String time, String essai, String pilihan) {
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

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getPhone() {
        return phone;
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
