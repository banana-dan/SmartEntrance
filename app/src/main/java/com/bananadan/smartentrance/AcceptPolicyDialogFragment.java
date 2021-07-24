package com.bananadan.smartentrance;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class AcceptPolicyDialogFragment extends DialogFragment {

    static final String ARGS_LOG_TEXT = "log_text";


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // начинаем строить диалог
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // ---- layout диалога ----
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_accept_policy, null);
        builder.setView(view);


        // получаем данные из intent


        // при нажатии на кнопку закрыть
        view.findViewById(R.id.dialog_accept_policy_button_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // закрываем диалог
                dismiss();

                // закрываем активность
                try {
                    ((AcceptPolicyInterface) getActivity()).rejectPolicyDialog();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            }
        });

        // при нажатии на кнопку сохранить
        view.findViewById(R.id.dialog_accept_policy_button_ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // закрываем диалог
                dismiss();

                try {
                    ((AcceptPolicyInterface) getActivity()).acceptPolicyDialog();
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }

            }
        });


        // наконец создаем диалог и возвращаем его
        Dialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }
}

interface AcceptPolicyInterface {
    void acceptPolicyDialog();

    void rejectPolicyDialog();
}