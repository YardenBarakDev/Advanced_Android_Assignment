package com.bawp.common;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public abstract class Activity_Parent extends AppCompatActivity {

    private TextView main_LBL_text;
    private TextView main_LBL_time;

    private long timeStart = 0;
    private long previousTime = 0;
    private AppsTimeInfo appsTimeInfo;
    private AppDatabase appDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        findAllViews();

        //init room database
        appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "time.db").build();

        //get info from url using retrofit
        downloadGarageInfo();

        timeStart = System.currentTimeMillis();
        appsTimeInfo = new AppsTimeInfo();
        appsTimeInfo.setEntryTime(getCurrentHour());
        getPreviousTime();
    }

    private void getPreviousTime() {
        new Thread(() -> {
            previousTime = appDatabase.timeDao().getTotalTime();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("pttt", "show total time: " + previousTime);
                    main_LBL_time.setText(""+previousTime);
                }
            });
        }).start();
    }

    RetrofitCallBack retrofitCallBack = new RetrofitCallBack() {
        @Override
        public void infoFromRetrofit(StringBuilder stringBuilder) {
            main_LBL_text.setText(stringBuilder);
        }
    };

    protected String getCurrentHour(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return dateFormatter.format(calendar.getTime());

    }
    protected void downloadGarageInfo(){
        GarageController garageController = new GarageController(retrofitCallBack);
        garageController.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        timeStart = System.currentTimeMillis();
        appsTimeInfo.setEntryTime(getCurrentHour());
        getPreviousTime();

       new Thread(() -> {
           List<AppsTimeInfo> appsTimeInfo = appDatabase.timeDao().getAll();
           for (AppsTimeInfo a : appsTimeInfo)
               Log.d("pttt", a.getTotalTime() + " " + a.getEntryTime() + " " + a.getExitTime());
       }).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        long timeStop = System.currentTimeMillis();
        long totalTime = timeStop- timeStart;
        appsTimeInfo.setTotalTime(totalTime + previousTime);
        appsTimeInfo.setExitTime(getCurrentHour());

        //save time info
        new Thread(() -> appDatabase.timeDao().insertAll(appsTimeInfo)).start();
    }

    protected void findAllViews(){
        main_LBL_text = findViewById(R.id.main_LBL_text);
        main_LBL_time = findViewById(R.id.main_LBL_time);
    }
}
  /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                appDatabase.timeDao().insertAll(new AppsTimeInfo(1, 2, 3));
                appDatabase.timeDao().insertAll(new AppsTimeInfo(4, 5, 6));
                appDatabase.timeDao().insertAll(new AppsTimeInfo(7, 8, 9));

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<AppsTimeInfo> appsTimeInfo = appDatabase.timeDao().getAll();
                for (AppsTimeInfo a : appsTimeInfo)
                    Log.d("pttt", a.totalTime + " " + a.entryTime + " " + a.exitTime);
            }
        }).start();

*/