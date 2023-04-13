package com.example.final_databse_money;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "income_details")
public class PojoOfJsonArray
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "custom_id")
    long custom_id;
    @ColumnInfo(name = "date")
    String date;
    @ColumnInfo(name = "account")
    String account;
    @ColumnInfo(name = "category")
    String category;
    @ColumnInfo(name = "note")
    String note;
    @ColumnInfo(name = "amount")
    String amount;

    public PojoOfJsonArray(String date, String account, String category, String note, String amount) {
        this.date = date;
        this.account = account;
        this.category = category;
        this.note = note;
        this.amount = amount;
    }

    public long getCustom_id() {
        return custom_id;
    }

    public void setCustom_id(long custom_id) {
        this.custom_id = custom_id;
    }

    public String getDate() {
        return date;
    }

    public String getAccount() {
        return account;
    }


    public String getCategory() {
        return category;
    }


    public String getNote() {
        return note;
    }


    public String getAmount() {
        return amount;
    }

}
