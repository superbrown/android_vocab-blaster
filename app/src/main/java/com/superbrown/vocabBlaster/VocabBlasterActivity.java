package com.superbrown.vocabBlaster;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.superbrown.android.util.DefectReportingActivity;
import com.superbrown.vocabBlaster.utils.audio.SoundPalette;

import static com.superbrown.vocabBlaster.VocabBlasterApplication.SoundPaletteName.GOMER_PYLE;
import static com.superbrown.vocabBlaster.VocabBlasterApplication.SoundPaletteName.SILENT;
import static com.superbrown.vocabBlaster.VocabBlasterApplication.SoundPaletteName.THREE_STOOGES;

/**
* Created by Mike on 2/1/14.
*/
public class VocabBlasterActivity extends DefectReportingActivity
{
    //    protected String LOG_TAG = getClass().getName();
    protected String LOG_TAG = "my debug";

    public Typeface CHALK_FONT;
    public int COLOR_CHALK_WHITE;
    public int COLOR_CHALK_GRAY;
    public int COLOR_CHALK_YELLOW;
    public int CHALK_FONT_SIZE;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        Log.i(LOG_TAG, "Calling onCreate()");

        // initialization code

//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        CHALK_FONT = Typeface.createFromAsset(getAssets(), "fonts/craya___.ttf");

        COLOR_CHALK_WHITE = ContextCompat.getColor(this, R.color.chalk_white);
        COLOR_CHALK_GRAY = ContextCompat.getColor(this, R.color.chalk_gray);
        COLOR_CHALK_YELLOW = ContextCompat.getColor(this, R.color.chalk_yellow);

        CHALK_FONT_SIZE = 25;

        setTitle("Vocab-Blaster");

//        getMyStateFromBundle(savedInstanceState);
    }

    @Override
    protected void onRestart()
    {
        super.onRestart();
//        Log.i(LOG_TAG, "Calling onRestart()");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
//        Log.i(LOG_TAG, "Calling onStart()");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
//        Log.i(LOG_TAG, "Calling onResume()");

    }

    @Override
    protected void onPause()
    {
        super.onPause();
//        Log.i(LOG_TAG, "Calling onPause()");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
//        Log.i(LOG_TAG, "Calling onStop()");
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
//        Log.i(LOG_TAG, "Calling onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem selectedMenuItem)
    {
        switch (selectedMenuItem.getItemId())
        {
            case R.id.optionsMenuItem_restart:
                getAppState().setSelectedVocabularyList(null);
                Intent selectVocabularyListIntent = new Intent(this, SelectVocabularyListActivity.class);
                startActivity(selectVocabularyListIntent);
                return true;

            case R.id.optionsMenuItem_gomerSound:
                getAppState().setSoundPaletteName(GOMER_PYLE);
                return true;

            case R.id.optionsMenuItem_3StoogesSound:
                getAppState().setSoundPaletteName(THREE_STOOGES);
                return true;

            case R.id.optionsMenuItem_NoSound:
                getAppState().setSoundPaletteName(SILENT);
                return true;

            default:
                return super.onOptionsItemSelected(selectedMenuItem);
        }
    }

    public void formatHandwritingOnAChalkboard(TextView textView)
    {
        textView.setTypeface(CHALK_FONT);
        textView.setTextColor(COLOR_CHALK_YELLOW);
        textView.setTextSize(CHALK_FONT_SIZE);
    }

    public void onConfigurationChanged(Configuration newConfig)
    {
//        Log.i(LOG_TAG, "Calling onConfigurationChanged()");

//        super.onConfigurationChanged(newConfig);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }


    public void reset()
    {
//        Log.i(LOG_TAG, "Calling reset()");

        VocabBlasterApplication application = ((VocabBlasterApplication) getApplication());
        application.reset();

        Intent administerTestActivity = new Intent(this, SelectVocabularyListActivity.class);
        startActivity(administerTestActivity);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
//        Log.i(LOG_TAG, "Calling onSaveInstanceState()");

        setAppStateToBundle(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        super.onRestoreInstanceState(savedInstanceState);
//        Log.i(LOG_TAG, "Calling onRestoreInstanceState()");

        getAppStateFromBundle(savedInstanceState);
    }

    public static final String BUNDLE_KEY_FOR_APPLICATION_STATE = "applicationState";

    private void setAppStateToBundle(Bundle outState)
    {
        VocabBlasterAppState appState = getAppState();
//        Log.i(LOG_TAG,
//                "[ P U T ] GradeLevel: " + appState.getSelectedGradeLevelName() +
//                ", Vocab List Name: " + appState.getSelectedVocabularyListName());
        outState.putSerializable(BUNDLE_KEY_FOR_APPLICATION_STATE, appState);
    }

    private void getAppStateFromBundle(Bundle savedInstanceState)
    {
        if (savedInstanceState == null)
        {
//            Log.i(LOG_TAG, "[ G E T ] (nothing found)");
            return;
        }

        VocabBlasterAppState appState =
                (VocabBlasterAppState)savedInstanceState.getSerializable(BUNDLE_KEY_FOR_APPLICATION_STATE);
//        Log.i(LOG_TAG,
//                "[ G E T ] GradeLevel: " + appState.getSelectedGradeLevelName() +
//                        ", Vocab List Name: " + appState.getSelectedVocabularyListName());
        setPersistentState(appState);
    }

    public SoundPalette getSoundPalette()
    {
        return getVocabBlasterApplication().getSoundPalette();
    }

    public VocabBlasterAppState getAppState()
    {
        return getVocabBlasterApplication().getAppState();
    }

    public void setPersistentState(VocabBlasterAppState appState)
    {
        // The app state is stored in the application object, but the idea here is to
        // hide that detail from the activities.
        getVocabBlasterApplication().setAppState(appState);
    }

    protected VocabBlasterApplication getVocabBlasterApplication()
    {
        // The app state is stored in the application object, but the idea here is to
        // hide that detail from the activities.
        return ((VocabBlasterApplication)getApplication());
    }
}
