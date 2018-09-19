package com.example.chiva.bimillahappico;

public class DataPost {
    private String username;
    private String post;
    private String judul;
    private String terlapor;
    private String tanggal;
    private String harapan;



    public DataPost(String username, String post, String tanggal, String harapan, String terlapor) {
        this.username = username;
        this.post = post;
        this.judul = judul;
        this.terlapor = terlapor;
        this.tanggal = tanggal;
        this.harapan = harapan;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPost() {
        return post;
    }
    public void setPost(String post) {
        this.post = post;
    }


    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getJudul() {
        return judul;
    }

    public void setTerlapor(String terlapor) {
        this.terlapor = terlapor;
    }

    public String getTerlapor() {
        return terlapor;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    public String getTanggal() {
        return tanggal;
    }

    public void setHarapan(String harapan) {
        this.harapan = harapan;
    }

    public String getHarapan() {
        return harapan;
    }


}
