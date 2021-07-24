package com.bananadan.smartentrance;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bananadan.smartentrance.teacher_settings.TeacherSettingsActivity;

public class StudentSettingsActivity extends AppCompatActivity {

    public static final String PREF_NAME = "learner_name";
    public static final String PREF_ID = "learner_id";

    EditText nameEditText;
    EditText idEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_settings);
        Toolbar toolbar = findViewById(R.id.base_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // текст версии приложения
        ((TextView) findViewById(R.id.activity_student_settings_version_text)).setText("v"+ BuildConfig.VERSION_NAME);

        nameEditText = findViewById(R.id.student_settings_edit_text_name);
        idEditText = findViewById(R.id.student_settings_edit_text_id);

        // получаем сохраненные данные
        SharedPreferences preferences =
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        { // имя
            String errorString = getResources().getString(R.string.no_name_text);
            String name = preferences.getString(StudentSettingsActivity.PREF_NAME, errorString);
            if (!name.equals(errorString)) {
                nameEditText.setText(name);
            }
        }
        { // id
            long id = preferences.getLong(StudentSettingsActivity.PREF_ID, -1L);
            if (0L < id && id < 9999999999L) {
                idEditText.setText(Long.toString(id));
            }
        }

        // сброс пользовательского режима
        findViewById(R.id.student_settings_delete_user_mode_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();
// я специально убрал это, вдруг пользователь случайно нажмет на кнопку выхода
// name
//                editor.putString(StudentSettingsActivity.PREF_NAME, getResources().getString(R.string.no_name_text));
//                // id
//                editor.putLong(StudentSettingsActivity.PREF_ID, -1L);

                // mode
                editor.putInt(TeacherSettingsActivity.PREF_USER_MODE, -1);
                editor.apply();

                setResult(StudentMainPage.RESULT_CLEAR_DATA);
                finish();
            }
        });

        // сохранение information по нажатию на кнопку
        findViewById(R.id.student_settings_save_user_info_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // редактируем сохранение
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                SharedPreferences.Editor editor = preferences.edit();

                // имя
                if (!nameEditText.getText().toString().equals("")) {
                    editor.putString(PREF_NAME, nameEditText.getText().toString());
                } else {
                    editor.putString(PREF_NAME, getResources().getString(R.string.no_name_text));
                }
                // id
                try {
                    long id = Long.parseLong(idEditText.getText().toString().trim()) % 10000000000L;
                    editor.putLong(PREF_ID, id);
                } catch (java.lang.NumberFormatException e) {
                    editor.putLong(PREF_ID, -1L);
                }
                editor.apply();

                setResult(StudentMainPage.RESULT_UPDATE);
                finish();
            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {//кнопка назад в actionBar
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    interface MyBackDialogInterface {
        void onBackData(String tag);
    }
}
