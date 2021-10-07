package com.dinhvu.quanlysuckhoe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dinhvu.quanlysuckhoe.Model.BMI;
import com.dinhvu.quanlysuckhoe.R;

import java.util.ArrayList;

public class BMIAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BMI> arrayList;

    public BMIAdapter(Context context, ArrayList<BMI> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public  class ViewHodler{
        TextView txtngay,txtketqua,txtchiso,txtloikhuyen;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_bmi,null);
            viewHodler=new ViewHodler();
            viewHodler.txtketqua=convertView.findViewById(R.id.txtketqua);
            viewHodler.txtloikhuyen=convertView.findViewById(R.id.txtloikhuyen);
            viewHodler.txtngay=convertView.findViewById(R.id.txtngay);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.txtngay.setText("Ngày-Giờ : "+arrayList.get(position).getNgay());
        viewHodler.txtloikhuyen.setText("Lời Khuyên : "+arrayList.get(position).getHuongdan());
        viewHodler.txtketqua.setText("Kết Quả : "+arrayList.get(position).getKetqua());


        return convertView;
    }
}
