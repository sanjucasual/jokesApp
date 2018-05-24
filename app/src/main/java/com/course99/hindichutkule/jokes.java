package com.course99.hindichutkule;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Rio on 9/10/2016.
 */
public class jokes extends BaseAdapter {

    Context context;
    String[] numbers;
    LayoutInflater inflater;

    public jokes(Context context, String[] numbers                     ) {
        this.context = context;
//        this.rank = rank;
        this.numbers= numbers;

    }
    @Override
    public int getCount() {
        return numbers.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        TextView textView;
        TextView txtcountry;


        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.my_layout,null);

        // Locate the TextViews in listview_item.xml
       // textView=(TextView)itemView.findViewById(R.id.textviewone);
        txtcountry = (TextView) itemView.findViewById(R.id.textview2);


        // set the fonts for textviews
        Typeface tf = Typeface.createFromAsset(context.getAssets(),"goodfoot.ttf");
        txtcountry.setTypeface(tf);
       // textView.setTypeface(tf);


        // Capture position and set to the TextViews
        txtcountry.setText(numbers[position]);


        return itemView;

    }
}
