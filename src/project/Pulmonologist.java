package project;

import java.util.PriorityQueue;

public class Pulmonologist extends Doctor
{
    private String bestTreatment;

    public Pulmonologist(String firstName, String lastName, int age, String qualification, String bodyPart, boolean isSurgeon, PriorityQueue<Appointment> appointmentsPQ, String bestTreatment)
    {
        super(firstName, lastName, age, qualification, bodyPart, isSurgeon, appointmentsPQ);
        this.bestTreatment = bestTreatment;
    }

    public String getBestTreatment()
    {
        return bestTreatment;
    }



    public void setBestTreatment(String bestTreatment)
    {
        this.bestTreatment = bestTreatment;
    }

    @Override
    public String toString()
    {
        return "Pulmolog " + super.toString() + ", cu tratamentul: " + bestTreatment + "\n";
    }
}
