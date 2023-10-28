package com.example.depo.ui.inventory_categories_page.paste_materials_page;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.depo.databinding.FragmentPasteMaterialsBinding;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class PasteMaterialsFragment extends Fragment {

    private FragmentPasteMaterialsBinding binding;
    private PasteMaterialsViewModel pasteMaterialsViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPasteMaterialsBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        pasteMaterialsViewModel= new ViewModelProvider(this).get(PasteMaterialsViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        /*
        pasteMaterialsViewModel.addData(new PasteMaterial(
                "kozalak",
                "1234",
                "11.10.2023",
                "gönderilmeye hazır",
                1500));

        pasteMaterialsViewModel.getData();
        pasteMaterialsViewModel.getPasteMaterials().observe(getViewLifecycleOwner(), new Observer<List<PasteMaterial>>() {
            @Override
            public void onChanged(List<PasteMaterial> pasteMaterials) {
                for(PasteMaterial p : pasteMaterials){
                    System.out.println(p.getMaterialName());
                }
            }
        });

         */


    }
/*
    public void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("ehehe");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

 */

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(),result -> {
        if (result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("resssult");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    });



}
