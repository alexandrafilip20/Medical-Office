package project;

import java.util.PriorityQueue;

public class Doctor
{
    private String lastName;
    private String firstName;
    private int age;
    private String qualification;
    private String bodyPart;
    private boolean isSurgeon;
    private PriorityQueue<Appointment> appointmentsPQ;

    public Doctor(String lastName, String firstName, int age, String qualification, String bodyPart, boolean isSurgeon, PriorityQueue<Appointment> appointmentsPQ)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.qualification = qualification;
        this.bodyPart = bodyPart;
        this.isSurgeon = isSurgeon;
        this.appointmentsPQ = appointmentsPQ;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public String getQualification()
    {
        return qualification;
    }

    public void setQualification(String qualification)
    {
        this.qualification = qualification;
    }

    public String getBodyPart()
    {
        return bodyPart;
    }

    public void setBodyPart(String bodyPart)
    {
        this.bodyPart = bodyPart;
    }

    public boolean isSurgeon()
    {
        return isSurgeon;
    }

    public void setSurgeon(boolean surgeon)
    {
        isSurgeon = surgeon;
    }

    public PriorityQueue<Appointment> getAppointmentsPQ()
    {
        return appointmentsPQ;
    }

    public void setAppointmentsPQ(PriorityQueue<Appointment> appointmentsPQ)
    {
        this.appointmentsPQ = appointmentsPQ;
    }

    @Override
    public String toString()
    {
        if(!appointmentsPQ.isEmpty())
        {
            if(isSurgeon)
                 return lastName + " " + firstName + ", in varsta de " + age + ", medic " + qualification + ", chirurg" + ", cu urmatoarele programari :\n" + appointmentsPQ;
            else
                return lastName + " " + firstName + ", in varsta de " + age + ", medic " + qualification + ", nu este chirurg" + ", cu urmatoarele programari :\n" + appointmentsPQ;
        }
        else
        {
            if(isSurgeon)
                return lastName + " " + firstName + ", in varsta de " + age + ", medic " + qualification + ", chirurg" + ", fara programari";
            else
                return lastName + " " + firstName + ", in varsta de " + age + ", medic " + qualification + ", nu este chirurg" + ", fara programari";
        }
    }
}
