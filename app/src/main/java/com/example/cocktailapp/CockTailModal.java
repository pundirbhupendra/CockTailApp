package com.example.cocktailapp;

public class CockTailModal {

    private String drinkName;
    private String drinkImage;

    public CockTailModal(String drinkName, String drinkImage) {
        this.drinkName = drinkName;
        this.drinkImage = drinkImage;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDrinkImage() {
        return drinkImage;
    }

    public void setDrinkImage(String drinkImage) {
        this.drinkImage = drinkImage;
    }
}
