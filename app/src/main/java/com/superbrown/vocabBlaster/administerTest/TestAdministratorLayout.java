package com.superbrown.vocabBlaster.administerTest;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.superbrown.vocabBlaster.AdministerTestActivity;
import com.superbrown.vocabBlaster.VocabBlasterActivity;
import com.superbrown.vocabBlaster.vocabulary.VocabularyList;
import com.superbrown.vocabBlaster.vocabulary.VocabularyWordTestingMetric;


/**
*/
public class TestAdministratorLayout extends TableLayout implements ICompletionHandler
{
    private VocabularyList vocabularyList;
    private TestAdministratorPanel testAdministratorPanel;

    public TestAdministratorLayout(
            Context context,
            VocabularyList vocabularyList)
    {
        super(context);
        this.vocabularyList = vocabularyList;
        init();
    }

    public void init()
    {
        removeAllViews();

        for (ITestableItem testable : vocabularyList.getTestables())
        {
            VocabularyWordTestingMetric testingMetric = new VocabularyWordTestingMetric();
            testingMetric.setCurrentTestQuestionPanelType(QuestionType.MULTIPLE_CHOICE);
            testable.setTestingMetric(testingMetric);
        }

        testAdministratorPanel = new TestAdministratorPanel(
                getContext(), this, vocabularyList.getTestables());

        addView(testAdministratorPanel);
    }

    public void handleCompletion()
    {
        removeAllViews();

        TextView label = new TextView(this.getContext());
        label.setText("Great work!!");
        getVocabBlasterActivity().formatHandwritingOnAChalkboard(label);
        addView(label);

        label = new TextView(this.getContext());
        label.setText("You have these words pretty well mastered.");
        getVocabBlasterActivity().formatHandwritingOnAChalkboard(label);

        Button button = new Button(this.getContext());
        button.setText("Start Over");
        button.setTextColor(Color.BLACK);

        LayoutParams layoutParams = new LayoutParams();
        layoutParams.weight = 1.0f;
        float density = getResources().getDisplayMetrics().density;
        layoutParams.width = (int)(150 * density);
        layoutParams.topMargin = 60;
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        button.setLayoutParams(layoutParams);

        final AdministerTestActivity vocabBlaster = (AdministerTestActivity) this.getContext();
        button.setOnClickListener(new OnClickListener()
        {
            public void onClick(View view)
            {
                vocabBlaster.reset();
            }
        });

        addView(label);
        addView(button);
    }

    private VocabBlasterActivity getVocabBlasterActivity() {
        return (VocabBlasterActivity) getContext();
    }

    public VocabularyList getVocabularyList()
    {
        return vocabularyList;
    }
}