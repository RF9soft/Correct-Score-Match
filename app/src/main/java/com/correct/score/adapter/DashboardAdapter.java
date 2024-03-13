package com.correct.score.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.correct.score.network.OnUserClickListener;
import com.correct.score.R;
import com.correct.score.ViewDetailsActivity;
import com.correct.score.footballtips.category.Datum;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder> {

    private Context context;
    private ArrayList<Datum> dashboardItemList;
    private OnUserClickListener onCategoryItemClick;

    public DashboardAdapter(Context context, ArrayList<Datum> dashboardItemList) {
        this.context = context;
        this.dashboardItemList = dashboardItemList;

    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.layout_dashboard, parent, false);
        return new DashboardViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Datum dashboardItemModel = dashboardItemList.get(position);

        holder.dashItemTitle.setText(dashboardItemModel.getCategoryName());
        holder.sub.setText(dashboardItemModel.getCategoryType());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = String.valueOf(dashboardItemModel.getId());
                Intent mIntent = new Intent(context, ViewDetailsActivity.class);
                mIntent.putExtra("id", id);

                context.startActivity(mIntent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return dashboardItemList.size();
    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {

        private ImageView dashItemImage;
        private TextView dashItemTitle, sub;
        private LinearLayout cardView;

        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            //  dashItemImage = itemView.findViewById(R.id.img_row_HomeMenu);
            dashItemTitle = itemView.findViewById(R.id.tv_row_HomeMenu_Title);
            sub = itemView.findViewById(R.id.tv_category_type);
            cardView = itemView.findViewById(R.id.dashboard_card);
        }
    }

    public void SetItemClick(OnUserClickListener onCategoryItemClick) {
        this.onCategoryItemClick = onCategoryItemClick;
    }

}
