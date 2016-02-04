package com.superbrown.vocabBlaster.vocabulary;

import java.io.Serializable;
import java.util.*;

/**
 */
public class GradeLevel implements Serializable
{
    private String name;
    private Map<String, VocabularyList> vocabularyLists = new LinkedHashMap<String, VocabularyList>();

    public GradeLevel(String name)
    {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Collection<VocabularyList> getValues()
    {
        return vocabularyLists.values();
    }

    public VocabularyList getVocabularyList(String name)
    {
        return vocabularyLists.get(name);
    }

    public void addVocabularyList(VocabularyList vocabularyList)
    {
        vocabularyLists.put(vocabularyList.getName(), vocabularyList);
    }

    @Override
    public String toString()
    {
        return getName();
    }
}