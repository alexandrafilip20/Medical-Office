package project;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class Pacient
{
    private String lastName;
    private String firstName;
    private int age;
    private char sex;
    protected String[] diseases;

    //private ArrayList<Appointment> appointments;

    public Pacient(String lastName, String firstName, int age, char sex, String[] diseases)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.sex = sex;
        this.diseases = diseases;
        //this.appointments = appointments;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String[] getDiseases()
    {
        return diseases;
    }

    public void setDiseases(String[] diseases) {
        this.diseases = diseases;
    }

//    public ArrayList<Appointment> getAppointments()
//    {
//        return appointments;
//    }
//
//    public void setAppointments(ArrayList<Appointment> appointments)
//    {
//        this.appointments = appointments;
//    }

//    @Override
//    public String toString()
//    {
//        if(sex == 'M')
//            return "Pacient " + lastName + " " + firstName + ", varsta " + age + ", sex Masculin" + ", afectiuni : " + Arrays.toString(diseases) + ", cu urmatoarele programari :\n"
//                  + appointments;
//        else
//            return "Pacienta " + lastName + " " + firstName + ", varsta " + age + ", sex Feminin" + ", afectiuni : " + Arrays.toString(diseases) + ", cu urmatoarele programari :\n"
//                + appointments;
//    }

    public abstract boolean isUnderAge();
}

