package com.example.owner.prunning;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

/**
 * Created by owner on 2016/08/08.
 */
public class TaskAdapter extends ArrayAdapter<TaskCard> {
    List<TaskCard> mTaskCard;

    public TaskAdapter(Context context,int layoutResourceId,List<TaskCard>objects){
        super(context,layoutResourceId,objects);

        mTaskCard = objects;
    }
}
