package com.example.depo.ui.inventory_categories_page.tea_materials_page;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.depo.model.CreamMaterial;
import com.example.depo.model.TeaMaterial;
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

public class TeaMaterialsViewModel extends ViewModel {
    private FirebaseFirestore firestore;
    private MutableLiveData<List<TeaMaterial>> teaMaterialsLiveData = new MutableLiveData<>();

    public TeaMaterialsViewModel() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getData(){


        firestore.collection("TeaMaterials") .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<TeaMaterial> materialsList = new ArrayList<>();
                            Log.d("TeaMaterials","reading successful");
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String materialName = (String) document.get("material_name");
                                String specificCode = (String) document.get("specific_code");
                                String expirationDate = (String) document.get("expiration_date");
                                String explanation = (String) document.get("explanation");
                                int numberOfPieces = (int) Integer.parseInt(document.get("number_of_pieces").toString());

                                TeaMaterial t = new TeaMaterial(
                                        materialName,
                                        specificCode,
                                        expirationDate,
                                        explanation,
                                        numberOfPieces);

                                materialsList.add(t);
                            }
                            teaMaterialsLiveData.setValue(materialsList);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TeaMaterials","Error reading document",e);
                    }
                });



    }

    public void addData(TeaMaterial teaMaterial){
        Map<String, Object> teaMaterialMap = new HashMap<>();
        teaMaterialMap.put("material_name",teaMaterial.getMaterialName());
        teaMaterialMap.put("specific_code",teaMaterial.getSpecificCode());
        teaMaterialMap.put("expiration_date",teaMaterial.getExpirationDate());
        teaMaterialMap.put("explanation",teaMaterial.getExplanation());
        teaMaterialMap.put("number_of_pieces",teaMaterial.getNumberOfPieces());

        firestore.collection("TeaMaterials").document()
                .set(teaMaterialMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TeaMaterials","successfully written");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("TeaMaterials","Error writing document",e);
                    }
                });
    }

    public LiveData<List<TeaMaterial>> getTeaMaterials() {
        return teaMaterialsLiveData;
    }


}
