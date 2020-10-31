package com.kilic.tweetydiary;


import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amirarcane.lockscreen.activity.EnterPinActivity;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.flask.colorpicker.ColorPickerView;
import com.flask.colorpicker.OnColorChangedListener;
import com.flask.colorpicker.OnColorSelectedListener;
import com.flask.colorpicker.builder.ColorPickerClickListener;
import com.flask.colorpicker.builder.ColorPickerDialogBuilder;
import com.kilic.tweetydiary.Fragments.TimePickerNotificationFragment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Calendar;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;
import static com.kilic.tweetydiary.R.xml.preferences;

//import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment  extends PreferenceFragmentCompat  implements SharedPreferences.OnSharedPreferenceChangeListener,  BillingProcessor.IBillingHandler {
    private EditTextPreference meditTextPreference;
    BillingProcessor bp;
    private String TEXTCOLORIS;
    private EditText edittext;

    private static final String BOUGHT = "boughtstate";
    private static final String MYFONT = "myfont";

    private String COLOR = "color";

    private static final String CANCELSTATE = "cancelstate";
    private static final String FONTSTATE = "fontstate";

    private static final String KEY_PIN = "pin";

    /////////////// private AdView mAdViewSettings;
    private static  String  RESULTSETTEDSCUCCESS = "resultsettedforsuccess";

    TextView diarytextView;
    TextView fontTV1;
    TextView fontTV2;
    TextView fontTV3;
    TextView fontTV4;
    TextView fontTV5;
    TextView fontTV6;
    TextView fontTV7;
    TextView fontTV8;
    TextView fontTV9;
    TextView fontTV10;




    static String timeMessage;
    static int hourrr;
    static int minuteee;
    private static final int NOTIFICATION_ID = 0;
    private static final String ACTION_DAILY_NOTIFICATION = "action_daily_notification";
    private final String NICKNAME_KEY = "nickname";
    private static final String NOTIFHOUR = "notifhour";


    private static final String KEY_RESTART_INTENT="restartintent";

    private static final String NOTIFMIN = "notifmin";
    public static NotificationManager reminderNotifyManager;
    private static SharedPreferences mPreferences;
    private static final String mSharedPrefFile = "com.kilic.tweetydiary.hellosharedprefs";
    public SwitchPreferenceCompat myNotifPref;
    private Preference myExportPref;
    private Preference myReviewPref;
    private Preference myPremiumPref;
    private Preference myColorPref;
    private Preference myBackupPref;
    private Preference myRestorePref;
    private Preference myTextColor_preference;
    private Preference  myFontPreference;
    SharedPreferences.Editor preferencesEditor;

    static SwitchPreferenceCompat myPasswordPref;
    private android.support.v7.preference.EditTextPreference editTextpref;
    private String oldeditText;
    static boolean stateOfNotif;
    private static final int REQUEST_CODEExport = 1;
    private static final int REQUEST_CODE2 = 123;
    private static final int REQUEST_CODEBackup = 456;
    private static final int REQUEST_CODERestore = 789;

    private static final int REQUEST_CODEPREMIUM = 101112;

    static AlarmManager alarmManager;
    static PendingIntent notifyPendingIntent;
    private static final String PREFERENCES = "com.amirarcane.lockscreen";

    PreferenceScreen root;
    Boolean switchState;
    private boolean CHECK = true;
    private boolean UPDATECHECK = false;
    private boolean CHECK2 = true;
    private boolean UPDATECHECK2 = false;
    private static final String STATESENT = "statesent";
    private static final String STATE = "state";
    private static final String TIMESETTED = "timesetted";

    private static final String PASSWORDSTATE = "passwordstate";
    private static final String SETTHEPASSWORD = "setthepassword";


    static String newValue1;
    private SharedPreferences.OnSharedPreferenceChangeListener listener;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        bp = BillingProcessor.newBillingProcessor(getContext(), "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzPcXNQHMhODCjDMDZCilJZpkqO43oZgSF5snGD7W797tKNqikMwqUg6tRzeI544hRKvuU33G+SvLPGOPEu3pTDjzpCviVyR/0V/m5soRj0nfbTM6jaN2NFikKLKl5uPZMzXKocNBT+jHHEiTHdkFt4koqOheDnPOqzZxDz8L1hkH96dJDVcBzAL+IO+3gAE5mkXTEcqS5gHy7K/zYVYaMutImOcFWIiFgl67zAmJ4TiaugTNDyuOuqdCxfxc5IgfiVVBoKN/ZCGk/RjrKtfGJBt3YaCMocwa5A3TMhov2m3M4qccqAk1gG3ys9MgeZkpd7WuZ6I6/1M8zklAeQ3FCwIDAQAB", this); // doesn't bind
        bp.initialize(); // binds
        ////Ã§
        bp.loadOwnedPurchasesFromGoogle();
        final SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        preferencesEditor = mPreferences.edit();

        if (bp.isPurchased("removeads")) {

            preferencesEditor.putBoolean(BOUGHT, true);
            preferencesEditor.apply();

            //  Toast.makeText(getContext(), "already purchased", Toast.LENGTH_SHORT).show();


        }


        // createListener();


        // alarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);


        // mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

        //onSharedPreferenceChanged(null, "");
        try {
            editTextpref = (android.support.v7.preference.EditTextPreference) findPreference("edittext_preference");
            oldeditText = editTextpref.getText();

            myNotifPref = (SwitchPreferenceCompat) findPreference("notification_switch_preference");

            myColorPref = findPreference("color_preference");
            myBackupPref = findPreference("backup_preference");
            myRestorePref = findPreference("restore_preference");
            myTextColor_preference = findPreference("textColor_preference");

            myFontPreference = findPreference("font_preference");

        } catch (Exception ex) {
            // Log.d("ERROR", "first time edittext");
        }


    }


