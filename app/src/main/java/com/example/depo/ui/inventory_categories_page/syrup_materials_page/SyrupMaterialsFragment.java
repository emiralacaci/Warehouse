package com.example.depo.ui.inventory_categories_page.syrup_materials_page;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.depo.databinding.FragmentSyrupMaterialsBinding;
import com.example.depo.model.PasteMaterial;
import com.example.depo.model.SyrupMaterial;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class SyrupMaterialsFragment extends Fragment {

    private FragmentSyrupMaterialsBinding binding;
    private SyrupMaterialsViewModel syrupMaterialsViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSyrupMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        syrupMaterialsViewModel = new ViewModelProvider(this).get(SyrupMaterialsViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }
}
