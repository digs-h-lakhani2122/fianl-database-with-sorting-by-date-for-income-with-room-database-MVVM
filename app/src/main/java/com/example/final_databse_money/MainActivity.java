package com.example.final_databse_money;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton floatingActionButton;
    RecyclerView recycler;
    ViewModel viewModel;
    List<ListItem> consolidatedList = new ArrayList<>();
    Adapter adapter;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        recycler = findViewById(R.id.recycler);

        floatingActionButton.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, adding_data.class);
            startActivityForResult(intent, 101010);
        });

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        viewModel.getAllIncomes().observe(this, pojoOfJsonArrays -> {
            consolidatedList.clear();
            HashMap<String, List<PojoOfJsonArray>> groupedHashMap = groupDataIntoHashMap(pojoOfJsonArrays);


            for (String date : groupedHashMap.keySet()) {
                DateItem dateItem = new DateItem();
                dateItem.setDate(date);
                consolidatedList.add(dateItem);


                for (PojoOfJsonArray pojoOfJsonArray : groupedHashMap.get(date)) {
                    GeneralItem generalItem = new GeneralItem();
                    generalItem.setPojoOfJsonArray(pojoOfJsonArray);//setBookingDataTabs(bookingDataTabs);
                    consolidatedList.add(generalItem);
                }
            }

            adapter = new Adapter(consolidatedList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recycler.setLayoutManager(layoutManager);
            recycler.setAdapter(adapter);

            adapter.notifyDataSetChanged();

        });

    }

    private HashMap<String, List<PojoOfJsonArray>> groupDataIntoHashMap(List<PojoOfJsonArray> listOfPojosOfJsonArray) {

        HashMap<String, List<PojoOfJsonArray>> groupedHashMap = new HashMap<>();

        for (PojoOfJsonArray pojoOfJsonArray : listOfPojosOfJsonArray) {

            String hashMapKey = pojoOfJsonArray.getDate();

            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedHashMap.get(hashMapKey).add(pojoOfJsonArray);
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                List<PojoOfJsonArray> list = new ArrayList<>();
                list.add(pojoOfJsonArray);
                groupedHashMap.put(hashMapKey, list);
            }
        }


        return groupedHashMap;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101010 && resultCode == RESULT_OK) {
            assert data != null;

            String saveDate = data.getStringExtra("extra_Date");
            String saveAccount = data.getStringExtra("extra_Account");
            String saveCategory = data.getStringExtra("extra_Category");
            String saveAmount = data.getStringExtra("extra_Amount");
            String saveNote = data.getStringExtra("extra_Note");
            String saveDescription = data.getStringExtra("extra_Description");

            Log.d("extra_Date", saveDate);

            PojoOfJsonArray pojoOfJsonArray = new PojoOfJsonArray(saveDate, saveAccount, saveCategory, saveNote, saveAmount);

            viewModel.insert(pojoOfJsonArray);
//            viewModel.update(pojoOfJsonArray);

            Toast.makeText(this, "Course saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Course not saved", Toast.LENGTH_SHORT).show();
        }
    }

}