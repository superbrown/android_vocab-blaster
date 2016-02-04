package com.superbrown.vocabBlaster.administerTest;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.superbrown.superspell.android.ver2.R;
import com.superbrown.vocabBlaster.VocabBlasterActivity;
import com.superbrown.vocabBlaster.vocabulary.VocabularyWord;

/**
*/
public abstract class VocabularyTestQuestionPanel extends TestQuestionPanel
{
    public VocabBlasterActivity getVocabBlasterActivity() {
        return (VocabBlasterActivity) getContext();
    }

    public VocabularyTestQuestionPanel(Context context,
                                       VocabularyWord vocabularyWord)
    {
        super(context, vocabularyWord);
    }

    protected View createQuestionTextWidget()
    {
        TableLayout panel = new TableLayout(this.getContext());

        VocabularyWord vocabularyWord = (VocabularyWord)getTestableItem();

        TextView label = new TextView(this.getContext());
        label.setText("Pick the word that has the definition below.");
        label.setTextColor(getVocabBlasterActivity().COLOR_CHALK_GRAY);
        label.setPadding(0, 0, 0, 5);

        panel.addView(label);

        String testQuestion = vocabularyWord.getTestQuestion();
        String[] testQuestionLines = testQuestion.split("\n");

        for (String testQuestionLine : testQuestionLines)
        {
            label = new TextView(this.getContext());
            label.setText(testQuestionLine);
            label.setTextColor(getVocabBlasterActivity().COLOR_CHALK_WHITE);
            label.setTextSize(16);
            panel.addView(label);
        }

        return panel;
    }
}
