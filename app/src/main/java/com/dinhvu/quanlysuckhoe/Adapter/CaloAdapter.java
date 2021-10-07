package com.dinhvu.quanlysuckhoe.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dinhvu.quanlysuckhoe.Config.SharePrefeConfig;
import com.dinhvu.quanlysuckhoe.Model.Calo;
import com.dinhvu.quanlysuckhoe.R;

import java.util.ArrayList;

public class CaloAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Calo> arrayList;

    public CaloAdapter(Context context, ArrayList<Calo> arrayList) {
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
        TextView txtngay,txtketqua,txtuser;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHodler viewHodler;
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.dong_calo,null);
            viewHodler=new ViewHodler();
            viewHodler.txtketqua=convertView.findViewById(R.id.txtketqua);
            viewHodler.txtngay=convertView.findViewById(R.id.txtngay);
            viewHodler.txtuser=convertView.findViewById(R.id.txtuser);
            convertView.setTag(viewHodler);
        }else{
            viewHodler= (ViewHodler) convertView.getTag();
        }
        viewHodler.txtngay.setText("Ngày-Giờ : "+arrayList.get(position).getNgay());
        viewHodler.txtketqua.setText("Tổng Cần Nạp : "+arrayList.get(position).getKetqua());
        SharePrefeConfig sharePrefeConfig=new SharePrefeConfig(context);
        sharePrefeConfig.PutshareFer(context);
        viewHodler.txtuser.setText(sharePrefeConfig.getName());

// bỏ tuổi giới tính a
        return convertView;
    }
}
