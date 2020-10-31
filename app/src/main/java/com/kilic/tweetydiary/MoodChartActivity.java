package com.kilic.tweetydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.StaticLabelsFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.kilic.tweetydiary.R.color.color1;
import static com.kilic.tweetydiary.R.color.color10;
import static com.kilic.tweetydiary.R.color.color12;
import static com.kilic.tweetydiary.R.color.color13;
import static com.kilic.tweetydiary.R.color.color14;
import static com.kilic.tweetydiary.R.color.color2;
import static com.kilic.tweetydiary.R.color.color3;
import static com.kilic.tweetydiary.R.color.color4;
import static com.kilic.tweetydiary.R.color.color5;
import static com.kilic.tweetydiary.R.color.color6;
import static com.kilic.tweetydiary.R.color.color7;
import static com.kilic.tweetydiary.R.color.color8;
import static com.kilic.tweetydiary.R.color.color9;

public class MoodChartActivity extends AppCompatActivity {
    ImageView img1;
    ImageView img2;
    ImageView img3;
   // private PieChart mChart;
    String[] mParties;
    private WordListOpenHelper mDB3;
TextView monthlyAverage;
    private String COLOR = "color";
  //  private float[] yData = {25, 10, 66, 44, 120};
  //  private String[] xData = {"Mitch", "Jessica" , "Mohammad" , "Kelsey", "Sam"};
LineChart lineChart;
    private LineChart mChart;

    private ArrayList <Integer> yDataal;
    private ArrayList <String> xDataal;

    public int mood1;
    public int mood2;
    public int mood3;
    public int mood4;
    public int mood5;

    ArrayList <Integer >  arlmoodinweek;
    ArrayList <String >  arlmooddateinweek;

    private AdView adView;

    ImageView backIV;
View view1;
    Bitmap bitmap;

