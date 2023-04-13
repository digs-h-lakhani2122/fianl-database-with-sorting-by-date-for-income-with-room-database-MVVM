package com.example.final_databse_money;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository
{
    private final Dao dao;
    LiveData<List<PojoOfJsonArray>> allCourses;

    public Repository(Application application)
    {
        Database databaseIncome = Database.getInstance(application);
        dao = databaseIncome.Dao();
        allCourses = dao.getAllData();
    }

    void insert (PojoOfJsonArray pojoOfJsonArray)
    {
        new InsertAsyncTask(dao).execute(pojoOfJsonArray);
    }

    void update (PojoOfJsonArray pojoOfJsonArray)
    {
        new UpdateAsyncTask(dao).execute(pojoOfJsonArray);
    }

    void delete (PojoOfJsonArray pojoOfJsonArray)
    {
        new DeleteAsyncTask(dao).execute(pojoOfJsonArray);
    }

    LiveData<List<PojoOfJsonArray>> getAllCourses()
    {
        return allCourses;
    }

    private static class InsertAsyncTask extends AsyncTask<PojoOfJsonArray,Void,Void> {
        Dao dao;
        public InsertAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PojoOfJsonArray... pojoOfJsonArrays)
        {
            dao.insert(pojoOfJsonArrays[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<PojoOfJsonArray,Void,Void> {
        Dao dao;
        public UpdateAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PojoOfJsonArray... pojoOfJsonArrays) {
            dao.update(pojoOfJsonArrays[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<PojoOfJsonArray,Void,Void>{
        Dao dao;
        public DeleteAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(PojoOfJsonArray... pojoOfJsonArrays) {
            dao.delete(pojoOfJsonArrays[0]);
            return null;
        }
    }
}
