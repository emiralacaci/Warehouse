package com.example.depo.model;

public class ScanQRMaterial {
    private Material material;
    private String materialType;

    public ScanQRMaterial(Material material, String materialType) {
        this.material = material;
        this.materialType = materialType;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
}