    PieChart pieChart;


    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));

    }


    @Override
    protected void onDestroy() {

        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       // getSupportActionBar().hide();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        setContentView(R.layout.activity_mood_chart);

     ////////   getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        adView = new AdView(this, getString(R.string.adfacebookbannerid2moodTrack), AdSize.BANNER_HEIGHT_50);
        LinearLayout adContainer = (LinearLayout) findViewById(R.id.banner_container);
        adContainer.addView(adView);
        //AdSettings.addTestDevice("3b5b04bd-592c-468a-9e18-3d6b363651bc");

        adView.loadAd();





        mDB3 = new WordListOpenHelper(this);

        showMoodResultMonthly();


        yDataal = new ArrayList<>();
        xDataal = new ArrayList<>();

        yDataal.add(mood5);
        yDataal.add(mood4);
        yDataal.add(mood3);
        yDataal.add(mood2);
        yDataal.add(mood1);


        pieChart = (PieChart) findViewById(R.id.idPieChart);
        pieChart.getDescription().setEnabled(false);
        // pieChart.setDescription("Sales by employee (In Thousands $) ");
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        //pieChart.setHoleColor(Color.BLUE);
        //pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setHoleRadius(35);
        pieChart.setTransparentCircleAlpha(0);
        //pieChart.setCenterText("Super Cool Chart");
        pieChart.setCenterTextSize(10);
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelTextSize(20);
        //More options just check out the documentation!
        pieChart.setMaxAngle(360);
        pieChart.setDrawSliceText(true);
        addDataSet();


        pieChart.animateXY(1000, 1500);


        float value = (float) (mood1 + mood2 * 2 + mood3 * 3 + mood4 * 4 + mood5 * 5) / (float)(mood1 + mood2 + mood3 + mood4 + mood5);
   //     value = Double.parseDouble(new DecimalFormat("#.#").format(value));
        //value = Float.parseFloat(new DecimalFormat("#.#").format(value));

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(1);

        view1 = findViewById(R.id.view1);
        view1.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                view1.setVisibility(View.VISIBLE);
                view1.startAnimation(AnimationUtils.loadAnimation(MoodChartActivity.this, android.R.anim.fade_in));

            }
        }, 2000);


        monthlyAverage = findViewById(R.id.monthlyAverageTV);
        monthlyAverage.setVisibility(View.INVISIBLE);

        monthlyAverage.setText(getString(R.string.monthlyAverage) + "\n" + df.format(value));

        new Handler().postDelayed(new Runnable() {
            public void run() {
                monthlyAverage.setVisibility(View.VISIBLE);
                monthlyAverage.startAnimation(AnimationUtils.loadAnimation(MoodChartActivity.this, android.R.anim.fade_in));
            }
        }, 2000);


        // monthlyAverage.startAnimation(AnimationUtils.loadAnimation(this, android.R.anim.fade_in));
        // monthlyAverage.clearAnimation();


        backIV = findViewById(R.id.back);
        backIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentback = new Intent(MoodChartActivity.this, MainActivity.class);
                startActivity(intentback);
            }
        });


        arlmoodinweek = new ArrayList<>();
        arlmooddateinweek = new ArrayList<>();
        showMoodResultlastWeek();
        final GraphView graph = (GraphView) findViewById(R.id.graph);



        try {

      /*  LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{

                    new DataPoint(0, arlmoodinweek.get(6)),
                    new DataPoint(1, arlmoodinweek.get(5)),
                    new DataPoint(2, arlmoodinweek.get(4)),
                    new DataPoint(3, arlmoodinweek.get(3)),
                    new DataPoint(4, arlmoodinweek.get(2)),
                    new DataPoint(5, arlmoodinweek.get(1)),
                    new DataPoint(6, arlmoodinweek.get(0))


        });*/



          /*  DataPoint dp1=new DataPoint(0, arlmoodinweek.get(6));
            DataPoint dp2=new DataPoint(1, arlmoodinweek.get(5));
            DataPoint dp3=new DataPoint(2, arlmoodinweek.get(4));
            DataPoint dp4=new DataPoint(3, arlmoodinweek.get(3));
            DataPoint dp5=new DataPoint(4, arlmoodinweek.get(2));
            DataPoint dp6=new DataPoint(5, arlmoodinweek.get(1));
            DataPoint dp7=new DataPoint(6, arlmoodinweek.get(0));
*/





            LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>();


            try{
                series.appendData(new DataPoint(0, arlmoodinweek.get(6)),true,7);
            }
            catch (Exception ex) {
                series.appendData(new DataPoint(0, 0),true,7);
            }




            try{
                series.appendData(new DataPoint(1, arlmoodinweek.get(5)),true,7);

            }
            catch (Exception ex) {
                series.appendData(new DataPoint(1, 0),true,7);
            }



            try{
                series.appendData(new DataPoint(2, arlmoodinweek.get(4)),true,7);

            }
            catch (Exception ex) {
                series.appendData(new DataPoint(2, 0),true,7);
            }


            try{
                series.appendData(new DataPoint(3, arlmoodinweek.get(3)),true,7);
            }
            catch (Exception ex) {
                series.appendData(new DataPoint(3, 0),true,7);
            }


            try{
                series.appendData(new DataPoint(4, arlmoodinweek.get(2)),true,7);

            }
            catch (Exception ex) {
                series.appendData(new DataPoint(4, 0),true,7);
            }


            try{
                series.appendData(new DataPoint(5, arlmoodinweek.get(1)),true,7);
            }
            catch (Exception ex) {
                series.appendData(new DataPoint(5, 0),true,7);
            }



            try{series.appendData(new DataPoint(6, arlmoodinweek.get(0)),true,7);
            }
            catch(Exception ex){
                series.appendData(new DataPoint(6, 0),true,7);
            }








            graph.addSeries(series);
            series.setDrawBackground(true);
          series.setBackgroundColor(Color.parseColor("#1A9933cc"));
          //  series.setAnimated(true);
           // series.setDrawAsPath(true);
           //graph.setTitle("Last 7 entry");


            graph.setTitleColor(ContextCompat.getColor(this, R.color.colorPrimary));
           series.setDrawDataPoints(true);



        }
        catch ( Exception ex){ex.printStackTrace();}



        StaticLabelsFormatter staticLabelsFormatter = new StaticLabelsFormatter(graph);
        /// staticLabelsFormatter.setHorizontalLabels(new String[] {"Very bad", "Bad", "Normal","Good", "Perfect"});
        staticLabelsFormatter.setHorizontalLabels(new String[] {"","", "",getString(R.string.Last7entry),"","",""});

      // staticLabelsFormatter.setVerticalLabels(new String[] {"low ", "middle", "high"});
        graph.getGridLabelRenderer().setLabelFormatter(staticLabelsFormatter);


        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(6);
        graph.getViewport().setMinY(1);
        graph.getViewport().setMaxY(5);
        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setXAxisBoundsManual(true);

        //graph.getViewport().setScalableY(true);

        graph.getViewport().setScrollable(true);

        graph.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                graph.setVisibility(View.VISIBLE);
                graph.startAnimation(AnimationUtils.loadAnimation(MoodChartActivity.this, android.R.anim.fade_in));

            }
        }, 2000);


























       /* chart = findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 4));
        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 4));

        LineDataSet dataSet = new LineDataSet(entries, "Customized values");
        dataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        //****
        // Controlling X axis
        XAxis xAxis = chart.getXAxis();
        // Set the xAxis position to bottom. Default is top
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //Customizing x axis value
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value];
            }
        };
        xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
        xAxis.setValueFormatter(formatter);

        //***
        // Controlling right side of y axis
        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);

        //***
        // Controlling left side of y axis
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        // Setting Data
        LineData data = new LineData(dataSet);
        chart.setData(data);
        chart.animateX(2500);
        //refresh
        chart.invalidate();
*/



      /*  pieChart.animateY(1400, Easing.EaseInOutQuad);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

*/




        /*pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {


                int pos1 = e.toString().indexOf("(sum): ");
                String sales = e.toString().substring(pos1 + 7);

                for(int i = 0; i < yData.length; i++){
                    if(yData[i] == Float.parseFloat(sales)){
                        pos1 = i;
                        break;
                    }
                }
                String employee = xData[pos1 + 1];
               // Toast.makeText(MainActivity.this, "Employee " + employee + "\n" + "Sales: $" + sales + "K", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });*/

    }
















    private void addDataSet() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        for(int i = 0; i < yDataal.size(); i++){
            yEntrys.add(new PieEntry(yDataal.get(i) , i));
        }

        for(int i = 1; i < xDataal.size(); i++){
            xEntrys.add(xDataal.get(i));
        }

        //create the data set
        PieDataSet pieDataSet = new PieDataSet(yEntrys, "");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12);

        //add colors to dataset
        ArrayList<Integer> colors = new ArrayList<>();
        colors.add(ResourcesCompat.getColor(getResources(), R.color.colorMood5, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.colorMood4, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.colorMood3, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.colorMood2, null));
        colors.add(ResourcesCompat.getColor(getResources(), R.color.colorMood1, null));


        pieDataSet.setColors(colors);

        //add legend to chart
       // Legend legend = pieChart.getLegend();
       // legend.setForm(Legend.LegendForm.CIRCLE);
       // legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);

        //create pie data object
        Legend legend = pieChart.getLegend();
        legend.setEnabled(false);



        PieData pieData = new PieData(pieDataSet);
        pieData.setDrawValues(false);

        pieChart.setDrawSliceText(false);
        pieChart.setData(pieData);
        pieChart.invalidate();



        final LinearLayout linearlayout = findViewById(R.id.linearlayout);


        linearlayout.setVisibility(View.INVISIBLE);
        new Handler().postDelayed(new Runnable(){
            public void run() {
                linearlayout.setVisibility(View.VISIBLE);
            }
        }, 2000);


      /*  ImageView[] IMGS= new ImageView[3];

        IMGS[0]=findViewById(R.id.imageView10);
        IMGS[1]=findViewById(R.id.imageView11);
        IMGS[2]=findViewById(R.id.imageView12);
*/










      /*


        mParties = new String[] {
                "Party A", "Party B", "Party C", "Party D", "Party E"
        };



        mChart = findViewById(R.id.chart1);
        mChart.setBackgroundColor(Color.WHITE);

       //////////////// moveOffScreen();

        mChart.setUsePercentValues(true);
        mChart.getDescription().setEnabled(false);

       /// mChart.setCenterTextTypeface(mTfLight);
        mChart.setCenterText(generateCenterSpannableText());

        mChart.setDrawHoleEnabled(true);
        mChart.setHoleColor(Color.WHITE);

        mChart.setTransparentCircleColor(Color.WHITE);
        mChart.setTransparentCircleAlpha(110);

        mChart.setHoleRadius(58f);
        mChart.setTransparentCircleRadius(61f);

        mChart.setDrawCenterText(true);

        mChart.setRotationEnabled(false);
        mChart.setHighlightPerTapEnabled(true);

        mChart.setMaxAngle(180f); // HALF CHART
        mChart.setRotationAngle(180f);
        mChart.setCenterTextOffset(0, -20);

        setData(4, 100);



        //float x= getInterpolation(1400);
        mChart.animateY(1500);

        Legend l = mChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(0f);
        l.setYOffset(0f);

        // entry label styling
        mChart.setEntryLabelColor(Color.WHITE);
     ///   mChart.setEntryLabelTypeface(mTfRegular);
        mChart.setEntryLabelTextSize(12f);
    }

    private void setData(int count, float range) {

        ArrayList<PieEntry> values = new ArrayList<PieEntry>();
        ArrayList values2 = new ArrayList();
        values2.add(16);
        values2.add(26);
        values2.add(36);
        values2.add(53);
        values2.add(22);




        for (int i = 0; i < count; i++) {
            //values.add(new PieEntry((float) ((Math.random() * range) + range / 5), mParties[i % mParties.length]));



        }

        PieDataSet dataSet = new PieDataSet(values2, "Election Results");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);

        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
      ///  data.setValueTypeface(mTfLight);
        mChart.setData(data);

        mChart.invalidate();
    }

    private SpannableString generateCenterSpannableText() {

        SpannableString s = new SpannableString("MPAndroidChart\ndeveloped by Philipp Jahoda");
        s.setSpan(new RelativeSizeSpan(1.7f), 0, 14, 0);
        s.setSpan(new StyleSpan(Typeface.NORMAL), 14, s.length() - 15, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 14, s.length() - 15, 0);
        s.setSpan(new RelativeSizeSpan(.8f), 14, s.length() - 15, 0);
        s.setSpan(new StyleSpan(Typeface.ITALIC), s.length() - 14, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(ColorTemplate.getHoloBlue()), s.length() - 14, s.length(), 0);
        return s;
    }

    private void moveOffScreen() {

        Display display = getWindowManager().getDefaultDisplay();
        int height = display.getHeight();  // deprecated

        int offset = (int)(height * 0.65); *//* percent to move *//*

        RelativeLayout.LayoutParams rlParams =
                (RelativeLayout.LayoutParams)mChart.getLayoutParams();
        rlParams.setMargins(0, 0, 0, -offset);
        mChart.setLayoutParams(rlParams);

    }


*/


    }


    public void showMoodResultMonthly(){

        Calendar rightNow = Calendar.getInstance();
        int currentMonth = rightNow.get(Calendar.MONTH);
        int currentDay = rightNow.get(Calendar.DAY_OF_MONTH);
        int currentYear = rightNow.get(Calendar.YEAR);


        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yy", Locale.US);
        Date date = new Date(rightNow.getTimeInMillis());
        String strDate = dateFormat.format(date);


        //String day = new SimpleDateFormat("dd").format(date.getTime());
        String monthName = new SimpleDateFormat("MMM").format(date.getTime());
        String yearName = new SimpleDateFormat("yy").format(date.getTime());
          // Toast.makeText(this, "month " + monthName, Toast.LENGTH_SHORT).show();
     //   Toast.makeText(this, "year " + yearName, Toast.LENGTH_SHORT).show();


        try {

            Cursor cursor = mDB3.searchMood(monthName + ", " + yearName);

            //Toast.makeText(this, "curor ok", Toast.LENGTH_SHORT).show();
            // You must move the cursor to the first item.
            cursor.moveToFirst();
            // Only process a non-null cursor with rows.

                if (cursor != null & cursor.getCount() > 0) {
                    int index;
                    int result;


                    do{

                        index = cursor.getColumnIndex(Contract.WordList.KEY_MOOD_IMAGE);
                        result = cursor.getInt(index);
                          if (result==1) mood1++;
                          else if (result==2) mood2++;
                          else if (result==3) mood3++;
                          else if (result==4) mood4++;
                          else if (result==5) mood5++;

                    }

                    while (cursor.moveToNext());
                    cursor.close();

                //    Toast.makeText(this, "mood5 " + mood5, Toast.LENGTH_SHORT).show();

                }

                     if(cursor.getCount() == 0){

                        /* Toast toast = Toast.makeText(this, getString(R.string.atLeastOneEntry), Toast.LENGTH_LONG);
                         View viewToast = toast.getView();
                         viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorMood4), PorterDuff.Mode.SRC_IN);
                         toast.show();*/


                         new Handler().postDelayed(new Runnable() {
                             public void run() {
                                 Toast toast = Toast.makeText(MoodChartActivity.this, getString(R.string.atLeastOneEntry), Toast.LENGTH_LONG);
                                 View viewToast = toast.getView();
                                 viewToast.getBackground().setColorFilter(getResources().getColor(R.color.colorMood4), PorterDuff.Mode.SRC_IN);
                                 toast.show();

                             }
                         }, 2000);




                     }




        }


        catch (Exception ex){ ex.printStackTrace();}


    }




