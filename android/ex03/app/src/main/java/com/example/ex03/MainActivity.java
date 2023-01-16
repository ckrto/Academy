package com.example.ex03;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    //정의한 DB 전역변수로 선언
    AddressDB helper;
    SQLiteDatabase db;
    JusoAdapter jusoAdapter;
    Cursor cursor;
    //컨택스트메뉴
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        int id=cursor.getInt(0);
        menu.setHeaderTitle(id+"번 메뉴선택");
        menu.add(0,1,0,"수정");
        menu.add(0,2,0,"삭제");
    }

    //컨택스트 아이템 클릭시
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        final int id=cursor.getInt(0);
        switch(item.getItemId()){
            case 1:     //수정
                LinearLayout insertView= (LinearLayout)getLayoutInflater().inflate(R.layout.insert_address,null,false);
                final EditText name=insertView.findViewById(R.id.name);
                final EditText tel=insertView.findViewById(R.id.tel);
                final EditText juso=insertView.findViewById(R.id.juso);
                name.setText(cursor.getString(1));
                tel.setText(cursor.getString(2));
                juso.setText(cursor.getString(3));

                AlertDialog.Builder box=new AlertDialog.Builder(this);
                box.setTitle("주소수정");
                box.setView(insertView);
                box.setPositiveButton("수정", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       String sql="update address set ";
                       sql+="name='"+name.getText().toString()+"',";
                       sql+="tel='"+tel.getText().toString()+"',";
                       sql+="juso='"+juso.getText().toString()+"' ";
                       sql+="where _id="+id;
                       db.execSQL(sql);
                       sql="select * from address order by _id desc";
                       cursor=db.rawQuery(sql,null);
                       jusoAdapter.changeCursor(cursor);

                    }
                });
                box.setNegativeButton("취소",null);
                box.show();
                break;

            case 2:     //삭제
                box= new AlertDialog.Builder(this);
                box.setTitle("질의");
                box.setMessage(id+"번을 삭제하시겠습니까?");
                box.setPositiveButton("예",null);
                box.setNegativeButton("아니오",null);
                box.show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //액션바(메뉴) 생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);

        //검색
        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView = (SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String word="'%" + newText + "%'";
                String sql="select * from address where";
                sql+=" name like " +word;
                sql+=" or juso like "+word;
                sql+=" or tel like "+word;
                cursor=db.rawQuery(sql,null);
                jusoAdapter.changeCursor(cursor);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    //액션바 아이콘 클릭시
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                Toast.makeText(this,"종료됩니다.",Toast.LENGTH_SHORT).show();
                break;

            case R.id.insert:
                final LinearLayout insertView=(LinearLayout) getLayoutInflater().inflate(R.layout.insert_address,null,false);
                AlertDialog.Builder box=new AlertDialog.Builder(this);
                box.setTitle("주소등록");
                box.setView(insertView);
                box.setPositiveButton("등록", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText name=insertView.findViewById(R.id.name);
                        String sname=name.getText().toString();
                        EditText tel=insertView.findViewById(R.id.tel);
                        String stel=tel.getText().toString();
                        EditText juso=insertView.findViewById(R.id.juso);
                        String sjuso=juso.getText().toString();

                        String sql="insert into address(name,tel,juso) values(";
                        sql+="'"+sname+"',";
                        sql+="'"+stel+"',";
                        sql+="'"+sjuso+"')";
                        db.execSQL(sql);
                        Toast.makeText(MainActivity.this,"주소가 등록되었습니다.",Toast.LENGTH_SHORT).show();
                        sql="select * from address order by _id desc";
                        cursor=db.rawQuery(sql,null);
                        jusoAdapter.changeCursor(cursor);
                    }
                });
                box.setNegativeButton("취소",null);
                box.show();
                break;

            case R.id.name:
                String sql="select * from address order by name";
                cursor=db.rawQuery(sql,null);
                jusoAdapter.changeCursor(cursor);
                break;

            case R.id.tel:
                sql="select * from address order by tel";
                cursor=db.rawQuery(sql,null);
                jusoAdapter.changeCursor(cursor);
                break;

            case R.id.juso:
                sql="select * from address order by juso";
                cursor=db.rawQuery(sql,null);
                jusoAdapter.changeCursor(cursor);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("주소록");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);

        //정의한 DB 생성
        helper= new AddressDB(this);
        db=helper.getWritableDatabase();

        //1.데이터 생성
        String sql="select * from address order by _id desc";
        cursor=db.rawQuery(sql,null); //db의 실행결과를 Cursor에 저장한다. java의 Resultset과 같은용도.

        //2.리스트뷰 생성
        ListView list=findViewById(R.id.list);
        registerForContextMenu(list);

        //4.어댑터 생성
        jusoAdapter= new JusoAdapter(this,cursor);
        //5.리스트의 어댑터 설정
        list.setAdapter(jusoAdapter);
    }

    //3.어댑터 정의
    class JusoAdapter extends CursorAdapter{
        public JusoAdapter(Context context, Cursor c) {
            super(context, c);
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view=getLayoutInflater().inflate(
                    R.layout.item_address, parent, false);
            return view;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            //6.데이터 설정
            TextView name= view.findViewById(R.id.name);
            final int id=cursor.getInt(0);
            String strname=cursor.getString(1);
            name.setText("["+id+"]"+strname);

            TextView tel= view.findViewById(R.id.tel);
            tel.setText(cursor.getString(2));

            TextView juso= view.findViewById(R.id.juso);
            juso.setText(cursor.getString(3));

            ImageView delete=view.findViewById(R.id.delete);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder box= new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("질의");
                    box.setMessage(id+"번을 삭제하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String sql="delete from address where _id="+id;
                            db.execSQL(sql);
                            sql="select * from address order by _id desc";
                            Cursor cursor=db.rawQuery(sql,null);
                            jusoAdapter.changeCursor(cursor);
                        }
                    });
                    box.setNegativeButton("아니오",null);
                    box.show();
                }
            });
        }
    }
}