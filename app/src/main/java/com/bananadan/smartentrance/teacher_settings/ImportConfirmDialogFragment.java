package com.bananadan.smartentrance.teacher_settings;


import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.bananadan.smartentrance.R;

public class ImportConfirmDialogFragment extends DialogFragment {

    static final String ARGS_LOG_TEXT = "log_text";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // начинаем строить диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // ---- layout диалога ----
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_teacher_settings, null);
        builder.setView(view);


        // получаем данные из intent

        // имя редактируемого ученика
        String log = getArguments().getString(ARGS_LOG_TEXT);
        ((TextView) view.findViewById(R.id.dialog_teacher_settings_log_text)).setText(log);

        // при нажатии на кнопку закрыть
        view.findViewById(R.id.dialog_teacher_settings_bttn_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // закрываем диалог
                dismiss();
            }
        });

        // при нажатии на кнопку сохранить
        view.findViewById(R.id.dialog_teacher_settings_button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // возвращаем измененные оценки в активность
                ((ImportDialogInterface) getActivity()).importAccept();

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


interface ImportDialogInterface {
    void importAccept();
}