package com.example.owner.prunning;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EnterActivity extends AppCompatActivity {

    private ArrayList<String> mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    EditText subject_edit, naiyou_edit, page_first_edit, page_second_edit;

    int yotei_year,yotei_monthOfYear,yotei_dayOfMonth;
    TextView enter_ok_textView;

    YoteiDB mYoteiDB;

    DatePickerDialog datePickerDialog;


    DatePickerDialog.OnDateSetListener DateSetListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(android.widget.DatePicker datePicker, int getYear,
                              int getMonthOfYear, int getDayOfMonth) {

            yotei_year = getYear;
            yotei_monthOfYear = getMonthOfYear;
            yotei_dayOfMonth = getDayOfMonth;
            //ログ出力
            Log.d("DatePicker", "year:" + yotei_year + " monthOfYear:" + yotei_monthOfYear
                    + " dayOfMonth:" + yotei_dayOfMonth);

        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPlanetTitles = new ArrayList<String>();

        mDrawerList = (ListView)findViewById(R.id.listView);

        List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items) {
            String subject;
            subject= i.subject;
            if(!mPlanetTitles.contains(i.subject)){
                mPlanetTitles.add(subject);
            }

        }

        SubjectAdapter arrayAdapter= new SubjectAdapter (this, R.layout.card,mPlanetTitles);
        mDrawerList.setAdapter(arrayAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?>parent,View v,int position,long id){
                String subject;
                subject = mPlanetTitles.get(position);
                Intent intent =new Intent (getApplicationContext(),NaiyouListActivity.class);
                intent.putExtra("科目",subject);
                startActivity(intent);

                //mTaskAdapter.notifyDataSetChanged();

            }
        });


        page_first_edit = (EditText) findViewById(R.id.page_first_edit);
        page_second_edit = (EditText) findViewById(R.id.page_second_edit);
        subject_edit = (EditText) findViewById(R.id.subject_edit);
        naiyou_edit = (EditText) findViewById(R.id.naiyou_edit);

        enter_ok_textView = (TextView)findViewById(R.id.enter_ok_textView);


        mYoteiDB = new YoteiDB();



        Toolbar mToolbar = (Toolbar) findViewById(R.id.tool_bar);
        mToolbar.setTitle("入力");

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
        mDrawerToggle.syncState();

    }

    public void calender(View v) {

        //日付初期値設定
        Calendar calendar = Calendar.getInstance();
        yotei_year = calendar.get(Calendar.YEAR); // 年
        yotei_monthOfYear = calendar.get(Calendar.MONTH); // 月
//        monthOfYear += 1;
        yotei_dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH); // 日
        Log.d("a","" + yotei_monthOfYear);

        // 日付設定ダイアログの作成・リスナの登録
        datePickerDialog = new DatePickerDialog(this, DateSetListener, yotei_year, yotei_monthOfYear, yotei_dayOfMonth);

        datePickerDialog.show();

    }

    public void setting(View v) {
        Intent intent = new Intent(this, SetteiActivity.class);
        startActivity(intent);
    }

    public void ok_enter(View v) {
        if (page_first_edit.getText().toString().equals("") ||
                page_second_edit.getText().toString().equals("") ||
                subject_edit.getText().toString().equals("") ||
                naiyou_edit.getText().toString().equals("") ) {


            enter_ok_textView.setText("入力されていない箇所があります。");

        } else {
            saveYotei();

            enter_ok_textView.setText(null);
            page_first_edit.setText("");
            page_second_edit.setText("");
            subject_edit.setText("");
            naiyou_edit.setText("");

            Intent intent =new Intent (getApplicationContext(),DisplayActivity.class);
            startActivity(intent);


        }

    }

    void saveYotei(){
        mYoteiDB.start_page = page_first_edit.getText().toString();
        mYoteiDB.finish_page = page_second_edit.getText().toString();
        mYoteiDB.subject = subject_edit.getText().toString();
        mYoteiDB.naiyou =naiyou_edit.getText().toString() ;
        String date = String.valueOf(yotei_year)+"/"+String.valueOf(yotei_monthOfYear+1)+"/"+String.valueOf(yotei_dayOfMonth);
        mYoteiDB.date =date;
        mYoteiDB.save();
    }
}



