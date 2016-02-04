package com.superbrown.vocabBlaster;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.*;

import com.superbrown.superspell.android.ver2.R;
import com.superbrown.vocabBlaster.vocabulary.GradeLevel;
import com.superbrown.vocabBlaster.vocabulary.VocabularyList;

import java.util.*;

/**
 * Created by Mike on 2/1/14.
 */
public class SelectVocabularyListActivity extends VocabBlasterActivity
{
    private Spinner gradeLevelSpinner;
    private Spinner vocabularyListSpinner;
    private Map<String, GradeLevel> gradeLevels;
    private GradeLevelSelectionListener gradeLevelSelectionListener;
    private VocabularyListSelectionListener vocabularyListSelectionListener;
    private TextView selectVocabularyListTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_select_vocabulary_list);

        gradeLevelSpinner = (Spinner) this.findViewById(R.id.selectVocabularyList_spinner_selectedGradeLevel);
        gradeLevelSelectionListener = new GradeLevelSelectionListener(this);

        vocabularyListSpinner = (Spinner) SelectVocabularyListActivity.this.findViewById(R.id.selectVocabularyList_spinner_selectVocabularyList);
        vocabularyListSelectionListener = new VocabularyListSelectionListener(this);

        selectVocabularyListTextView = (TextView) SelectVocabularyListActivity.this.findViewById(R.id.selectVocabularyList_textView_selectVocabularyList);

        initUserInterface();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initUserInterface()
    {
        setTitle("Vocab-Blaster");

        ArrayAdapter<CharSequence> gradeLevelSpinnerAdapter =
                new ArrayAdapter(this, android.R.layout.simple_spinner_item);

        gradeLevelSpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        gradeLevelSpinnerAdapter.add("Select One");

        gradeLevels = getVocabBlasterApplication().grades;
        Set<String> gradeLevelNames = gradeLevels.keySet();
        gradeLevelSpinnerAdapter.addAll(gradeLevelNames);

        gradeLevelSpinner.setAdapter(gradeLevelSpinnerAdapter);

        gradeLevelSpinner.setOnItemSelectedListener(gradeLevelSelectionListener);

        String selectedGradeLevelName = getAppState().getSelectedGradeLevelName();
        if (selectedGradeLevelName != null)
        {
            int i = indexOf(gradeLevelNames, selectedGradeLevelName);
            if (i != -1)
            {
                gradeLevelSpinner.setSelection(i + 1);
            }
            else
            {
                gradeLevelSpinner.setSelection(0);
            }
        }
        else
        {
            gradeLevelSpinner.setSelection(0);
        }
    }

    private class GradeLevelSelectionListener implements AdapterView.OnItemSelectedListener
    {
        private Activity owningActivity;

        private GradeLevelSelectionListener(Activity owningActivity) {
            this.owningActivity = owningActivity;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
        {
            String selectedGradeLevelName = (String) gradeLevelSpinner.getItemAtPosition(i);

            GradeLevel selectedGradeLevel = gradeLevels.get(selectedGradeLevelName);

            getAppState().setSelectedGradeLevel(selectedGradeLevel);

            if (selectedGradeLevel == null)
            {
                selectVocabularyListTextView.setVisibility(View.INVISIBLE);
                vocabularyListSpinner.setVisibility(View.INVISIBLE);
                return;
            }

            setTitle("Vocab-Blaster > " + selectedGradeLevelName);

            ArrayAdapter<CharSequence> vocabularyListSpinnerAdapter =
                    new ArrayAdapter(
                            SelectVocabularyListActivity.this,
                            android.R.layout.simple_spinner_item);

            vocabularyListSpinnerAdapter.setDropDownViewResource(
                    android.R.layout.simple_spinner_dropdown_item);

            vocabularyListSpinnerAdapter.add("Select One");

            VocabularyList selectedVocabularyList = getAppState().getSelectedVocabularyList();
            String selectedVocabularyListName = null;
            if (selectedVocabularyList != null)
            {
                selectedVocabularyListName = selectedVocabularyList.getName();
            }

            Collection<VocabularyList> vocabularyListsForTheGrade = selectedGradeLevel.getValues();
            int index = 1;
            int indexOfSelectedVocabularyList = -1;
            for (VocabularyList vocabularyList : vocabularyListsForTheGrade)
            {
                String vocabularyListName = vocabularyList.getName();
                vocabularyListSpinnerAdapter.add(vocabularyListName);

                if (selectedVocabularyListName != null &&
                    vocabularyListName.equals(selectedVocabularyListName))
                {
                    indexOfSelectedVocabularyList = index;
                }

                index++;
            }

            vocabularyListSpinner.setAdapter(vocabularyListSpinnerAdapter);
            vocabularyListSpinner.setOnItemSelectedListener(vocabularyListSelectionListener);

            if (indexOfSelectedVocabularyList != -1)
            {
                vocabularyListSpinner.setSelection(indexOfSelectedVocabularyList);
            }
            else
            {
                vocabularyListSpinner.setSelection(0);
            }

            selectVocabularyListTextView.setVisibility(View.VISIBLE);
            vocabularyListSpinner.setVisibility(View.VISIBLE);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView)
        {
            //To change body of implemented methods use File | Settings | File Templates.
        }
    }

    private class VocabularyListSelectionListener implements AdapterView.OnItemSelectedListener
    {
        private Activity owningActivity;

        private VocabularyListSelectionListener(Activity owningActivity)
        {
            this.owningActivity = owningActivity;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l)
        {
            String selectedVocabularyListName = (String) vocabularyListSpinner.getItemAtPosition(i);
            VocabularyList selectedVocabularyList =
                    getAppState().getSelectedGradeLevel().getVocabularyList(selectedVocabularyListName);

            getAppState().setSelectedVocabularyList(selectedVocabularyList);

            if (selectedVocabularyList == null)
            {
                return;
            }

            Intent administerTestActivity = new Intent(SelectVocabularyListActivity.this,
                                                       AdministerTestActivity.class);
            startActivity(administerTestActivity);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView)
        {
        }

        private class NoneSelected extends Exception
        {
        }
    }

    private int indexOf(Set<String> items, String itemToMatch)
    {
        int index = -1;
        int i = 0;

        for (Object item : items)
        {
            if (item.equals(itemToMatch))
            {
                index = i;
                break;
            }

            i++;
        }

        return index;
    }
}
