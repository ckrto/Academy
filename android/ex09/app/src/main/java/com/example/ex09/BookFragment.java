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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class BookFragment extends Fragment {
    String query="안드로이드";
    JSONArray array=new JSONArray();
    BookAdapter bookAdapter=new BookAdapter();
    RecyclerView list;
    ProgressBar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_blog, container, false);
        EditText search=view.findViewById(R.id.search);
        search.setHint("도서 검색어");

        bar=view.findViewById(R.id.progress);
        list=view.findViewById(R.id.list);
        new KakaoThread().execute();

        list.setLayoutManager(new LinearLayoutManager(getActivity()));

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
        return view;
    }

    class KakaoThread extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            String url="https://dapi.kakao.com/v3/search/book?target=title&query=" + query;
            String result=Kakao.connect(url);
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                array=new JSONObject(s).getJSONArray("documents");
                System.out.println("데이터갯수:" + array.length());
                list.setAdapter(bookAdapter);
                bar.setVisibility(View.GONE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            bar.setVisibility(View.VISIBLE);
        }
    }

    class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
        @NonNull
        @Override
        public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=getActivity().getLayoutInflater().inflate(
                    R.layout.item_blog, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
            try {
                JSONObject obj=array.getJSONObject(position);
                String strImage=obj.getString("thumbnail");
                String strAuthor=obj.getString("authors");
                final String strTitle=obj.getString("title");
                final String strUrl=obj.getString("url");

                holder.title.setText(strTitle);
                holder.author.setText(strAuthor);
                //Picasso.with(getActivity()).load(strImage).into(holder.image);

                holder.item.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),
                                BlogActivity.class);
                        intent.putExtra("url", strUrl);
                        intent.putExtra("name", strTitle);
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
            TextView title, author;
            RelativeLayout item;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                image=itemView.findViewById(R.id.image);
                title=itemView.findViewById(R.id.title);
                author=itemView.findViewById(R.id.name);
                item=itemView.findViewById(R.id.item);
            }
        }
    }
}