package project;

import java.util.PriorityQueue;

public class Dermatologist extends Doctor
{
    private int treatedCases;

    public Dermatologist(String firstName, String lastName, int age, String qualification, String bodyPart, boolean isSurgeon, int treatedCases)
    {
        super(firstName, lastName, age, qualification, bodyPart, isSurgeon);
        this.treatedCases = treatedCases;
    }

    public int getTreatedCases()
    {
        return treatedCases;
    }

    public void setTreatedCases(int treatedCases)
    {
        this.treatedCases = treatedCases;
    }

    @Override
    public String toString()
    {
        return "Dermatolog " + super.toString() + ", numar de cazuri tratate: " + treatedCases  + "\n";
    }
}
