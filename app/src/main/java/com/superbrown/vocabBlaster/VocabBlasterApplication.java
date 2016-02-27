package com.superbrown.vocabBlaster;

import android.app.Application;

import com.superbrown.vocabBlaster.soundPalettes.SoundPalette_GomerPyle;
import com.superbrown.vocabBlaster.soundPalettes.SoundPalette_ThreeStooges;
import com.superbrown.vocabBlaster.utils.audio.SoundPalette;
import com.superbrown.vocabBlaster.vocabulary.GradeLevel;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class VocabBlasterApplication extends Application implements Serializable  {
    public enum SoundPaletteName {
        SILENT,
        THREE_STOOGES,
        GOMER_PYLE;
    }

    public VocabBlasterAppState appState;

    public Map<String, GradeLevel> grades;
    public Map<SoundPaletteName, SoundPalette> soundPaletteInventory;

    public VocabBlasterApplication() {

        appState = new VocabBlasterAppState();
        initializeVocabularyLists();
        initializeSoundPalettes();
    }

    protected void initializeVocabularyLists() {

        grades = new LinkedHashMap();

        GradeLevel gradeLevel = new GradeLevel("6th Grade");
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson01("Lesson 01"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson02("Lesson 02"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson03("Lesson 03"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson04("Lesson 04"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson05("Lesson 05"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson06("Lesson 06"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson07("Lesson 07"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson08("Lesson 08"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson09("Lesson 09"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson10("Lesson 10"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson11("Lesson 11"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson12("Lesson 12"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson13("Lesson 13"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson14("Lesson 14"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson15("Lesson 15"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson16("Lesson 16"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson17("Lesson 17"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson18("Lesson 18"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson19("Lesson 19"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson20("Lesson 20"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson21("Lesson 21"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson22("Lesson 22"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson23("Lesson 23"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_Lesson24("Lesson 24"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_WordMasterI("WordMaster I"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade6.VocabularyList_WordMasterII("WordMaster II"));

        grades.put(gradeLevel.getName(), gradeLevel);

        gradeLevel = new GradeLevel("7th Grade");
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson01("Lesson 01"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson02("Lesson 02"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson03("Lesson 03"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson01Thru03Review("Lesson Review 01 - 03"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson04("Lesson 04"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson05("Lesson 05"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson06("Lesson 06"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson04Thru06Review("Lesson Review 04 - 06"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson07("Lesson 07"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson08("Lesson 08"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson09("Lesson 09"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson07Thru09Review("Lesson Review 07 - 09"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson10("Lesson 10"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson11("Lesson 11"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson12("Lesson 12"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson10Thru12Review("Lesson Review 10 - 12"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson13("Lesson 13"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson14("Lesson 14"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson15("Lesson 15"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson13Thru15Review("Lesson Review 13 - 15"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson16("Lesson 16"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson17("Lesson 17"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson18("Lesson 18"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson19Thru21Review("Lesson Review 19 - 21"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson19("Lesson 19"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson20("Lesson 20"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson21("Lesson 21"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_Lesson16Thru18Review("Lesson Review 16 - 18"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_WordMaster01("WordMaster I"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_WordMaster02("WordMaster II"));
        gradeLevel.addVocabularyList(new com.superbrown.vocabBlaster.vocabulary.vocabularyLists.grade7.VocabularyList_WordMaster03("WordMaster III"));

        grades.put(gradeLevel.getName(), gradeLevel);
    }

    public VocabBlasterAppState getAppState() {
        return appState;
    }

    public void setAppState(VocabBlasterAppState appState) {
        this.appState = appState;
    }

    public void reset() {

        if (this.appState == null)
        {
            this.appState = new VocabBlasterAppState();
        }
        else
        {
            this.appState.reset();
        }
    }

    private void initializeSoundPalettes() {

        soundPaletteInventory = new HashMap<>();
        soundPaletteInventory.put(SoundPaletteName.SILENT, new SoundPalette());
        soundPaletteInventory.put(SoundPaletteName.THREE_STOOGES, new SoundPalette_ThreeStooges(this));
        soundPaletteInventory.put(SoundPaletteName.GOMER_PYLE, new SoundPalette_GomerPyle(this));

        setSoundPalette(SoundPaletteName.SILENT);
    }

    public SoundPalette getSoundPalette() {

        SoundPaletteName soundPaletteName = getAppState().getSoundPaletteName();
        return soundPaletteInventory.get(soundPaletteName);
    }

    private void setSoundPalette(SoundPaletteName soundPaletteName) {

        getAppState().setSoundPaletteName(soundPaletteName);
    }
}
