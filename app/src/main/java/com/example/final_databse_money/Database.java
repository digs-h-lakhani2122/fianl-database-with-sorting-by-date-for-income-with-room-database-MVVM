package com.example.final_databse_money;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@androidx.room.Database(entities = {PojoOfJsonArray.class},version = 1)
public abstract class Database extends RoomDatabase
{
    public abstract Dao Dao();
    private static Database instance;

    public static synchronized Database getInstance(Context context)
    {
        if(instance==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),Database.class,"income_database").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return  instance;
    }

    private  static final RoomDatabase.Callback roomCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbASync(instance).execute();
        }
    };

    private static class PopulateDbASync extends AsyncTask<Void,Void,Void> {
        public PopulateDbASync(Database instance)
        {
            Dao dao = instance.Dao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
