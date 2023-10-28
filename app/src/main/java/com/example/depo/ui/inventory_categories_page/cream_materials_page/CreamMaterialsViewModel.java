package com.example.depo.ui.inventory_categories_page.cream_materials_page;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.depo.model.CreamMaterial;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreamMaterialsViewModel extends ViewModel {
    private FirebaseFirestore firestore;
    private MutableLiveData<List<CreamMaterial>> creamMaterialsLiveData = new MutableLiveData<>();
    private List<CreamMaterial> materialsList;

    public CreamMaterialsViewModel() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getData(){


        firestore.collection("CreamMaterials")
                .orderBy("createdAt", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            materialsList = new ArrayList<>();
                            Log.d("CreamMaterials","reading successful");
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String materialName = (String) document.get("material_name");
                                String specificCode = (String) document.get("specific_code");
                                String expirationDate = (String) document.get("expiration_date");
                                String explanation = (String) document.get("explanation");
                                String numberOfPieces = (String) document.get("number_of_pieces");

                                CreamMaterial c = new CreamMaterial(
                                        materialName,
                                        specificCode,
                                        expirationDate,
                                        explanation,
                                        numberOfPieces);

                                materialsList.add(c);
                            }
                            creamMaterialsLiveData.setValue(materialsList);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("CreamMaterials","Error reading document",e);
                    }
                });



    }

    public void addData(CreamMaterial creamMaterial){
        Map<String, Object> creamMaterialMap = new HashMap<>();
        creamMaterialMap.put("material_name",creamMaterial.getMaterialName());
        creamMaterialMap.put("specific_code",creamMaterial.getSpecificCode());
        creamMaterialMap.put("expiration_date",creamMaterial.getExpirationDate());
        creamMaterialMap.put("explanation",creamMaterial.getExplanation());
        creamMaterialMap.put("number_of_pieces",creamMaterial.getNumberOfPieces());
        creamMaterialMap.put("createdAt", FieldValue.serverTimestamp());

        firestore.collection("CreamMaterials").document()
                .set(creamMaterialMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("CreamMaterials","successfully written");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("CreamMaterials","Error writing document",e);
                    }
                });
    }

    public CreamMaterial getCreamMaterialUsingPosition(int position){
        return materialsList.get(position);
    }

    public LiveData<List<CreamMaterial>> getCreamMaterials() {
        return creamMaterialsLiveData;
    }

}
