package edu.uph.m24si2.bangunruangapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class SegitigaActivity extends AppCompatActivity {
    private EditText etAngka1, etAngka2;
    private Button btnTambah, btnKali, btnBagi;
    private TextView tvHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_segitiga);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etAngka1 = findViewById(R.id.etAngka1);
        etAngka2 = findViewById(R.id.etAngka2);
        btnTambah = findViewById(R.id.btnTambah);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);
        tvHasil = findViewById(R.id.tvHasil);

        btnTambah.setOnClickListener(v -> hitung('+'));
        btnKali.setOnClickListener(v -> hitung('*'));
        btnBagi.setOnClickListener(v -> hitung('/'));
    }

    private void hitung(char operator) {
        String input1 = etAngka1.getText().toString();
        String input2 = etAngka2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            Toast.makeText(this, "Silakan masukkan kedua angka", Toast.LENGTH_SHORT).show();
            return;
        }

        double angka1 = Double.parseDouble(input1);
        double angka2 = Double.parseDouble(input2);
        double hasil = 0;

        switch (operator) {
            case '+':
                hasil = angka1 + angka2;
                break;
            case '*':
                hasil = angka1 * angka2;
                break;
            case '/':
                if (angka2 == 0) {
                    Toast.makeText(this, "Tidak dapat membagi dengan nol", Toast.LENGTH_SHORT).show();
                    return;
                }
                hasil = angka1 / angka2;
                break;
        }

        // Format hasil untuk menghilangkan .0 jika itu bilangan bulat
        if (hasil == (long) hasil) {
            tvHasil.setText(String.format(Locale.getDefault(), "%d", (long) hasil));
        } else {
            tvHasil.setText(String.format(Locale.getDefault(), "%.2f", hasil));
        }
    }
}
