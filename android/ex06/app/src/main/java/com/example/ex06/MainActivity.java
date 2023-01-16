package com.example.ex06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    AddressDB helper;
    SQLiteDatabase db;
    String sqlAll = "select * from address order by _id desc";
    Cursor cursor;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        helper = new AddressDB(this);
        db = helper.getWritableDatabase();
        cursor = db.rawQuery(sqlAll, null);

        adapter = new Adapter(this, cursor);
        ListView list = findViewById(R.id.list);
        list.setAdapter(adapter);
    }

    class Adapter extends CursorAdapter {

        public Adapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.item_address, parent, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            TextView name = view.findViewById(R.id.name);
            name.setText(cursor.getString(1));

            TextView tel = view.findViewById(R.id.tel);
            tel.setText(cursor.getString(2));

            TextView address = view.findViewById(R.id.juso);
            address.setText(cursor.getString(3));

            final int id = cursor.getInt(0);

            CircleImageView image = view.findViewById(R.id.image);
            String strImage = cursor.getString(4);
            if(strImage != null) {
                image.setImageURI(Uri.parse(strImage));
            }

            FloatingActionButton add = findViewById(R.id.add);
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, InsertActivity.class);
                    startActivity(intent);
                }
            });

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });

        }
    }

    protected void onRestart() {
        super.onRestart();
        cursor = db.rawQuery(sqlAll, null);
        adapter.changeCursor(cursor);
    }

    public void checkPermission() {
        String[] permissions= { Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA };
        ArrayList<String> noPermissions=new ArrayList<>();
        for(String permission:permissions) {
            if (ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                noPermissions.add(permission);

            }
            if (noPermissions.size() > 0) {
                String[] reqPermissions = noPermissions.toArray(new String[noPermissions.size()]);
                ActivityCompat.requestPermissions(this, reqPermissions, 100);
            }
        }
    }
}