package com.example.chaosworkout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

public class Exeadapter extends BaseAdapter {

    Context context;
    String exeid [];
    String exename [];
    String exedisc [];
    String exevariant [];
    int exebasept [];
    String exediff[];
    int exeimage [];
    LayoutInflater inflater;

    public Exeadapter(Context applicationContext, String[] exeid , String[] exename , String[] exedisc, String[] exevariant,
                      int[] exebasept, String[] exediff, int[] exeimage){
        this.context=context;
        this.exeid=exeid;
        this.exename=exename;
        this.exedisc=exedisc;           //leírás
        this.exevariant=exevariant;           //variációk
        this.exebasept=exebasept;   //alappont
        this.exediff=exediff;           //nehézség
        this.exeimage=exeimage;
        inflater=(LayoutInflater.from(applicationContext)) ;
    }

    @Override
    public int getCount(){
        return exename.length;
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
        view=inflater.inflate(R.layout.list_exercises,null);
        TextView enametext=(TextView)  view.findViewById(R.id.movename);
        TextView edisctext=(TextView)  view.findViewById(R.id.movedisc);
        TextView ecounttext=(TextView)  view.findViewById(R.id.movecount);
        ImageView eimage=(ImageView)  view.findViewById(R.id.moveimageView);
        enametext.setText("#"+exeid[i]+" "+exename[i]);
        edisctext.setText(exedisc[i]+"\n"+exevariant[i]);
        ecounttext.setText("Base point: "+exebasept[i]+" Difficulty: "+exediff[i]);
        eimage.setImageResource(exeimage[i]);
        return view;
    }
}
