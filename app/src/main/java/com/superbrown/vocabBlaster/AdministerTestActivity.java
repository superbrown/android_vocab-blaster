package com.superbrown.vocabBlaster;

import android.os.Bundle;
import android.widget.LinearLayout;
import com.superbrown.vocabBlaster.administerTest.TestAdministratorLayout;
import com.superbrown.vocabBlaster.R;
import com.superbrown.vocabBlaster.vocabulary.VocabularyList;

/**
 * Created by Mike on 2/1/14.
 */
public class AdministerTestActivity extends VocabBlasterActivity
{
    private LinearLayout mainPanel;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_administer_test);
        initUserInterface();
    }

    private void initUserInterface()
    {
        setTitle("Vocab-Blaster" +
                " > " + getAppState().getSelectedGradeLevelName() +
                " > " + getAppState().getSelectedVocabularyListName());

        loadVocabularyList(getAppState().getSelectedVocabularyList());
    }


    private void loadVocabularyList(VocabularyList vocabularyList)
    {
        if (vocabularyList == null)
        {
            return;
        }

        vocabularyList.shuffle();

        TestAdministratorLayout testAdministratorLayout = new TestAdministratorLayout(this, vocabularyList);

        mainPanel = (LinearLayout) findViewById(R.id.administerTest_linearLayout_main);
        mainPanel.removeAllViews();
        mainPanel.addView(testAdministratorLayout);
    }

}
