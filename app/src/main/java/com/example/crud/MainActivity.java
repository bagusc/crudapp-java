package com.example.crud;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    DbHelper dbHelper;
    private EditText etName, etNim;
    private Button btnSave, btnList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DbHelper(this);

        etName = findViewById(R.id.edt_name);
        etNim = findViewById(R.id.edt_nim);
        btnSave = findViewById(R.id.btn_submit);
        btnList = findViewById(R.id.btn_list);

        btnSave.setOnClickListener(v -> {
            if (etNim.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Judul lagu harus diisi!", Toast.LENGTH_SHORT).show();
            } else if (etName.getText().toString().isEmpty()) {
                Toast.makeText(MainActivity.this, "Error: Genre harus diisi!", Toast.LENGTH_SHORT).show();
            } else {
                dbHelper.addUserDetail(etNim.getText().toString(), etName.getText().toString());
                etName.setText("");
                etNim.setText("");
                Toast.makeText(MainActivity.this, "Simpan berhasil!", Toast.LENGTH_SHORT).show();
            }
        });

        btnList.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ListStudentsActivity.class);
            startActivity(intent);
        });
    }
}
