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
import com.example.depo.model.ScanQRMaterial;
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
import java.util.Objects;

public class MainActivityViewModel extends ViewModel {
    private FirebaseFirestore firestore;
    private int count=0;
    private Material material;
    private ScanQRMaterial scanQRMaterial;
    private MutableLiveData<ScanQRMaterial> scanQRMaterialMutableLiveData=new MutableLiveData<>();
    //private HashMap<String, Object> materialHashMap=new HashMap<>();
    //private MutableLiveData<HashMap<String, Object>> materialMapLiveData = new MutableLiveData<>();

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
                            Log.d("akış","task içi dolu bulundu");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                material=new Material(
                                        (String) document.getData().get("material_name"),
                                        (String) document.getData().get("specific_code"),
                                        (String) document.getData().get("expiration_date"),
                                        (String) document.getData().get("explanation"),
                                        (String) document.getData().get("number_of_pieces"));

                            }
                            System.out.println(material.getExpirationDate());
                            scanQRMaterial=new ScanQRMaterial(material,collectionPath);
                            scanQRMaterialMutableLiveData.setValue(scanQRMaterial);
                            //materialHashMap.put("collection",collectionPath);
                            //materialHashMap.put("material",material);
                            //materialMapLiveData.setValue(materialHashMap);
                        }else {
                            Log.d("akış","task içi dolu bulunmadı");
                            if(!isMatching(scanQRMaterial.getMaterialType()) && count>=4){
                                scanQRMaterial.setMaterial(new Material());
                                scanQRMaterial.setMaterialType("none");
                                scanQRMaterialMutableLiveData.setValue(scanQRMaterial);
                                Log.d("akış","none oldu");
                                //materialHashMap.put("collection","none");
                                //materialHashMap.put("material","null");
                                //materialMapLiveData.setValue(materialHashMap);
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
        if(scanQRMaterial==null){
            scanQRMaterial=new ScanQRMaterial(null,"");
        }else {
            scanQRMaterial.setMaterial(null);
            scanQRMaterial.setMaterialType("");
        }
        count=0;
        //materialHashMap.put("collection","");
        //materialHashMap.put("material","null");
        //material=null;
        //count=0;
    }

    public LiveData<ScanQRMaterial> scanQRMaterialLiveData(){
        return scanQRMaterialMutableLiveData;
    }



    /*
    public LiveData<HashMap<String, Object>> getMaterialHash() {
        return materialMapLiveData;
    }

     */



}
