package com.superbrown.vocabBlaster.vocabulary;

public class VocabularyWordThatAlsoWorksAsASpellingWord extends VocabularyWord
{
    private Mode currentMode = Mode.VOCABULARY;

    private String phoneticSpelling;
    private String sampleSentenceWithPhoneticSpelling;
    private String fullSentenceWithCorrectSpelling;
    private String rawSampleSentence;

    private enum Mode
    {
        VOCABULARY,
        SPELLING,
    }

    public VocabularyWordThatAlsoWorksAsASpellingWord()
    {
        super();
    }

    public VocabularyWordThatAlsoWorksAsASpellingWord(
            String definition,
            VocabularyList vocabularyList,
            boolean isAProperNoun,
            String sampleSentence,
            String phoneticSpelling)
    {
        super(definition, vocabularyList, isAProperNoun);

        init(correctAnswer, phoneticSpelling, sampleSentence);
    }

    private void init(String correctAnswer, String phoneticSpelling, String sampleSentence)
    {
        this.rawSampleSentence = sampleSentence;

        correctAnswer = correctAnswer.trim();
        phoneticSpelling = phoneticSpelling.trim();
        sampleSentence = sampleSentence.trim();

        if (startsWithAnUppercaseCharacter(correctAnswer))
        {
            phoneticSpelling = capitalize(phoneticSpelling);
        }

        this.fullSentenceWithCorrectSpelling = sampleSentence;

        this.correctAnswer = correctAnswer;
        this.phoneticSpelling = phoneticSpelling;

        String phoneticSpellingWithQuotationMarks = "\"" + phoneticSpelling + "\"";

        String sampleSentenceWithPhoneticSpelling = sampleSentence.replace(
                correctAnswer, phoneticSpellingWithQuotationMarks);

        // capitalize word if it's at the beginning of a sentence
        if (sampleSentenceWithPhoneticSpelling.indexOf(phoneticSpellingWithQuotationMarks) == 0)
        {
            sampleSentenceWithPhoneticSpelling = "\"" +
                    Character.toUpperCase(sampleSentenceWithPhoneticSpelling.charAt(1)) +
                    sampleSentenceWithPhoneticSpelling.substring(2);
        }
        sampleSentenceWithPhoneticSpelling = sampleSentenceWithPhoneticSpelling.replace("\",", ",\"");
        sampleSentenceWithPhoneticSpelling = sampleSentenceWithPhoneticSpelling.replace("\".", ".\"");

        this.sampleSentenceWithPhoneticSpelling = sampleSentenceWithPhoneticSpelling;
    }

    private String capitalize(String string)
    {
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }

    public String getPhoneticSpelling()
    {
        return phoneticSpelling;
    }

    public void setPhoneticSpelling(String phoneticSpelling)
    {
        this.phoneticSpelling = phoneticSpelling;
    }

    public String getSampleSentenceWithPhoneticSpelling()
    {
        return sampleSentenceWithPhoneticSpelling;
    }

    public void setSampleSentenceWithPhoneticSpelling(String sampleSentenceWithPhoneticSpelling)
    {
        this.sampleSentenceWithPhoneticSpelling = sampleSentenceWithPhoneticSpelling;
    }

    public String getFullSentenceWithCorrectSpelling()
    {
        return fullSentenceWithCorrectSpelling;
    }

    public String getRawSampleSentence()
    {
        return rawSampleSentence;
    }

    public boolean wordIsAtTheBeginningOfTheSampleSentence()
    {
        return getRawSampleSentence().startsWith(this.correctAnswer);
    }

    public void switchToSpellingMode()
    {
        currentMode = Mode.SPELLING;
    }

    public void switchToVocabularyMode()
    {
        currentMode = Mode.VOCABULARY;
    }
}