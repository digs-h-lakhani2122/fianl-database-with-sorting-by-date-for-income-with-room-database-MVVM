package com.example.final_databse_money;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModel extends AndroidViewModel {
    Repository repository;
    LiveData<List<PojoOfJsonArray>> allIncomes;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        allIncomes = repository.getAllCourses();
    }

    void insert(PojoOfJsonArray pojoOfJsonArray) {
        repository.insert(pojoOfJsonArray);
    }

    void update(PojoOfJsonArray pojoOfJsonArray) {
        repository.update(pojoOfJsonArray);
    }

    void delete(PojoOfJsonArray pojoOfJsonArray) {
        repository.delete(pojoOfJsonArray);
    }

    LiveData<List<PojoOfJsonArray>> getAllIncomes() {
        return allIncomes;
    }
}
