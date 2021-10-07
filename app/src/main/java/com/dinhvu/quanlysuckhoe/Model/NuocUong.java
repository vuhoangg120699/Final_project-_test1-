package com.dinhvu.quanlysuckhoe.Model;

import android.content.Context;

import com.dinhvu.quanlysuckhoe.Database.DATABASE;

import java.util.ArrayList;

public class NuocUong {
    private int ma;
    private String thetich;
    private int sudung,chuasudung;
    private String date,ketqua,gio;
    private DATABASE database;
    private int checked;
    public NuocUong(Context context){
        database=new DATABASE(context);
        database.CreateTableNuocUong();
    }

    public NuocUong(int ma, String thetich, int sudung, String date, String ketqua, String gio) {
        this.ma = ma;
        this.thetich = thetich;
        this.sudung = sudung;
        this.date = date;
        this.ketqua = ketqua;
        this.gio = gio;
    }

    public NuocUong(int ma, int chuasudung, String gio,int checked) {
        this.ma = ma;
        this.chuasudung = chuasudung;
        this.gio = gio;
        this.checked=checked;
    }

    public void HandleCreateDrink(String sdt, String thetich, int chuasudung,int sudung, String thoigian, String ketqua, String ngay){
          database.AddNuocUong(sdt,thetich,chuasudung,sudung,thoigian,ketqua,ngay);

    }
    public void HandleUpdateKetQua(String sdt,String ngay,String ketqua){
        database.UpdateKetQua(ngay,sdt,ketqua);
    }
    public  void HandleUpdateLuaChon(int ID,int checked){
        database.UpdateLuaChon(ID,checked);
    }
    public  void HandleUpdateGio(int ID,String gio){
        database.UpdateGio(ID,gio);
    }
    public ArrayList<NuocUong> getDataListNuocUong(String ngay,String sdt){
        ArrayList<NuocUong> arrayList=database.getDataListNuocUong(ngay,sdt);
        return  arrayList;
    }
    public ArrayList<NuocUong> getDataListNuocUongLichSu(String ngay,String sdt){
        ArrayList<NuocUong> arrayList=database.getDataListNuocUongLichSu(ngay,sdt);
        return  arrayList;
    }

    public  void HandleUpdateSuDung(int ID,int sudung,int checked){
        database.UpdateSuDung(ID,sudung,checked);
    }
    public  void HandleDeleteSuDung(int ID){
        database.DeleteSuDung(ID);
    }
    public  int getDataSuDung(String sdt,String date){
        int Tong=database.getTongML(date,sdt);
        return  Tong;
    }


    public int getMa() {
        return ma;
    }

    public String getThetich() {
        return thetich;
    }

    public int getSudung() {
        return sudung;
    }

    public String getDate() {
        return date;
    }

    public String getKetqua() {
        return ketqua;
    }

    public String getGio() {
        return gio;
    }

    public int getChecked() {
        return checked;
    }

    public int getChuasudung() {
        return chuasudung;
    }
}
