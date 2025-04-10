package com.example.chaosworkout;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;
import java.util.Locale;


public class ActualFragment extends Fragment {

    private List<Exercise> actualList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View bview = inflater.inflate(R.layout.fragment_actual, container, false);

        ImageButton infoa = bview.findViewById(R.id.bt_infoactual);

        infoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder actualbuild = new AlertDialog.Builder(getActivity());
                actualbuild.setMessage("Workout point get from the avarage of push/pull/burn" +"\n"+
                        "Push points:" +"\n"+
                        "chest-tripeps-shoulder / 33%-33%-33%" +"\n"+
                        "Pull points:" +"\n"+
                        "biceps-back / 50%-50%" +"\n"+
                        "Burn points:" +"\n"+
                        "legs-abs-cardio / any %" +"\n"+
                        "" +"\n"+
                        "Challange point get from 6 best avarage (0 also count if not enough)" +"\n"+
                        "best 2 from:" +"\n"+
                        "push-up, dips, pike push-up" +"\n"+
                        "best 2 from:" +"\n"+
                        "chin-up, pull-up, wide pull-up" +"\n"+
                        "best 2 from:" +"\n"+
                        "squat, burpee, toes to bar, bike, run:");
                AlertDialog alertDialog = actualbuild.create();
                alertDialog.show();
            }
        });


        String[] weeks = {"Shaman","Valkyr","Druid","Naga","Dryad","Thunderbird","Harpie","Mummy","Lamashu","Minotaur",
                "Siren","Gryphon","Wyvern","Dragon","Pixie","Sphinx","Kappa","Mage","Cerberus","Typhon",
                "Titan","Rakshara","Centaur","Nymph","Treant","Devil","Witch","Vampire","Genie","Gorgon",
                "Chimera","Satyr","Hydra","Daemon","Pegasus","Basilisk","Werewolf","Unicorn","Hippogriff","Fenrir",
                "Zealot","Fairy","Kraken","Manticore","Behemoth","Ogre","Lich","Phoenix","Golem","Cyclops",
                "Angel","Kirin","Yeti"};

        TextView atitle = bview.findViewById(R.id.tb_actualtitle);

        TextView overalpush = bview.findViewById(R.id.tb_pushoveral);
        TextView overalpull = bview.findViewById(R.id.tb_pulloveral);
        TextView overalburn = bview.findViewById(R.id.tb_burnoveral);
        TextView achest = bview.findViewById(R.id.tb_mchest);
        TextView atriceps = bview.findViewById(R.id.tb_mtricep);
        TextView ashoulder = bview.findViewById(R.id.tb_mshoulder);
        TextView abiceps = bview.findViewById(R.id.tb_mbicep);
        TextView aback = bview.findViewById(R.id.tb_mback);
        TextView alegs = bview.findViewById(R.id.tb_mleg);
        TextView acore = bview.findViewById(R.id.tb_mcore);
        TextView acardio = bview.findViewById(R.id.tb_mcardio);

        TextView txtacpushup = bview.findViewById(R.id.tb_challpush);
        TextView txtacdip = bview.findViewById(R.id.tb_challdip);
        TextView txtacpike = bview.findViewById(R.id.tb_challpike);
        TextView txtacchin = bview.findViewById(R.id.tb_challchin);
        TextView txtacpullup = bview.findViewById(R.id.tb_challpullup);
        TextView txtacwidepullup = bview.findViewById(R.id.tb_challwidepull);
        TextView txtacsquat = bview.findViewById(R.id.tb_challsquat);
        TextView txtactoes = bview.findViewById(R.id.tb_challtoe);
        TextView txtacburpee = bview.findViewById(R.id.tb_challburp);
        TextView txtacbike = bview.findViewById(R.id.tb_challcardiobike);
        TextView txtacrun = bview.findViewById(R.id.tb_challkardiorun);

        int chestpt = 0;
        int tricepspt = 0;
        int shoulderpt = 0;
        int bicepspt = 0;
        int backpt = 0;
        int abspt = 0;
        int legspt = 0;
        int kardiopt = 0;

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

        try {
            readactualweek();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Calendar calend = Calendar.getInstance();
        calend.setFirstDayOfWeek(Calendar.MONDAY);
        atitle.setText("Week of "+weeks[calend.get(Calendar.WEEK_OF_YEAR)]);


        for (Exercise e : actualList){

            if (e.getEweek() == calend.get(Calendar.WEEK_OF_YEAR) & e.getEyear() == calend.get(Calendar.YEAR)){

                if(e.getEmuscle().equals("chest") & e.getEbase()!= 1){
                    chestpt = chestpt + (e.getErep()*e.getEbase()*((e.getEdiff()+100)/100)+(e.getEduration()*3));
                }
                if(e.getEmuscle().equals("triceps")& e.getEbase()!= 1){
                    tricepspt = tricepspt + (e.getErep()*e.getEbase()*((e.getEdiff()+100)/100)+(e.getEduration()*3));
                }
                if(e.getEmuscle().equals("shoulder")& e.getEbase()!= 1){
                    shoulderpt = shoulderpt + (e.getErep()*e.getEbase()*((e.getEdiff()+100)/100)+(e.getEduration()*3));
                }
                if(e.getEmuscle().equals("biceps")& e.getEbase()!= 1){
                    bicepspt = bicepspt + (e.getErep()*e.getEbase()*((e.getEdiff()+100)/100)+(e.getEduration()*3));
                }
                if(e.getEmuscle().equals("back")& e.getEbase()!= 1){
                    backpt = backpt + (e.getErep()*e.getEbase()*((e.getEdiff()+100)/100)+(e.getEduration()*3));
                }
                if(e.getEmuscle().equals("abs")& e.getEbase()!= 1){
                    abspt = abspt + (e.getErep()*e.getEbase()*((e.getEdiff()+100)/100)+(e.getEduration()*3));
                }
                if(e.getEmuscle().equals("legs")& e.getEbase()!= 1){
                    legspt = legspt + (e.getErep()*e.getEbase()*((e.getEdiff()+100)/100)+(e.getEduration()*3));
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
                    acwidepulluppt =  Math.round((e.getErep() * 1000)/100);
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
                if(e.getEname().equals("Rowing machine") ){
                    backpt = backpt + (e.getEbase()*(e.getEduration()*6));
                }

            }
        }

        overalpush.setText(chestpt+tricepspt+shoulderpt+"/3000 pt");
        achest.setText("Chest: "+ chestpt+" /1000 pt");
        atriceps.setText("Triceps: "+tricepspt+" /1000 pt");
        ashoulder.setText("Shoulder: "+shoulderpt+" /1000 pt");
        overalpull.setText(bicepspt+backpt+"/3000 pt");
        abiceps.setText("Biceps: "+bicepspt+" /1500 pt");
        aback.setText("Back: "+backpt+" /1500 pt");
        overalburn.setText(abspt+legspt+kardiopt+"/3000 pt");
        acore.setText("Abs: "+abspt+" pt");
        alegs.setText("Legs: "+legspt+" pt");
        acardio.setText("Cardio: "+kardiopt+" pt");

        txtacpushup.setText("Push-up:"+"\n"+acpushuppt+" /1000 pt"+"\n"+"Best reps: "+Math.round((acpushuppt * 250)/1000)+"/250");
        txtacdip.setText("Triceps dips:"+"\n"+acdippt+" /1000 pt"+"\n"+"Best reps: "+Math.round((acdippt * 200)/1000)+"/200");
        txtacpike.setText("Bench pike push-up:"+"\n"+acpikept+" /1000 pt"+"\n"+"Best reps: "+Math.round((acpikept * 150)/1000)+"/150");
        txtacchin.setText("Chin-up:"+"\n"+acchinpt+" /1000 pt"+"\n"+"Best reps: "+Math.round((acchinpt * 150)/1000)+"/150");
        txtacpullup.setText("Pull-up:"+"\n"+acpulluppt+" /1000 pt"+"\n"+"Best reps: "+Math.round((acpulluppt * 150)/1000)+"/150");
        txtacwidepullup.setText("Wide pull-up:"+"\n"+acwidepulluppt+" /1000 pt"+"\n"+"Best reps: "+Math.round((acwidepulluppt * 100)/1000)+"/100");
        txtacsquat.setText("Squat challange:"+"\n"+acsquatpt+" /1000 pt"+"\n"+"Best reps: "+Math.round((acsquatpt * 250)/1000)+"/250");
        txtactoes.setText("Toes to bar:"+"\n"+actoespt+" /1000 pt"+"\n"+"Best reps: "+Math.round((actoespt * 200)/1000)+"/200");
        txtacburpee.setText("Burpee:"+"\n"+acburpeept+" /1000 pt"+"\n"+"Best reps: "+Math.round((acburpeept * 200)/1000)+"/200");
        txtacbike.setText("Stationary bike:"+"\n"+acbikept+" /1000 pt"+"\n"+"Best reps: "+Math.round((acbikept * 7000)/1000)+"/7000m");
        txtacrun.setText("Running:"+"\n"+acrunpt+" /1000 pt"+"\n"+"Best reps: "+Math.round((acrunpt * 3000)/1000)+"/3000m");

    return bview;
    }

    private void readactualweek() throws FileNotFoundException {
        actualList.clear();
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
                    actualList.add(actualraw);
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