package com.kilic.tweetydiary;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.WindowManager;
import android.widget.Toast;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntro2Fragment;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import java.util.List;
import java.util.Locale;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

import static com.kilic.tweetydiary.R.string.Intro1;

public class IntroActivity extends AppIntro {



    SharedPreferences mPreferences;
    SharedPreferences.Editor preferencesEditor ;
    private static final String FONTSTATE = "fontstate";
    String myfont;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Note here that we DO NOT use setContentView();

        // Add your slide fragments here.
        // AppIntro will automatically generate the dots indicator and buttons.
       /// addSlide(firstFragment);
      ///  addSlide(secondFragment);
      ///  addSlide(thirdFragment);
      ///  addSlide(fourthFragment);
        // Instead of fragments, you can also use our default slide.
        // Just create a `SliderPage` and provide title, description, background and image.
        // AppIntro will do the rest.



        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferencesEditor = mPreferences.edit();
        if(mPreferences.contains(FONTSTATE)){
            myfont= mPreferences.getString(FONTSTATE,"fonts/AnjelikaRose.ttf" );



        }

        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath(myfont)
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
        //....












        String desctypeface= String.format(Locale.US, "fonts/%s", "Herofont.ttf");

        SliderPage sliderPage = new SliderPage();
        sliderPage.setTitle(getString(R.string.Intro1));
        sliderPage.setDescription(getString(R.string.Intro2));
        sliderPage.setImageDrawable(R.drawable.intro1);
        sliderPage.setBgColor(Color.parseColor("#990099"));
        // sliderPage.setBgColor(getResources().getColor(R.color.intro1));
        sliderPage.setDescTypeface(desctypeface);
        sliderPage.setTitleTypeface(desctypeface);






        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle(getString(R.string.Intro3));
        sliderPage2.setDescription(getString(R.string.Intro4));
        sliderPage2.setImageDrawable(R.drawable.intro2);
        sliderPage2.setBgColor(Color.parseColor("#ffffff"));
        // sliderPage.setBgColor(getResources().getColor(R.color.intro1));
        sliderPage2.setDescTypeface(desctypeface);
        sliderPage2.setTitleTypeface(desctypeface);
        sliderPage2.setTitleColor(Color.parseColor("#000000"));
        sliderPage2.setDescColor(Color.parseColor("#000000"));


        ////Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Herofont.ttf");


        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle(getString(R.string.Intro5));
        sliderPage3.setDescription(getString(R.string.Intro6));
        sliderPage3.setImageDrawable(R.drawable.intro3);
        sliderPage3.setBgColor(Color.parseColor("#ffb3ff"));
        // sliderPage.setBgColor(getResources().getColor(R.color.intro1));
        sliderPage3.setDescTypeface(desctypeface);
        sliderPage3.setTitleTypeface(desctypeface);
        sliderPage3.setTitleColor(Color.parseColor("#000000"));
        sliderPage3.setDescColor(Color.parseColor("#000000"));

        addSlide(AppIntroFragment.newInstance(sliderPage));
        addSlide(AppIntroFragment.newInstance(sliderPage2));
        addSlide(AppIntroFragment.newInstance(sliderPage3));


        setDepthAnimation();
        setFadeAnimation();
       //      setZoomAnimation();
   //     setFlowAnimation();
  //      setSlideOverAnimation();




        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#6666ff"));
        setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(false);
        setProgressButtonEnabled(true);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
       // setVibrate(true);
       // setVibrateIntensity(30);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        // Do something when users tap on Skip button.


    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        // Do something when users tap on Done button.
        Intent i3= new Intent(IntroActivity.this, MainActivity.class);
        startActivity(i3);
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.

    }




    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));

    }



}