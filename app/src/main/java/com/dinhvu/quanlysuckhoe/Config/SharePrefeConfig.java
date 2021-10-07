package com.dinhvu.quanlysuckhoe.Config;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefeConfig {
    private SharedPreferences sharedPreferences,sharedPreferences1;
    private SharedPreferences.Editor editor,editor1;

    public  SharePrefeConfig(Context context){
        sharedPreferences=context.getSharedPreferences("INFO",Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }
    public  void PutshareFer(Context context){
        sharedPreferences1=context.getSharedPreferences(getSDT(),Context.MODE_PRIVATE);
        editor1=sharedPreferences1.edit();

    }
    public void PutSDT(String sdt){
        editor.putString("SDT",sdt);
        editor.commit();
    }
    public void PutAge(int age){
        editor1.putInt("AGE",age);
        editor1.commit();
    }
    public  void  PutInfo(String ten,long height,long weight){
        editor1.putString("NAME",ten);
        editor1.putLong("HEIGHT",height);
        editor1.putLong("WEIGHT",weight);
        editor1.commit();
    }
    public  int getAge(){
        int age=sharedPreferences1.getInt("AGE",0);
        return  age;
    }
    public  String getSDT(){
        String sdt=sharedPreferences.getString("SDT","");
        return  sdt;
    }
    public String getName(){
        String name=sharedPreferences1.getString("NAME","");
        return  name;
    }
    public long getHeight(){
        long height=sharedPreferences1.getLong("HEIGHT",0);
        return  height;
    }
    public long getWeight(){
        long weight=sharedPreferences1.getLong("WEIGHT",0);
        return  weight;
    }
    public void putSex(String gioitinh){
        editor1.putString("SEX",gioitinh);
        editor1.commit();
    }
    public String getSex(){
        String sex=sharedPreferences1.getString("SEX","");
        return  sex;
    }
    public void putDrink(int  ml){
        editor1.putInt("DRINK",ml);
        editor1.commit();
    }
    public int getDrink(){
        int  drink=sharedPreferences1.getInt("DRINK",2240);
        return  drink;
    }

}