//    haredPreferences mPreferences = getSharedPreferences(mSharedPrefFile, 0);
    //  String nickkey = mPreferences.getString(NICKNAME_KEY, "");
    //        values.put(KEY_NICKNAME_DATE, "@" + nickkey + " ãƒ»" + strDate );


    private void createListener() {
        listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            @Override
            public void onSharedPreferenceChanged(
                    SharedPreferences sharedPreferences, String key) {

                try {
                    String value = sharedPreferences.getString("edittext_preference", "");

                    //Toast.makeText(getContext(), "value is  : " + "aaaaaaaaa" , Toast.LENGTH_LONG).show();


                    // mPreferences = getContext().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
                    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

                    ///   mPreferences =PreferenceManager.getDefaultSharedPreferences(getActivity());


                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    preferencesEditor.putString(NICKNAME_KEY, value);
                    preferencesEditor.apply();


                    if (value.equals("")) {
                        editTextpref.setText(oldeditText);
                        setPreferenceScreen(null);
                        addPreferencesFromResource(preferences);
                    } else {
                        oldeditText = value;
                        //  preferencesEditor.putString(NICKNAME_KEY,value ).apply();
                    }

                    ///////////////////////// fragmenti tekrar baÅŸlatmaca.. Ã¶bÃ¼r tÃ¼rl switch on-off dan sonra tekra almÄ±yordu ikinci alarmÄ± nedense
                    FragmentTransaction tr = getFragmentManager().beginTransaction();
                    tr.replace(android.R.id.content, new SettingsFragment());
                    tr.commit();

                } catch (Exception ex) {
                    // Log.d("Exc", "edittext exception");
                }
                ///Ã§  Constants.nickName = value;
                // Toast.makeText(getContext(), "value is  : " + value , Toast.LENGTH_LONG).show();

            }
        };
        PreferenceManager.getDefaultSharedPreferences(getActivity())
                .registerOnSharedPreferenceChangeListener(listener);


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle extras = new Bundle();
        extras.putString("max_ad_content_rating", "G");

        LinearLayout v = (LinearLayout) super.onCreateView(inflater, container, savedInstanceState);
      /*  AdView adView = new AdView(getContext());
      adView.setAdUnitId(getString(R.string.ad_unitbanner_id));
      adView.setAdSize(AdSize.BANNER);*/


        ///////////////  AdRequest request1 = new AdRequest.Builder().addNetworkExtrasBundle(AdMobAdapter.class, extras).build();
        ///////////////adView.loadAd(request1);

        //////////////  v.addView(adView);

        // v.setcontentView(R.layout.admob);
        /*LinearLayout v = (LinearLayout) super.onCreateView(inflater, container, savedInstanceState);

        AdView adView = new AdView(getContext());
        adView.setAdUnitId("ca-app-pub-xxxxxxxxxxxx/xxxxxxxx");
        adView.setAdSize(AdSize.BANNER);



        AdRequest request1 = new AdRequest.Builder().build();
        adView.loadAd(request1);

        v.addView(adView);

        Button btn = new Button(getActivity().getApplicationContext());
        btn.setText("Button on Bottom");

        //v.addView(btn);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });

        return v;*/

        createListener();
        ////// return super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }


    public void turnOnSwitch() {
        try {
            myNotifPref = (SwitchPreferenceCompat) findPreference("notification_switch_preference");
            myNotifPref.setChecked(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public void turnOffSwitch() {
        try {
            myNotifPref = (SwitchPreferenceCompat) findPreference("notification_switch_preference");
            //myNotifPref.setChecked(true);
            // myNotifPref.setChecked(false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }




    /*

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        AdView adView = getActivity().findViewById(R.id.adViewSettingsActivity);

    }
*/


    @Override
    public void onCreatePreferences(Bundle savedInstanceState,
                                    String rootKey) {

        setPreferencesFromResource(preferences, rootKey);
        // addPreferencesFromResource(R.xml.preferences);


        final AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(ALARM_SERVICE);
        Intent notifyIntent = new Intent(getContext(), AlarmReceiver.class);
        notifyIntent.setAction(ACTION_DAILY_NOTIFICATION);
        //*********
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (getContext(), NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        try {
            root = getPreferenceManager().createPreferenceScreen(getContext());
            myNotifPref = (SwitchPreferenceCompat) findPreference("notification_switch_preference");
            myExportPref = findPreference("export_preference");
            myReviewPref = findPreference("review_preference");
            myPremiumPref = findPreference("premium_preference");
            myColorPref = findPreference("color_preference");
            myPasswordPref = (SwitchPreferenceCompat)findPreference("password_preference");
            myBackupPref = findPreference("backup_preference");
            myRestorePref = findPreference("restore_preference");
            myTextColor_preference = findPreference("textColor_preference");
            myFontPreference= findPreference("font_preference");
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        myNotifPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                // do whatever you want with new value
                //stateOfNotif=   Boolean.parseBoolean(newValue.toString());


                CHECK = ((SwitchPreferenceCompat) preference)
                        .isChecked();

                UPDATECHECK = !CHECK;
                //  Toast.makeText(getContext(), "check is  " + CHECK, Toast.LENGTH_SHORT).show();

                SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                ////Ã§Ã§  SharedPreferences.Editor preferencesEditor = root.getPreferenceManager().getSharedPreferences().edit();
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                preferencesEditor.putBoolean(STATE, UPDATECHECK);
                preferencesEditor.apply();
                // true to update the state of the Preference with the new value
                // in case you want to disallow the change return false
                // Toast.makeText(getContext(), "STATEÃ¶nce is " + mPreferences.getBoolean(STATE, false ), Toast.LENGTH_SHORT).show();

                return true;
            }
        });


        myNotifPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {

            public boolean onPreferenceClick(Preference preference) {

                try {
                    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

                    ///Ã§Ã§       mPreferences = getContext().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
                    switchState = mPreferences.getBoolean(STATE, UPDATECHECK);


                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                        /* preferencesEditor.putBoolean(TIMESETTED, false);
                        preferencesEditor.apply();*/
                    //  Toast.makeText(getContext(), "switchstate is " + switchState, Toast.LENGTH_SHORT).show();


                    // Toast.makeText(getContext(), "STATEsonra is " + mPreferences.getBoolean(STATE, false ), Toast.LENGTH_SHORT).show();


                    if (switchState == true) {
                        preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                        preferencesEditor.putBoolean(STATESENT, true);
                        preferencesEditor.apply();
                        // Toast.makeText(getContext(), "statesent1 " + mPreferences.getBoolean(STATESENT, false), Toast.LENGTH_SHORT).show();


                        sendNotification();


///////////////////////// fragmenti tekrar baÅŸlatmaca.. Ã¶bÃ¼r tÃ¼rl switch on-off dan sonra tekra almÄ±yordu ikinci alarmÄ± nedense
                        //     FragmentTransaction tr = getFragmentManager().beginTransaction();
                        //      tr.replace(android.R.id.content, new SettingsFragment());
                        //      tr.commit();


                    }
                    if (switchState == false)

                    {
                        alarmManager.cancel(notifyPendingIntent);
                        //preferencesEditor = root.getPreferenceManager().getSharedPreferences().edit();
                        preferencesEditor.putBoolean(STATESENT, false);
                        preferencesEditor.apply();

                        //  Toast.makeText(getContext(), "statesent2 " + mPreferences.getBoolean(STATESENT, false), Toast.LENGTH_SHORT).show();
///////////////////////// fragmenti tekrar baÅŸlatmaca.. Ã¶bÃ¼r tÃ¼rl switch on-off dan sonra tekra almÄ±yordu ikinci alarmÄ± nedense

                        //    FragmentTransaction tr = getFragmentManager().beginTransaction();
                        //    tr.replace(android.R.id.content, new SettingsFragment());
                        //    tr.commit();


                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return true;
            }


        });


        myExportPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                export();
                return false;
            }
        });



        myTextColor_preference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (!bp.isPurchased("removeads")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.premium_dialog_layout, null);
                    builder.setView(dialogView);
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    bp.purchase(getActivity(), "removeads");
return true;
                }

else{
                ColorPickerDialogBuilder
                        .with(preference.getContext())
                        // .setTitle(R.string.color_dialog_title)
                        // .initialColor(currentBackgroundColor)
                        .wheelType(ColorPickerView.WHEEL_TYPE.FLOWER)
                        .density(12)
                        .setOnColorChangedListener(new OnColorChangedListener() {
                            @Override
                            public void onColorChanged(int selectedColor) {
                                // Handle on color change
                                /////////       Log.d("ColorPicker", "onColorChanged: 0x" + Integer.toHexString(selectedColor));


                            }
                        })
                        .setOnColorSelectedListener(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(int selectedColor) {
                                //Toast.makeText(NewEntry.this, "clor is " +Integer.toHexString(selectedColor) , Toast.LENGTH_SHORT).show();


                            }
                        })
                        .setPositiveButton("ok", new ColorPickerClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int selectedColor, Integer[] allColors) {
                                //changeBackgroundColor(selectedColor);
                                if (allColors != null) {
                                    StringBuilder sb = null;

                                    for (Integer color : allColors) {
                                        if (color == null)
                                            continue;
                                        if (sb == null)
                                            sb = new StringBuilder("Color List:");
                                        sb.append("\r\n#" + Integer.toHexString(color).toUpperCase());
                                    }

                                    if (sb != null) {
                                    } //Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_SHORT).show();
                                }

                                preferencesEditor.putInt(TEXTCOLORIS, Color.parseColor("#" + (Integer.toHexString(selectedColor))));
                                preferencesEditor.apply();
                                /////     ///////////         int color = mPreferences.getInt(TEXTCOLORIS,  (R.color.colorEntry));
                                /// Toast.makeText(NewEntry.this, "sss" + (Integer.toHexString(selectedColor)) , Toast.LENGTH_SHORT).show();
                                /////////////            edittext = getActivity().findViewById(R.id.editText);
                                //////////// edittext.setTextColor(   Color.parseColor("#"+(Integer.toHexString(selectedColor)))   );
                                // edittext.setTextColor(   color   );

                              /*  try{
                                    MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();}
                                catch(Exception ex){ex.printStackTrace();}*/
                            }
                        })
                        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .showColorEdit(true)
                        .setColorEditTextColor(ContextCompat.getColor(getActivity(), android.R.color.holo_blue_bright))
                        .build()
                        .show();



           /*     try{
                    MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();}
                catch(Exception ex){ex.printStackTrace();}*/
                return true;
            }
                }
        });










        myReviewPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                Uri uri = Uri.parse("market://details?id=" + getContext().getPackageName());
                Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
                // To count with Play market backstack, After pressing back button,
                // to taken back to our application, we need to add following flags to intent.
                goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                        Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                        Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET |     ////bu satÄ±r silinebilir belki, denersin
                        Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                try {
                    startActivity(goToMarket);
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getContext().getPackageName())));
                }
                return false;
            }
        });


        myPremiumPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

                if (!bp.isPurchased("removeads")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.premium_dialog_layout, null);
                    builder.setView(dialogView);
                    final AlertDialog dialog = builder.create();
                    dialog.show();
                    bp.purchase(getActivity(), "removeads");
                    return true;
                }


                else{
                    boolean isOneTimePurchaseSupported = bp.isOneTimePurchaseSupported();
                    if (isOneTimePurchaseSupported) {
                        bp.purchase(getActivity(), "removeads");
                    }

////Ã§Ã§         bp.purchase(getActivity(), "android.test.purchased");


                    return false;
                }




            }

        });






        myColorPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {

          /*      SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
// then you use
               // prefs.getInt("color_preference", 0);

                Toast.makeText(getActivity(), prefs.getInt("color_preference", 0) , Toast.LENGTH_SHORT).show();*/

                //////   int color = android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("color_preference", Color.YELLOW);
                //   Toast.makeText(getActivity(),  "color  " + getColorHex(color) , Toast.LENGTH_SHORT).show();
                //////    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

                ///   mPreferences =PreferenceManager.getDefaultSharedPreferences(getActivity());
                ///// ColorPreference colorPreference;
                //   new ColorPreference(getActivity()).getValue();

                ///sil
                // new ColorPreference(getActivity()).setValue(222222);
/*
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString(COLOR, getColorHex(color) );
                preferencesEditor.apply();

                Toast.makeText(getActivity(), mPreferences.getString(COLOR,"ffffff"), Toast.LENGTH_SHORT).show();

*/

                return true;
            }

        });

        myColorPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//mPreferences.contains("color_preference"


                int color = android.preference.PreferenceManager.getDefaultSharedPreferences(getActivity()).getInt("color_preference", Color.YELLOW);
                ///      Toast.makeText(getActivity(),  "color  " + getColorHex((Integer)newValue) , Toast.LENGTH_SHORT).show();

                SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                preferencesEditor.putString(COLOR, (getColorHex((Integer) newValue)));
                //preferencesEditor.putInt("color_preference",  (Integer)newValue   );
                preferencesEditor.apply();




                ((SettingsActivity)getActivity()).onResume();

