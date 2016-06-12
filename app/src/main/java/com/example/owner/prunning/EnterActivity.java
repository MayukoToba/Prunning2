package com.example.owner.prunning;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class EnterActivity extends AppCompatActivity {

    private ArrayList<String> mPlanetTitles;
    private ListView mDrawerList;
    private DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;

    EditText subject_edit,naiyou_edit,page_first_edit,page_second_edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mPlanetTitles = new ArrayList<String>();

        mPlanetTitles.add("数学");
        mDrawerList = (ListView) findViewById(R.id.listView);

        SubjectAdapter subjectAdapter = new SubjectAdapter(this,
                R.layout.card, mPlanetTitles);

        // Set the adapter for the list view
        mDrawerList.setAdapter(subjectAdapter);


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
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();

    }

    public void setting(View v){
        Intent intent = new Intent(this,SetteiActivity.class);
        startActivity(intent);
    }

    public void ok_enter (View v){
       //はじめのページ
        page_first_edit = (EditText)findViewById(R.id.page_first_edit);


        Editable get_page_first = page_first_edit.getText();
        int int_page_first = Integer.parseInt(get_page_first.toString());

        //終わりのページ
        page_second_edit= (EditText)findViewById(R.id.page_second_edit);


        Editable  get_page_second= page_second_edit.getText();
        int int_page_second = Integer.parseInt(get_page_second.toString());

        //教科名
        subject_edit = (EditText)findViewById(R.id.subject_edit);

        Editable get_subject = subject_edit.getText();

        //内容名
        naiyou_edit = (EditText)findViewById(R.id.naiyou_edit);

        Editable get_naiyou =subject_edit.getText();



    }
}


