package com.exemple.appquanlysv;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListSVAdapter extends BaseAdapter {
    Context mycontext;
    int myLayout;
    List<SinhVien> sinhVienList;

    public ListSVAdapter(Context context, int layout, List<SinhVien> list) {
        mycontext = context;
        myLayout = layout;
        sinhVienList = list;
    }

    @Override
    public int getCount() {
        return sinhVienList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        viewHolder holder;
        if (convertView==null){
            holder = new viewHolder();
            LayoutInflater inflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sv_items,null);

            convertView.setTag(holder);
        }
        else
        {
            holder =(viewHolder) convertView.getTag();
        }

        final SinhVien sinhVien = sinhVienList.get(position);


        if (sinhVien!=null)
        {
            holder.tvTen = convertView.findViewById(R.id.tensv);
            holder.tvGioiTinh = convertView.findViewById(R.id.gioitinh);
            holder.tvLop = convertView.findViewById(R.id.lop);
            holder.tvNamSinh = convertView.findViewById(R.id.ngaysinh);
            holder.tvSothich = convertView.findViewById(R.id.sothich);
            holder.sua = convertView.findViewById(R.id.sua);
            holder.xoa = convertView.findViewById(R.id.xoa);

            holder.tvTen.setText(String.valueOf(sinhVien.Ten));
            holder.tvGioiTinh.setText(String.valueOf(sinhVien.gioitinh));
            if (sinhVien.getGioitinh()==1)
                holder.tvGioiTinh.setText("Nu");
            else holder.tvGioiTinh.setText("Nam");
            holder.tvSothich.setText(String.valueOf(sinhVien.soThich));
            holder.tvNamSinh.setText(String.valueOf(sinhVien.namsinh));



        }

        holder.xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mycontext);
                builder.setTitle("Xác nhận xoá");
                builder.setMessage("Bạn có chắc chắc xoá Sinh Viên này ? ");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        delete(sinhVien.id);
                    }
                });

        // bắt sự kiện xoá

                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        // bắt sự kiện cuar item sinhvien
        holder.sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mycontext, SuaThongTin.class);
                intent.putExtra("id",sinhVien.getId());
                mycontext.startActivity(intent);
            }
        });


        return convertView;// trả về view cho list bên màn hinh ActivityMain
    }
    public class viewHolder{

        TextView tvTen, tvSothich, tvNamSinh, tvGioiTinh, tvLop;
        ImageButton sua, xoa;
    }
    // Xoá Sinh viên
//    private void delete(int idSinhVien) {
//        //đọc database
//        SQLiteDatabase database = Database.initDatabase((Activity) mycontext,"QUANLYSINHVIEN.sqlite");
//        database.delete("SV","ID = ? ",new String[]{idSinhVien +""});
//        // đấu hoit chấm là cấu trúc lẹnh rawQuery, bên trái có bao nhiêu dấu ? thì bên phải có bấy nhiêu phần tử
//
//        Cursor cursor = database.rawQuery("SELECT * FROM SINHVIEN",null);
//        while ((cursor.moveToNext()))
//        {
//            int id = cursor.getInt(0);
//            String ten = cursor.getString(1);
//            String lop = cursor.getString(3);
//            int gioitinh = cursor.getInt(4);
//            String namsinh = cursor.getString(2);
//            String sothich = cursor.getString(5);
//            sinhVienList.add(ten,lop,gioitinh,namsinh,sothich);
//        }
//        notifyDataSetChanged();
//    }
}
