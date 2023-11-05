package com.example.depo.util;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.depo.R;
import com.example.depo.databinding.DialogCreamLayoutBinding;

public class MaterialUpdateDialog extends AppCompatDialogFragment {

    private DialogCreamLayoutBinding binding;
    private CreamDialogListener listener;
    int backgroundColor=R.color.black;
    int cancelButtonColor=R.color.white;
    int updateButtonColor=R.color.white;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialogTheme);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        binding = DialogCreamLayoutBinding.inflate(inflater);

        builder.setView(binding.getRoot());


        binding.layout.setBackgroundTintList(getResources().getColorStateList(backgroundColor));

        // İptal düğmesinin rengini ayarla
        binding.cancelButton.setBackgroundTintList(getResources().getColorStateList(cancelButtonColor));

        // Güncelleme düğmesinin rengini ayarla
        binding.updateButton.setBackgroundTintList(getResources().getColorStateList(updateButtonColor));


        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updatedData = binding.updatedText.getText().toString().trim();
                if(!updatedData.isEmpty()){
                    listener.applyTexts(updatedData);
                    dismiss();
                }else {
                    Toast.makeText(getActivity(), "Bir değer girin", Toast.LENGTH_SHORT).show();
                }
            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });




        return builder.create();
    }

    public void setColor(int backgroundColor, int cancelButtonColor, int updateButtonColor) {
        this.backgroundColor = backgroundColor;
        this.cancelButtonColor = cancelButtonColor;
        this.updateButtonColor = updateButtonColor;
    }


    public void setListener(CreamDialogListener listener) {
        this.listener = listener;
    }




    public interface CreamDialogListener {
        void applyTexts(String updatedData);
    }
}
