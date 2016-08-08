package com.example.owner.prunning;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.Calendar;

public class TestDateActivity extends AppCompatActivity {

    private ArrayList<String> mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    int year = 0;
    int monthOfYear = 0;
    int dayOfMonth = 0;

    String titleText;

    EditText test_name_edit;

    SharedPreferences pref;


    DatePickerDialog datePickerDialog;


    DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(android.widget.DatePicker datePicker, int getYear,
                              int getMonthOfYear, int getDayOfMonth) {

            year = getYear;
            monthOfYear = getMonthOfYear;
            dayOfMonth = getDayOfMonth;
            //ログ出力
            Log.d("DatePicker", "year:" + year + " monthOfYear:" + monthOfYear
                    + " dayOfMonth:" + dayOfMonth);

            //textView.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_date);
        test_name_edit = (EditText) findViewById(R.id.test_name_edit);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPlanetTitles = new ArrayList<String>();

        mPlanetTitles.add("数学");
        mDrawerList = (ListView) findViewById(R.id.listView);

        pref = getSharedPreferences("pref_test", MODE_PRIVATE);


        SubjectAdapter subjectAdapter = new SubjectAdapter(this,
                R.layout.card, mPlanetTitles);

        // Set the adapter for the list view
        mDrawerList.setAdapter(subjectAdapter);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);

        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            public void onDrawerClosed(View drawerView) {
            }


            public void onDrawerOpened(View drawerView) {

            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    public void calender(View v) {

        //日付初期値設定
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR); // 年
        monthOfYear = calendar.get(Calendar.MONTH); // 月
//        monthOfYear += 1;
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 日
       // Log.d("a","" + monthOfYear);

        // 日付設定ダイアログの作成・リスナの登録
        datePickerDialog = new DatePickerDialog(this, DateSetListener, year, monthOfYear, dayOfMonth);

        datePickerDialog.show();

    }

    public void set(View v) {
        titleText = test_name_edit.getText().toString();

        SharedPreferences.Editor editor =pref.edit();
        editor.putString("title", titleText);
        editor.putInt("year", year);
        editor.putInt("month", monthOfYear);
        editor.putInt("day", dayOfMonth);
        editor.commit();


        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
        finish();


    }


    public void setting(View v) {
        Intent intent = new Intent(this, SetteiActivity.class);
        startActivity(intent);
    }

}

