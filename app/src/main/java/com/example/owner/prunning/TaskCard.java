package com.example.owner.prunning;

import android.content.res.ColorStateList;

/**
 * Created by owner on 2016/08/08.
 */
public class TaskCard {
    public String subject;
    public String naiyou;
    public String start_page;
    public String finish_page;
    public String mColor;

    public TaskCard(String subject,String naiyou,String start_page,String finish_page,String mColor){
        this.subject = subject;
        this.naiyou = naiyou;
        this.start_page = start_page;
        this.finish_page = finish_page;
        this.mColor = mColor;
    }

}
