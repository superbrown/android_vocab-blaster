package com.superbrown.vocabBlaster.vocabulary;

import com.superbrown.vocabBlaster.administerTest.TestingMetric;
import com.superbrown.vocabBlaster.administerTest.TestableItem;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class VocabularyWord extends TestableItem implements Serializable
{
    protected String definition;
    protected VocabularyList vocabularyList;
    protected String testQuestion;
    protected boolean isAProperNoun;

    public VocabularyWord()
    {
        super(new VocabularyWordTestingMetric());
    }

    @Override
    public Set<String> getIncorrectAnswers()
    {
        Set<String> incorrectAnswers =
                new HashSet<String>(vocabularyList.getAllWords());
        incorrectAnswers.remove(correctAnswer);
        return incorrectAnswers;
    }

    public VocabularyWord(String definition, VocabularyList vocabularyList, boolean isAProperNoun)
    {
        this();

        this.definition = definition.trim();
        this.vocabularyList = vocabularyList;
        this.isAProperNoun = isAProperNoun;

        String theWord = extractTheWord(definition, isAProperNoun);

        this.correctAnswer = theWord;
        this.testQuestion = definition.replace("|" + extractTheTextBetweenTheBars(definition) + "| ", "").trim();
    }

    public static String extractTheWord(String definition, boolean isAProperNoun)
    {
        String textBetweenBars = extractTheTextBetweenTheBars(definition);

        String theWord;

        if (isAProperNoun)
        {
            theWord = capitalize(textBetweenBars);
        }
        else
        {
            theWord = textBetweenBars.toLowerCase();
        }
        return theWord;
    }

    private static String extractTheTextBetweenTheBars(String definition)
    {
        int indexOfFirstBar = definition.indexOf("|");
        int indexOfSecondBar = definition.indexOf("|", indexOfFirstBar + 1);
        return definition.substring(indexOfFirstBar + 1, indexOfSecondBar).trim();
    }

    private static String capitalize(String string)
    {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public boolean startsWithAnUppercaseCharacter(String correctSpelling)
    {
        return Character.isUpperCase(correctSpelling.charAt(0));
    }

    public String getDefinition()
    {
        return definition;
    }

    public void setDefinition(String definition)
    {
        this.definition = definition;
    }

    public TestingMetric getTestMetric()
    {
        return testingMetric;
    }

    public void setTestMetric(TestingMetric testingMetric)
    {
        this.testingMetric = testingMetric;
    }

    public String getTestQuestion()
    {
        return testQuestion;
    }

    public boolean isAProperNoun()
    {
        return isAProperNoun;
    }

    public boolean wordIsAtTheBeginningOfTheTestQuestion()
    {
        return this.getTestQuestion().startsWith("__");
    }

    public void setVocabularyList(VocabularyList vocabularyList)
    {
        this.vocabularyList = vocabularyList;
    }
}