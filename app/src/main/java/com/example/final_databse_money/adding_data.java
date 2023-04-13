package com.example.final_databse_money;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class adding_data extends AppCompatActivity
{
    TextView date_dayI, timeI;
    EditText account_income2, category_income2, amount_income2, note_income2, descriptionI;
    Button save_income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_data);

        save_income = findViewById(R.id.save_income);

        date_dayI = findViewById(R.id.date_dayI);
        timeI = findViewById(R.id.timeI);

        account_income2 = findViewById(R.id.account_income2);
        category_income2 = findViewById(R.id.category_income2);
        amount_income2 = findViewById(R.id.amount_income2);
        note_income2 = findViewById(R.id.note_income2);
        descriptionI = findViewById(R.id.descriptionI);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat current = new SimpleDateFormat("dd/MM/yyyy (EEE)");
        String currentFixed = current.format(new Date());
        date_dayI.setText(currentFixed);

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat current2 = new SimpleDateFormat("hh:mm aa");
        String currentFixed2 = current2.format(new Date());
        timeI.setText(currentFixed2);

        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int mHour = calendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = calendar.get(Calendar.MINUTE);
        calendar.set(year, month, day, mHour, mMinute);


        @SuppressLint("SetTextI18n")
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, (view1, year1, month1, dayOfMonth) -> {

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat simpledateformat = new SimpleDateFormat("EEE");

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat format = new SimpleDateFormat("dd/MM");

            Date date = new Date(year1, (month1), dayOfMonth);

            Date dateDay = new Date(year1, month1, (dayOfMonth - 1));

            String strDate = format.format(date);
            String dayOfWeek = simpledateformat.format(dateDay);

            date_dayI.setText(strDate + "/" + year1 + " (" + dayOfWeek + ") ");

        }, year, month, day);

        @SuppressLint("SetTextI18n")
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, (view12, hourOfDay, minute) -> {

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat mFormat = new SimpleDateFormat("aa");

            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat mFormat23 = new SimpleDateFormat("hh:mm");

            Date date34 = new Date(year, month, day, hourOfDay, minute);
            String time = mFormat.format(date34);
            String strDate = mFormat23.format(date34);

            timeI.setText(strDate + " " + time);
        }, mHour, mMinute, DateFormat.is24HourFormat(this));

        date_dayI.setOnClickListener(v -> datePickerDialog.show());

        timeI.setOnClickListener(v -> timePickerDialog.show());

        save_income.setOnClickListener(v -> {

            String saveDate = date_dayI.getText().toString();
            String saveAccount = account_income2.getText().toString();
            String saveCategory = category_income2.getText().toString();
            String saveAmount = amount_income2.getText().toString();
            String saveNote = note_income2.getText().toString();
            String saveDescription = descriptionI.getText().toString();

            if (saveAccount.isEmpty()) {
                account_income2.setError("required");
            }
            if (saveCategory.isEmpty()) {
                category_income2.setError("required");
            }
            if (saveAmount.isEmpty()) {
                amount_income2.setError("required");
            }
            if (saveNote.isEmpty()) {
                note_income2.setError("required");
            } else {
                Log.d("saveDate",saveDate);
                Log.d("saveAccount",saveAccount);
                Log.d("saveCategory",saveCategory);
                Log.d("saveAmount",saveAmount);
                Log.d("saveNote",saveNote);

                saveTask(saveDate, saveAccount, saveCategory, saveNote, saveAmount);
            }
        });

    }

    private void saveTask(String saveDate, String saveAccount, String saveCategory, String saveNote, String saveAmount)
    {
        Intent data = new Intent();

        data.putExtra("extra_Date", saveDate);
        data.putExtra("extra_Account", saveAccount);
        data.putExtra("extra_Category", saveCategory);
        data.putExtra("extra_Amount", saveAmount + "/-");
        data.putExtra("extra_Note", saveNote);
//        data.putExtra("extra_Description", saveDescription);

        int id = getIntent().getIntExtra("extra_id", -1);

        if (id != -1) {
            data.putExtra("extra_id", id);
        }
        setResult(RESULT_OK, data);
        finish();

        Toast.makeText(this, "Data gone to database", Toast.LENGTH_SHORT).show();
    }
}