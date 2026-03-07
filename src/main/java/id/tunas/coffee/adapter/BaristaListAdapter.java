package id.tunas.coffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import id.tunas.coffee.R;
import id.tunas.coffee.dto.Barista;

public class BaristaListAdapter extends RecyclerView.Adapter<BaristaListAdapter.BaristaViewHolder> {

    private final List<Barista> baristas;
    private final OnItemClickListener onClickListener;

    public BaristaListAdapter(List<Barista> baristas, OnItemClickListener onClickListener) {
        this.baristas = baristas;
        this.onClickListener = onClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull BaristaViewHolder holder, int position) {
        Barista barista = baristas.get(position);
        Glide.with(holder.itemView.getContext()).load(barista.getImg()).into(holder.img);
        holder.name.setText(barista.getName());
        holder.badge.setImageResource(R.drawable.barista_badge_green);
        holder.itemView.setOnClickListener(view -> {
            onClickListener.onItemClick(barista);
        });
    }

    @NonNull
    @Override
    public BaristaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.barista_list_item, parent, false);
        return new BaristaViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return baristas.size();
    }

    public class BaristaViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView img;
        private ImageView badge;

        public BaristaViewHolder(@NonNull View itemView) {
            super(itemView);

            this.name = itemView.findViewById(R.id.barista_name);
            this.img = itemView.findViewById(R.id.barista_img);
            this.badge = itemView.findViewById(R.id.barista_badge);

        }
    }

    public interface OnItemClickListener{
        void onItemClick(Barista barista);
    }
}
