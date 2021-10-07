package com.dinhvu.quanlysuckhoe.View;

import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.BMI;
import com.dinhvu.quanlysuckhoe.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IndexBodyActivity  extends AppCompatActivity
implements View.OnClickListener {
    private BMI bmi;
    private TextView editHeight,editWeight;
    private TextView txtKetQua,txtNhom,txtChiso;
    private Button btnluu,btntinh;
    private ImageView back;

    private String sdt;
    private String huongdan;

    private SharePrefeConfig sharePrefeConfig;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indexbody);
        InitWidget();
        Init();
        HandleEvents();
    }

    private void HandleEvents() {
        btnluu.setOnClickListener(this::onClick);
        btntinh.setOnClickListener(this::onClick);
        back.setOnClickListener(this);

    }

    private void Init() {

       sharePrefeConfig=new SharePrefeConfig(this);
        sharePrefeConfig.PutshareFer(this);
       sdt=sharePrefeConfig.getSDT();
        editHeight.setText(sharePrefeConfig.getHeight()+" cm");
        editWeight.setText(sharePrefeConfig.getWeight()+" kg");


        bmi=new BMI(this);

    }



    private void InitWidget() {

        editHeight=findViewById(R.id.editchieucao);
        editWeight=findViewById(R.id.editcannang);
        txtKetQua=findViewById(R.id.txtketqua);
        txtNhom=findViewById(R.id.txtnhom);
        txtChiso=findViewById(R.id.txtchiso);
        btntinh=findViewById(R.id.btntinh);
        btnluu=findViewById(R.id.btnluu);
        back=findViewById(R.id.back);



    }

    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.btntinh:
                  HandleTinhBMI();
                  break;
              case R.id.btnluu:
                  Calendar calendar=Calendar.getInstance();
                  SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");

                  String nhom=txtNhom.getText().toString().trim();
                  String result=txtKetQua.getText().toString().trim();
                  String ngay=simpleDateFormat.format(calendar.getTime());
                  bmi.HandleAddBMI(sdt,(int)sharePrefeConfig.getHeight(),(int)sharePrefeConfig.getWeight(),nhom,result,ngay,huongdan);
                  Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                  break;
              case R.id.back:
                  finish();break;


          }

    }

    private void HandleTinhBMI() {

        if(sharePrefeConfig.getHeight()>0 && sharePrefeConfig.getWeight()>0) {
            float height_convert = (float) sharePrefeConfig.getHeight() / 100;
            double ketqua = 2f;
            ketqua = (double) (sharePrefeConfig.getWeight()) / ((height_convert * height_convert));

            String Nhom = "", KetQua = "";

            if (ketqua < 18.5) {
                Nhom = "Nhóm 1";
                KetQua = "Gầy";
                huongdan = "Cần ăn nhiều hơn để tăng cân ! bạn quá gầy";
            } else if (ketqua >= 18.5 && ketqua <= 24.9) {
                Nhom = "Nhóm 2";
                KetQua = "Bình Thường";
                huongdan = "Tốt lắm hay duy trì tiếp nhé !";

            } else if (ketqua >= 25) {
                Nhom = "Nhóm 3";
                KetQua = "Thừa Cân";
                huongdan = "Thừa cân rồi ! hãy cố gắng tập thể dục bạn nhé !";
            } else if (ketqua > 25 && ketqua <= 29.9) {
                Nhom = "Nhóm 4";
                KetQua = "Tiền Béo Phì";
                huongdan = "Béo phì! nhanh chóng luyện tập và ăn nhiều rau xanh";
            } else if (ketqua >= 30 && ketqua <= 34.9) {
                Nhom = "Nhóm 5";
                KetQua = "Béo Phì Độ I";
                huongdan = "Béo phì! nhanh chóng luyện tập và ăn nhiều rau xanh";
            } else if (ketqua > 34 && ketqua <= 39.9) {
                Nhom = "Nhóm 6";
                KetQua = "Béo Phì Độ II";
                huongdan = "Nguy ! bạn cần nhanh chóng luyện tập và ăn nhiều rau xanh";
            } else if (ketqua >= 40) {
                Nhom = "Nhóm 7";
                KetQua = "Béo Phì Độ III";
                huongdan = "Cảnh báo ! nhanh chóng luyện tập và ăn nhiều rau xanh";
            }
            Toast.makeText(this, huongdan, Toast.LENGTH_SHORT).show();
            txtChiso.setText(String.valueOf(ketqua));
            txtKetQua.setText(KetQua);
            txtNhom.setText(Nhom);
        }
         
       
    }


}
