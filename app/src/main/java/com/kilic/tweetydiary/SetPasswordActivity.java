package com.kilic.tweetydiary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SetPasswordActivity extends AppCompatActivity {
EditText editText1;
EditText editText2;
private String PASSis = "pass" ;
private String PASSSetted = "passsetted" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        editText1= findViewById(R.id.editText2);

        editText2= findViewById(R.id.editText3);





    }

    public void comparePasswords(View view) {
        if (editText1.getText().toString().equals((editText2.getText().toString()))) {

            Toast toast = Toast.makeText(getBaseContext(),
                    "Password set", Toast.LENGTH_LONG);
            View viewToast = toast.getView();
            viewToast.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
            toast.show();


            Intent intent2 = new Intent(this, SettingsActivity.class);
            startActivity(intent2);
            int[] correctPattern = new int[editText1.getText().toString().length()];

            Toast toast2 = Toast.makeText(getBaseContext(),
                    "pass is" + correctPattern.length, Toast.LENGTH_LONG);
            View viewToast2 = toast2.getView();
            viewToast2.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
            toast.show();


            String passwString = editText1.getText().toString();


            for (int i = 0; i < correctPattern.length; i++) {
                correctPattern[i] = Integer.parseInt(String.valueOf(passwString.charAt(i)));
                // Log.d("HIII", "" + correctPattern[i]);

            }

            //  for (int i =0; i<editText1.getText().toString().length() ; i++){
            //      correctPattern[i]=editText1.getText().toString().charAt(i);
            //     Toast.makeText(this, "pass is" + correctPattern[i], Toast.LENGTH_SHORT).show();
            // }


            SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor.putString(PASSis, editText1.getText().toString());
            preferencesEditor.putBoolean(PASSSetted, true);
            preferencesEditor.apply();


        } else {
            Toast toast3 = Toast.makeText(getBaseContext(),
                    "Password not match please try again", Toast.LENGTH_LONG);
            View viewToast3 = toast3.getView();
            viewToast3.getBackground().setColorFilter(getBaseContext().getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
            toast3.show();
        }
    }
    public void removePassword(View view) {
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();

        try{
            if(mPreferences.contains(PASSSetted)){
                preferencesEditor.putBoolean(PASSSetted, false);
                preferencesEditor.apply();

            }









        }catch(Exception ex){ex.printStackTrace();}


    }
}
