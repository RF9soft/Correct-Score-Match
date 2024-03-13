package com.correct.score.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.correct.score.network.RetrofitClient;
import com.correct.score.R;
import com.correct.score.adapter.TodayTipsAdapter;
import com.correct.score.databinding.FragmentMoreBinding;
import com.correct.score.oldtips.OldTipsResponse;
import com.correct.score.todaytips.TodayTipsData;
import com.correct.score.todaytips.TodayTipsResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_More extends Fragment {
    Activity activity;
    FragmentMoreBinding binding;
    private RecyclerView recyclerView,old;
    private TodayTipsAdapter dashboardAdapter;
    ArrayList<TodayTipsData> ReportMOdels2 = new ArrayList<>();
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMoreBinding.inflate(inflater, container, false);
        activity = getActivity();


        CategoryList();
        CategoryListOld();
        return binding.getRoot();
    }
    private void CategoryListOld() {
        Call<OldTipsResponse> call = RetrofitClient.getInstance().getApi().OldResponse();
        call.enqueue(new Callback<OldTipsResponse>() {

            @Override
            public void onResponse(Call<OldTipsResponse> call, Response<OldTipsResponse> response) {

                if (response.isSuccessful()) {

                    OldTipsResponse list = response.body();
                    ReportMOdels2 = list.getData();
                    showOldData();

                }
            }

            @Override
            public void onFailure(Call<OldTipsResponse> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

    private void showOldData() {

        dashboardAdapter = new TodayTipsAdapter(getActivity(), ReportMOdels2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.rvold.setLayoutManager(layoutManager);
        binding.rvold.setAdapter(dashboardAdapter);
    }
    private void CategoryList() {
        final lottiedialogfragment lottie = new lottiedialogfragment(getActivity());
        lottie.show();
        Call<TodayTipsResponse> call = RetrofitClient.getInstance().getApi().todayResponse();
        call.enqueue(new Callback<TodayTipsResponse>() {

            @Override
            public void onResponse(Call<TodayTipsResponse> call, Response<TodayTipsResponse> response) {

                if (response.isSuccessful()) {
                    lottie.dismiss();

                    TodayTipsResponse list = response.body();
                    ReportMOdels2 = list.getData();
                    showCatData();

                }
            }

            @Override
            public void onFailure(Call<TodayTipsResponse> call, Throwable t) {
                lottie.dismiss();
                t.printStackTrace();

            }
        });
    }




    private void showCatData() {

        dashboardAdapter = new TodayTipsAdapter(getActivity(), ReportMOdels2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewToday.setLayoutManager(layoutManager);
        binding.recyclerViewToday.setAdapter(dashboardAdapter);

    }
    public class lottiedialogfragment extends Dialog {
        public lottiedialogfragment(Context context) {
            super(context);

            WindowManager.LayoutParams wlmp = getWindow().getAttributes();

            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            getWindow().setAttributes(wlmp);
            getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            setTitle(null);
            setCancelable(false);
            setOnCancelListener(null);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.dialog_lottie, null);
            setContentView(view);
        }
    }

}
