package com.example.ex15;

import static com.example.ex15.RetomeService.BASE_URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    Retrofit retrofit;
    RetomeService service;
    List<FoodVO> array=new ArrayList<>();
    FoodAdapter adapter=new FoodAdapter();
    String word="";
    String order="recently";

    @Override
    public boolean onPrepareOptionsMenu(@NonNull Menu menu) {
        switch (order){
            case "recently":
                menu.findItem(R.id.recently).setChecked(true);
                break;
            case "name":
                menu.findItem(R.id.name).setChecked(true);
                break;
            case "tel":
                menu.findItem(R.id.tel).setChecked(true);
                break;
            case "address":
                menu.findItem(R.id.address).setChecked(true);
                break;
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.recently:
                order="recently";
                break;
            case R.id.name:
                order="name";
                break;
            case R.id.tel:
                order="tel";
                break;
            case R.id.address:
                order="address";
                break;
        }
        onRestart();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem search=menu.findItem(R.id.search);
        SearchView searchView=(SearchView)search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                word=newText;
                onRestart();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();

        getSupportActionBar().setTitle("맛집관리");

        RecyclerView list=findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service=retrofit.create(RetomeService.class);
        onRestart();

        Button location=findViewById(R.id.location);
        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,
                        MapsActivity.class);
                intent.putExtra("x",126.6751131);
                intent.putExtra("y", 37.4388406);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Call<List<FoodVO>> call=service.list(word, order);
        call.enqueue(new Callback<List<FoodVO>>() {
            @Override
            public void onResponse(Call<List<FoodVO>> call, Response<List<FoodVO>> response) {
                array=response.body();
                System.out.println("데이타갯수:" + array.size());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<FoodVO>> call, Throwable t) {
                System.out.println(".........." + t.toString());
            }
        });
    }

    class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
        @NonNull
        @Override
        public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getLayoutInflater().inflate(
                    R.layout.item, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
            FoodVO vo=array.get(position);
            holder.name.setText(vo.getName());
            holder.tel.setText(vo.getTel());
            holder.address.setText(vo.getAddress());
            holder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(MainActivity.this,
                            ReadActivity.class);
                    intent.putExtra("id", vo.getId());
                    startActivity(intent);
                }
            });

            holder.item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog.Builder box=new AlertDialog.Builder(MainActivity.this);
                    box.setTitle("질의");
                    box.setMessage(vo.getName() + "을(를) 삭제하실래요?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Call<Void> call=service.delete(vo.getId());
                            call.enqueue(new Callback<Void>() {
                                @Override
                                public void onResponse(Call<Void> call, Response<Void> response) {
                                    onRestart();
                                }

                                @Override
                                public void onFailure(Call<Void> call, Throwable t) {

                                }
                            });
                        }
                    });
                    box.setNegativeButton("아니오", null);
                    box.show();
                    return true;
                }
            });

        }

        @Override
        public int getItemCount() {
            return array.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name,tel,address;
            LinearLayout item;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                tel=itemView.findViewById(R.id.tel);
                address=itemView.findViewById(R.id.address);
                item=itemView.findViewById(R.id.item);
            }
        }
    }

    public void checkPermission() {
        String[] permissions= {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        ArrayList<String> noPermissions=new ArrayList<>();
        for(String permission:permissions){
            if(ActivityCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED){
                noPermissions.add(permission);
            }
        }
        if(noPermissions.size() > 0){
            String[] reqPermissions=noPermissions.toArray(new String[noPermissions.size()]);
            ActivityCompat.requestPermissions(this, reqPermissions, 100);
        }
    }
}