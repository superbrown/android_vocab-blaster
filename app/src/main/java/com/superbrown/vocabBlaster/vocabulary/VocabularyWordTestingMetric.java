package com.superbrown.vocabBlaster.vocabulary;

import com.superbrown.vocabBlaster.administerTest.TestingMetric;
import com.superbrown.vocabBlaster.administerTest.QuestionType;

import java.io.Serializable;


public class VocabularyWordTestingMetric extends TestingMetric implements Serializable
{
    public VocabularyWordTestingMetric()
    {
        super();
    }

    protected void init()
    {
        super.init();
        setNumberOfRequiredSuccesses(1);
        // the default
        setCurrentTestQuestionPanelType(QuestionType.MULTIPLE_CHOICE);
    }

    public void incrementSuccessCounter()
    {
        successCount++;

        if (successCount == getNumberOfRequiredSuccesses())
        {
            setTestingComplete(true);
        }
    }

    public void incrementFailureCounter()
    {
        failureCount++;
        setNumberOfRequiredSuccesses(2);
    }
}