///////////////////////////////////////////*78*7*7*7*7*7*fdvdfvdfsvdfv


                ////////////////////?**********************

           /*     getFragmentManager()
                        .beginTransaction()
                        .detach(SettingsFragment.this)
                        .attach(SettingsFragment.this)
                        .commit();*/



                try {
                    // getFragmentManager().beginTransaction()
                    //       .replace(R.id.container, SettingsFragment.class.newInstance()).commit();



                    /*
                    FragmentTransaction tr = getFragmentManager().beginTransaction();
                    tr.replace(android.R.id.content, new SettingsFragment());
                    tr.commit();*/
                    // Toast.makeText(getContext(), "aaaaaaaaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                }
                catch (Exception ex){ex.printStackTrace();}



             /*   FragmentTransaction tr = getFragmentManager().beginTransaction();
                tr.replace(R.id.v, yourFragmentInstance);
                tr.commit()*/

                return true;

            }

        });

/*
myPasswordPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
    @Override
    public boolean onPreferenceClick(Preference preference) {
       // Intent intent1= new Intent(getContext(), SetPasswordActivity.class);
      //  startActivity(intent1);

        Intent intent = new Intent(getContext(), EnterPinActivity.class);
        startActivity(intent);

        return true;
    }
});*/




        myBackupPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                backup();
                return true;
            }
        });



        myRestorePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                restore();
                return true;
            }
        });

