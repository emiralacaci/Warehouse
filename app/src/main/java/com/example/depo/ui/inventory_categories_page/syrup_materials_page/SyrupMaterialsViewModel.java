package com.example.depo.ui.inventory_categories_page.syrup_materials_page;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.depo.model.CreamMaterial;
import com.example.depo.model.SyrupMaterial;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyrupMaterialsViewModel extends ViewModel {
    private FirebaseFirestore firestore;
    private MutableLiveData<List<SyrupMaterial>> syrupMaterialsLiveData = new MutableLiveData<>();

    public SyrupMaterialsViewModel() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getData(){


        firestore.collection("SyrupMaterials") .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<SyrupMaterial> materialsList = new ArrayList<>();
                            Log.d("SyrupMaterials","reading successful");
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String materialName = (String) document.get("material_name");
                                String specificCode = (String) document.get("specific_code");
                                String expirationDate = (String) document.get("expiration_date");
                                String explanation = (String) document.get("explanation");
                                String numberOfPieces = (String) document.get("number_of_pieces");

                                SyrupMaterial c = new SyrupMaterial(
                                        materialName,
                                        specificCode,
                                        expirationDate,
                                        explanation,
                                        numberOfPieces);

                                materialsList.add(c);
                            }
                            syrupMaterialsLiveData.setValue(materialsList);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("SyrupMaterials","Error reading document",e);
                    }
                });



    }

    public void addData(SyrupMaterial syrupMaterial){
        Map<String, Object> syrupMaterialMap = new HashMap<>();
        syrupMaterialMap.put("material_name",syrupMaterial.getMaterialName());
        syrupMaterialMap.put("specific_code",syrupMaterial.getSpecificCode());
        syrupMaterialMap.put("expiration_date",syrupMaterial.getExpirationDate());
        syrupMaterialMap.put("explanation",syrupMaterial.getExplanation());
        syrupMaterialMap.put("number_of_pieces",syrupMaterial.getNumberOfPieces());

        firestore.collection("SyrupMaterials").document()
                .set(syrupMaterialMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("SyrupMaterials","successfully written");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("SyrupMaterials","Error writing document",e);
                    }
                });
    }



    public LiveData<List<SyrupMaterial>> getSyrupMaterials() {
        return syrupMaterialsLiveData;
    }
}
