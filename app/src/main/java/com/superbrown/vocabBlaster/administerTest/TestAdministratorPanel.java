package com.superbrown.vocabBlaster.administerTest;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.superbrown.android.util.ResourceUtils;
import com.superbrown.utils.RandomList;
import com.superbrown.vocabBlaster.vocabulary.VocabularyWord;

import java.util.List;

public class TestAdministratorPanel extends TableLayout
{
    private ICompletionHandler completionHandler;

//    private final List<ITestableItem> testableItems;
//    private int numberOfQuestionsAsked = 0;

    public TestAdministratorPanel(Context context, ICompletionHandler completionHandler, List<ITestableItem> testableItems)
    {
        super(context);

        this.setOrientation(HORIZONTAL);

        this.completionHandler = completionHandler;

        List sortedItemsToTest = RandomList.shuffle(testableItems);
        askQuestion(0, sortedItemsToTest);
    }

    private void askQuestion(final int indexOfWord, final List<ITestableItem> testableItems)
    {
        removeAllViews();

        if (testableItems.size() == 0)
        {
            return;
        }

        final ITestableItem testableItem = testableItems.get(indexOfWord);

        final VocabularyTestQuestionPanel testQuestionPanel =
            new MultipleChoiceTestQuestionPanel(
                getContext(),
                (VocabularyWord)testableItem);

        testQuestionPanel.setButtonOnClickListener(new OnClickListener()
        {
            public void onClick(View view)
            {
                handleUserSubmission(testQuestionPanel, testableItems, testableItem, indexOfWord);
            }
        });

        addView(testQuestionPanel);
    }

    private void handleUserSubmission(
            final VocabularyTestQuestionPanel testQuestionPanel,
            final List<ITestableItem> itemsToTest,
            final ITestableItem testableItem,
            final int indexOfItem)
    {
        // If the user didn't input anything, interpret it as a user mistake and forgive him.
        if (!testQuestionPanel.hasQuestionBeenAnswered())
        {
            return;
        }

        testQuestionPanel.getButton().setEnabled(false);

        AnswerResultType answerResultType =
                testQuestionPanel.wasAnswerRight() ? AnswerResultType.CORRECT : AnswerResultType.INCORRECT;

        testQuestionPanel.displayResult(
                answerResultType,
                new ICompletedListener()
                {
                    public void completed()
                    {
                        setUpForNextQuestion(
                                testQuestionPanel,
                                itemsToTest,
                                testableItem,
                                indexOfItem);
                    }
                });

    }

    private void setUpForNextQuestion(VocabularyTestQuestionPanel testQuestionPanel, List<ITestableItem> itemsToTest, ITestableItem spellingWord, int indexOfWord)
    {
        int indexOfNextWord;

        if (spellingWord.getTestingMetric().isTestingComplete())
        {
            itemsToTest.remove(indexOfWord);

            if (itemsToTest.size() == 0)
            {
                setButtonToNextAndWaitForUserInput(testQuestionPanel, 0, itemsToTest);
                return;
            }

            indexOfNextWord = indexOfWord;
        }
        else
        {
            indexOfNextWord = indexOfWord + 1;
        }

        if (indexOfNextWord == itemsToTest.size())
        {
            // start back at the beginning
            indexOfNextWord = 0;

            final int finalIndexOfNextWord = indexOfNextWord;

            shuffleWords(testQuestionPanel, itemsToTest, finalIndexOfNextWord);
        }
        else
        {
            setButtonToNextAndWaitForUserInput(testQuestionPanel, indexOfNextWord, itemsToTest);
        }
    }

    private void shuffleWords(
            VocabularyTestQuestionPanel testQuestionPanel,
            List<ITestableItem> spellingWordsToTest,
            final int finalIndexOfNextWord1)
    {
        setButtonToNextAndWaitForUserInput(
                testQuestionPanel,
                finalIndexOfNextWord1,
                RandomList.shuffle(spellingWordsToTest));
    }

    protected void setButtonToNextAndWaitForUserInput(
            VocabularyTestQuestionPanel testQuestionPanel,
            final int indexOfNextWord,
            final List<ITestableItem> spellingWordsToTest)
    {
        Button nextButton = new Button(getContext());
        nextButton.setText("Next");
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.weight = 1.0f;
        layoutParams.width = ResourceUtils.toLengthInLightOfScreenDensity(getResources(), 150);
        layoutParams.topMargin = ResourceUtils.toLengthInLightOfScreenDensity(getResources(), 5);
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        nextButton.setLayoutParams(layoutParams);

        nextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (spellingWordsToTest.size() == 0) {
                    completionHandler.handleCompletion();
                    return;
                }

                askQuestion(indexOfNextWord, spellingWordsToTest);
            }
        });

        testQuestionPanel.swapOutButton(nextButton);
    }

}