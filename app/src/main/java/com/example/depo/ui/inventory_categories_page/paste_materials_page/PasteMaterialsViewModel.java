package com.example.depo.ui.inventory_categories_page.paste_materials_page;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.depo.model.CreamMaterial;
import com.example.depo.model.PasteMaterial;
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

public class PasteMaterialsViewModel extends ViewModel {
    private FirebaseFirestore firestore;
    private MutableLiveData<List<PasteMaterial>> pasteMaterialsLiveData = new MutableLiveData<>();

    public PasteMaterialsViewModel() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void getData(){


        firestore.collection("PasteMaterials") .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<PasteMaterial> materialsList = new ArrayList<>();
                            Log.d("PasteMaterials","reading successful");
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String materialName = (String) document.get("material_name");
                                String specificCode = (String) document.get("specific_code");
                                String expirationDate = (String) document.get("expiration_date");
                                String explanation = (String) document.get("explanation");
                                int numberOfPieces = (int) Integer.parseInt(document.get("number_of_pieces").toString());

                                PasteMaterial c = new PasteMaterial(
                                        materialName,
                                        specificCode,
                                        expirationDate,
                                        explanation,
                                        numberOfPieces);

                                materialsList.add(c);
                            }
                            pasteMaterialsLiveData.setValue(materialsList);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("PasteMaterials","Error reading document",e);
                    }
                });



    }

    public void addData(PasteMaterial pasteMaterial){
        Map<String, Object> pasteMaterialMap = new HashMap<>();
        pasteMaterialMap.put("material_name",pasteMaterial.getMaterialName());
        pasteMaterialMap.put("specific_code",pasteMaterial.getSpecificCode());
        pasteMaterialMap.put("expiration_date",pasteMaterial.getExpirationDate());
        pasteMaterialMap.put("explanation",pasteMaterial.getExplanation());
        pasteMaterialMap.put("number_of_pieces",pasteMaterial.getNumberOfPieces());

        firestore.collection("PasteMaterials").document()
                .set(pasteMaterialMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("PasteMaterials","successfully written");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("PasteMaterials","Error writing document",e);
                    }
                });
    }

    public LiveData<List<PasteMaterial>> getPasteMaterials() {
        return pasteMaterialsLiveData;
    }
}
