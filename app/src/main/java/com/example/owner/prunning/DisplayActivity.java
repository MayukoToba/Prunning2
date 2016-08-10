package com.example.owner.prunning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ParseException;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.activeandroid.query.Select;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DisplayActivity extends AppCompatActivity {

    private ArrayList<String> mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    TextView dateText;
    TextView countDateText;
    TextView countTimeText;
    TextView display_textView;

    SharedPreferences pref1,pref2;

    Calendar nCalendar, tCalendar;

    List<TaskCard> taskCardList;
    TaskAdapter mTaskAdapter;
    ListView mlistView;


    int year;
    int monthOfYear;
    int dayOfMonth;

    int nowYear;
    int nowMonth;
    int nowDay;


    java.util.Date utilStartDate;
    java.sql.Date sqlStartDate;
    java.util.Date utilToDate;
    java.sql.Date sqlToDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        mlistView = (ListView) findViewById(R.id.todo_list);
        taskCardList = new ArrayList<TaskCard>();


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPlanetTitles = new ArrayList<String>();

        mDrawerList = (ListView) findViewById(R.id.listView);





        List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items) {
                String subject;
                 subject= i.subject;
                mPlanetTitles.add(subject);

        }

         SubjectAdapter arrayAdapter= new SubjectAdapter (this, R.layout.card,mPlanetTitles);
         mDrawerList.setAdapter(arrayAdapter);







        pref1 = getSharedPreferences("pref_test", MODE_PRIVATE);
        pref2 = getSharedPreferences("pref_card", MODE_PRIVATE);


        SubjectAdapter subjectAdapter = new SubjectAdapter(this,
                R.layout.card, mPlanetTitles);

        // Set the adapter for the list view
        mDrawerList.setAdapter(subjectAdapter);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("表示");

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


        //titleText = (TextView) findViewById(R.id.titleView);
        dateText = (TextView) findViewById(R.id.dateView);
        //made = (TextView) findViewById(R.id.madeView);
        countDateText = (TextView) findViewById(R.id.countDateView);
        countTimeText = (TextView) findViewById(R.id.countTimeView);
        display_textView = (TextView) findViewById(R.id.display_textView);

        nowDate();
    }
    void nowDate() {
        nCalendar = Calendar.getInstance();
        nowYear = nCalendar.get(Calendar.YEAR); // 年
        nowMonth = nCalendar.get(Calendar.MONTH) + 1; // 月
        nowDay = nCalendar.get(Calendar.DAY_OF_MONTH); // 日
        //dateText.setText(nowYear + "/" + nowMonth + "/" + nowDay);
        Log.d("test", nowYear + "/" + nowMonth + "/" + nowDay);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    public void setting(View v) {
        Intent intent = new Intent(this, SetteiActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        String title = pref1.getString("title","テスト頑張ってね");
        year = pref1.getInt("year", 0);
        monthOfYear = pref1.getInt("month", 0) + 1;
        dayOfMonth = pref1.getInt("day", 0);


        //初期状態だった時現在日時を表示する
        if (year == 0 && monthOfYear == 0 && dayOfMonth == 0) {
            dateText.setText(nowYear + "/" + nowMonth + "/" + nowDay);
        }
        //年を設定した時
        else {
            display_textView.setText(title + "");
            dateText.setText(year + "年" + monthOfYear + "月" + dayOfMonth + "日");
            Log.d("test", year + "年" + monthOfYear + "月" + dayOfMonth + "日");

            tCalendar = Calendar.getInstance();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

            // 日付を作成します。
            try {
                utilStartDate = sdf.parse(nowYear + "/" + nowMonth + "/" + nowDay);
                sqlStartDate = new java.sql.Date(utilStartDate.getTime());
                Log.d("start", sqlStartDate + "");

                utilToDate = sdf.parse(year + "/" + monthOfYear + "/" + dayOfMonth);
                sqlToDate = new java.sql.Date(utilToDate.getTime());
                Log.d("to", sqlToDate + "");
            } catch (ParseException e) {
                e.printStackTrace();
            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }

            // 日付をlong値に変換します。
            long dateTimeTo = sqlToDate.getTime();
            long dateTimeFrom = sqlStartDate.getTime();

            // 差分の日数を算出します。
            long dayDiff = (dateTimeTo - dateTimeFrom) / (1000 * 60 * 60 * 24);

            countDateText.setVisibility(View.VISIBLE);
            countDateText.setText("あと" + dayDiff + "日");
            countTimeText.setVisibility(View.VISIBLE);
        }

        setYotei();
        mlistView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent,View v,int position,long id){
               taskCardList.remove(position);
                mTaskAdapter.notifyDataSetChanged();

            }
        });
    }

    void setYotei(){
        List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items) {
            nowDate();
            String nowDate = String.valueOf(nowYear) + "/" + String.valueOf(nowMonth) + "/" + String.valueOf(nowDay);
            if (i.date.equals(nowDate)){
                TaskCard mTaskCard;
                mTaskCard = new TaskCard(i.subject,i.naiyou,i.start_page,i.finish_page);
                taskCardList.add(mTaskCard);

                Log.d("taskdate",""+i.date);
            }
        }

        mTaskAdapter = new TaskAdapter(this, R.layout.display_card,taskCardList);
        mlistView.setAdapter(mTaskAdapter);


    }


}
