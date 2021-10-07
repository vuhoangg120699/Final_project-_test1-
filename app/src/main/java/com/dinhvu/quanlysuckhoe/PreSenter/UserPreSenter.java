package com.dinhvu.quanlysuckhoe.PreSenter;

import android.content.Context;

import com.dinhvu.quanlysuckhoe.Model.User;

public class UserPreSenter  implements  UserModel{
    private User user;
    private UserView callback;
    public UserPreSenter(UserView callback,Context context){
        this.callback=callback;
        user=new User(this,context);
    }
    public void getDataLogin(String SDT,String Pass){
       user.LoginUser(SDT,Pass);
    }
    public  void HandleDangKy(String SDT,String pass){
        user.RegistUser(SDT,pass);
    }
    public  void HandleUpdate(String sdt,String matkhau){
        user.HandleUpdateUser(sdt,matkhau);
    }
    @Override
    public void OnSucess() {
        callback.OnSucess();
    }

    @Override
    public void OnEmpty() {
     callback.OnEmpty();
    }

    @Override
    public void OnError() {
   callback.OnError();
    }
}
