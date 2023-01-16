package com.example.ex05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MemoDB helper;
    SQLiteDatabase db;
    Cursor cursor;
    String sqlAll = "select * from memo order by date desc";
    Adapter adapter;
    ArrayList<String> arrayChk = new ArrayList<String>();
    boolean isDelete = false;
    MenuItem itemDelete;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.old :
                String sql = "select * from memo order by date";
                cursor = db.rawQuery(sql, null);
                adapter.changeCursor(cursor);
                break;
            case R.id.recent :
                sql = "select * from memo order by date desc";
                cursor = db.rawQuery(sql, null);
                adapter.changeCursor(cursor);
                break;
            case R.id.delete :
                AlertDialog.Builder box = new AlertDialog.Builder(this);
                box.setTitle("질의");
                if(arrayChk.size() == 0) {
                    Toast.makeText(MainActivity.this, "삭제할 메모를 선택해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    box.setMessage(arrayChk.size() + "개를 삭제하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for(String id : arrayChk) {
                                String sql = "delete from memo where _id = " + id;
                                db.execSQL(sql);
                            }
                            onRestart();
                            arrayChk.clear();
                        }
                    });
                    box.setNegativeButton("아니요", null);
                    box.show();
                }



                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        itemDelete = menu.findItem(R.id.delete);
        itemDelete.setVisible(isDelete);

        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String word = "'%" + newText + "%'";
                String sql = "select * from memo where content like " + word;
                cursor = db.rawQuery(sql, null);
                adapter.changeCursor(cursor);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);

        helper = new MemoDB(this);
        db = helper.getWritableDatabase();
        cursor = db.rawQuery(sqlAll, null);

        System.out.println("............" + cursor.getCount());

        ListView list = findViewById(R.id.list);
        adapter = new Adapter(this, cursor);
        list.setAdapter(adapter);

        FloatingActionButton edit = findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                startActivity(intent);
            }
        });
    }

    //어댑터 정의
    class Adapter extends CursorAdapter {

        public Adapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.item_memo, parent, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, final Cursor cursor) {
            TextView content = view.findViewById(R.id.content);
            content.setText(cursor.getString(1));

            TextView date = view.findViewById(R.id.date);
            date.setText(cursor.getString(2));
            final int id = cursor.getInt(0);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ReadActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            });

            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    isDelete = true;
                    itemDelete.setVisible(isDelete);
                    onRestart();
                    return false;
                }
            });

            final CheckBox chk = view.findViewById(R.id.chk);
            if(isDelete) {
                chk.setVisibility(View.VISIBLE);
            }
            else {
                chk.setVisibility(View.INVISIBLE);
            }
            chk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(chk.isChecked()) {
                        arrayChk.add(String.valueOf(id));
                    }
                    else {
                        arrayChk.remove(String.valueOf(id));
                    }
                }
            });
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        cursor = db.rawQuery(sqlAll, null);
        adapter.changeCursor(cursor);
    }
}