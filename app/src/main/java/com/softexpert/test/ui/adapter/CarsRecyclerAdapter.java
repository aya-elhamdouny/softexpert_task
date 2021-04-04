package com.softexpert.test.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.softexpert.test.R;
import com.softexpert.test.data.models.CarsResponseData;
import com.softexpert.test.utils.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private List<CarsResponseData> carsList;
    private Context context;

    public CarsRecyclerAdapter(Context context, List<CarsResponseData> carsList) {
        this.carsList = carsList;
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_car, parent, false));
            case VIEW_TYPE_LOADING:
                return new ProgressHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == carsList.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return carsList == null ? 0 : carsList.size();
    }

    public void addItems(List<CarsResponseData> carsListItems) {
        carsList.addAll(carsListItems);
        notifyDataSetChanged();
    }

    public void addLoading() {
        isLoaderVisible = true;
        carsList.add(new CarsResponseData());
        notifyItemInserted(carsList.size() - 1);
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = carsList.size() - 1;
        CarsResponseData item = getItem(position);
        if (item != null) {
            carsList.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        carsList.clear();
        notifyDataSetChanged();
    }

    CarsResponseData getItem(int position) {
        return carsList.get(position);
    }

    public class ViewHolder extends BaseViewHolder {
        @BindView(R.id.item_car_image)
        ImageView carImageView;
        @BindView(R.id.item_car_brand)
        TextView brandTextView;
        @BindView(R.id.item_car_year)
        TextView yearTextView;
        @BindView(R.id.item_car_is_used)
        TextView isUsedTextView;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
        }

        @SuppressLint("SetTextI18n")
        public void onBind(int position) {
            super.onBind(position);
            CarsResponseData item = carsList.get(position);
            Glide.with(context)
                    .load(item.getImageUrl())
                    .apply(new RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.placeholder))
                    .into(carImageView);
            brandTextView.setText("Brand: " + item.getBrand());
            String year = "";
            if (item.getConstractionYear() != null)
                year = item.getConstractionYear();
            else
                year = "Not added";

            yearTextView.setText("Year: " + year);
            String isUsed = "";
            if (item.getIsUsed())
                isUsed = "Yes";
            else
                isUsed = "No";
            isUsedTextView.setText("Is used: " + isUsed);
        }
    }

    public class ProgressHolder extends BaseViewHolder {
        ProgressHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {
        }
    }
}