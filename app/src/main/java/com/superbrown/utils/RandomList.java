package com.superbrown.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 */
public class RandomList
{
    private List elements = new ArrayList();
    private List previousElements = new ArrayList();
    private Random random = new Random(System.currentTimeMillis());


    public RandomList(List elements)
    {
        this.elements.addAll(elements);
    }

    public RandomList(Object[] elements)
    {
        this.elements = new ArrayList();
        for (Object element : elements)
        {
            this.elements.add(element);
        }
    }

    public Object getNextElement()
    {
        if (hasUsedAllTheStrings())
        {
            elements.addAll(previousElements);
            previousElements.clear();
        }

        int randomIndex = random.nextInt(elements.size());
        Object newElement = elements.get(randomIndex);

        previousElements.add(newElement);
        elements.remove(newElement);

        return newElement;
    }

    public boolean hasUsedAllTheStrings()
    {
        return elements.size() == 0;
    }

    public static List shuffle(List elements)
    {
        RandomList shuffler = new RandomList(elements);
        List shuffledList = new ArrayList();

        do
        {
            shuffledList.add(shuffler.getNextElement());
        }
        while (!shuffler.hasUsedAllTheStrings());

        return shuffledList;
    }
}
