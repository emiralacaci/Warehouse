package com.example.depo.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class CreamDialog extends AppCompatDialogFragment {

    private DialogCreamLayoutBinding binding;
    private CreamDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialogTheme);

        LayoutInflater inflater = requireActivity().getLayoutInflater();
        binding = DialogCreamLayoutBinding.inflate(inflater);

        builder.setView(binding.getRoot());

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

    public void setListener(CreamDialogListener listener) {
        this.listener = listener;
    }

    /*
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (CreamDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

     */

    public interface CreamDialogListener {
        void applyTexts(String updatedData);
    }
}
