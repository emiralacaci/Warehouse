package com.example.depo.ui.add_new_material;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.depo.R;
import com.example.depo.databinding.FragmentAddNewPasteMaterialBinding;
import com.example.depo.model.CreamMaterial;
import com.example.depo.model.PasteMaterial;
import com.example.depo.ui.MainActivity;
import com.example.depo.ui.inventory_categories_page.paste_materials_page.PasteMaterialsFragment;
import com.example.depo.ui.inventory_categories_page.paste_materials_page.PasteMaterialsViewModel;
import com.example.depo.util.CaptureAct;
import com.example.depo.util.FragmentHelper;
import com.example.depo.util.Utils;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.util.Calendar;

public class AddNewPasteMaterialFragment extends Fragment {
    private FragmentAddNewPasteMaterialBinding binding;
    private FragmentHelper helper;
    private PasteMaterialsViewModel pasteMaterialsViewModel;

    public AddNewPasteMaterialFragment(PasteMaterialsViewModel pasteMaterialsViewModel) {
        this.pasteMaterialsViewModel = pasteMaterialsViewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAddNewPasteMaterialBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        helper = new FragmentHelper(getActivity());
        ((MainActivity) getActivity()).updateStatusBarColor(R.color.paste_material_tertiary);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToCreamMaterialsFragment();
            }
        });

        binding.qrScanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startQRCodeScanner();
            }
        });

        binding.expirationDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        binding.save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeNewMaterialDataToFirebase();
            }
        });
    }

    private void writeNewMaterialDataToFirebase() {
        String materialName = binding.materialName.getText().toString();
        String specificCode = binding.specificCode.getText().toString();
        String explanation = binding.explanation.getText().toString();
        String numberOfPieces = binding.numberOfPieces.getText().toString();
        String expirationDate = binding.expirationDate.getText().toString();

        if(!Utils.areNullOrEmpty(materialName,specificCode,expirationDate,numberOfPieces,explanation)){
            Utils.showAlertDialog(getContext(), "EVET", "HAYIR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    //write data
                    pasteMaterialsViewModel.addData(
                            new PasteMaterial(materialName, specificCode,expirationDate,explanation,numberOfPieces));
                    goToCreamMaterialsFragment();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(getActivity(), "İŞLEM İPTAL EDİLDİ", Toast.LENGTH_SHORT).show();
                }
            });
        }else {
            Toast.makeText(getActivity(), "EKSİK GİRİLEN VERİ VAR !", Toast.LENGTH_SHORT).show();
        }


    }

    private void showDatePickerDialog(){
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH);
        int day = today.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String month = String.valueOf(i1+1);
                String day = String.valueOf(i2);
                if(i1+1<10){
                    month = "0"+month;
                }
                if(i2<10){
                    day = "0"+day;
                }
                binding.expirationDate.setText(day+"."+month+"."+i);
            }
        },year,month,day);
        dialog.show();
    }

    private void startQRCodeScanner(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Flaş İçin Ses Açma Tuşuna Basın !");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        launcher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> launcher = registerForActivityResult(new ScanContract(), result -> {
        if (result.getContents() != null){
            binding.specificCode.setText(result.getContents());
        }
    });

    private void goToCreamMaterialsFragment(){
        PasteMaterialsFragment fragment = new PasteMaterialsFragment();
        helper.changeFragment(R.id.body_container,fragment,"CreamMaterialsFragment");
    }
}
