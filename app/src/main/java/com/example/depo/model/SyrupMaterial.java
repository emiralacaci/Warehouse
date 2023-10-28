package com.example.depo.model;

public class SyrupMaterial{
    private String materialName, specificCode, expirationDate, explanation;
    private int numberOfPieces;



    public SyrupMaterial(String materialName, String specificCode, String expirationDate, String explanation, int numberOfPieces) {
        this.materialName = materialName;
        this.specificCode = specificCode;
        this.expirationDate = expirationDate;
        this.explanation = explanation;
        this.numberOfPieces = numberOfPieces;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getSpecificCode() {
        return specificCode;
    }

    public void setSpecificCode(String specificCode) {
        this.specificCode = specificCode;
    }

    public int getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(int numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }
}
