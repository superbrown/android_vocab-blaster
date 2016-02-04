package com.superbrown.vocabBlaster.administerTest;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TableLayout;

import com.superbrown.superspell.android.ver2.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class StringValueRadioButtonPanel extends RadioGroup
{
    public static final String HIGHLIGHT_COLOR = "#C7CB34";
    private boolean hasTwoColumns;
    Map<Integer, RadioButton> radioButtons = new HashMap<Integer, RadioButton>();


    public StringValueRadioButtonPanel(Context context, List<String> values)
    {
        super(context);

        this.setOrientation(HORIZONTAL);
        this.setMinimumHeight(150);

        // If there are more than 10 choices, split it up into two columns
        hasTwoColumns = values.size() > 5;

        if (hasTwoColumns)
        {
            TableLayout tableLayout01 = new TableLayout(getContext());
            addView(tableLayout01);

            TableLayout tableLayout02 = new TableLayout(getContext());
            tableLayout02.setPadding(10, 0, 0, 0);
            addView(tableLayout02);

            TableLayout[] verticalPanels = new TableLayout[] {tableLayout01, tableLayout02};

            int halfTheSize = values.size() / 2;

            int index = 0;
            for (String value : values)
            {
                int indexOfPanelToAddTo;
                if (index < halfTheSize)
                {
                    indexOfPanelToAddTo = 0;
                }
                else
                {
                    indexOfPanelToAddTo = 1;
                }

                RadioButton radioButton = new RadioButton(this.getContext());
                radioButton.setText(value);
                radioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.chalk_white));
                radioButton.setId(index);
                radioButton.setSoundEffectsEnabled(true);
                radioButton.setPadding(0, 0, 3, 0);
                radioButton.setOnClickListener(new OnClickListener()
                {
                    public void onClick(View clickecButton)
                    {
                        for (RadioButton radioButton : radioButtons.values())
                        {
                            if (radioButton != clickecButton)
                            {
                                // uncheck other buttons
                                if (radioButton.isChecked())
                                {
                                    radioButton.setChecked(false);
                                }
                            }
                        }

                    }
                });

                verticalPanels[indexOfPanelToAddTo].addView(radioButton);
                radioButtons.put(index, radioButton);

                index++;
            }
        }
        else
        {
            TableLayout verticalPanel = new TableLayout(getContext());
            addView(verticalPanel);

            int index = 0;
            for (String value : values)
            {
                RadioButton radioButton = new RadioButton(this.getContext());
                radioButton.setText(value);
                radioButton.setTextColor(ContextCompat.getColor(getContext(), R.color.chalk_white));
                verticalPanel.addView(radioButton);
                radioButtons.put(index, radioButton);
                index++;
            }
        }

        for (RadioButton radioButtonAndStringValue : radioButtons.values())
        {
            // TODO
            //radioButton.addStyleName(this.textStyle);
        }
    }

    public String getSelectedValue() throws NothingSelected
    {
        RadioButton selectedButton = getSelectedRadioButton();

        if (selectedButton == null)
        {
            throw new NothingSelected();
        }
        else
        {
            return selectedButton.getText().toString();
        }
    }

    public RadioButton getSelectedRadioButton()
    {
        for (RadioButton radioButton : radioButtons.values())
        {
            String text = radioButton.getText().toString();
            
            if (radioButton.isChecked())
            {
                return radioButton;
            }
        }

        return null;
    }

    public void highlightSelection(String correctAnswer)
    {
        for (RadioButton radioButton : this.radioButtons.values())
        {
            if (radioButton.getText().equals(correctAnswer))
            {
                radioButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.chalk_yellow));
                radioButton.setTextColor(Color.BLACK);
                break;
            }
        }
    }
}
