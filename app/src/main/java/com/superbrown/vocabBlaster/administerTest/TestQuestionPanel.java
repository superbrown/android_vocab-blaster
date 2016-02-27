package com.superbrown.vocabBlaster.administerTest;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import com.superbrown.android.util.ResourceUtils;
import com.superbrown.vocabBlaster.utils.audio.SoundPalette;
import com.superbrown.utils.RandomList;
import com.superbrown.vocabBlaster.VocabBlasterActivity;

/**
*/
public abstract class TestQuestionPanel extends TableLayout
{
    protected TestableItem testableItem;
    protected Button button;
//    protected KeyPressHandler keyPressHandler;
    protected OnClickListener buttonOnClickListener;


    private static RandomList gotTheAnswerCorrectMessages = new RandomList(new String[]
        {
            "Yes!",
            "Great job!",
            "Terrific!",
            "Cool!",
            "Excellent!",
            "Rock 'n' Roll!",
            "F.A.B.!",
            "Exactly!",
            "Way to go!",
            "Very good!",
            "Yey!",
            "You rock!",
            "Woo-whoo!"
        });

    protected static RandomList incorrectAnswerMessages = new RandomList(new String[]
        {
            "No.",
            "Incorrect.",
            "You knucklehead!",
            "You goof-ball!",
            "Really??",
            "You think so??",
            "I thought you knew this!",
            "Whoops!",
            "=o("
        });

    public TestQuestionPanel(Context context,
                             TestableItem testableItem)
    {
        super(context);
        this.testableItem = testableItem;
        addQuestionToPanel();
    }

    public abstract void addQuestionToPanel();

    public TestableItem getTestableItem()
    {
        return testableItem;
    }

    public boolean wasAnswerRight()
    {
        try
        {
            return getUserAnswer().equals(getCorrectAnswer());
        }
        catch (NothingSelected nothingSelected)
        {
            return false;
        }
    }

    public String getCorrectAnswer()
    {
        return testableItem.getCorrectAnswer();
    }

    protected abstract String getUserAnswer() throws NothingSelected;

    public Button getButton()
    {
        return button;
    }

    public void setButton(Button button)
    {
        this.button = button;

        if (buttonOnClickListener != null)
        {
            button.setOnClickListener(buttonOnClickListener);
        }
    }

    public void setButtonOnClickListener(OnClickListener buttonOnClickListener)
    {
        this.buttonOnClickListener = buttonOnClickListener;

        if (button != null)
        {
            button.setOnClickListener(buttonOnClickListener);
        }
    }

    public void swapOutButton(Button newButton)
    {
        ViewGroup parent = (ViewGroup) button.getParent();
        parent.removeView(button);
        parent.addView(newButton);
        button = newButton;
    }

//    public void setKeyPressHandler(KeyPressHandler keyPressHandler)
//    {
//        this.keyPressHandler = keyPressHandler;
//    }

    protected View createSubmitAnswerButton()
    {
        Button button = new Button(this.getContext());
        button.setText("Submit Answer");
        button.setSoundEffectsEnabled(true);
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.weight = 1.0f;
        layoutParams.width = ResourceUtils.toLengthInLightOfScreenDensity(getResources(), 150);
        layoutParams.topMargin = ResourceUtils.toLengthInLightOfScreenDensity(getResources(), 5);
        layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        layoutParams.gravity = Gravity.CENTER_HORIZONTAL;
        button.setLayoutParams(layoutParams);
        setButton(button);

        return button;
    }

    public VocabBlasterActivity getVocabBlasterActivity() {
        return (VocabBlasterActivity) getContext();
    }

    protected abstract View createQuestionTextWidget();

    public abstract boolean hasQuestionBeenAnswered();

    protected String getARandomGotTheAnswerCorrectMessage()
    {
        return (String) gotTheAnswerCorrectMessages.getNextElement();
    }

    protected String getARandomIncorrectAnswerMessage()
    {
        return (String) incorrectAnswerMessages.getNextElement();
    }

    public void displayResult(AnswerResultType answerResultType, ICompletedListener completedListener)
    {
        if (answerResultType == AnswerResultType.CORRECT)
        {
            testableItem.getTestingMetric().incrementSuccessCounter();
        }
        else if (answerResultType == AnswerResultType.INCORRECT ||
                 answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
        {
            testableItem.getTestingMetric().incrementFailureCounter();
        }

        playResultSound(answerResultType);

        completedListener.completed();
    }

    private void playResultSound(AnswerResultType answerResultType)
    {
        try
        {
            SoundPalette soundPalette = getVocabBlasterActivity().getSoundPalette();

            if (answerResultType == AnswerResultType.CORRECT)
            {
                soundPalette.playSuccessSound();
            }
            else if (answerResultType == AnswerResultType.INCORRECT)
            {
    //            SuperSpell.flashColorsOnScreen(completedListener);
                soundPalette.playFailureSound();
            }
            else if (answerResultType == AnswerResultType.USER_RAN_OUT_OF_TIME)
            {
    //            SuperSpell.flashColorsOnScreen(completedListener);
                soundPalette.playFailureSound();
            }

        }
        catch (Throwable e)
        {
            e.printStackTrace();
            // ignore, not the end of the world
        }
    }
}
