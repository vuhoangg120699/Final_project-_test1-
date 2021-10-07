package com.dinhvu.quanlysuckhoe.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.dinhvu.quanlysuckhoe.Model.BMI;
import com.dinhvu.quanlysuckhoe.Model.Calo;
import com.dinhvu.quanlysuckhoe.Model.NuocUong;

import java.util.ArrayList;
import java.util.List;

public class DATABASE extends SQLiteOpenHelper {
    private static  final String DATABASE_NAME="QLSK.sqlite";
    private static  final String TABLE_NAME_USER="TAIKHOAN";
    private static final String TABLE_NAME_BMI="BMI";
    private static final String TABLE_NAME_CALO="CALO";
    private static  final String TABLE_NAME_NUOCUONG="NUOCUONG";

    private static  final String TABLE_NAME_THONGTIN="THONGTIN";
    private static  final String TABLE_NAME_KETQUA="KETQUA";
    private static  final String Column_ID="ID";
    private static  final String Column_TEN="TEN";
    private static  final String Column_NGAYSINH="NGAYSINH";
    private static  final String Column_GIOITINH="GIOITINH";
    private static  final String Column_SDT="SDT";
    private static final String Column_Pass="Pass";
    private static  final String Column_BMI="BMI";
    private static  final String Column_BRM="BRM";
    private static final String Column_DATE="NGAY";
    private static final String Column_TUOI="TUOI";
    private static final String Column_HEIGHT="HEIGHT";
    private static final String Column_WEIGTHT="WEIGHT";
    private static final String Column_GROUP="NHOM";
    private static final String Column_RESULT="KETQUA";
    private static final String Column_HUONGDAN="HUONGDAN";
    private static  final String Column_THETICH="THETICH";
    private static  final String Column_THOIGIAN="THOIGIAN";
    private static  final String Column_SUDUNG="SUDUNG";
    private static  final String Column_CHECKED="KIEMTRA";
    private static  final String Column_CHUASUDUNG="CHUASUDUNG";
    private SQLiteDatabase sqLiteDatabase;



