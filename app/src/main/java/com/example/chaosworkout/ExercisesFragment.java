package com.example.chaosworkout;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;


public class ExercisesFragment extends Fragment {

    private List<Tutorial> exeList = new ArrayList<>();
    private List<Exercise> addList = new ArrayList<>();
    ListView exerciselist;
    private String buttonhelper="chest";
    private String idtype="e";
    private String musclehelp="chest";
    private int endpthelp=0;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View aview = inflater.inflate(R.layout.fragment_exercises, container, false);


        View popupView = LayoutInflater.from(getActivity()).inflate(R.layout.addworkout,null);
        int pwith= ConstraintLayout.LayoutParams.MATCH_PARENT;
        int pheight=ConstraintLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, pwith,pheight, focusable);

        ImageButton infoex = aview.findViewById(R.id.bt_infoexe);

        infoex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder exebuild = new AlertDialog.Builder(getActivity());
                exebuild.setMessage("Record your workouts here: " +"\n"+
                        "1. set the time of the workout" +"\n"+
                        "2. record the number or distance of the exersise" +"\n"+
                        "3. record the weight of barbell" +"\n"+
                        "Get points from time repitition and difficulty" +"\n"+
                        "Cardio and practice execise have fixed points:" +"\n"+
                        "You can fill the repitition or difficulty, but not change the final point" +"\n"+
                        "Tip: Use the muscle buttons to filter");
                AlertDialog alertDialog = exebuild.create();
                alertDialog.show();
            }
        });


        ImageButton ibcha = aview.findViewById(R.id.ibchallange);
        ImageButton ibche = aview.findViewById(R.id.ibchest);
        ImageButton ibsho = aview.findViewById(R.id.ibshoulder);
        ImageButton ibtri = aview.findViewById(R.id.ibtriceps);
        ImageButton ibbic = aview.findViewById(R.id.ibbiceps);
        ImageButton ibbac = aview.findViewById(R.id.ibback);
        ImageButton ibabs = aview.findViewById(R.id.ibabs);
        ImageButton ibleg = aview.findViewById(R.id.iblegs);
        ImageButton ibkar = aview.findViewById(R.id.ibkardio);
        ImageButton iball = aview.findViewById(R.id.iball);

        Button btadd =popupView.findViewById(R.id.addbutton);
        Button btcancel =popupView.findViewById(R.id.cancelbutton);
        TextView addtitle =popupView.findViewById(R.id.addtitle);
        pl.droidsonroids.gif.GifImageView addgif =popupView.findViewById(R.id.addpicture);
        RadioButton tenadd = popupView.findViewById(R.id.radio10);
        RadioButton fifteenadd = popupView.findViewById(R.id.radio15);
        RadioButton thirtynadd = popupView.findViewById(R.id.radio30);
        EditText editrep = popupView.findViewById(R.id.editlreps);
        EditText editdiff = popupView.findViewById(R.id.editldiff);

        readtutorial();

        exerciselist = (ListView) aview.findViewById(R.id.list_exercises);
        String Eid[] = new String[exeList.size()];
        String Ename[] = new String[exeList.size()];
        String Edesc[] = new String[exeList.size()];
        String Evariant[] = new String[exeList.size()];
        int Ept[] = new int[exeList.size()];
        String Ediff[] = new String[exeList.size()];
        int Eimage[] = new int[exeList.size()];
        for (int i = 0; i < exeList.size(); i++) {
            Eid[i] = exeList.get(i).getTid();
            Ename[i] = exeList.get(i).getTname();
            Edesc[i] = exeList.get(i).getTdesc();
            Evariant[i] = exeList.get(i).getTvariant();
            Ept[i] = exeList.get(i).getTbasept();
            Ediff[i] = exeList.get(i).getTdiff();
            Eimage[i] = getResources().getIdentifier(exeList.get(i).getTid(), "drawable", "com.example.chaosworkout");
        }

        Exeadapter customAdapter = new Exeadapter(getContext(), Eid, Ename, Edesc, Evariant,
                Ept, Ediff, Eimage);
        exerciselist.setAdapter(customAdapter);

        exerciselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupWindow.showAsDropDown(view,Gravity.CENTER,0,0);
                editrep.getText().clear();
                editdiff.setText("0");
                if (exeList.get(i).getTvariant().equals("challange")){
                    fifteenadd.setChecked(true);
                    tenadd.setVisibility(View.INVISIBLE);
                    thirtynadd.setVisibility(View.INVISIBLE);
                    if(exeList.get(i).getTname().equals("Stationary bike challange") ||
                            exeList.get(i).getTname().equals("Running challange")){
                        editrep.setHint("meter");
                    }
                    else{
                        editrep.setHint("count");
                    }


                }else {
                    tenadd.setVisibility(View.VISIBLE);
                    thirtynadd.setVisibility(View.VISIBLE);
                }

                addtitle.setText(exeList.get(i).getTname());
                int imagehelp=getResources().getIdentifier(exeList.get(i).getTid(), "drawable", "com.example.chaosworkout");
                addgif.setImageResource(imagehelp);
                musclehelp = exeList.get(i).getTmuscle();
                endpthelp = exeList.get(i).getTbasept();


                if (exeList.get(i).getTvariant().equals("challange")){idtype="c";}
                else if (exeList.get(i).getTvariant().equals("practice")){idtype="p";}
                else {idtype="e";}

            }
        });



        ibcha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="challange";
                readtutorial3();
                addlistexe();
                customAdapter.notifyDataSetChanged();


            }
        });
        ibche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="chest";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        ibtri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="triceps";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        ibsho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="shoulder";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        ibbic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="biceps";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        ibbac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="back";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        ibabs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="abs";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        ibleg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="legs";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        ibkar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonhelper="kardio";
                readtutorial2();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        iball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readtutorial();
                addlistexe();
                customAdapter.notifyDataSetChanged();
            }
        });

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int durationhelp= 30;
                if (tenadd.isChecked()){
                    durationhelp= 10;
                } else if (fifteenadd.isChecked()) {
                    durationhelp= 15;
                } else {
                }

                try {
                    readexe();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }


                if(editrep.getText().toString().isEmpty() || editdiff.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Missing repitions or difficulty!", Toast.LENGTH_LONG).show();
                }
                else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setFirstDayOfWeek(Calendar.MONDAY);

                    String titlehelps = addtitle.getText().toString();
                    String repshelps = editrep.getText().toString();
                    int addreps= Integer.parseInt(repshelps);
                    String diffsshelps = editdiff.getText().toString();
                    int adddiffs= Integer.parseInt(diffsshelps);

                    Exercise storeraw= new Exercise();
                    if (idtype.equals("c")){storeraw.setEid("c"+ String.valueOf(addList.size()));}
                    else if (idtype.equals("p")) {storeraw.setEid("p"+ String.valueOf(addList.size()));}
                    else {storeraw.setEid("e"+ String.valueOf(addList.size()));}
                    storeraw.setEname(titlehelps); //gyakorlat neve a címből
                    storeraw.setEmuscle(musclehelp); // izom a gyakorlat adataiból
                    storeraw.setEdiff(adddiffs); // nehezités pontszáma
                    storeraw.setEbase(endpthelp); // nehézség alapján változtatott alappontszám
                    storeraw.setErep(addreps); // ismétlések
                    storeraw.setEduration(durationhelp); // radiobutton alapján az időtartam átvétele
                    storeraw.setEyear(calendar.get(Calendar.YEAR)); //idő rögzítése szétszedve
                    storeraw.setEmonth(calendar.get(Calendar.MONTH));
                    storeraw.setEweek(calendar.get(Calendar.WEEK_OF_YEAR));
                    if((calendar.get(Calendar.DAY_OF_WEEK)) == 1){storeraw.setEday(calendar.get(Calendar.DAY_OF_WEEK)+6);}
                    else{storeraw.setEday(calendar.get(Calendar.DAY_OF_WEEK)-1);}
                    addList.add(storeraw); // rögzítés listában


                    // teszt üzi
                    Toast.makeText(getActivity(), "Added workout: " +titlehelps+" "+repshelps+" rep "+diffsshelps+" kg "+durationhelp+" min", Toast.LENGTH_LONG).show();
                    popupWindow.dismiss();

                    addworkout();
                }
            }
        });

        btcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });

        return aview;
    }


    // gyakorlat adatok beolvasása listába töltése
    private void readtutorial(){
        exeList.clear();
        InputStream steamach = getResources().openRawResource(R.raw.gyakorlatok);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(steamach, Charset.forName("ISO-8859-2"))
        );
        String line=" ";
        try {
            //fejléc kihagyása
            reader.readLine();
            while ((line=reader.readLine()) !=null){
                Log.d("MyActivity", "Line" +line);
                //Split ;
                String[] token = line.split(";");
                //Read
                Tutorial tutorialraw= new Tutorial();
                tutorialraw.setTid(token[0]);
                tutorialraw.setTname(token[1]);
                tutorialraw.setTmuscle(token[2]);
                tutorialraw.setTdesc(token[3]);
                tutorialraw.setTvariant(token[4]);
                tutorialraw.setTbasept(Integer.parseInt(token[5]));
                tutorialraw.setTdiff(token[6]);
                exeList.add(tutorialraw);
                Log.d("MyActivity", "Just created" +tutorialraw);
            }
            reader.close();
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

    private void readtutorial2(){
        exeList.clear();
        InputStream steamach2 = getResources().openRawResource(R.raw.gyakorlatok);
        BufferedReader reader2 = new BufferedReader(
                new InputStreamReader(steamach2, Charset.forName("ISO-8859-2"))
        );
        String line=" ";
        try {
            //fejléc kihagyása
            reader2.readLine();
            while ((line=reader2.readLine()) !=null){
                Log.d("MyActivity", "Line" +line);
                //Split ;
                String[] token = line.split(";");
                //Read
                Tutorial tutorialraw2= new Tutorial();
                tutorialraw2.setTid(token[0]);
                tutorialraw2.setTname(token[1]);
                tutorialraw2.setTmuscle(token[2]);
                tutorialraw2.setTdesc(token[3]);
                tutorialraw2.setTvariant(token[4]);
                tutorialraw2.setTbasept(Integer.parseInt(token[5]));
                tutorialraw2.setTdiff(token[6]);
                if (tutorialraw2.getTmuscle().equals(buttonhelper)){exeList.add(tutorialraw2);}
                Log.d("MyActivity", "Just created" +tutorialraw2 +buttonhelper+" "+token[2]);
            }
            reader2.close();
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }
    private void readtutorial3(){
        exeList.clear();
        InputStream steamach3 = getResources().openRawResource(R.raw.gyakorlatok);
        BufferedReader reader3 = new BufferedReader(
                new InputStreamReader(steamach3, Charset.forName("ISO-8859-2"))
        );
        String line=" ";
        try {
            //fejléc kihagyása
            reader3.readLine();
            while ((line=reader3.readLine()) !=null){
                Log.d("MyActivity", "Line" +line);
                //Split ;
                String[] token = line.split(";");
                //Read
                Tutorial tutorialraw3= new Tutorial();
                tutorialraw3.setTid(token[0]);
                tutorialraw3.setTname(token[1]);
                tutorialraw3.setTmuscle(token[2]);
                tutorialraw3.setTdesc(token[3]);
                tutorialraw3.setTvariant(token[4]);
                tutorialraw3.setTbasept(Integer.parseInt(token[5]));
                tutorialraw3.setTdiff(token[6]);
                if(tutorialraw3.getTvariant().equals(buttonhelper))
                {exeList.add(tutorialraw3);}
                Log.d("MyActivity", "Just created" +tutorialraw3);
            }
            reader3.close();
        } catch (IOException e) {
            Log.wtf("MyActivity", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }

    private void readexe() throws FileNotFoundException {
        addList.clear();
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String fnameread = "saveworkout.csv";
        File fileread= new File(directory, fnameread);

        if (fileread.exists()){
            InputStream steamread = new FileInputStream(fileread);
            BufferedReader reader4 = new BufferedReader(
                    new InputStreamReader(steamread, Charset.forName("ISO-8859-2"))
            );
            String line=" ";
            try {
                //fejléc kihagyása
                reader4.readLine();
                while ((line=reader4.readLine()) !=null){
                    Log.d("MyActivity", "Line" +line);
                    //Split ;
                    String[] token = line.split(";");
                    //Read
                    Exercise exehelp= new Exercise();
                    exehelp.setEid(token[0]);
                    exehelp.setEname(token[1]);
                    exehelp.setEmuscle(token[2]);
                    exehelp.setEdiff(Integer.parseInt(token[3]));
                    exehelp.setEbase(Integer.parseInt(token[4]));
                    exehelp.setErep(Integer.parseInt(token[5]));
                    exehelp.setEduration(Integer.parseInt(token[6]));
                    exehelp.setEyear(Integer.parseInt(token[7]));
                    exehelp.setEmonth(Integer.parseInt(token[8]));
                    exehelp.setEweek(Integer.parseInt(token[9]));
                    exehelp.setEday(Integer.parseInt(token[10]));
                    addList.add(exehelp);
                    Log.d("MyActivity", "Just created" +exehelp);

                }
                reader4.close();
            } catch (IOException e) {
                Log.wtf("MyActivity", "Error reading data file on line" + line, e);
                e.printStackTrace();
            }
        }
    }


    private void addlistexe(){
        String Eid[] = new String[exeList.size()];
        String Ename[] = new String[exeList.size()];
        String Edesc[] = new String[exeList.size()];
        String Evariant[] = new String[exeList.size()];
        int Ept[] = new int[exeList.size()];
        String Ediff[] = new String[exeList.size()];
        int Eimage[] = new int[exeList.size()];
        for (int i = 0; i < exeList.size(); i++) {
            Eid[i] = exeList.get(i).getTid();
            Ename[i] = exeList.get(i).getTname();
            Edesc[i] = exeList.get(i).getTdesc();
            Evariant[i] = exeList.get(i).getTvariant();
            Ept[i] = exeList.get(i).getTbasept();
            Ediff[i] = exeList.get(i).getTdiff();
            Eimage[i] = getResources().getIdentifier(exeList.get(i).getTid(), "drawable", "com.example.chaosworkout");

        }
        Exeadapter customAdapter = new Exeadapter(getContext(), Eid, Ename, Edesc, Evariant,
                Ept, Ediff, Eimage);
        exerciselist.setAdapter(customAdapter);

    }

    private void addworkout() {
        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        String filename = "saveworkout.csv";
        String[] appen = {"id","name","muscle","difficult","type","rep","duration","day","week","month","year"};
        String csvfile = (Environment.getExternalStorageDirectory()+"/saveworkout.csv");
        File file= new File(directory, filename);

        if (!file.exists()){
            try {
                file.createNewFile();
                FileWriter fw = new FileWriter(file);
                BufferedWriter writer = new BufferedWriter(fw);
                writer.newLine();
                writer.flush();
                writer.close();
                fw.close();

            } catch (Exception e){
                e.printStackTrace();
            }

            try {
                CSVWriter cwriter = new CSVWriter(new FileWriter(file),';', CSVWriter.NO_QUOTE_CHARACTER);
                String[] helpadd = new String[11];
                cwriter.writeNext(appen);
                for(Exercise e : addList){
                    helpadd[0]=e.getEid();
                    helpadd[1]=e.getEname();
                    helpadd[2]=e.getEmuscle();
                    helpadd[3]=String.valueOf(e.getEdiff());
                    helpadd[4]=String.valueOf(e.getEbase());
                    helpadd[5]=String.valueOf(e.getErep());
                    helpadd[6]=String.valueOf(e.getEduration());
                    helpadd[7]=String.valueOf(e.getEyear());
                    helpadd[8]=String.valueOf(e.getEmonth());
                    helpadd[9]=String.valueOf(e.getEweek());
                    helpadd[10]=String.valueOf(e.getEday());
                    cwriter.writeNext(helpadd);
                }
                cwriter.close();


            } catch (Exception e){
                e.printStackTrace();
            }
        }


        else {
            try {
                CSVWriter cwriter = new CSVWriter(new FileWriter(file),';', CSVWriter.NO_QUOTE_CHARACTER);
                String[] helpadd = new String[11];
                cwriter.writeNext(appen);
                for(Exercise e : addList){
                    helpadd[0]=e.getEid();
                    helpadd[1]=e.getEname();
                    helpadd[2]=e.getEmuscle();
                    helpadd[3]=String.valueOf(e.getEdiff());
                    helpadd[4]=String.valueOf(e.getEbase());
                    helpadd[5]=String.valueOf(e.getErep());
                    helpadd[6]=String.valueOf(e.getEduration());
                    helpadd[7]=String.valueOf(e.getEyear());
                    helpadd[8]=String.valueOf(e.getEmonth());
                    helpadd[9]=String.valueOf(e.getEweek());
                    helpadd[10]=String.valueOf(e.getEday());
                    cwriter.writeNext(helpadd);
                }
                cwriter.close();


            } catch (Exception e){
                e.printStackTrace();
            }
        }
        addList.clear();
    }

}