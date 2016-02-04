package com.superbrown.vocabBlaster.vocabulary;

import com.superbrown.vocabBlaster.administerTest.ITestable;
import com.superbrown.vocabBlaster.administerTest.ITestableItem;
import com.superbrown.vocabBlaster.administerTest.TestableItemList;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 */
public abstract class VocabularyList extends TestableItemList implements Serializable
{
    public VocabularyList()
    {
        this("");
    }

    public VocabularyList(String optionalTitle)
    {
        super(optionalTitle);
    }

    protected void addVocabularyWordToInventory(String sampleSentence, boolean isAProperNoun)
    {
        VocabularyWord vocabularyWord = new VocabularyWord(sampleSentence, this, isAProperNoun);
        addVocabularyWordToInventory(vocabularyWord);
    }

    public void addVocabularyWordToInventory(VocabularyWord vocabularyWord)
    {
        testables.add(vocabularyWord);
        vocabularyWord.setVocabularyList(this);
    }

    protected void addVocabularyWordToInventory(String sampleSentence)
    {
        addVocabularyWordToInventory(sampleSentence, false);
    }

    protected void addVocabularyWordToInventory(String definition, String sampleSentence, String phoneticSpelling)
    {
        String word = extractWordFromDefinition(definition);

        VocabularyWord vocabularyWord = new VocabularyWordThatAlsoWorksAsASpellingWord(
                definition,
                this,
                false,
                sampleSentence,
                phoneticSpelling
        );

        addVocabularyWordToInventory(vocabularyWord);
    }


    public Set<String> getAllWords()
    {
        Set<String> allWords = new HashSet<String>();

        for (ITestable vocabularyWord : testables)
        {
            allWords.add(((ITestableItem)vocabularyWord).getCorrectAnswer());
        }

        return allWords;
    }

    public void shuffle()
    {
        Collections.shuffle(testables);
    }

    public static String extractWordFromDefinition(String definition)
    {
        int firstIndex = definition.indexOf("|");
        int secondIndex = definition.indexOf("|", firstIndex + 1);
        String correctAnswer = definition.substring(firstIndex + 1, secondIndex);
        return correctAnswer;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}