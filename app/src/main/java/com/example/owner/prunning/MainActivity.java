package com.example.owner.prunning;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.activeandroid.query.Select;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPlanetTitles = new ArrayList<String>();
        mDrawerList = (ListView) findViewById(R.id.listView);

        List<YoteiDB> items = new Select().from(YoteiDB.class).execute();
        for (YoteiDB i : items) {
            String subject;
            subject = i.subject;
            if (!mPlanetTitles.contains(i.subject)) {
                mPlanetTitles.add(subject);
            }

        }

        SubjectAdapter arrayAdapter = new SubjectAdapter(this, R.layout.card, mPlanetTitles);
        mDrawerList.setAdapter(arrayAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                String subject;
                subject = mPlanetTitles.get(position);
                Intent intent = new Intent(getApplicationContext(), NaiyouListActivity.class);
                intent.putExtra("科目", subject);
                startActivity(intent);

                //mTaskAdapter.notifyDataSetChanged();

            }
        });


        // Set the adapter for the list view


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

//        List<YoteiDB> items1 = new Select().from(YoteiDB.class).execute();
//        for (YoteiDB i : items1) {
//            i.delete();
//        }


    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    public void setting(View v) {
        Intent intent = new Intent(this, SetteiActivity.class);
        startActivity(intent);
    }

    public void nyuuryoku(View v) {
        Intent intent = new Intent(this, EnterActivity.class);
        startActivity(intent);

    }

    public void hyouzi(View v) {
        Intent intent = new Intent(this, DisplayActivity.class);
        startActivity(intent);
    }


}

