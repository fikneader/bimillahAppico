package com.example.chiva.bimillahappico;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditProfilActivity extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    public BottomNavigationView navigationView;
    DatabaseReference rootReff, userReff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbareditprofil);
        setSupportActionBar(toolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        rootReff = FirebaseDatabase.getInstance().getReference();
        userReff = rootReff.child("User");

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(EditProfilActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };

        final EditText notelp = (EditText) findViewById(R.id.notelp);
        userReff.child(user.getUid()).child("notelp").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String notelp2 = dataSnapshot.getValue(String.class);
                notelp.setText(notelp2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText asal = (EditText) findViewById(R.id.asal);
        userReff.child(user.getUid()).child("daerahasal").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String asal2 = dataSnapshot.getValue(String.class);
                asal.setText(asal2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText pekerjaan = (EditText) findViewById(R.id.pekerjaan);
        userReff.child(user.getUid()).child("pekerjaan").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String pekerjaan2 = dataSnapshot.getValue(String.class);
                pekerjaan.setText(pekerjaan2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        final EditText bio = (EditText) findViewById(R.id.bio);
        userReff.child(user.getUid()).child("tentangsaya").addValueEventListener(new com.google.firebase.database.ValueEventListener() {
            @Override
            public void onDataChange(@NonNull com.google.firebase.database.DataSnapshot dataSnapshot) {
                String bio2 = dataSnapshot.getValue(String.class);
                bio.setText(bio2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Button simpan = (Button) findViewById(R.id.simpanprofil);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notelp3 = notelp.getText().toString().trim();
                userReff.child(user.getUid()).child("notelp").setValue(notelp3);

                String asal3 = asal.getText().toString().trim();
                userReff.child(user.getUid()).child("daerahasal").setValue(asal3);

                String pekerjaan3 = pekerjaan.getText().toString().trim();
                userReff.child(user.getUid()).child("pekerjaan").setValue(pekerjaan3);

                String bio3 = bio.getText().toString().trim();
                userReff.child(user.getUid()).child("tentangsaya").setValue(bio3);

                startActivity(new Intent(EditProfilActivity.this, MainActivity.class));
            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dotmenu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.menu_logout){
            // do something
            // Toast.makeText(getApplicationContext(),"tet", Toast.LENGTH_SHORT).show();
            auth.signOut();
        }if(id == R.id.menu_editprofil){
            // do something
            //Toast.makeText(getApplicationContext(),"edit", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(EditProfilActivity.this, EditProfilActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
