package com.example.owner.prunning;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.activeandroid.annotation.Column;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by owner on 2016/08/08.
 */
public class TaskAdapter extends ArrayAdapter<TaskCard> {
    List<TaskCard> mTaskCard;

    public TaskAdapter(Context context,int layoutResourceId,List<TaskCard> objects){
        super(context,layoutResourceId,objects);

        mTaskCard = objects;
    }

    @Override
    public int getCount(){
        return mTaskCard.size();
    }

    @Override
    public TaskCard getItem(int position){
        return mTaskCard.get(position);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        final ViewHolder viewHolder;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.display_card,null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final TaskCard item = getItem(position);

        if (item != null){
            //set data
            viewHolder.subjectTextView.setText(item.subject);
            viewHolder.subjectTextView.setTextColor(Color.parseColor(item.mColor));
            viewHolder.naiyouTextView.setText(item.naiyou);
            viewHolder.naiyouTextView.setTextColor(Color.parseColor(item.mColor));
            viewHolder.startpageTextView.setText(item.start_page);
            viewHolder.startpageTextView.setTextColor(Color.parseColor(item.mColor));
            viewHolder.finishpageTextView.setText(item.finish_page);
            viewHolder.finishpageTextView.setTextColor(Color.parseColor(item.mColor));

        }



        return convertView;
    }

    private class ViewHolder{
        TextView subjectTextView,naiyouTextView,
                startpageTextView,finishpageTextView;

        public ViewHolder(View view){
            //get instance
            subjectTextView = (TextView) view.findViewById(R.id.subject_textView);
            naiyouTextView = (TextView)view.findViewById(R.id.naiyou_textView);
            startpageTextView = (TextView)view.findViewById(R.id.startpage_textView);
            finishpageTextView = (TextView)view.findViewById(R.id.finishpage_textView);

        }

    }

}
