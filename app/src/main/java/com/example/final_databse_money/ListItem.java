package com.example.final_databse_money;

public abstract class ListItem {
    public static final int TYPE_DATE = 0;
    public static final int TYPE_GENERAL = 1;

    abstract public int getType();
}