    public DATABASE(@Nullable Context context){
        super(context, DATABASE_NAME, null  , 1);
        sqLiteDatabase=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public  void CreateTable(){
        String SQL="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_USER+" ("+Column_SDT+" nvarchar(14) PRIMARY KEY,"+Column_Pass+" nvarchar(30) ) ";
          sqLiteDatabase.execSQL(SQL);
    }

    public  void  CreateTableBMI(){
        String SQL="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_BMI+"("+Column_ID+" INTEGER   PRIMARY KEY AUTOINCREMENT , '"+Column_SDT+"' nvarchar(20), " +
                " "+Column_HEIGHT+" INTEGER, "+Column_WEIGTHT+" INTEGER , "+Column_GROUP+" nvarchar(30),"+Column_RESULT+"  nvarchar(50),"+Column_DATE+" nvarchar(20)," +
                " "+Column_HUONGDAN+" TEXT) ";
        sqLiteDatabase.execSQL(SQL);
    }
    public  void  CreateaTableCALO(){
        String SQL="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_CALO+"("+Column_ID+" INTEGER   PRIMARY KEY AUTOINCREMENT , '"+Column_SDT+"' nvarchar(20), " +
                " "+Column_HEIGHT+" INTEGER, "+Column_WEIGTHT+" INTEGER ,"+Column_GIOITINH+" nvarchar(10),"+Column_TUOI+" INTEGER,"+Column_RESULT+"  nvarchar(50),"+Column_DATE+" nvarchar(20)) ";
        sqLiteDatabase.execSQL(SQL);
    }
    public  void  CreateTableNuocUong(){
      String SQL="CREATE TABLE IF NOT EXISTS "+TABLE_NAME_NUOCUONG+"('"+Column_ID+"' INTEGER PRIMARY KEY AUTOINCREMENT," +
              " "+Column_SDT+" nvarchar(20), "+Column_THETICH+" nvarchar(50), "+Column_SUDUNG+" INTEGER,"+Column_CHUASUDUNG+" INTEGER,"+Column_THOIGIAN+" nvarchar(30),"+Column_RESULT+" nvarchar(200)," +
              " "+Column_DATE+" nvarchar(10) ,"+Column_CHECKED+" INTEGER)";
      sqLiteDatabase.execSQL(SQL);
    }
    public  void  AddNuocUong(String sdt,String thetich,int chuasudung,int sudung,String thoigian,String ketqua,String ngay){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_SDT,sdt);
        contentValues.put(Column_THETICH,thetich);
        contentValues.put(Column_CHUASUDUNG,chuasudung);
        contentValues.put(Column_SUDUNG,sudung);
        contentValues.put(Column_THOIGIAN,thoigian);
        contentValues.put(Column_RESULT,ketqua);
        contentValues.put(Column_DATE,ngay);
        sqLiteDatabase.insert(TABLE_NAME_NUOCUONG,null,contentValues);
    }

    public List<String> GetDataTaiKhoan(String SDT,String Pass){
        sqLiteDatabase=getReadableDatabase();
        List<String> arr=new ArrayList<>();
        String SQL="SELECT * FROM "+TABLE_NAME_USER+" WHERE "+Column_SDT+" = '"+SDT+"' AND "+Column_Pass+" ='"+Pass+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
        while(cursor.moveToNext()){
            arr.add(cursor.getString(0));
            arr.add(cursor.getString(1));

        }
        return  arr;
    }
    public void  RegistUser(String sdt,String pass){
        String SQL="INSERT INTO "+TABLE_NAME_USER+"("+Column_SDT+","+Column_Pass+") VALUES('"+sdt+"','"+pass+"')";
        sqLiteDatabase.execSQL(SQL);

    }
    public  void AddBMI(String SDT,int height,int weight,String nhom,String ketqua,String ngay,String huongdan){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_SDT,SDT);
        contentValues.put(Column_HEIGHT,height);
        contentValues.put(Column_WEIGTHT,weight);
        contentValues.put(Column_GROUP,nhom);
        contentValues.put(Column_RESULT,ketqua);
        contentValues.put(Column_DATE,ngay);
        contentValues.put(Column_HUONGDAN,huongdan);
        sqLiteDatabase.insert(TABLE_NAME_BMI,null,contentValues);

    }
    public  void AddCaLo(String sdt,int height,int weight,String gioitinh,int tuoi,String ketqua,String ngay){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_SDT,sdt);
        contentValues.put(Column_HEIGHT,height);
        contentValues.put(Column_WEIGTHT,weight);
        contentValues.put(Column_GIOITINH,gioitinh);
        contentValues.put(Column_TUOI,tuoi);
        contentValues.put(Column_RESULT,ketqua);
        contentValues.put(Column_DATE,ngay);
        sqLiteDatabase.insert(TABLE_NAME_CALO,null,contentValues);

    }
    public  void UpdateLuaChon(int ID,int checked){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_CHECKED,checked);
        sqLiteDatabase.update(TABLE_NAME_NUOCUONG,contentValues,Column_ID+" ="+ID,null);
    }
    public  void UpdateGio(int ID,String gio){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_THOIGIAN,gio);
        sqLiteDatabase.update(TABLE_NAME_NUOCUONG,contentValues,Column_ID+" ="+ID,null);
    }

    public  void  UpdateSuDung(int ID,int sudung,int checked){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_SUDUNG,sudung);
        contentValues.put(Column_CHECKED,checked);
        sqLiteDatabase.update(TABLE_NAME_NUOCUONG,contentValues,Column_ID+"="+ID,null);


    }
    public  void DeleteSuDung(int ID){
        String SQL="DELETE FROM "+TABLE_NAME_NUOCUONG+" WHERE "+Column_ID+"='"+ID+"'";
        sqLiteDatabase.execSQL(SQL);
    }
    public  void UpdateKetQua(String date,String sdt,String ketqua){
        String SQL="UPDATE "+TABLE_NAME_NUOCUONG+" SET  "+Column_RESULT+" = '"+ketqua+"' WHERE" +
                " "+Column_SDT+" = '"+sdt+"' AND "+Column_DATE+" = '"+date+"'";
        sqLiteDatabase.execSQL(SQL);
    }
    public  void UpdateMatKhau(String sdt,String pass){
        ContentValues contentValues=new ContentValues();
        contentValues.put(Column_Pass,pass);
        sqLiteDatabase.update(TABLE_NAME_USER,contentValues,Column_SDT+"= '"+sdt+"'",null);
    }
    public  ArrayList<BMI> getDataList(String SDT){
        sqLiteDatabase=getReadableDatabase();
        ArrayList<BMI> arrayList=new ArrayList<>();
        String SQL="SELECT * FROM "+TABLE_NAME_BMI+" WHERE "+Column_SDT+" ='"+SDT+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
        while (cursor.moveToNext()){
            arrayList.add(new BMI(cursor.getInt(0),cursor.getString(5),
                    cursor.getString(7),cursor.getString(6)));
        }
        return  arrayList;
    }
    public  ArrayList<Calo> getDataListCaLo(String sdt){
        sqLiteDatabase=getReadableDatabase();
        ArrayList<Calo> arrayList=new ArrayList<>();
        String SQL="SELECT * FROM "+TABLE_NAME_CALO+" WHERE "+Column_SDT+" ='"+sdt+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
        while (cursor.moveToNext()){
            arrayList.add(new Calo(cursor.getInt(0),cursor.getString(4),
                    cursor.getInt(5),cursor.getString(6),cursor.getString(7)));
        }
        return  arrayList;
    }

    public  ArrayList<NuocUong> getDataListNuocUong(String date,String SDT){
        sqLiteDatabase=getReadableDatabase();
        ArrayList<NuocUong> arrayList=new ArrayList<>();
        String SQL="SELECT * FROM "+TABLE_NAME_NUOCUONG+" WHERE "+Column_DATE+"= '"+date+"' AND "+Column_SDT+" = '"+SDT+"'";
        Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
        while (cursor.moveToNext()){
            if(cursor.getInt(8)==2){
                continue;
            }
            arrayList.add(new NuocUong(cursor.getInt(0),cursor.getInt(4),
                    cursor.getString(5),cursor.getInt(8)));

        }
        return  arrayList;
    }
    public int getTongML(String  date,String SDT){
        sqLiteDatabase=getReadableDatabase();
             int tongml=0;
        String SQL="SELECT * FROM "+TABLE_NAME_NUOCUONG+" WHERE "+Column_DATE+"= '"+date+"' AND "+Column_SDT+" = '"+SDT+"'" ;
        Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
        while (cursor.moveToNext()){
           tongml+=cursor.getInt(3);
        }
        return  tongml;
    }
    public ArrayList<NuocUong> getDataListNuocUongLichSu(String ngay,String sdt){
        sqLiteDatabase=getReadableDatabase();
        ArrayList<NuocUong> arrayList=new ArrayList<>();
        String SQL="SELECT * FROM "+TABLE_NAME_NUOCUONG+" WHERE "+Column_SDT+" = '"+sdt+"' AND "+Column_DATE+" = '"+ngay+"'" ;
        Cursor cursor=sqLiteDatabase.rawQuery(SQL,null);
        while (cursor.moveToNext()){
            arrayList.add(new NuocUong(cursor.getInt(0),cursor.getString(2),
                    cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
        }
        return  arrayList;
    }





}
