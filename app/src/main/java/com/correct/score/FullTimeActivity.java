package com.correct.score;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.correct.score.footballtips.category.CategoryListResponse;
import com.correct.score.footballtips.category.Datum;
import com.correct.score.model.DatewiseResponse;
import com.correct.score.network.RetrofitClient;
import com.correct.score.adapter.TipsAdapter;
import com.correct.score.footballtips.Tip;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Calendar;

import devs.mulham.horizontalcalendar.HorizontalCalendar;
import devs.mulham.horizontalcalendar.utils.HorizontalCalendarListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FullTimeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView recyclerView;

    private DrawerLayout drawer;
    private RecyclerView rvPrediction;
    private TipsAdapter predictionsAdapter;
    private ArrayList<Tip> predictionsList;
    String recipient;
    ArrayList<Datum> ReportMOdels2 = new ArrayList<>();
    String subject, body;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_time);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view_left);
        View headerview = navigationView.getHeaderView(0);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        loadcurrentdatedate();
        loadAds();
        CategoryList();

    }

    private void CategoryList() {
        Call<CategoryListResponse> call = RetrofitClient.getInstance().getApi().getGameCatResponse();
        call.enqueue(new Callback<CategoryListResponse>() {
            @Override
            public void onResponse(Call<CategoryListResponse> call, Response<CategoryListResponse> response) {

                if (response.isSuccessful()) {


                    CategoryListResponse list = response.body();
                    ReportMOdels2 = list.getData();


                }
            }

            @Override
            public void onFailure(Call<CategoryListResponse> call, Throwable t) {
                t.printStackTrace();


            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadAds() {

        /* starts before 1 month from now */
        Calendar startDate = Calendar.getInstance();
        startDate.add(Calendar.MONTH, -1);

        /* ends after 1 month from now */
        Calendar endDate = Calendar.getInstance();
        endDate.add(Calendar.MONTH, 1);

        // on below line we are setting up our horizontal calendar view and passing id our calendar view to it.
        HorizontalCalendar horizontalCalendar = new HorizontalCalendar.Builder(this, R.id.calendarView)
                // on below line we are adding a range
                // as start date and end date to our calendar.
                .range(startDate, endDate)
                // on below line we are providing a number of dates
                // which will be visible on the screen at a time.
                .datesNumberOnScreen(5)
                // at last we are calling a build method
                // to build our horizontal recycler view.
                .build();
        horizontalCalendar.setCalendarListener(new HorizontalCalendarListener() {
            @Override
            public void onDateSelected(Calendar date, int position) {
                int year = date.get(Calendar.YEAR);
                int month = date.get(Calendar.MONTH) + 1; // Add 1 to adjust the month value
                int day = date.get(Calendar.DAY_OF_MONTH);
                String selectedDate = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
                getData(selectedDate);
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void loadcurrentdatedate() {
        // Get current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Note: Months are zero-based
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Display current date
        String currentDate = year + "-" + (month < 10 ? "0" + month : month) + "-" + (day < 10 ? "0" + day : day);
        getData(currentDate);
    }


    private void getData(String date) {

        Call<DatewiseResponse> call = RetrofitClient.getInstance().getApi().DateResponse(date);
        call.enqueue(new Callback<DatewiseResponse>() {

            @Override
            public void onResponse(Call<DatewiseResponse> call, Response<DatewiseResponse> response) {

                if (response.isSuccessful()) {

                    DatewiseResponse list = response.body();
                    predictionsList = list.getData();

                    showCatData();

                }
            }

            @Override
            public void onFailure(Call<DatewiseResponse> call, Throwable t) {

                t.printStackTrace();

            }
        });


    }

    private void showCatData() {
        // Find the RecyclerView with the id predictions_recycler_view
        rvPrediction = findViewById(R.id.predictions_recycler_view);

// Create and set the layout manager for rvPrediction
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rvPrediction.setLayoutManager(layoutManager);

// Create and set the adapter for rvPrediction
        predictionsAdapter = new TipsAdapter(getApplicationContext(), predictionsList);
        rvPrediction.setAdapter(predictionsAdapter);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.both:
                String id1 = String.valueOf(ReportMOdels2.get(0).getId());
                Intent mIntent = new Intent(getApplicationContext(), ViewDetailsActivity.class);
                mIntent.putExtra("id", id1);
                mIntent.putExtra("name", ReportMOdels2.get(0).getCategoryName());
                startActivity(mIntent);
                break;

            case R.id.halftimefull:
                String id2 = String.valueOf(ReportMOdels2.get(1).getId());
                Intent mIntent2 = new Intent(getApplicationContext(), ViewDetailsActivity.class);
                mIntent2.putExtra("id", id2);
                mIntent2.putExtra("name", ReportMOdels2.get(1).getCategoryName());
                startActivity(mIntent2);
                break;

            case R.id.fulltime:
                String id3 = String.valueOf(ReportMOdels2.get(2).getId());
                Intent mIntent3 = new Intent(getApplicationContext(), ViewDetailsActivity.class);
                mIntent3.putExtra("id", id3);
                mIntent3.putExtra("name", ReportMOdels2.get(2).getCategoryName());
                startActivity(mIntent3);
                break;

            case R.id.correctscore:
                String id4 = String.valueOf(ReportMOdels2.get(3).getId());
                Intent mIntent5 = new Intent(getApplicationContext(), ViewDetailsActivity.class);
                mIntent5.putExtra("id", id4);
                mIntent5.putExtra("name", ReportMOdels2.get(3).getCategoryName());
                startActivity(mIntent5);
                break;

            case R.id.overunder:
                String id5 = String.valueOf(ReportMOdels2.get(4).getId());
                Intent mIntent7 = new Intent(getApplicationContext(), ViewDetailsActivity.class);
                mIntent7.putExtra("id", id5);
                mIntent7.putExtra("name", ReportMOdels2.get(4).getCategoryName());
                startActivity(mIntent7);
                break;



            case R.id.rate:
                Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getApplicationContext().getPackageName()));
                startActivity(rateIntent);
                break;


            case R.id.tel:
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.tel_link)));
                startActivity(i);
                break;
                case R.id.wh:

                    // WhatsApp link to be opened
                    String whatsappLink = "https://wa.link/7u3loi";

                    // Create an Intent with the ACTION_VIEW action and the WhatsApp link
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(whatsappLink));

                    // Check if WhatsApp is installed on the device
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        // WhatsApp is installed, open the link
                        startActivity(intent);
                    } else {
                        // WhatsApp is not installed, handle the situation (e.g., show a message to the user)
                        // You can display a Toast message or show an AlertDialog to inform the user
                    }
                break;
            case R.id.contact:
                Intent intent5 = new Intent(Intent.ACTION_VIEW, Uri.parse("mailto:" + "correctscoregpt@gmail.com"));
                intent5.putExtra(Intent.EXTRA_SUBJECT, "email_subject");
                intent5.putExtra(Intent.EXTRA_TEXT, "email_body");
                startActivity(intent5);
                break;


        }

        drawer.closeDrawer(GravityCompat.START);
        return true;

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FullTimeActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
//            super.onBackPressed();
        }
    }


}