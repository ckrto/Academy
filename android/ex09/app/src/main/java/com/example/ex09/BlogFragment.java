package com.example.ex09;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.AsynchronousChannelGroup;

public class BlogFragment extends Fragment {
    String query="커피";
    JSONArray array=new JSONArray();
    RecyclerView list;
    BlogAdapter blogAdapter=new BlogAdapter();
    ProgressBar progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blog, container, false);
        list=view.findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(getActivity()));
        progressBar=view.findViewById(R.id.progress);

        new KakaoThread().execute();

        EditText search=view.findViewById(R.id.search);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                query=s.toString();
                //System.out.println("..........." + query);
                new KakaoThread().execute();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        return view;
    }

    class KakaoThread extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            String url="https://dapi.kakao.com/v2/search/blog?query=" + query;
            String result=Kakao.connect(url);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                array= new JSONObject(s).getJSONArray("documents");
                System.out.println("데이터갯수:" + array.length());
                list.setAdapter(blogAdapter);
                progressBar.setVisibility(View.GONE);

            } catch (JSONException e) {
                System.out.println("블로그 오류:" + e.toString());
            }
            super.onPostExecute(s);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    //블로그 어댑터 정의
    class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.ViewHolder> {
        @NonNull
        @Override
        public BlogAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getActivity().getLayoutInflater().inflate(
                    R.layout.item_blog, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BlogAdapter.ViewHolder holder, int position) {
            try {
                JSONObject obj=array.getJSONObject(position);
                final String strName=obj.getString("blogname");
                holder.name.setText(strName);
                String strTitle=obj.getString("title");
                holder.title.setText(Html.fromHtml(strTitle));
                String strImage=obj.getString("thumbnail");
                final String link=obj.getString("url");
                //Picasso.with(getActivity()).load(strImage).into(holder.image);

                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),
                                BlogActivity.class);
                        intent.putExtra("url", link);
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
            ImageView image;
            TextView name, title;
            RelativeLayout item;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image=itemView.findViewById(R.id.image);
                name=itemView.findViewById(R.id.name);
                title=itemView.findViewById(R.id.title);
                item=itemView.findViewById(R.id.item);
            }
        }
    }
}