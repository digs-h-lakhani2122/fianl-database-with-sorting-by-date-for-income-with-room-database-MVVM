package com.example.final_databse_money;

public class GeneralItem extends ListItem {
    private PojoOfJsonArray pojoOfJsonArray;

    public PojoOfJsonArray getPojoOfJsonArray() {
        return pojoOfJsonArray;
    }

    public void setPojoOfJsonArray(PojoOfJsonArray pojoOfJsonArray) {
        this.pojoOfJsonArray = pojoOfJsonArray;
    }

    public GeneralItem() {
    }

    @Override
    public int getType() {
        return ListItem.TYPE_GENERAL;
    }
}
