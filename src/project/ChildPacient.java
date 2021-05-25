package project;

import java.util.ArrayList;

public class ChildPacient extends Pacient
{
    private String parentName;

    public ChildPacient(String firstName, String lastName, int age, char sex, String[] diseases, String parentName)
    {
        super(firstName, lastName, age, sex, diseases);
        this.parentName = parentName;
    }

    public String getParentName()
    {
        return parentName;
    }

    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    @Override
    public String toString()
    {
        return super.toString() + ", nume parinte : " + parentName + "\n";
    }

    @Override
    public boolean isUnderAge()
    {
        return true;
    }
}
