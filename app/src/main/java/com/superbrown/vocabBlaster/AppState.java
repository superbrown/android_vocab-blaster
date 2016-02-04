package com.superbrown.vocabBlaster;

import android.app.Application;

import com.superbrown.vocabBlaster.vocabulary.GradeLevel;
import com.superbrown.vocabBlaster.vocabulary.VocabularyList;

import java.io.Serializable;

/**
 * Created by Mike on 2/1/14.
 */
public class AppState extends Application implements Serializable
{
    protected String LOG_TAG = "my debug";

    private GradeLevel selectedGradeLevel;
    private VocabularyList selectedVocabularyList;

    public AppState()
    {
        reset();
    }


    public void reset()
    {
        selectedGradeLevel = null;
        selectedVocabularyList = null;
    }

    public GradeLevel getSelectedGradeLevel()
    {
        return selectedGradeLevel;
    }


    public String getSelectedGradeLevelName()
    {
        if (getSelectedGradeLevel() != null)
        {
            return getSelectedGradeLevel().getName();
        }
        else
        {
            return null;
        }
    }

    public void setSelectedGradeLevel(GradeLevel selectedGradeLevel)
    {
        if (this.selectedGradeLevel != selectedGradeLevel)
        {
            setSelectedVocabularyList(null);
        }

        this.selectedGradeLevel = selectedGradeLevel;
//        Log.i(LOG_TAG, "Calling setSelectedGradeLevel(): " +
//                (selectedGradeLevel == null ? null : selectedGradeLevel.getName()));
    }

    public VocabularyList getSelectedVocabularyList()
    {
        return selectedVocabularyList;
    }

    public String getSelectedVocabularyListName()
    {
        if (getSelectedVocabularyList() != null)
        {
            return getSelectedVocabularyList().getName();
        }
        else
        {
            return null;
        }
    }

    public void setSelectedVocabularyList(VocabularyList selectedVocabularyList)
    {
        this.selectedVocabularyList = selectedVocabularyList;
//        Log.i(LOG_TAG, "Calling setSelectedVocabularyList(): " +
//                (selectedVocabularyList == null ? null : selectedVocabularyList.getName()));
    }
}
