package com.example.ex09;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LocalFragment extends Fragment {
    String query="인천 학익동";
    JSONArray array=new JSONArray();
    ProgressBar progress;
    RecyclerView list;
    LocalAdapter localAdapter=new LocalAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blog, container, false);

        EditText search=view.findViewById(R.id.search);
        search.setHint("지역검색어");
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query=s.toString();
                new KakaoThread().execute();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        progress=view.findViewById(R.id.progress);
        list=view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));

        new KakaoThread().execute();
        return view;
    }

    class KakaoThread extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String url="https://dapi.kakao.com/v2/local/search/keyword.json?query=" + query;
            String result=Kakao.connect(url);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                array=new JSONObject(s).getJSONArray("documents");
                System.out.println("데이터갯수:" + array.length());
                list.setAdapter(localAdapter);
                progress.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.setVisibility(View.VISIBLE);
        }
    }

    class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder>{
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getActivity().getLayoutInflater().inflate(
                    R.layout.item_local, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            try {
                JSONObject obj=array.getJSONObject(position);
                final String strName=obj.getString("place_name");
                String strTel=obj.getString("phone");
                String strAddress=obj.getString("address_name");
                holder.name.setText(strName);
                holder.address.setText(strAddress);
                holder.tel.setText(strTel);
                final double x=obj.getDouble("x");
                final double y=obj.getDouble("y");
                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),
                                MapsActivity.class);
                        intent.putExtra("x", x);
                        intent.putExtra("y", y);
                        intent.putExtra("name", strName);
                        startActivity(intent);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return array.length();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, address, tel;
            RelativeLayout item;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name=itemView.findViewById(R.id.name);
                address=itemView.findViewById(R.id.address);
                tel=itemView.findViewById(R.id.tel);
                item=itemView.findViewById(R.id.item);
            }
        }
    }
}