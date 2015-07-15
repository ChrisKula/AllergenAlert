package com.itescia.rkouraichi_ckula.allergenalert.pojos;

public class Allergy {

    private String name = "Sample";
    private String description = "";
    private boolean isAllergicTo = false;

    public Allergy(String name) {
        this.name = name;
    }

    public Allergy(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Allergy(String name, String description, boolean isAllergicTo) {
        this.name = name;
        this.description = description;
        this.isAllergicTo = isAllergicTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllergicTo() {
        return isAllergicTo;
    }

    public void setIsAllergicTo(boolean isAllergicTo) {
        this.isAllergicTo = isAllergicTo;
    }
}