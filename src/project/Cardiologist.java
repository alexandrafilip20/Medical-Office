package project;

import java.util.PriorityQueue;

public class Cardiologist extends Doctor
{
    private String recommendation;

    public Cardiologist(String firstName, String lastName, int age, String qualification, String bodyPart, boolean isSurgeon, PriorityQueue<Appointment> appointmentsPQ, String recommendations)
    {
        super(firstName, lastName, age, qualification, bodyPart, isSurgeon, appointmentsPQ);
        this.recommendation = recommendations;
    }

    public String getRecommentdations()
    {
        return recommendation;
    }

    public void setRecommentdations(String recommentdations)
    {
        this.recommendation = recommentdations;
    }

    @Override
    public String toString()
    {
        return "Cardiolog " + super.toString() + ", cu recomandarile: " + recommendation  + "\n";
    }
}
