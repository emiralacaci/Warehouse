package com.example.depo.model;

public class Material {
    private String materialName, specificCode, expirationDate, explanation,numberOfPieces;



    public Material(){

    }

    public Material(String materialName, String specificCode, String expirationDate, String explanation, String numberOfPieces) {
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

    public String getNumberOfPieces() {
        return numberOfPieces;
    }

    public void setNumberOfPieces(String numberOfPieces) {
        this.numberOfPieces = numberOfPieces;
    }
}
