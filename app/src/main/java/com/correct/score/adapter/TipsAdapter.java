package com.correct.score.adapter;

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
import com.correct.score.R;
import com.correct.score.footballtips.Tip;

import java.util.ArrayList;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.DashboardViewHolder> {

    private Context context;
    private ArrayList<Tip> dashboardItemList;


    public TipsAdapter(Context context, ArrayList<Tip> dashboardItemList) {
        this.context = context;
        this.dashboardItemList = dashboardItemList;

    }

    @NonNull
    @Override
    public DashboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_item_layout2, parent, false);
        return new DashboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardViewHolder holder, final int position) {
        Tip dashboardItemModel = dashboardItemList.get(position);

       holder.date.setText(dashboardItemModel.getDate());
        holder.team1.setText(dashboardItemModel.getTeamOne());
        holder.team2.setText(dashboardItemModel.getTeamTwo());
        holder.odds.setText("Odds:"+dashboardItemModel.getOddsValue());
        holder.tips.setText("Tips:"+dashboardItemModel.getTipsName());
        holder.leage.setText(dashboardItemModel.getLeagueName());
        holder.status.setText(dashboardItemModel.getStatus());
        holder.time.setText(dashboardItemModel.getTime());

        Glide.with(context).load( dashboardItemList.get(position).getTeamOneImage()).apply(new RequestOptions().
                error(R.drawable.football).fitCenter()).into(holder.team1logo);
        Glide.with(context).load( dashboardItemList.get(position).getTeamTwoImage()).apply(new RequestOptions().
                error(R.drawable.football).fitCenter()).into(holder.team2logo);

    }

    @Override
    public int getItemCount() {
        if(dashboardItemList!=null) {
            return dashboardItemList.size();
        }
        else
        {
            return 0;
        }

    }

    public class DashboardViewHolder extends RecyclerView.ViewHolder {


        private TextView date,team1,team2,odds,tips,time,leage,status;
        private ImageView team1logo,team2logo;


        public DashboardViewHolder(@NonNull View itemView) {
            super(itemView);

            leage = itemView.findViewById(R.id.league_name);
            date = itemView.findViewById(R.id.date);
            team1 = itemView.findViewById(R.id.team_one);
            team2 = itemView.findViewById(R.id.team_two);
            odds = itemView.findViewById(R.id.odds);
            time = itemView.findViewById(R.id.time);
            status = itemView.findViewById(R.id.status);
            tips = itemView.findViewById(R.id.pro_tips);
            team1logo = itemView.findViewById(R.id.team_one_img);
            team2logo = itemView.findViewById(R.id.team_two_img);
        }
    }
}
