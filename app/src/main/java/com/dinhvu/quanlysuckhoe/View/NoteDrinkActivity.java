package com.dinhvu.quanlysuckhoe.View;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.NuocUong;
import com.dinhvu.quanlysuckhoe.R;
import com.shawnlin.numberpicker.NumberPicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class NoteDrinkActivity extends AppCompatActivity
implements View.OnClickListener {
    private Toolbar toolbar;

    private Button btntaonuoc;
    private NuocUong nuocUong;
    private Intent intent1;
    private String sdt;
    private TextView txtmuctieu,txtmucsudung;
    private GridView gv;
    private ArrayList<NuocUong> arrayList;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private int Sudung=0;
    private SharePrefeConfig sharePrefeConfig;
    private SimpleDateFormat simpleDateFormat;
    private TextView txtsudung,txttong,txttime;
    private  int ml=0;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_drink);
        InitWidget();
        Init();
        InitProgress();
        HandleEvents();
    }


    private void InitProgress() {
        int ketqua= (int) (sharePrefeConfig.getWeight()*2*0.5*0.03*1000);
        sharePrefeConfig.putDrink(ketqua);

        simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        Sudung=nuocUong.getDataSuDung(sdt,simpleDateFormat.format(calendar.getTime()));
        int Max=sharePrefeConfig.getDrink();
        txtmuctieu.setText(Max+" ml");
        float percent=((float) Sudung/Max)*100;
        txtmucsudung.setText(String.format("%.1f",percent)+"%");
        txtsudung.setText(Sudung+" ml");
        txttong.setText(Max+" ml");
        if(Sudung==Max){
            nuocUong.HandleUpdateKetQua(sdt,simpleDateFormat.format(calendar.getTime()),"Hoàn Thành");
        }
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd MMM");
        txttime.setText("Today, "+simpleDateFormat.format(Calendar.getInstance().getTime()));

    }

    private void HandleEvents() {
        btntaonuoc.setOnClickListener(this::onClick);
    }

    private void Init() {
        sharePrefeConfig=new SharePrefeConfig(this);
        sharePrefeConfig.PutshareFer(this);
        sdt=sharePrefeConfig.getSDT();
        nuocUong=new NuocUong(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }







    private void InitWidget() {
        txttime=findViewById(R.id.txttime);
        txtsudung=findViewById(R.id.txtml);
        txttong=findViewById(R.id.txtfull);
        txtmucsudung=findViewById(R.id.txtmucsudung);
        txtmuctieu=findViewById(R.id.txtmuctieu);
        toolbar=findViewById(R.id.toolbar);
        btntaonuoc=findViewById(R.id.btntaonuoc);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btntaonuoc:
                BottomSheet();break;
        }
    }

    private void DiaLogTaoNuocUong() {
        Calendar calendar=Calendar.getInstance();

        Dialog dialog=new Dialog(this);
        dialog.setContentView(R.layout.dialog_tao_nuoc);
        dialog.show();
        TextView txtgio=dialog.findViewById(R.id.txttimer);
        EditText editml=dialog.findViewById(R.id.editml);
        Button btnxacnhan=dialog.findViewById(R.id.btntao);
        txtgio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaLogTimer(txtgio);
            }
        });
        btnxacnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gio=txtgio.getText().toString().trim();
                if(editml.getText().toString().length()>0 && gio.length()>0){
                    int mlnuoc= Integer.parseInt(editml.getText().toString().trim());
                    String muctieu=txtmuctieu.getText().toString().trim();
                    if(mlnuoc>0){
                        nuocUong.HandleCreateDrink(sdt,muctieu,mlnuoc,0,gio,"Fail",simpleDateFormat.format(calendar.getTime()));

                        dialog.cancel();
                    }else{
                        Toast.makeText(NoteDrinkActivity.this, "ml >0", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

    }

    private void DiaLogTimer(TextView txtgio) {
        Calendar calendar=Calendar.getInstance();
        int gio=calendar.get(Calendar.HOUR_OF_DAY);
        int phut=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                calendar.set(0,0,0,hourOfDay,minute);
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("HH:mm");
                txtgio.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_setting,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                Intent intent=new Intent(this,SettingNuocUongActivity.class);

                startActivity(intent);finish();



        }
        return super.onOptionsItemSelected(item);
    }
    private void BottomSheet(){

        BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.bottom_sheet);
        bottomSheetDialog.show();
         SimpleDateFormat  simpleDateFormat1=  new SimpleDateFormat("HH:mm");
         Button btnadd=bottomSheetDialog.findViewById(R.id.btnadd);
        NumberPicker numberPicker=bottomSheetDialog.findViewById(R.id.number_picker);
        numberPicker.setMaxValue(17);
        numberPicker.setMinValue(0);
        numberPicker.setDisplayedValues(new String[]{"50 ML","100 ML","150 ML","200 ML","250 ML",
                "300 ML","350 ML","400 ML","450 ML","500 ML","550 ML","600 ML","650 ML","700 ML","750 ML",
        "800 ML","850 ML","900 ML"});

        numberPicker.setOnScrollListener(new NumberPicker.OnScrollListener() {
            @Override
            public void onScrollStateChange(NumberPicker view, int scrollState) {

                if(scrollState==SCROLL_STATE_IDLE){

                     switch (view.getValue()){
                         case 0:ml=50;break;
                         case 1:ml=100;break;
                         case 2:ml=150;break;
                         case 3:ml=200;break;
                         case 4:ml=250;break;
                         case 5:ml=300;break;
                         case 6:ml=350;break;
                         case 7:ml=400;break;
                         case 8:ml=450;break;
                         case 9:ml=500;break;
                         case 10:ml=550;break;
                         case 11:ml=600;break;
                         case 12:ml=650;break;
                         case 13:ml=700;break;
                         case 14:ml=750;break;
                         case 15:ml=800;break;
                         case 16:ml=850;break;
                         case 17:ml=900;break;

                     }
                    Toast.makeText(NoteDrinkActivity.this, " "+ml, Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nuocUong.HandleCreateDrink(sdt,txttong.getText().toString().trim(),ml,0,simpleDateFormat1.format(Calendar.getInstance().getTime()),"Fail",simpleDateFormat.format(Calendar.getInstance().getTime()));
                          bottomSheetDialog.cancel();
                Toast.makeText(NoteDrinkActivity.this, "Thanh Cong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
