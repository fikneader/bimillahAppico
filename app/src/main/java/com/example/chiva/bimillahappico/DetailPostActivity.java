package com.example.chiva.bimillahappico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailPostActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            Toast.makeText(getApplicationContext(), "DATA NULL", Toast.LENGTH_SHORT).show();
        } else {
            //String kata = getActivity().getIntent().getStringExtra("KATA");
            //String pengertian = getActivity().getIntent().getStringExtra("PENGERTIAN");
            String username = bundle.getString("USERNAME");
            String post = bundle.getString("POST");
            String judul = bundle.getString("JUDUL");
            String terlapor = bundle.getString("TERLAPOR");
            String tglpost = bundle.getString("TGLPOST");
            String harapan = bundle.getString("HARAPAN");

            final TextView username1 = findViewById(R.id.txtusername);
            final TextView pihaklapor = findViewById(R.id.txtpihaklaporan);
            final TextView deskripsi1 = findViewById(R.id.txtpost);
            final TextView tglpost1 = findViewById(R.id.txttanggal);
            final TextView harapan1 = findViewById(R.id.txtharapan);


            username1.setText(username);
            deskripsi1.setText(post);
            pihaklapor.setText(terlapor);
            tglpost1.setText(tglpost);
            harapan1.setText(harapan);

        }
    }
}