public void showMoodResultlastWeek(){
    Calendar rightNow = Calendar.getInstance();
    int currentYear = rightNow.get(Calendar.YEAR);
   // SimpleDateFormat dateFormat = new SimpleDateFormat("yy", Locale.US);
    Date date = new Date(rightNow.getTimeInMillis());
    String yearName = new SimpleDateFormat("yy").format(date.getTime());


    try {

        Cursor cursor = mDB3.searchMood(", " + yearName);

        //Toast.makeText(this, "curor ok", Toast.LENGTH_SHORT).show();
        // You must move the cursor to the first item.
        cursor.moveToLast(); //non-null cursor with rows.
int counter=0;


        if (cursor != null & cursor.getCount() > 0) {


            int index;
            int result;


            do{
 if (counter==7 ) {cursor.close(); return;}
                index = cursor.getColumnIndex(Contract.WordList.KEY_MOOD_IMAGE);
                result = cursor.getInt(index);
                if (result==1) {arlmoodinweek.add(1);arlmooddateinweek.add(cursor.getString(cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE)));   counter++;   }
                else if (result==2) {arlmoodinweek.add(2); arlmooddateinweek.add(cursor.getString(cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE)));  counter++;   }
                else if (result==3) {arlmoodinweek.add(3); arlmooddateinweek.add(cursor.getString(cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE))); counter++;   }
                else if (result==4){arlmoodinweek.add(4); arlmooddateinweek.add(cursor.getString(cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE)));   counter++;   }
                else if (result==5) {arlmoodinweek.add(5); arlmooddateinweek.add(cursor.getString(cursor.getColumnIndex(Contract.WordList.KEY_NICKNAME_DATE))); counter++;   }

            }
            while (cursor.moveToPrevious() );
            cursor.close();

            //    Toast.makeText(this, "mood5 " + mood5, Toast.LENGTH_SHORT).show();
        }
    }

    catch (Exception ex){ ex.printStackTrace();}


}







    public void onResume() {
        super.onResume();



        try{
            SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


            ActionBar actionBar = getSupportActionBar();

            if (mPreferences.contains(COLOR)) {
                String colorstate = mPreferences.getString(COLOR, "ffffff");


                if (colorstate.equals("#33b5e5")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color1));
                    }
                } else if (colorstate.equals("#aa66cc")) {
                    //  fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.fab2));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color2));
                    }

                } else if (colorstate.equals("#99cc00")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color3));
                    }
                } else if (colorstate.equals("#ffbb33")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color4));
                    }
                } else if (colorstate.equals("#ff4444")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color5));
                    }
                } else if (colorstate.equals("#0099cc")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color6));
                    }
                } else if (colorstate.equals("#9933cc")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color7));
                    }
                } else if (colorstate.equals("#669900")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color8));
                    }
                } else if (colorstate.equals("#ff8800")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color9));
                    }
                } else if (colorstate.equals("#cc0000")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color10));
                    }
                } else if (colorstate.equals("#ffffff")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                    }
                } else if (colorstate.equals("#eeeeee")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                    }
                } else if (colorstate.equals("#cccccc")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color13));
                    }
                } else if (colorstate.equals("#888888")) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color14));
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                    }
                }

            } else {
                //  Toast.makeText(this, "333", Toast.LENGTH_SHORT).show();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), color12));
                }


            }

        } catch (Exception ex) {ex.printStackTrace();}







        // prefs.getPreferenceScreen().getSharedPreferences()
        //       .registerOnSharedPreferenceChangeListener(this);

    }





}


