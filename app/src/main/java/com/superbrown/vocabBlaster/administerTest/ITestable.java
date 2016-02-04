package com.superbrown.vocabBlaster.administerTest;

/**
 */
public interface ITestable
{
    boolean isGotItOnTheFirstTry();

    void reset(boolean force);

    String getName();

    void setName(String name);
}
