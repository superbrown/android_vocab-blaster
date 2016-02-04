package com.superbrown.vocabBlaster.administerTest;

import java.io.Serializable;

/**
 */
public interface ITestableItem extends ITestable, Serializable
{
    TestingMetric getTestingMetric();

    String getCorrectAnswer();

    void setTestingMetric(TestingMetric testingMetric);

    boolean isGotItOnTheFirstTry();

    void reset(boolean force);
}