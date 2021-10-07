package com.dinhvu.quanlysuckhoe.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dinhvu.quanlysuckhoe.Model.NuocUong;
import com.dinhvu.quanlysuckhoe.R;

import java.util.ArrayList;

public class NuocUongAdapter  extends BaseAdapter {
    private Context context;
    private ArrayList<NuocUong> arrayList;

    public NuocUongAdapter(Context context, ArrayList<NuocUong> arrayList) {
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
        TextView txtml,txtgio;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_nuocuong,null);
            viewHodler=new ViewHodler();
            viewHodler.txtgio=convertView.findViewById(R.id.txtgio);
            viewHodler.txtml=convertView.findViewById(R.id.txtml);

            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        if(arrayList.get(position).getChecked()==1){
            viewHodler.txtgio.setBackgroundResource(R.color.purple_700);
            viewHodler.txtml.setTextColor(Color.WHITE);

        }
        viewHodler.txtml.setText(arrayList.get(position).getChuasudung()+" ml");
        viewHodler.txtgio.setText(arrayList.get(position).getGio());

        return convertView;
    }
}
