package id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.NowFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.model.Results;

/**
 * Created by Marissa on 5/13/2017.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Results> movieList;
    NowFragment nowFragment;
    Context context;
    private int lastposition = -1;

    public MovieAdapter(NowFragment nowFragment, ArrayList<Results> movieList, Context context) {
        this.movieList = movieList;
        this.nowFragment = nowFragment;
        this.context = context;

    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.now_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MovieAdapter.ViewHolder holder, int position) {

        Results results = movieList.get(position);
        holder.tvJudul.setText(results.title);
        holder.tvDeskripsi.setText(results.overview);
        image = url + results.backdrop_path;
        Glide.with(context).load(image)
                .crossFade()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher)
                .into(holder.ivFoto);

    }

    @Override
    public int getItemCount() {
        if (movieList != null)
            return movieList.size();
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvJudul;
        TextView tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
        }
    }
}
