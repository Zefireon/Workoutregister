package com.example.chaosworkout;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;


public class ProgressFragment extends Fragment {

    private List<Exercise> progressList = new ArrayList<>();
    ListView progresslist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pview = inflater.inflate(R.layout.fragment_progress, container, false);

        ImageButton infos = pview.findViewById(R.id.bt_infoweek);

        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder weekbuild = new AlertDialog.Builder(getActivity());
                weekbuild.setMessage("Final rank depend on avarage of workout and challange points 50%-50% " +"\n"+
                                "0-99 pt - WATER LORD" +"\n"+
                                "100-199 pt - AIR RIDER" +"\n"+
                                "200-299 pt - FIRE FIGHTER" +"\n"+
                                "300-399 pt - EARTH WARRIOR" +"\n"+
                                "400-499 pt - NATURE DRUID" +"\n"+
                                "500-599 pt - MIND BLASTER" +"\n"+
                                "600-699 pt - SPIRIT SHAMAN" +"\n"+
                                "700-799 pt - BODY GUARDIAN" +"\n"+
                                "800-849 pt - DARK KNIGHT" +"\n"+
                                "850-899 pt - LIGHT ANGEL" +"\n"+
                                "900-949 pt - ASTRAL HERO" +"\n"+
                                "950-999 pt - COSMIC DRAGON" +"\n"+
                        "1000 pt - CHAOS CHAMPION");
                AlertDialog alertDialog = weekbuild.create();
                alertDialog.show();
            }
        });


        int chestpt = 0;
        int tricepspt = 0;
        int shoulderpt = 0;
        int bicepspt = 0;
        int backpt = 0;
        int abspt = 0;
        int legspt = 0;
        int kardiopt = 0;

        int pullpt = 0;
        int pushpt = 0;
        int burnpt = 0;
        int runbikept = 0;
        int burpeesquatpt= 0;
        int chpullpt = 0;
        int chpushpt = 0;
        int chburnpt = 0;

        int acpushuppt = 0;
        int acdippt = 0;
        int acpikept = 0;
        int acchinpt = 0;
        int acpulluppt = 0;
        int acwidepulluppt = 0;
        int acsquatpt = 0;
        int actoespt = 0;
        int acburpeept = 0;
        int acbikept = 0;
        int acrunpt = 0;

        int weekhelp =0;
        int yearhelp =0;
        String rankhelp ="";
        int ranknum=0;

        String[] pweeks = {"Giant","Valkyr","Celestial","Naga","Dryad","Thunderbird","Harpie","Mummy","Lamashu","Minotaur",
                "Siren","Gryphon","Wyvern","Dragon","Pixie","Sphinx","Kappa","Mage","Cerberus","Typhon",
                "Titan","Rakshara","Centaur","Nymph","Treant","Devil","Witch","Vampire","Genie","Gorgon",
                "Chimera","Satyr","Hydra","Daemon","Pegasus","Basilisk","Werewolf","Unicorn","Hippogriff","Fenrir",
                "Zealot","Fairy","Kraken","Manticore","Behemoth","Ogre","Lich","Phoenix","Golem","Cyclops",
                "Seraph","Kirin","Yeti"};
        String[] ranks = {"Water Lord","Air Rider","Fire Fighter","Earth Warrior", "Nature Druid",
                "Mind Blaster", "Spirit Shaman", "Body Guardian",
                "Dark Knight","Light Angel","Astral Hero","Cosmic Dragon","Chaos Champion"};

        try {
            readallweek();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Collections.reverse(progressList);

        // hetek számának begyűjtése
        ArrayList<String> Wweekhelp = new ArrayList<>();
        for (int i = 0; i < progressList.size(); i++) {
            if (!Wweekhelp.contains(String.valueOf(progressList.get(i).getEyear())+String.valueOf(progressList.get(i).getEweek()))) {
                Wweekhelp.add(String.valueOf(progressList.get(i).getEyear())+String.valueOf(progressList.get(i).getEweek()));
            }
        }

        progresslist = (ListView) pview.findViewById(R.id.list_progress);
        String Wname[] = new String[Wweekhelp.size()];
        int Wchalpt[] = new int[Wweekhelp.size()];
        int Wworkpt[] = new int[Wweekhelp.size()];
        int Wweekpt[] = new int[Wweekhelp.size()];
        int Wimage[] = new int[Wweekhelp.size()];
        String Wranking[] = new String[Wweekhelp.size()];


        for (int i = 0; i < Wweekhelp.size(); i++) {
            //év és hét egyezéshez segédlet
            yearhelp = Integer.parseInt(Wweekhelp.get(i).substring(0,4));
            weekhelp = Integer.parseInt(Wweekhelp.get(i).substring(4));
            Log.w("MyActivity", "Just created" +yearhelp+" "+weekhelp);
            for (Exercise e : progressList){
                if (e.getEyear()==yearhelp & e.getEweek()==weekhelp){
                    if(e.getEmuscle().equals("chest") & e.getEbase()!= 1){
                        chestpt = chestpt + (e.getErep()*e.getEbase()*(Math.round((e.getEdiff()+100)/100))+(e.getEduration()*3));
                    }
                    if(e.getEmuscle().equals("triceps")& e.getEbase()!= 1){
                        tricepspt = tricepspt + (e.getErep()*e.getEbase()*(Math.round((e.getEdiff()+100)/100))+(e.getEduration()*3));
                    }
                    if(e.getEmuscle().equals("shoulder")& e.getEbase()!= 1){
                        shoulderpt = shoulderpt + (e.getErep()*e.getEbase()*(Math.round((e.getEdiff()+100)/100))+(e.getEduration()*3));
                    }
                    if(e.getEmuscle().equals("biceps")& e.getEbase()!= 1){
                        bicepspt = bicepspt + (e.getErep()*e.getEbase()*(Math.round((e.getEdiff()+100)/100))+(e.getEduration()*3));
                    }
                    if(e.getEmuscle().equals("back")& e.getEbase()!= 1){
                        backpt = backpt + (e.getErep()*e.getEbase()*(Math.round((e.getEdiff()+100)/100))+(e.getEduration()*3));
                    }
                    if(e.getEmuscle().equals("abs")& e.getEbase()!= 1){
                        abspt = abspt + (e.getErep()*e.getEbase()*(Math.round((e.getEdiff()+100)/100))+(e.getEduration()*3));
                    }
                    if(e.getEmuscle().equals("legs")& e.getEbase()!= 1){
                        legspt = legspt + (e.getErep()*e.getEbase()*(Math.round((e.getEdiff()+100)/100))+(e.getEduration()*3));
                    }
                    if(e.getEmuscle().equals("kardio")& e.getEbase()!= 1){
                        kardiopt = kardiopt + (e.getEbase()*(e.getEduration()*6));
                    }


                    if(e.getEmuscle().equals("chest") & e.getEbase()== 1){
                        chestpt = chestpt + (e.getEduration()*15);
                    }
                    if(e.getEmuscle().equals("triceps")& e.getEbase()== 1){
                        tricepspt = tricepspt + (e.getEduration()*15);
                    }
                    if(e.getEmuscle().equals("shoulder")& e.getEbase()== 1){
                        shoulderpt = shoulderpt + (e.getEduration()*15);
                    }
                    if(e.getEmuscle().equals("biceps")& e.getEbase()== 1){
                        bicepspt = bicepspt + (e.getEduration()*15);
                    }
                    if(e.getEmuscle().equals("back")& e.getEbase()== 1){
                        backpt = backpt + (e.getEduration()*15);
                    }
                    if(e.getEmuscle().equals("abs")& e.getEbase()== 1){
                        abspt = abspt +  (e.getEduration()*15);
                    }
                    if(e.getEmuscle().equals("legs")& e.getEbase()== 1){
                        legspt = legspt + (e.getEduration()*15);
                    }
                    if(e.getEmuscle().equals("kardio")& e.getEbase()== 1){
                        kardiopt = kardiopt + (e.getEduration()*15);
                    }


                    if(e.getEname().equals("Push-up challange") & acpushuppt<e.getErep()){
                        acpushuppt = Math.round((e.getErep() * 1000)/250);
                    }
                    if(e.getEname().equals("Triceps dips challange") & acdippt<e.getErep()){
                        acdippt =  Math.round((e.getErep() * 1000)/200);
                    }
                    if(e.getEname().equals("Bench pike push-up challange") & acpikept<e.getErep()){
                        acpikept =  Math.round((e.getErep() * 1000)/150);
                    }
                    if(e.getEname().equals("Chin-up challange") & acchinpt<e.getErep()){
                        acchinpt =  Math.round((e.getErep() * 1000)/150);
                    }
                    if(e.getEname().equals("Pull-up challange") & acpulluppt<e.getErep()){
                        acpulluppt =  Math.round((e.getErep() * 1000)/150);
                    }
                    if(e.getEname().equals("Wide pull-up challange") & acwidepulluppt<e.getErep()){
                        acwidepulluppt =  Math.round((e.getErep() * 1000)/1000);
                    }
                    if(e.getEname().equals("Squat challange") & acsquatpt<e.getErep()){
                        acsquatpt =  Math.round((e.getErep() * 1000)/250);
                    }
                    if(e.getEname().equals("Toes to bar challange") & actoespt<e.getErep()){
                        actoespt =  Math.round((e.getErep() * 1000)/200);
                    }
                    if(e.getEname().equals("Burpee challange") & acburpeept<e.getErep()){
                        acburpeept =  Math.round((e.getErep() * 1000)/200);
                    }
                    if(e.getEname().equals("Stationary bike challange") & acbikept<e.getErep()){
                        acbikept =  Math.round((e.getErep() * 1000)/7000);
                    }
                    if(e.getEname().equals("Running challange") & acrunpt<e.getErep()){
                        acrunpt =  Math.round((e.getErep() * 1000)/3000);
                    }

                }
            }
            if (chestpt>1000){chestpt=1000;}
            if (tricepspt>1000){tricepspt=1000;}
            if (shoulderpt>1000){shoulderpt=1000;}
            if (backpt>1500){backpt=1500;}
            if (bicepspt>1500){bicepspt=1500;}

            if (acpushuppt>1000){acpushuppt=1000;}
            if (acdippt>1000){acdippt=1000;}
            if (acpikept>1000){acpikept=1000;}
            if (acchinpt>1000){acchinpt=1000;}
            if (acpulluppt>1000){acpulluppt=1000;}
            if (acwidepulluppt>1000){acwidepulluppt=1000;}
            if (acsquatpt>1000){acsquatpt=1000;}
            if (actoespt>1000){actoespt=1000;}
            if (acburpeept>1000){acburpeept=1000;}
            if (acbikept>1000){acbikept=1000;}
            if (acrunpt>1000){acrunpt=1000;}

            pushpt = Math.round((chestpt+tricepspt+shoulderpt)/3);
            pullpt = Math.round((bicepspt+backpt)/3);
            burnpt = Math.round((legspt+kardiopt+abspt)/3);
            if (burnpt>1000){burnpt=1000;}

            if (acpushuppt+acdippt > acdippt+acpikept || acpushuppt+acdippt > acpushuppt+acpikept){
                chpushpt= Math.round((acpushuppt+acdippt)/2);}
            else if (acpushuppt+acpikept > acdippt+acpikept || acpushuppt+acpikept > acpushuppt+acdippt){
                chpushpt= Math.round((acpushuppt+acpikept)/2);}
            else {chpushpt= Math.round((acdippt+acpikept)/2);}

            if (acchinpt+acpulluppt > acpulluppt+acwidepulluppt || acchinpt+acpulluppt > acchinpt+acwidepulluppt){
                chpullpt= Math.round((acchinpt+acpulluppt)/2);}
            else if (acchinpt+acwidepulluppt > acpulluppt+acwidepulluppt || acchinpt+acwidepulluppt > acchinpt+acpulluppt){
                chpullpt= Math.round((acchinpt+acwidepulluppt)/2);}
            else {chpullpt= Math.round((acpulluppt+acwidepulluppt)/2);}

            if (acrunpt>acbikept){runbikept= acrunpt;}
            else {runbikept= acbikept;}

            if (acsquatpt>acburpeept){burpeesquatpt= acsquatpt;}
            else {burpeesquatpt= acburpeept;}

            if (runbikept+burpeesquatpt > burpeesquatpt+actoespt || runbikept+burpeesquatpt > runbikept+actoespt){
                chburnpt= Math.round((runbikept+burpeesquatpt)/2);}
            else if (runbikept+actoespt > burpeesquatpt+actoespt || runbikept+actoespt > runbikept+burpeesquatpt){
                chburnpt= Math.round((runbikept+actoespt)/2);}
            else {chburnpt= Math.round((burpeesquatpt+actoespt)/2);}

            int challangefinal = Math.round((chpushpt+chpullpt+chburnpt)/3);
            int workfinal=Math.round((pushpt+pullpt+burnpt)/3);
            int overallfinal= Math.round((challangefinal+workfinal)/2);
            if(overallfinal<100){rankhelp = "lord"; ranknum=0;}
            else if (overallfinal>=100 & overallfinal<200){rankhelp = "rider";ranknum=1;}
            else if (overallfinal>=200 & overallfinal<300){rankhelp = "fighter";ranknum=2;}
            else if (overallfinal>=300 & overallfinal<400){rankhelp = "warrior";ranknum=3;}
            else if (overallfinal>=400 & overallfinal<500){rankhelp = "druid";ranknum=4;}
            else if (overallfinal>=500 & overallfinal<600){rankhelp = "blaster";ranknum=5;}
            else if (overallfinal>=600 & overallfinal<700){rankhelp = "shaman";ranknum=6;}
            else if (overallfinal>=700 & overallfinal<800){rankhelp = "guardian";ranknum=7;}
            else if (overallfinal>=800 & overallfinal<850){rankhelp = "knight";ranknum=8;}
            else if (overallfinal>=850 & overallfinal<900){rankhelp = "angel";ranknum=9;}
            else if (overallfinal>=900 & overallfinal<950){rankhelp = "hero";ranknum=10;}
            else if (overallfinal>=950 & overallfinal<1000){rankhelp = "dragon";ranknum=11;}
            else {rankhelp = "champion";ranknum=12;}


            Wname[i] = "#"+(Wweekhelp.size()-i)+" Week of " +pweeks[weekhelp];
            Wchalpt[i] = challangefinal;
            Wworkpt[i] = workfinal;
            Wweekpt[i] = overallfinal;
            Wimage[i] = getResources().getIdentifier(rankhelp, "drawable", "com.example.chaosworkout");
            Wranking[i] = ranks[ranknum];

            chestpt = 0;
            tricepspt = 0;
            shoulderpt = 0;
            bicepspt = 0;
            backpt = 0;
            abspt = 0;
            legspt = 0;
            kardiopt = 0;

            pullpt = 0;
            pushpt = 0;
            burnpt = 0;
            runbikept = 0;
            burpeesquatpt= 0;
            chpullpt = 0;
            chpushpt = 0;
            chburnpt = 0;

            acpushuppt = 0;
            acdippt = 0;
            acpikept = 0;
            acchinpt = 0;
            acpulluppt = 0;
            acwidepulluppt = 0;
            acsquatpt = 0;
            actoespt = 0;
            acburpeept = 0;
            acbikept = 0;
            acrunpt = 0;


        }


        Weekadapter customAdapter = new Weekadapter(getContext(), Wname, Wchalpt, Wworkpt,
                Wweekpt, Wimage, Wranking);

        progresslist.setAdapter(customAdapter);


        return pview;
    }

    private void readallweek() throws FileNotFoundException {
        File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String fnameread = "saveworkout.csv";
        File fileread= new File(dir, fnameread);

        if (fileread.exists()){
            InputStream steamactual = new FileInputStream(fileread);
            BufferedReader readera = new BufferedReader(
                    new InputStreamReader(steamactual, Charset.forName("ISO-8859-2"))
            );
            String line=" ";
            try {
                //fejléc kihagyása
                readera.readLine();
                while ((line=readera.readLine()) !=null){
                    Log.d("MyActivity", "Line" +line);
                    //Split ;
                    String[] token = line.split(";");
                    //Read
                    Exercise actualraw= new Exercise();
                    actualraw.setEid(token[0]);
                    actualraw.setEname(token[1]);
                    actualraw.setEmuscle(token[2]);
                    actualraw.setEdiff(Integer.parseInt(token[3]));
                    actualraw.setEbase(Integer.parseInt(token[4]));
                    actualraw.setErep(Integer.parseInt(token[5]));
                    actualraw.setEduration(Integer.parseInt(token[6]));
                    actualraw.setEyear(Integer.parseInt(token[7]));
                    actualraw.setEmonth(Integer.parseInt(token[8]));
                    actualraw.setEweek(Integer.parseInt(token[9]));
                    actualraw.setEday(Integer.parseInt(token[10]));
                    progressList.add(actualraw);
                    Log.d("MyActivity", "Just created" +actualraw);
                }
                readera.close();
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line" + line, e);
                e.printStackTrace();
            }
        }
    }


}