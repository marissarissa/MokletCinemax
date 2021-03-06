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

import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.ComingFragment;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.R;
import id.sch.smktelkom_mlg.privateassignment.xirpl317.mokletcinemax.model.Results;

/**
 * Created by Marissa on 5/14/2017.
 */

public class ComingAdapter extends RecyclerView.Adapter<ComingAdapter.ViewHolder> {

    public String url = "https://image.tmdb.org/t/p/w500";
    public String image;
    ArrayList<Results> comingList;
    ComingFragment comingFragment;
    Context context;
    private int lastposition = -1;

    public ComingAdapter(ComingFragment comingFragment, ArrayList<Results> movieList, Context context) {
        this.comingList = movieList;
        this.comingFragment = comingFragment;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.coming_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Results results = comingList.get(position);
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
        if (comingList != null)
            return comingList.size();
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
