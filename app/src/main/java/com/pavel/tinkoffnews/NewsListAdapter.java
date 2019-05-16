package com.pavel.tinkoffnews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.pavel.tinkoffnews.databinding.NewsItemBinding;
import com.pavel.tinkoffnews.remote.data.Title;

import java.util.List;

/**
 * Created by p.mazhnik on 15.05.2019.
 * to tinkoff-android-lab19
 */
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.MyViewHolder> {

    List<Title> mNewsList;

//    public NewsListAdapter(List<Title> newsList) {
//        mNewsList = newsList;
//    }

    public void setNewsList(final List<Title> newsList) {
        //if (this.mNewsList == null) {
            this.mNewsList = newsList;
            //notifyItemRangeInserted(0, newsList.size());
            notifyDataSetChanged();
        //}
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public NewsListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // create a new view
        View v = (View) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsListAdapter.MyViewHolder holder, int position) {
        holder.textView.setText(mNewsList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mNewsList == null ? 0 : mNewsList.size();
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(mNewsList.get(position).getId());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