///////////////////Ã‡Ã§
        myPasswordPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                // do whatever you want with new value
                //stateOfNotif=   Boolean.parseBoolean(newValue.toString());


                CHECK2 = ((SwitchPreferenceCompat) preference)
                        .isChecked();

                UPDATECHECK2 = !CHECK2;
                //  Toast.makeText(getContext(), "check is  " + CHECK, Toast.LENGTH_SHORT).show();

                SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                ////Ã§Ã§  SharedPreferences.Editor preferencesEditor = root.getPreferenceManager().getSharedPreferences().edit();
                SharedPreferences.Editor preferencesEditor = mPreferences.edit();

                preferencesEditor.putBoolean(PASSWORDSTATE, UPDATECHECK2);
                preferencesEditor.apply();
                // true to update the state of the Preference with the new value
                // in case you want to disallow the change return false
                // Toast.makeText(getContext(), "STATEÃ¶nce is " + mPreferences.getBoolean(STATE, false ), Toast.LENGTH_SHORT).show();

                return true;
            }
        });





        myFontPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                try{

                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.font_dialog_layout, null);
                    builder.setView(dialogView);


                    fontTV1 = dialogView.findViewById(R.id.font1);
                    fontTV2 = dialogView.findViewById(R.id.font2);
                    fontTV3 = dialogView.findViewById(R.id.font3);
                    fontTV4 = dialogView.findViewById(R.id.font4);
                    fontTV5 = dialogView.findViewById(R.id.font5);
                    fontTV6 = dialogView.findViewById(R.id.font6);
                    fontTV7 = dialogView.findViewById(R.id.font7);
                    fontTV8 = dialogView.findViewById(R.id.font8);
                    fontTV9 = dialogView.findViewById(R.id.font9);
                    fontTV10 = dialogView.findViewById(R.id.font10);


                    //  fontTV6= dialogView.findViewById(R.id.font6);
                    //  fontTV6= dialogView.findViewById(R.id.font6);


                    /*TextView textView1 = dialogView.findViewById(R.id.font1);
                    TextView textView2 = dialogView.findViewById(R.id.font2);
                    TextView textView3 = dialogView.findViewById(R.id.font3);
                    TextView textView4 = dialogView.findViewById(R.id.font4);
                    TextView textView5 = dialogView.findViewById(R.id.font5);
                    TextView textView6 = dialogView.findViewById(R.id.font6);
                    TextView textView7 = dialogView.findViewById(R.id.font7);
                    TextView textView8 = dialogView.findViewById(R.id.font8);
                    TextView textView9 = dialogView.findViewById(R.id.font9);
                    TextView textView10 = dialogView.findViewById(R.id.font10);


                    *//*Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
                    Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Simplehandwritting-Regular.ttf");
                    Typeface typeface3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/PacificAgain.ttf");
                    Typeface typeface4 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/KBZipaDeeDooDah.ttf");
                    Typeface typeface5 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Xiomara.ttf");
                    Typeface typeface6 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Herofont.ttf");
                    Typeface typeface7 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/CookieMonster.ttf");
                    Typeface typeface8 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/CutiePatootie.ttf");
                    Typeface typeface9 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LittleMissPriss.ttf");
                    Typeface typeface10 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AnjelikaRose.ttf");
*//*





                    Typeface typeface1 =  ResourcesCompat.getFont(getActivity(), R.font.robotoregular);
                    Typeface typeface2 =  ResourcesCompat.getFont(getActivity(), R.font.simplehandwrittingregular);
                    Typeface typeface3 =  ResourcesCompat.getFont(getActivity(), R.font.pacificagain);
                    Typeface typeface4 =  ResourcesCompat.getFont(getActivity(), R.font.kbzipadeedoodah);
                    Typeface typeface5 =  ResourcesCompat.getFont(getActivity(), R.font.xiomara);
                    Typeface typeface6 =  ResourcesCompat.getFont(getActivity(), R.font.herofont);
                    Typeface typeface7 =  ResourcesCompat.getFont(getActivity(), R.font.cookiemonster);
                    Typeface typeface8 =  ResourcesCompat.getFont(getActivity(), R.font.cutiepatootie);
                    Typeface typeface9 =  ResourcesCompat.getFont(getActivity(), R.font.littlemisspriss);
                    Typeface typeface10 =  ResourcesCompat.getFont(getActivity(), R.font.anjelikarose);






                    textView1.setTypeface(typeface1);
                    textView2.setTypeface(typeface2);
                    textView3.setTypeface(typeface3);
                    textView4.setTypeface(typeface4);
                    textView5.setTypeface(typeface5);
                    textView6.setTypeface(typeface6);
                    textView7.setTypeface(typeface7);
                    textView8.setTypeface(typeface8);
                    textView9.setTypeface(typeface9);
                    textView10.setTypeface(typeface10);


                    final AlertDialog dialog = builder.create();


                    fontTV1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/robotoregular.ttf").apply();


                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 50, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                          preferencesEditor.putString(FONTSTATE, "fonts/simplehandwrittingregular.ttf").apply();
                            //     preferencesEditor.putString(FONTSTATE, "simplehandwrittingregular.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/pacificagain.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/kbzipadeedoodah.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/xiomara.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/herofont.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/cookiemonster.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/cutiepatootie.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/littlemisspriss.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/anjelikarose.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });*/ TextView textView1 = dialogView.findViewById(R.id.font1);
                    TextView textView2 = dialogView.findViewById(R.id.font2);
                    TextView textView3 = dialogView.findViewById(R.id.font3);
                    TextView textView4 = dialogView.findViewById(R.id.font4);
                    TextView textView5 = dialogView.findViewById(R.id.font5);
                    TextView textView6 = dialogView.findViewById(R.id.font6);
                    TextView textView7 = dialogView.findViewById(R.id.font7);
                    TextView textView8 = dialogView.findViewById(R.id.font8);
                    TextView textView9 = dialogView.findViewById(R.id.font9);
                    TextView textView10 = dialogView.findViewById(R.id.font10);


                    Typeface typeface1 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");
                    Typeface typeface2 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Simplehandwritting-Regular.ttf");
                    Typeface typeface3 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/PacificAgain.ttf");
                    Typeface typeface4 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/KBZipaDeeDooDah.ttf");
                    Typeface typeface5 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Xiomara.ttf");
                    Typeface typeface6 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Herofont.ttf");
                    Typeface typeface7 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/CookieMonster.ttf");
                    Typeface typeface8 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/CutiePatootie.ttf");
                    Typeface typeface9 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LittleMissPriss.ttf");
                    Typeface typeface10 = Typeface.createFromAsset(getActivity().getAssets(), "fonts/AnjelikaRose.ttf");


                    textView1.setTypeface(typeface1);
                    textView2.setTypeface(typeface2);
                    textView3.setTypeface(typeface3);
                    textView4.setTypeface(typeface4);
                    textView5.setTypeface(typeface5);
                    textView6.setTypeface(typeface6);
                    textView7.setTypeface(typeface7);
                    textView8.setTypeface(typeface8);
                    textView9.setTypeface(typeface9);
                    textView10.setTypeface(typeface10);


                    final AlertDialog dialog = builder.create();


                    fontTV1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/Roboto-Regular").apply();



                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 50, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/Simplehandwritting-Regular.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/PacificAgain.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/KBZipaDeeDooDah.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV5.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/Xiomara.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV6.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/Herofont.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV7.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/CookieMonster.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV8.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/CutiePatootie.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV9.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/LittleMissPriss.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    fontTV10.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            preferencesEditor.putString(FONTSTATE, "fonts/AnjelikaRose.ttf").apply();

                            Intent mStartActivity = new Intent(getContext(), SettingsActivity.class);
                            int mPendingIntentId = 123456;
                            PendingIntent mPendingIntent = PendingIntent.getActivity(getContext(), mPendingIntentId, mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
                            AlarmManager mgr = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
                            mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                            System.exit(0);

                        }
                    });


                    dialog.show();


                    return true;

                }


                catch(Exception ex){ex.printStackTrace();}
                return true;
            }
        });









        myPasswordPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {


            public boolean onPreferenceClick(Preference preference) {

                try {
                    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

                    ///Ã§Ã§       mPreferences = getContext().getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);
                    switchState = mPreferences.getBoolean(PASSWORDSTATE, UPDATECHECK2);


                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    // preferencesEditor.putBoolean(TIMESETTED, false);
                    preferencesEditor.apply();
                    //  Toast.makeText(getContext(), "switchstate is " + switchState, Toast.LENGTH_SHORT).show();


                    // Toast.makeText(getContext(), "STATEsonra is " + mPreferences.getBoolean(STATE, false ), Toast.LENGTH_SHORT).show();


                    if (switchState == true) {
                        preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                        //preferencesEditor.putBoolean(SETTHEPASSWORD, true);
                        preferencesEditor.apply();
                        // Toast.makeText(getContext(), "SETTHEPASSWORD " + mPreferences.getBoolean(SETTHEPASSWORD, false), Toast.LENGTH_SHORT).show();

                        //      EnterPinActivity epa = new EnterPinActivity();
                        //    SharedPreferences prefs = epa.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
                        //    String a=prefs.getString(KEY_PIN, "");
                        //Toast.makeText(getContext(), "pass " , Toast.LENGTH_SHORT).show();
                        // Toast.makeText(getContext(), "SETTHEPASSWORD " + mPreferences.getBoolean(SETTHEPASSWORD, false), Toast.LENGTH_SHORT).show();

                        ////Intent intent = EnterPinActivity.getIntent(getContext(), true);
                        //startActivity(intent);
                        Intent intent3 = EnterPinActivity.getIntent(getActivity(), true);
                        // startActivity(intent3);

                        getActivity().startActivityForResult(intent3, REQUEST_CODE2);
                        preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                        preferencesEditor.putBoolean(SETTHEPASSWORD, true);
                        preferencesEditor.commit();



                          /*  if(mPreferences.contains(RESULTSETTEDSCUCCESS)){
                                //Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();

                                if(mPreferences.getBoolean(RESULTSETTEDSCUCCESS, false)==false) {
                                    preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                                    preferencesEditor.putBoolean(SETTHEPASSWORD, false);
                                    preferencesEditor.apply();
                                    Toast.makeText(getContext(), "silindi", Toast.LENGTH_SHORT).show();
                                    myPasswordPref.setChecked(false);

                                }
                            }*/

/*
                        if(mPreferences.contains(RESULTSETTEDSCUCCESS)){
                            //Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();

                            if(mPreferences.getBoolean(RESULTSETTEDSCUCCESS, true)==true) {
                                preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                                preferencesEditor.putBoolean(SETTHEPASSWORD, true);
                                preferencesEditor.apply();
                                Toast.makeText(getContext(), "pass eklendi", Toast.LENGTH_SHORT).show();
                                myPasswordPref.setChecked(true);

                            }
                        }*/









                    }
                    if (switchState == false) {
                        preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                        preferencesEditor.putBoolean(SETTHEPASSWORD, false);
                        preferencesEditor.apply();
                        myPasswordPref.setChecked(false);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }


                return true;
            }


        });











