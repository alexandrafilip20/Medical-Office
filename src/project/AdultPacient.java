package project;

import java.util.ArrayList;

public class AdultPacient extends Pacient
{
    private boolean smoker;

    public AdultPacient(String firstName, String lastName, int age, char sex, String[] diseases, boolean smoker)
    {
        super(firstName, lastName, age, sex, diseases);
        this.smoker = smoker;
    }

    public boolean isSmoker()
    {
        return smoker;
    }

    public void setSmoker(boolean smoker)
    {
        this.smoker = smoker;
    }

    @Override
    public boolean isUnderAge()
    {
        return false;
    }

    @Override
    public String toString()
    {
        if(smoker) return super.toString() +", fumator\n";
        else return super.toString() +", nefumator\n";
    }
}
