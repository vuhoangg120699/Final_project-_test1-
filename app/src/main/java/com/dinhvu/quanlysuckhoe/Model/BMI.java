package com.dinhvu.quanlysuckhoe.Model;

import android.content.Context;

import com.dinhvu.quanlysuckhoe.Database.DATABASE;

import java.util.ArrayList;

public class BMI  {
    private int ID;
    private String sdt;
    private int height,weight;
    private String nhom,ketqua,huongdan,ngay;
    private DATABASE database;

    public BMI(Context context){
        database=new DATABASE(context);
        database.CreateTableBMI();
    }

    public BMI(int ID, String ketqua, String huongdan, String ngay) {
        this.ID = ID;
        this.ketqua = ketqua;
        this.huongdan = huongdan;
        this.ngay = ngay;
    }

    public void HandleAddBMI(String sdt, int height, int weight, String nhom, String ketqua, String ngay, String huongdan){
        database.AddBMI(sdt,height,weight,nhom,ketqua,ngay,huongdan);
    }
    public ArrayList<BMI> getDataList(String SDT){
        ArrayList<BMI> arrayList=database.getDataList(SDT);
        return  arrayList;
    }

    public int getID() {
        return ID;
    }

    public String getSdt() {
        return sdt;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public String getNhom() {
        return nhom;
    }

    public String getKetqua() {
        return ketqua;
    }

    public String getHuongdan() {
        return huongdan;
    }

    public String getNgay() {
        return ngay;
    }
}