/*
       ////Ã§Ã§Ã§Ã§  editTextpref = (android.support.v7.preference.EditTextPreference)findPreference("edittext_preference");
        editTextpref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {

            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                editTextpref.setSummary(newValue.toString() + "sssssssssssssss");

                    newValue1= newValue.toString();

               // mPreferences.edit().putString(NICKNAME, (Constants.nickName)).apply();


                /////Ã§Ã§       mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, 0);
                /////Ã§Ã§      SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                /////Ã§Ã§      preferencesEditor.putString(NICKNAME_KEY,newValue1 );
                /////Ã§Ã§     preferencesEditor.apply();

                Constants.nickName = newValue.toString();






               // SharedPreferences.Editor preferencesEditor = root.getPreferenceManager().getSharedPreferences().edit();
               // preferencesEditor.putString(NICKNAME, Constants.nickName);
               // preferencesEditor.commit();


             //   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
              //  String value = preferences.getString("editTextpref", "");

                return true;
            }

        });*/


        //Toast.makeText(root.getContext(), Constants.nickName, Toast.LENGTH_SHORT).show();

    }


    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {


      /*  if (key.equals("edittext_preference")) {
            Constants.nickName = newValue1;
            Preference connectionPref = findPreference(key);
            // Set summary to be the user-description for the selected value
            connectionPref.setSummary(sharedPreferences.getString(key, "qqqqq"));
        }*/



    }


    public void export() {

        try {
//checking wether the permission is already granted
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

// permission is already granted
// here you can directly access contacts


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    ExportDatabaseCSVTask exp = new ExportDatabaseCSVTask(getContext());
                    exp.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


                } else {

                    new ExportDatabaseCSVTask(getContext()).execute();
                }


            } else {

//persmission is not granted yet
//Asking for permission
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODEExport);
///////////////////////// fragmenti tekrar baÅŸlatmaca.. Ã¶bÃ¼r tÃ¼rl switch on-off dan sonra tekra almÄ±yordu ikinci alarmÄ± nedense
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                tr.replace(android.R.id.content, new SettingsFragment());
                tr.commit();
            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }




    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {



        try {

            File sd = Environment.getExternalStorageDirectory();
            File data = Environment.getDataDirectory();
            switch (requestCode){



                case REQUEST_CODERestore:

                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        if (sd.canRead()) {
                            String currentDBPath = "/data/" + "com.kilic.tweetydiary"
                                    + "/databases/" + Contract.WordList.DATABASE_NAME;



                            String backupDBPath = Contract.WordList.DATABASE_NAME; // From SD directory.
                            File currentDB = new File(data, currentDBPath);
                            File backupDB = new File(sd, backupDBPath);
                            /// File currentDB = new File(this.getDatabasePath(Contract.WordList.DATABASE_NAME).getPath());


                            FileChannel src = new FileInputStream(backupDB).getChannel();
                            FileChannel dst = new FileOutputStream(currentDB).getChannel();
                            dst.transferFrom(src, 0, src.size());
                            src.close();
                            dst.close();
                  /*      try{
                            MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();}
                        catch(Exception ex){ex.printStackTrace();}*/

                            Toast toast = Toast.makeText(getActivity(), getString(R.string.importSuccessful), Toast.LENGTH_SHORT);
                            View viewToast = toast.getView();
                            viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
                            toast.show();


                        }


                    }


                    else{
                        Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.permission_denied), Toast.LENGTH_LONG);
                        View viewToast = toast.getView();
                        viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                        toast.show();


                    }
                    break;







                case REQUEST_CODEExport:

