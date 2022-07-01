package com.example.villariveradbsystem.databasegui;

public class DatabaseInquiries {
    private int francis, simon, fatima, miguel, gabriel, pax2, pax4, pax18, guessCount;
    private String firstname, lastname, contactNumber, date, time;

    public void setFrancis(int francis) {
        this.francis = francis;
    }

    public void setSimon(int simon) {
        this.simon = simon;
    }

    public void setFatima(int fatima) {
        this.fatima = fatima;
    }

    public void setMiguel(int miguel) {
        this.miguel = miguel;
    }

    public void setGabriel(int gabriel) {
        this.gabriel = gabriel;
    }

    public void setPax2(int pax2) {
        this.pax2 = pax2;
    }

    public void setPax4(int pax4) {
        this.pax4 = pax4;
    }

    public void setPax18(int pax18) {
        this.pax18 = pax18;
    }

    public void setGuessCount(int guessCount) {
        this.guessCount = guessCount;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getFrancis() {
        return francis;
    }

    public int getSimon() {
        return simon;
    }

    public int getFatima() {
        return fatima;
    }

    public int getMiguel() {
        return miguel;
    }

    public int getGabriel() {
        return gabriel;
    }

    public int getPax2() {
        return pax2;
    }

    public int getPax4() {
        return pax4;
    }

    public int getPax18() {
        return pax18;
    }

    public int getGuessCount() {
        return guessCount;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public DatabaseInquiries(String firstname, String lastname, int guessCount, String contactNumber, String date, String time, int francis, int simon, int fatima, int miguel, int gabriel, int pax2, int pax4, int pax18) {
        this.francis = francis;
        this.simon = simon;
        this.fatima = fatima;
        this.miguel = miguel;
        this.gabriel = gabriel;
        this.pax2 = pax2;
        this.pax4 = pax4;
        this.pax18 = pax18;
        this.guessCount = guessCount;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactNumber = contactNumber;
        this.date = date;
        this.time = time;
    }
}
