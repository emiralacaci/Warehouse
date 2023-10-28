package com.example.depo.ui;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.depo.R;
import com.example.depo.model.CreamMaterial;
import com.example.depo.model.Material;
import com.example.depo.model.PasteMaterial;
import com.example.depo.ui.detail_of_material_pages.cream_material_detail_page.CreamMaterialDetailFragment;
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

public class MainActivityViewModel extends ViewModel {
    private FirebaseFirestore firestore;
    private int count=0;
    private Material material;
    private MutableLiveData<String> materialStringLiveData = new MutableLiveData<>();

    public MainActivityViewModel() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void queryForSpecificCode(String collectionPath, String specificCode){
        firestore.collection(collectionPath)
                .whereEqualTo("specific_code",specificCode)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        count++;
                        System.out.println(count);
                        if (!task.getResult().isEmpty()){
                            materialStringLiveData.setValue(collectionPath);
                        }else {
                            if(!isMatching(materialStringLiveData.getValue()) && count>=4){
                                materialStringLiveData.setValue("none");
                            }
                        }
                    }
                });


    }

    private boolean isMatching(String materialString){
        if(materialString.matches("PasteMaterials")){
            return true;
        }
        if(materialString.matches("TeaMaterials")){
            return true;
        }
        if(materialString.matches("SyrupMaterials")){
            return true;
        }
        if(materialString.matches("CreamMaterials")){
            return true;
        }
        return false;
    }

    public void restartMaterialStringLiveData(){
        materialStringLiveData.setValue("");
        count=0;
    }

    public LiveData<String> getMaterialStringLiveData() {
        return materialStringLiveData;
    }

}
