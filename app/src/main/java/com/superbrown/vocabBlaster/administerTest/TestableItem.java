package com.superbrown.vocabBlaster.administerTest;

import java.io.Serializable;
import java.util.Set;

/**
 */
public abstract class TestableItem implements ITestableItem, Serializable
{
    protected TestingMetric testingMetric;
    protected String correctAnswer;

    public TestableItem(TestingMetric testingMetric)
    {
        this.testingMetric = testingMetric;
        testingMetric.setMyTestableItem(this);
    }

    public TestableItem()
    {
    }

    public TestingMetric getTestingMetric()
    {
        return testingMetric;
    }

    public String getCorrectAnswer()
    {
        return correctAnswer;
    }

    public abstract Set<String> getIncorrectAnswers();

    public void setTestingMetric(TestingMetric testingMetric)
    {
        this.testingMetric = testingMetric;
    }

    public boolean isGotItOnTheFirstTry()
    {
        return getTestingMetric().isGotItOnTheFirstTry();
    }

    public void reset(boolean force)
    {
        getTestingMetric().reset(force);
       
    }

    public String getName()
    {
        // default is to return the correct answer
        return getCorrectAnswer();
    }

    public void setName(String name)
    {
        // default is to do nothing
    }
}
