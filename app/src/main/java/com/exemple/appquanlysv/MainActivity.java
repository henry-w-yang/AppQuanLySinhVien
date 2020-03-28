package com.exemple.appquanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    private static final String DATABASE_NAME ="QUANLYSINHVIEN.db" ;
    EditText editHoten,edtNS,edtLop;
    RadioButton rdNam,rdNu;
    CheckBox cbDocSach,cbTheThao,cbDuLich;
    Button btNhap,btNhapLai;
    SinhVienDatabase database;
    SQLiteDatabase db;
    ImageButton imgDanhsasch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editHoten = findViewById(R.id.hoten);
        edtNS = findViewById(R.id.namsinh);
        edtLop = findViewById(R.id.lop);

        rdNam = findViewById(R.id.gioitinh_nam);
        rdNu = findViewById(R.id.gioitinh_nu);

        cbDocSach = findViewById(R.id.cbdocsach);
        cbTheThao = findViewById(R.id.cbthethao);
        cbDuLich = findViewById(R.id.cbdulic);

        btNhap = findViewById(R.id.nhaplieu);
        btNhapLai = findViewById(R.id.nhaplai);
        imgDanhsasch = findViewById(R.id.dsSinhVien);


        database = new SinhVienDatabase(this);

        NhapLai();
        Nhap();
        initUI();
        imgDanhsasch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,DanhSachSV.class));
            }
        });

    }


    private void NhapLai() {
        btNhapLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editHoten.setText("");
                edtLop.setText("");
                edtNS.setText("");
                rdNam.setChecked(true);
                rdNu.setChecked(false);
                cbDocSach.setChecked(false);
                cbDuLich.setChecked(false);
                cbTheThao.setChecked(false);
            }
        });

    }
    public void Nhap(){
        btNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String soThich = "";
                int gTinh = 0;
                String hoTen = editHoten.getText().toString();
                String NamSinh = edtNS.getText().toString();
                String Lop = edtLop.getText().toString();


                if (rdNu.isChecked())
                {
                    gTinh= 1 ;
                }
                if (cbTheThao.isChecked())
                {
                    soThich+=","+cbTheThao.getText().toString();
                }
                if (cbDuLich.isChecked())
                {
                    soThich+=","+cbDuLich.getText().toString();
                }
                if (cbDocSach.isChecked())
                {
                    soThich+=","+cbDocSach.getText().toString();
                }
                SinhVien sv = new SinhVien(hoTen, NamSinh,Lop, gTinh,soThich);
                if (sv!=null){
                    database.ThemSinhVien(sv);
                    Toast.makeText(MainActivity.this,"Bạn Đã thêm thành công!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }



    private void initUI() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1);

        db = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = db.rawQuery("SELECT * FROM SINHVIEN ID = ? ",new String[]{id + "",});
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String lop = cursor.getString(3);
        int gioitinh = cursor.getInt(4);
        String namsinh = cursor.getString(2);
        String sothich = cursor.getString(5);

        editHoten.setText(ten);
        edtLop.setText(lop);
        edtNS.setText(namsinh);
        if (gioitinh==1)
        {
            rdNu.setChecked(true);
            rdNam.setChecked(false);
        }
        else
        {
            rdNu.setChecked(false);
            rdNam.setChecked(true);
        }

        if (sothich.equalsIgnoreCase("Thể thao")==true)
            cbTheThao.setChecked(true);
        if (sothich.equalsIgnoreCase("Đọc sách")==true)
            cbTheThao.setChecked(true);
        if (sothich.equalsIgnoreCase("Du Lịch")==true)
            cbTheThao.setChecked(true);

    }

}