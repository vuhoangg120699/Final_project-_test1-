package com.dinhvu.quanlysuckhoe.Model;

import android.content.Context;

import com.dinhvu.quanlysuckhoe.Database.DATABASE;
import com.dinhvu.quanlysuckhoe.PreSenter.UserModel;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private  String sdt;
    private String pass;
    private DATABASE database;
    private UserModel callback;
    public  User(UserModel callback, Context context){
        this.callback=callback;
        database=new DATABASE(context);
        database.CreateTable();

    }
    public void LoginUser(String SDT, String Pass){

        try{
          List<String>  arr=database.GetDataTaiKhoan(SDT,Pass);
             if(SDT.equalsIgnoreCase(arr.get(0)) && Pass.equals(arr.get(1))){
                 callback.OnSucess();
             }else{
                 callback.OnEmpty();
             }
        }catch (Exception e){
            callback.OnError();
        }

    }

    public  void RegistUser(String sdt,String pass){
        try{
            if(sdt.length()>0 && pass.length()>0){
                database.RegistUser(sdt,pass);
                callback.OnSucess();
            }else{
                callback.OnEmpty();
            }

        }catch (Exception e){
         callback.OnError();
        }
    }
    public void HandleUpdateUser(String sdt,String matkhau){
        if(sdt.length()>0 && matkhau.length()>0){
            database.UpdateMatKhau(sdt,matkhau);
            callback.OnSucess();
        }else{
            callback.OnEmpty();
        }
    }


}
