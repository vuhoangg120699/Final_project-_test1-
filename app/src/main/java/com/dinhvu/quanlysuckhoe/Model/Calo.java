package com.dinhvu.quanlysuckhoe.Model;

import android.content.Context;

import com.dinhvu.quanlysuckhoe.Database.DATABASE;

import java.util.ArrayList;

public class Calo {
    private int ID;
    private String sdt;
    private  int height,weight;
    private String gioitinh;
    private int tuoi;
    private String ketqua,ngay;
    private DATABASE database;
    public Calo(Context context){
        database=new DATABASE(context);
        database.CreateaTableCALO();
    }

    public Calo(int ID,  String gioitinh, int tuoi, String ketqua,String ngay) {
        this.ID = ID;
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
        this.ketqua = ketqua;
        this.ngay=ngay;
    }

    public void AddCaLo(String sdt, int height, int weight, String gioitinh, int tuoi, String ketqua, String ngay){
        database.AddCaLo(sdt,height,weight,gioitinh,tuoi,ketqua,ngay);

    }
    public ArrayList<Calo> getDataListCaLo(String SDT){
        ArrayList<Calo> arrayList=database.getDataListCaLo(SDT);
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

    public String getGioitinh() {
        return gioitinh;
    }

    public int getTuoi() {
        return tuoi;
    }

    public String getKetqua() {
        return ketqua;
    }

    public String getNgay() {
        return ngay;
    }
}
