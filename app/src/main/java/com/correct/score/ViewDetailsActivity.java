package com.correct.score;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.correct.score.network.RetrofitClient;
import com.correct.score.adapter.TipsAdapter;
import com.correct.score.footballtips.Tip;
import com.correct.score.footballtips.details.DetailsReponse;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewDetailsActivity extends AppCompatActivity {
    TipsAdapter ReportListAdapter;
    RecyclerView recyclerView;
    private AppCompatImageView back;
    ArrayList<Tip> ReportMOdels = new ArrayList<>();
    TextView gametypename, toolbar;
    String userID, GameName;
    int id;
    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        InterstitialAd.load(this,getString(R.string.admob_interstialId), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        Log.i(TAG, "onAdLoaded");
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        Log.d(TAG, loadAdError.toString());
                        mInterstitialAd = null;
                    }
                });


        Intent mIntent = getIntent();
        userID = mIntent.getStringExtra("id");

        id = Integer.parseInt(userID);

        initView();
        initFunc();
        clickListener();


    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar);

    }

    private void clickListener() {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd != null) {
                    mInterstitialAd.show(ViewDetailsActivity.this);
                    finish();
                } else {
                    finish();
                }
            }
        });

    }

    private void initFunc() {
        final lottiedialogfragment lottie = new lottiedialogfragment(ViewDetailsActivity.this);
        lottie.show();
        Call<DetailsReponse> call = RetrofitClient.getInstance().getApi().getTipsDetails(id);
        call.enqueue(new Callback<DetailsReponse>() {

            @Override
            public void onResponse(Call<DetailsReponse> call, Response<DetailsReponse> response) {

                if (response.isSuccessful()) {
                    lottie.dismiss();
                    DetailsReponse gameDetailsResponse = response.body();
                    ReportMOdels =gameDetailsResponse.getData().getTips();
                   // Toast.makeText(ViewDetailsActivity.this, ""+response.body().getCategoryName(), Toast.LENGTH_SHORT).show();
                  //  toolbar.setText(GameName);
                    showData();

                }
            }

            @Override
            public void onFailure(Call<DetailsReponse> call, Throwable t) {
                t.printStackTrace();
                lottie.dismiss();
            }
        });


    }

    private void showData() {
        recyclerView = findViewById(R.id.reportList);
        ReportListAdapter = new TipsAdapter(getApplicationContext(),ReportMOdels);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(ReportListAdapter);
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