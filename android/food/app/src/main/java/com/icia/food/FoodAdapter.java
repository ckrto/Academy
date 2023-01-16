package com.icia.food;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder>{
    Context context;
    List<FoodVO> array;
    FoodDAO dao = new FoodDAO();
    FoodDB helper;
    String name;

    public FoodAdapter(Context context, List<FoodVO> array, String name) {
        this.context = context;
        this.array = array;
        helper = new FoodDB(context);
        this.name = name;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_food,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FoodVO vo = array.get(position);
        holder.name.setText(vo.getName());
        holder.description.setText(vo.getDescription());
        String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        //System.out.println("sdPath = " + sdPath);
        String image = sdPath + "/pictures/" + vo.getImage();
        //System.out.println(" //////////= " +image);
        holder.image.setImageBitmap(BitmapFactory.decodeFile(image));

        AlertDialog.Builder box = new AlertDialog.Builder(context);
        box.setTitle("질의");

        if(vo.getKeep()==1){
            holder.keep.setImageResource(R.drawable.ic_keep_on);
        }else{
            holder.keep.setImageResource(R.drawable.ic_keep_off);
        }

        holder.keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vo.getKeep()==1){
                    holder.keep.setImageResource(R.drawable.ic_keep_on);
                    box.setMessage("즐겨찾기를 취소하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.updateKeep(helper, 0, vo.getId());
                            holder.keep.setImageResource(R.drawable.ic_keep_off);
                            vo.setKeep(0);
                            if(name.equals("keep")) {
                                array.remove(vo);
                                notifyDataSetChanged();
                            }
                        }
                    });
                    box.setNegativeButton("아니요", null);
                    box.show();
                }else{
                    holder.keep.setImageResource(R.drawable.ic_keep_off);
                    box.setMessage("즐겨찾기에 등록하시겠습니까?");
                    box.setPositiveButton("예", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dao.updateKeep(helper, 1, vo.getId());
                            vo.setKeep(1);
                            holder.keep.setImageResource(R.drawable.ic_keep_on);
                        }
                    });
                    box.setNegativeButton("아니요", null);
                    box.show();
                }
            }
        });

        //아이템을 클릭한경우
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, FoodInfoActivity.class);
                intent.putExtra("id", vo.getId());
                if(name.equals("keep")) {
                    ((Activity)context).startActivityForResult(intent,
                            100);
                }else{
                    ((Activity)context).startActivityForResult(intent,
                            200);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image,keep;
        TextView name,description;
        CardView item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            keep = itemView.findViewById(R.id.keep);
            item = itemView.findViewById(R.id.item);
        }
    }
}