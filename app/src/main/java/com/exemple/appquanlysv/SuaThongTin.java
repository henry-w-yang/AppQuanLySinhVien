package com.exemple.appquanlysv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;

public class SuaThongTin extends AppCompatActivity {

    private static final String DATABASE_NAME = "QUANLYSINHVIEN.sqlite" ;
SQLiteDatabase database;
    EditText editHoten, edtNS, edtLop;
    RadioButton rdNam, rdNu;
    CheckBox cbDocSach, cbTheThao, cbDuLich;
    Button btNhap, btNhapLai;
    SQLiteDatabase db;
    ImageButton imgDanhsasch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);

        AnhXa();
        initUI();
    }

    private void AnhXa() {
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
    }


    //bắt sự kiện từ bên DanhSachSV Buntton sửa
    private void initUI() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",-1); // nhận id
// thực hiện mở file và đọc
        database = Database.initDatabase(this,DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM SINHVIEN WHERE ID = ? ",new String[]{id + "",});
        cursor.moveToFirst(); // di chuyển ccon trỏ đến vt đầu

        // con trỏ chỏ đến cột 0,1,2,3,4,5 của hàng này
        String ten = cursor.getString(1);
        String lop = cursor.getString(3);
        int gioitinh = cursor.getInt(4);
        String namsinh = cursor.getString(2);
        String sothich = cursor.getString(5);

        // load dữ liệu cũ ra
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

