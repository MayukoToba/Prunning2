package com.example.owner.prunning;

/**
 * Created by owner on 2016/08/08.
 */
public class TaskCard {
    public String subject;
    public String naiyou;
    public int start_page = 0;
    public int finish_page = 0;

    public TaskCard(String subject,String naiyou,int start_page,int finish_page){
        this.subject = subject;
        this.naiyou = naiyou;
        this.start_page = start_page;
        this.finish_page = finish_page;
    }

}