//checking if the permission is granted
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

//permission is granted,do your operation
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                            ExportDatabaseCSVTask exp = new ExportDatabaseCSVTask(getActivity());
                            exp.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                            //Toast.makeText(getContext() ,"ggggggggggggggggg", Toast.LENGTH_SHORT).show();

                        } else {

                            new ExportDatabaseCSVTask(getActivity()).execute();
                        }


                    } else {

// permission not granted
//Display your message to let the user know that he requires permission to access the app
                        Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.permission_denied), Toast.LENGTH_LONG);
                        View viewToast = toast.getView();
                        viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                        toast.show();
                    }
                    break;





                case REQUEST_CODEBackup:

                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        if (sd.canWrite()) {

                            String currentDBPath = "/data/" + "com.kilic.tweetydiary"
                                    + "/databases/" + Contract.WordList.DATABASE_NAME;


                            String backupDBPath = Contract.WordList.DATABASE_NAME;
                            File currentDB = new File(data, currentDBPath);
                            File backupDB = new File(sd, backupDBPath);

                            FileChannel src = new FileInputStream(currentDB).getChannel();
                            FileChannel dst = new FileOutputStream(backupDB).getChannel();
                            dst.transferFrom(src, 0, src.size());
                            src.close();
                            dst.close();
        /*    try{
                MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();}
            catch(Exception ex){ex.printStackTrace();}*/


                            Toast toast = Toast.makeText(getActivity(), "Backup Successful!", Toast.LENGTH_LONG);
                            View viewToast = toast.getView();
                            viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
                            toast.show();


                        }



                    }


                    else{
                        Toast toast = Toast.makeText(getActivity(), getContext().getString(R.string.permission_denied), Toast.LENGTH_LONG);
                        View viewToast = toast.getView();
                        viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                        toast.show();


                    }
                    break;













                default:
                    super.onRequestPermissionsResult(requestCode, permissions, grantResults);


            }



        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }


    public void sendNotification() throws FileNotFoundException {
        showTimePickerDialog();


        // Toast.makeText(getActivity(), "it s ok set to " + TimePickerNotificationFragment.hourr + " : " + TimePickerNotificationFragment.minutee, Toast.LENGTH_LONG).show();

    }


    public void showTimePickerDialog() {
        DialogFragment newFragment2 = new TimePickerNotificationFragment();
        newFragment2.show(getFragmentManager(),
                getString(R.string.time_picker));





    }


    public static void processTimePickerResult(Context context, int hour, int minute) {
// Convert time elements into strings.
        //\ hourrr= TimePickerNotificationFragment.hourr;
        //\ minuteee= TimePickerNotificationFragment.minutee;
        hourrr = hour;
        minuteee = minute;
        // Toast.makeText(context, "entered time **" + TimePickerNotificationFragment.hourr + ":" + TimePickerNotificationFragment.minutee, Toast.LENGTH_SHORT).show();

        String hour_string = Integer.toString(hourrr);
        String minute_string = Integer.toString(minuteee);
        if (minuteee < 10) {

            minute_string = "0" + minuteee;
            timeMessage = (hour_string + ":" + minute_string);
            ///\  Toast.makeText(getActivity(), getString(string.time) + " " +timeMessage,
            ///\       Toast.LENGTH_SHORT).show();

        }
// Assign the concatenated strings to timeMessage.
        else {
            timeMessage = (hour_string + ":" + minute_string);
            //   Toast.makeText(this, "Reminder is set to "   +getString(R.string.time) + " " + timeMessage,
            //           Toast.LENGTH_SHORT).show();
        }

        //Constants.alarmFromWho="fromDailyReminder";
        reminderNotifyManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);


        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        Intent notifyIntent = new Intent(context, AlarmReceiver.class);
        notifyIntent.setAction(ACTION_DAILY_NOTIFICATION);
        //*********
        PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (context, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();


        cal.set(Calendar.HOUR_OF_DAY, hourrr);
        cal.set(Calendar.MINUTE, minuteee);
        // cal.set(Calendar.SECOND, 1);

        // long triggerTime = SystemClock.elapsedRealtime();


//////////////////////////////Ã§Ã§Ã§Ã§
    /*    mPreferences = context.getSharedPreferences(mSharedPrefFile, 0);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(NOTIFHOUR, hourrr);
        preferencesEditor.putInt(NOTIFMIN, minuteee);
        preferencesEditor.apply();
        preferencesEditor.commit();*/

        //long repeatInterval = 60*1000;

        // alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), notifyPendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000 * 60 * 24, notifyPendingIntent);
        ///   alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60 * 1000 * 10 , notifyPendingIntent);


        Toast toast = Toast.makeText(context, context.getString(R.string.toastreminderSetted) + " " + timeMessage, Toast.LENGTH_SHORT);
        View viewToast = toast.getView();
        viewToast.getBackground().setColorFilter(context.getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
        toast.show();


        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(NOTIFHOUR, hourrr);
        preferencesEditor.putInt(NOTIFMIN, minuteee);
        preferencesEditor.apply();

        //int hr = mPreferences.getInt(NOTIFHOUR,0);
        // int mn = mPreferences.getInt(NOTIFMIN,0);
        // Toast.makeText(context, "notif "  + mPreferences.getInt(NOTIFMIN,0),  Toast.LENGTH_SHORT).show();


    }


    @Override
    public void onResume() {
        super.onResume();
        try {
            getPreferenceScreen().getSharedPreferences()
                    .registerOnSharedPreferenceChangeListener(listener);
//////////////////////////////burdaki this i listener yaptÄ±m. hata verirse deÄŸiÅŸtir
            mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            Boolean cancelState = mPreferences.getBoolean(CANCELSTATE, false);
            if (cancelState == true)
                myNotifPref.setChecked(false);
            // Toast.makeText(getContext(), "" + mPreferences.getBoolean(CANCELSTATE, false), Toast.LENGTH_SHORT).show();
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();

            preferencesEditor.putBoolean(CANCELSTATE, false);
            preferencesEditor.apply();





            //Toast.makeText(getContext(), "kkkkkkkkkkkkkk", Toast.LENGTH_SHORT).show();

            ////Ã§
          /*  if(mPreferences.contains(RESULTSETTEDSCUCCESS)) {
                //Toast.makeText(getContext(), "false", Toast.LENGTH_SHORT).show();

                if (mPreferences.getBoolean(RESULTSETTEDSCUCCESS, false) == false) {
                    preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                    preferencesEditor.putBoolean(SETTHEPASSWORD, false);
                    preferencesEditor.commit();
                    Toast.makeText(getContext(), "silindi", Toast.LENGTH_SHORT).show();
                    myPasswordPref.setChecked(false);

                }
            }*/


        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        // mPreferences = getActivity().getSharedPreferences(mSharedPrefFile, 0);

        //preferencesEditor.commit();


        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(listener);


    }


    @Override
    public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
        // Toast.makeText(getContext(), getString(R.string.purchase_was_done), Toast.LENGTH_SHORT).show();

        Toast toast = Toast.makeText(getContext(), getString(R.string.purchase_was_done), Toast.LENGTH_SHORT);
        View viewToast = toast.getView();
        viewToast.getBackground().setColorFilter(getContext().getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
        toast.show();


    }

    @Override
    public void onPurchaseHistoryRestored() {

    }

    @Override
    public void onBillingError(int errorCode, @Nullable Throwable error) {

    }

    @Override
    public void onBillingInitialized() {

    }







    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        /////////////Ã‡Ã§
        if (!bp.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
        // Toast.makeText(getContext(), "hiiiiiiiiiiiiiiiiiii", Toast.LENGTH_SHORT).show();
        //super.onActivityResult(requestCode, resultCode, data);

        if(mPreferences.getBoolean(RESULTSETTEDSCUCCESS, false)==false){
            SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            SharedPreferences.Editor preferencesEditor = mPreferences.edit();
            preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
            preferencesEditor.putBoolean(SETTHEPASSWORD, false);
            preferencesEditor.apply();
            //Toast.makeText(getContext(), "pass silindi", Toast.LENGTH_SHORT).show();
            myPasswordPref.setChecked(false);


        }


        switch (requestCode) {
            case REQUEST_CODE2:
                // Toast.makeText(getContext(), "aaaa", Toast.LENGTH_SHORT).show();
                if (resultCode == EnterPinActivity.RESULT_BACK_PRESSED) {
                    //Toast.makeText(getContext(), "back pressed", Toast.LENGTH_LONG).show();



                    SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor preferencesEditor = mPreferences.edit();
                    preferencesEditor = getPreferenceManager().getSharedPreferences().edit();
                    preferencesEditor.putBoolean(SETTHEPASSWORD, false);
                    preferencesEditor.apply();
                    //  Toast.makeText(getContext(), "passsssssssss silindi", Toast.LENGTH_SHORT).show();
                    myPasswordPref.setChecked(false);
                }
                break;





        }

        //Toast.makeText(getContext(), "aaaa", Toast.LENGTH_SHORT).show();






    }








    public void onDestroy() {
        if (bp != null) {
            bp.release();
        }
        super.onDestroy();
    }


    String getColorHex(int color) {
        return String.format("#%02x%02x%02x", Color.red(color), Color.green(color), Color.blue(color));
    }







    public void restore(){
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            try {
                File sd = Environment.getExternalStorageDirectory();

                //  File sd = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) +
                //         File.separator + "Your Backup Folder"+
                //         File.separator );

                File data = Environment.getDataDirectory();
                if (sd.canRead()) {
                    String currentDBPath = "/data/" + "com.kilic.tweetydiary"
                            + "/databases/" + Contract.WordList.DATABASE_NAME;

                    // Toast.makeText(getContext(), "aaaaaaaaaaaaaaaaaaaaaa", Toast.LENGTH_SHORT).show();
                    String backupDBPath = Contract.WordList.DATABASE_NAME; // From SD directory.
                    File currentDB = new File(data, currentDBPath);
                    File backupDB = new File(sd, backupDBPath);
                    /// File currentDB = new File(this.getDatabasePath(Contract.WordList.DATABASE_NAME).getPath());


                    FileChannel src = new FileInputStream(backupDB).getChannel();
                    FileChannel dst = new FileOutputStream(currentDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
                  /*      try{
                            MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();}
                        catch(Exception ex){ex.printStackTrace();}*/

                    Toast toast = Toast.makeText(getActivity(), getString(R.string.importSuccessful), Toast.LENGTH_SHORT);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
                    toast.show();


                }
            } catch (Exception e) {


                Toast toast = Toast.makeText(getActivity(), "Import Failed!", Toast.LENGTH_SHORT);
                View viewToast = toast.getView();
                viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
                toast.show();


            }

        } else {

//persmission is not granted yet
//Asking for permission
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODERestore);
        }

    }






    public void backup(){



        try {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
                File sd = Environment.getExternalStorageDirectory();
                File data = Environment.getDataDirectory();

                if (sd.canWrite()) {

                    String currentDBPath = "/data/" + "com.kilic.tweetydiary"
                            + "/databases/" + Contract.WordList.DATABASE_NAME;


                    String backupDBPath = Contract.WordList.DATABASE_NAME;
                    File currentDB = new File(data, currentDBPath);
                    File backupDB = new File(sd, backupDBPath);

                    FileChannel src = new FileInputStream(currentDB).getChannel();
                    FileChannel dst = new FileOutputStream(backupDB).getChannel();
                    dst.transferFrom(src, 0, src.size());
                    src.close();
                    dst.close();
        /*    try{
                MainActivity.mRecyclerView.getAdapter().notifyDataSetChanged();}
            catch(Exception ex){ex.printStackTrace();}*/


                    Toast toast = Toast.makeText(getActivity(), "Backup Successful!", Toast.LENGTH_LONG);
                    View viewToast = toast.getView();
                    viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorNotifColor), PorterDuff.Mode.SRC_IN);
                    toast.show();


                }
            }
            else{
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODEBackup);
            }

        } catch (Exception e) {

            Toast toast = Toast.makeText(getActivity(), "Backup Failed!", Toast.LENGTH_SHORT);
            View viewToast = toast.getView();
            viewToast.getBackground().setColorFilter(this.getResources().getColor(R.color.colorBlack), PorterDuff.Mode.SRC_IN);
            toast.show();



        }

    }











}






















