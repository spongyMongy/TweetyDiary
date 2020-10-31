package com.kilic.tweetydiary;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExportDatabaseCSVTask extends AsyncTask<String, Void, Boolean> {


    WordListOpenHelper dbhelper;
    File exportDir;

    private Context mContext;

    public ExportDatabaseCSVTask (Context context){
        mContext = context;
    }

    //  private final ProgressDialog dialog = new ProgressDialog(mContext);

    @Override
    protected void onPreExecute() {
        //  this.dialog.setMessage("Exporting database...");
        // this.dialog.show();
        dbhelper = new WordListOpenHelper(mContext);
    }

    protected Boolean doInBackground(final String... args) {


        exportDir = new File(Environment.getExternalStorageDirectory(), "/PinkDiary/");

        if (!exportDir.exists()) { exportDir.mkdirs(); }

        File file = new File(exportDir, "PinkDiary.csv");
        try {
            file.createNewFile();
            CSVWriter csvWrite = new CSVWriter(new FileWriter(file));
            Cursor curCSV = dbhelper.raw();
            csvWrite.writeNext(curCSV.getColumnNames());
            while(curCSV.moveToNext()) {
                String arrStr[]=null;
                String[] mySecondStringArray = new String[curCSV.getColumnNames().length];
                for(int i=0;i<curCSV.getColumnNames().length;i++)
                {
                    mySecondStringArray[i] =curCSV.getString(i);
                }
                csvWrite.writeNext(mySecondStringArray);
            }
            csvWrite.close();
            curCSV.close();
            return true;
        } catch (IOException e) {
            return false;
        }









    }

    protected void onPostExecute(final Boolean success) {
        // if (this.dialog.isShowing()) { this.dialog.dismiss(); }
        if (success) {

            try {
                Toast toast = Toast.makeText(mContext, mContext.getString(R.string.exportSuccessful), Toast.LENGTH_SHORT);
                View viewToast = toast.getView();
                viewToast.getBackground().setColorFilter(mContext.getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
                toast.show();


                File exportDir = new File(Environment.getExternalStorageDirectory(), "/PinkDiary/");
                String fileName = "PinkDiary.csv";
                File sharingGifFile = new File(exportDir, fileName);
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("application/csv");
                Uri uri = FileProvider.getUriForFile(mContext,
                        BuildConfig.APPLICATION_ID + ".provider",
                        sharingGifFile);

                shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                mContext.startActivity(Intent.createChooser(shareIntent, "Share CSV"));
            }
            catch (Exception ex){ex.printStackTrace();}


            // ShareGif();
        } else {
            Toast toast=   Toast.makeText(mContext, mContext.getString(R.string.exportFailed), Toast.LENGTH_SHORT);
            View viewToast = toast.getView();
            viewToast.getBackground().setColorFilter(mContext.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
            toast.show();

        }
    }
}

