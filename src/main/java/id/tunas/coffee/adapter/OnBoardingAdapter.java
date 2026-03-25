package id.tunas.coffee.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.tunas.coffee.R;
import id.tunas.coffee.dto.OnBoardingItem;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnboardingViewHolder>{

    List<OnBoardingItem> items;

    public OnBoardingAdapter(List<OnBoardingItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public OnboardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater from = LayoutInflater.from(parent.getContext());
        return new OnboardingViewHolder(from.inflate(R.layout.item_onboarding, parent, false));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(@NonNull OnboardingViewHolder holder, int position) {
        OnBoardingItem onBoardingItem = items.get(position);
        holder.title.setText(onBoardingItem.getTitle());
        holder.description.setText(onBoardingItem.getDescription());
    }

    public static class OnboardingViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        TextView description;
        public OnboardingViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
        }
    }
}
