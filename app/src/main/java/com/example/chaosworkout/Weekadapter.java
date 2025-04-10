package com.example.chaosworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Weekadapter extends BaseAdapter {

    Context context;
    String weekname [];
    int weekchall [];
    int weekwork [];
    int weekpoint [];
    int exeimage [];
    String weekrank [];
    LayoutInflater inflater;

    public Weekadapter(Context applicationContext, String[] weekname , int[] weekchall, int[] weekwork, int[] weekpoint, int[] exeimage
            , String[] weekrank){
        this.context=context;
        this.weekname=weekname;             //hét neve a sorszámmal
        this.weekchall=weekchall;           //heti kihivás pont
        this.weekwork=weekwork;             //heti edzés pont
        this.weekpoint=weekpoint;           //teljes pont
        this.exeimage=exeimage;
        this.weekrank=weekrank;             //pontszám alapján minősítés
        inflater=(LayoutInflater.from(applicationContext)) ;
    }

    @Override
    public int getCount(){
        return weekname.length;
    }
    @Override
    public Object getItem(int i){
        return null;
    }
    @Override
    public long getItemId(int i){
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup){
        view=inflater.inflate(R.layout.list_weeks,null);
        TextView wnametext=(TextView)  view.findViewById(R.id.weektotal);
        TextView wchalltext=(TextView)  view.findViewById(R.id.weekchallange);
        TextView wworktext=(TextView)  view.findViewById(R.id.weektrain);
        ImageView wimage=(ImageView)  view.findViewById(R.id.weekimageView);
        wnametext.setText(weekname[i]+"\n" +weekpoint[i]+" pt - "+weekrank[i]);
        wchalltext.setText(weekchall[i]+" challange point");
        wworktext.setText(weekwork[i]+" workout point");
        wimage.setImageResource(exeimage[i]);
        return view;
    }



}
