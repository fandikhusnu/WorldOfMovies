package id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl511.worldofmovies.model.Popular;

/**
 * Created by hyuam on 12/10/2016.
 */

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    Context context;
    ArrayList<Popular> list;
    PopularAdapter.IPopularAdapter mIPopularAdapter;

    public PopularAdapter(Context context, ArrayList<Popular> list) {
        this.list = list;
        this.context = context;
        mIPopularAdapter = (PopularAdapter.IPopularAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.source_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Popular result = list.get(position);
        holder.tvName.setText(result.title);
        holder.tvDesc.setText(result.overview);
        Glide.with(context)
                .load("http://image.tmdb.org/t/p/w500" + result.poster_path)
                .into(holder.ivPoster);
    }

    @Override
    public int getItemCount() {
        if (list != null)
            return list.size();
        return 0;
    }

    public interface IPopularAdapter {
        void showDetail(String name, String poster_path, String overview, String release_date, String vote_count, String vote_average);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDesc;
        ImageView ivPoster;


        public ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.textViewName);
            tvDesc = (TextView) itemView.findViewById(R.id.textViewDesc);
            ivPoster = (ImageView) itemView.findViewById(R.id.imageViewPoster);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Popular popular = list.get(getAdapterPosition());
                    mIPopularAdapter.showDetail(popular.title, popular.poster_path, popular.overview, popular.release_date, popular.vote_count, popular.vote_average);
                }
            });
        }
    }
}
