package com.dinhvu.quanlysuckhoe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dinhvu.quanlysuckhoe.Model.NuocUong;
import com.dinhvu.quanlysuckhoe.R;

import java.util.ArrayList;

public class LichSuNuocUongAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<NuocUong> arrayList;

    public LichSuNuocUongAdapter(Context context, ArrayList<NuocUong> arrayList) {
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
        TextView txtsudung,txtdate,txtgio;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;

        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_lichsu_nuoc,null);
            viewHodler=new ViewHodler();
            viewHodler.txtdate=convertView.findViewById(R.id.txtngay);
            viewHodler.txtgio=convertView.findViewById(R.id.txtgio);
            viewHodler.txtsudung=convertView.findViewById(R.id.txtsudung);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        if(arrayList.get(position).getSudung()>0){
            viewHodler.txtsudung.setText("Đã dùng : "+arrayList.get(position).getSudung()+" / "+arrayList.get(position).getThetich()+" ml");
            viewHodler.txtgio.setText("Giờ Sử Dụng : "+arrayList.get(position).getGio());
            viewHodler.txtdate.setText("Ngày : "+arrayList.get(position).getDate());
        }
        return convertView;
    }
}
