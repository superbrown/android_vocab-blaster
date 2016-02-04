package com.superbrown.vocabBlaster.administerTest;


import java.util.ArrayList;
import java.util.List;


public class TestableItemList implements ITestable
{
    private String name;

    protected List<ITestableItem> testables;

    public TestableItemList(String name)
    {
        this.name = name;
        testables = new ArrayList<ITestableItem>();
    }

    public TestableItemList()
    {
        this("");
    }

    public List<ITestableItem> getTestables()
    {
        return testables;
    }

    @Override
    public String toString()
    {
        String string = "";
        for (ITestable testable : testables)
        {
            if (testable instanceof ITestableItem)
            {
                string += ((ITestableItem)testable).getCorrectAnswer();

                if (testable != testables.get(testables.size() - 1))
                {
                    string += ", ";
                }
            }
            else
            {
                string += testable.toString();

                if (testable != testables.get(testables.size() - 1))
                {
                    string += ", ";
                }
            }
        }

        return string;
    }

    public boolean isGotItOnTheFirstTry()
    {
        for (ITestable testableItem : testables)
        {
            if (!testableItem.isGotItOnTheFirstTry())
            {
                return false;
            }
        }

        return true;
    }

    public void reset(boolean force)
    {
        for (ITestable testableItem : testables)
        {
            testableItem.reset(force);
        }
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
