package com.superbrown.vocabBlaster.administerTest;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.superbrown.vocabBlaster.vocabulary.VocabularyWord;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
*/
public class MultipleChoiceTestQuestionPanel extends VocabularyTestQuestionPanel
{
    private List<String> allWords;
    private StringValueRadioButtonPanel answerChoiceRadioButtonPanel;
    private String correctAnswer;

    public MultipleChoiceTestQuestionPanel(Context context, VocabularyWord vocabularyWord)
    {
        super(context, vocabularyWord);
    }

    @Override
    protected String getUserAnswer() throws NothingSelected
    {
        return answerChoiceRadioButtonPanel.getSelectedValue().trim();
    }

    public void addQuestionToPanel()
    {
        removeAllViews();

        allWords = new ArrayList();
        correctAnswer = getTestableItem().getCorrectAnswer();
        allWords.add(correctAnswer);
        allWords.addAll(getTestableItem().getIncorrectAnswers());
        Collections.sort(allWords);

        View questionTextWidget = createQuestionTextWidget();

        StringValueRadioButtonPanel answerChoiceRadioButtonPanel =
                new StringValueRadioButtonPanel(getContext(), allWords);
        setAnswerChoiceRadioButtonPanel(answerChoiceRadioButtonPanel);

        addView(questionTextWidget);
        addView(answerChoiceRadioButtonPanel);
        addView(createSubmitAnswerButton());
    }

    private void setAnswerChoiceRadioButtonPanel(StringValueRadioButtonPanel panel)
    {
        this.answerChoiceRadioButtonPanel = panel;
    }

    public StringValueRadioButtonPanel getAnswerChoiceRadioButtonPanel()
    {
        return answerChoiceRadioButtonPanel;
    }

    public void highlightCorrectAnswer()
    {
        answerChoiceRadioButtonPanel.highlightSelection(correctAnswer);
    }

    @Override
    public void displayResult(
            final AnswerResultType answerResultType,
            final ICompletedListener completedListener)
    {
        super.displayResult(
                answerResultType,
                new ICompletedListener()
                {
                    public void completed()
                    {
                        if (answerResultType == AnswerResultType.CORRECT)
                        {
                            TextView messageLabel = new TextView(getContext());
                            getVocabBlasterActivity().formatHandwritingOnAChalkboard(messageLabel);
                            messageLabel.setText(getARandomGotTheAnswerCorrectMessage() + " That's correct.");
                            addView(messageLabel, getChildCount() - 1);
                        }
                        else
                        {
                            highlightCorrectAnswer();

                            TextView firstMessage = new TextView(getContext());

                            if (answerResultType == AnswerResultType.INCORRECT)
                            {
                                firstMessage.setText(getARandomIncorrectAnswerMessage());
                            }
                            else if (answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
                            {
                                firstMessage.setText("You ran out of time.");
                            }

                            getVocabBlasterActivity().formatHandwritingOnAChalkboard(firstMessage);
                            firstMessage.setPadding(0, 10, 0, 0);

                            addView(firstMessage, getChildCount() - 1);

                            TextView secondMessage = new TextView(getContext());
                            secondMessage.setText("The correct answer is highlighted.");
                            secondMessage.setTextColor(getVocabBlasterActivity().COLOR_CHALK_GRAY);

                            addView(secondMessage, getChildCount() - 1);
                        }

                        completedListener.completed();
                    }
                });
    }

    @Override
    public boolean hasQuestionBeenAnswered()
    {
        String userAnswer = null;
        try
        {
            userAnswer = getUserAnswer();
        }
        catch (NothingSelected nothingSelected)
        {
            return false;
        }
        return userAnswer != null && !userAnswer.trim().equals("");
    }
}