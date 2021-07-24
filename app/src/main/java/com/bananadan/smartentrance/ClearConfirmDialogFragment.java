package com.bananadan.smartentrance;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

// диалог подтверждения очистки
public class ClearConfirmDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // начинаем строить диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // ---- layout диалога ----
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_teacher_main_page_clear, null);
        builder.setView(view);


        // при нажатии на кнопку закрыть
        view.findViewById(R.id.dialog_teacher_main_page_clear_button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // закрываем диалог
                dismiss();
            }
        });

        // при нажатии на кнопку сохранить
        view.findViewById(R.id.dialog_teacher_main_page_clear_button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // возвращаем измененные оценки в активность
                ((ClearDialogInterface) getActivity()).clearAccept();

                // закрываем диалог
                dismiss();
            }
        });


        // наконец создаем диалог и возвращаем его
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }
}


interface ClearDialogInterface {
    void clearAccept();
}
