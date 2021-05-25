package project;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReaderWriter
{
    private static ReaderWriter instance = null;

    ReaderWriter(){}

    public static ReaderWriter getInstance() {
        if (instance == null)
            instance = new ReaderWriter();
        return instance;
    }
    public ArrayList<ArrayList<String>> readCSVFile(String path)
    {
        ArrayList<ArrayList<String>> objects = new ArrayList<>();

        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);

            String k;
            k = scanner.nextLine();

            while (k != null) {
                ArrayList<String> current_obj = new ArrayList<>();
                current_obj.addAll(Arrays.asList(k.split(",")));
                objects.add(current_obj);

                if (scanner.hasNextLine()) {
                    k = scanner.nextLine();
                } else {
                    k = null;
                }

            }
        }catch (IOException e) {
            System.out.println("An error occurred while reading CSV file");
            e.printStackTrace();
        }

        return objects;
    }
//
//    public void writeCSVFile(String path, String string) {
//
//        try {
//            File file = new File(path);
//            if(!file.exists())
//                file.createNewFile();
//
//            FileWriter writer = new FileWriter(file);
//            writer.write(string);
//            writer.flush();
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//    }
//
    public void readChild(DoctorOffice doctorOffice)
    {
        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/childPacient.csv";

        ArrayList<ArrayList<String>> childList = readCSVFile(path);

        for(ArrayList<String> child : childList)
        {
            String lastName = child.get(0);
            String firstName = child.get(1);
            int age = Integer.parseInt(child.get(2).trim());
            char sex = child.get(3).charAt(0);
            String[] diseases = child.get(4).split("-");
            String parentName = child.get(5);

            ChildPacient c = new ChildPacient(lastName, firstName, age, sex, diseases, parentName);
            doctorOffice.getPacients().add(c);
        }

    }

    public void readAdult(DoctorOffice doctorOffice)
    {
        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/adultPacient.csv";

        ArrayList<ArrayList<String>> adultList = readCSVFile(path);

        for(ArrayList<String> adult : adultList)
        {
            String lastName = adult.get(0);
            String firstName = adult.get(1);
            int age = Integer.parseInt(adult.get(2).trim());
            char sex = adult.get(3).charAt(0);
            String[] diseases = adult.get(4).split("-");
            boolean smoker = Boolean.parseBoolean(adult.get(5));

            AdultPacient a = new AdultPacient(lastName, firstName, age, sex, diseases, smoker);
            doctorOffice.getPacients().add(a);
        }

    }
//
//    public void readDoctor(DoctorOffice doctorOffice)
//    {
//        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/doctor.csv";
//
//        ArrayList<ArrayList<String>> doctorList = readCSVFile(path);
//        for(ArrayList<String> doc : doctorList)
//        {
//            String lastName = doc.get(0);
//            String firstName = doc.get(1);
//            int age = Integer.parseInt(doc.get(2));
//            String qualification = doc.get(3);
//            String bodyPart = doc.get(4);
//            boolean isSurgeon = Boolean.parseBoolean(doc.get(5));
//            if(bodyPart.equals("plamani"))
//            {
//                String bestTreatment = doc.get(6);
//                Pulmonologist p = new Pulmonologist(lastName, firstName, age, qualification, bodyPart, isSurgeon,bestTreatment);
//                doctorOffice.getDoctors().add(p);
//            }
//            else if(bodyPart.equals("piele"))
//            {
//                int treatedCases = Integer.parseInt(doc.get(6));
//                Dermatologist d = new Dermatologist(lastName, firstName, age, qualification, bodyPart, isSurgeon,treatedCases);
//                doctorOffice.getDoctors().add(d);
//            }
//            else  if(bodyPart.equals("inima"))
//            {
//                String recommendation = doc.get(6);
//                Cardiologist c = new Cardiologist(lastName, firstName, age, qualification, bodyPart, isSurgeon,recommendation);
//                doctorOffice.getDoctors().add(c);
//            }
//        }
//
//    }
//
//    public void readAppointment(DoctorOffice doctorOffice) throws ParseException
//    {
//        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/appointment.csv";
//
//        ArrayList<ArrayList<String>> appointmentList = readCSVFile(path);
//
//        for(ArrayList<String> appointment : appointmentList)
//        {
//            //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//            String pacientLastName = appointment.get(0);
//            String pacientFirstName = appointment.get(1);
//
//            String doctorLastName = appointment.get(2) ;
//            String doctorFirstName = appointment.get(3) ;
//
//            int hour = Integer.parseInt(appointment.get(4));
//            String d = appointment.get(5);
//            //Date date = format.parse(d);
//            String reason = appointment.get(6);
//            double price = Double.parseDouble(appointment.get(7));
//
//            Appointment a = new Appointment(pacientLastName, pacientFirstName, doctorLastName, doctorFirstName, hour, d, reason, price);
//            doctorOffice.getAppointments().add(a);
//        }
//
//    }
//
//    public void writeChildPacient(DoctorOffice doctorOffice)
//    {
//        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/childPacient.csv";
//
//        StringBuilder pacients = new StringBuilder();
//        for(Pacient p : doctorOffice.getPacients())
//        {
//            if(p.isUnderAge())
//            {
//                ChildPacient c = (ChildPacient) p;
//                pacients.append(c.getLastName()).append(",");
//                pacients.append(c.getFirstName()).append(",");
//                pacients.append(c.getAge()).append(",");
//
//                pacients.append(c.getSex()).append(",");
//                StringBuilder dis = new StringBuilder();
//                dis.append(c.getDiseases()[0]);
//                for(int i=1;i< c.getDiseases().length;i++)
//                {
//                    dis.append("-").append(c.getDiseases()[i]);
//                }
//                //System.out.println(dis);
//                pacients.append(dis).append(",");
//                pacients.append(c.getParentName());
//                pacients.append("\n");
//            }
//        }
//
//        writeCSVFile(path, pacients.toString());
//    }
//
//    public void writeAdultPacient(DoctorOffice doctorOffice)
//    {
//        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/adultPacient.csv";
//
//        StringBuilder pacients = new StringBuilder();
//        for(Pacient p : doctorOffice.getPacients())
//        {
//            if(!p.isUnderAge())
//            {
//                AdultPacient c = (AdultPacient) p;
//                pacients.append(c.getLastName()).append(",");
//                pacients.append(c.getFirstName()).append(",");
//                pacients.append(c.getAge()).append(",");
//
//                pacients.append(c.getSex()).append(",");
//                StringBuilder dis = new StringBuilder();
//                dis.append(c.getDiseases()[0]);
//                for(int i=1;i< c.getDiseases().length;i++)
//                {
//                    dis.append("-").append(c.getDiseases()[i]);
//                }
//                pacients.append(dis).append(",");
//                pacients.append(c.isSmoker());
//                pacients.append("\n");
//            }
//        }
//
//        writeCSVFile(path, pacients.toString());
//    }
//
//    public void writeDoctor(DoctorOffice doctorOffice)
//    {
//        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/doctor.csv";
//
//        StringBuilder doctors = new StringBuilder();
//        for(Doctor d : doctorOffice.getDoctors())
//        {
//            doctors.append(d.getLastName()).append(",");
//            doctors.append(d.getFirstName()).append(",");
//            doctors.append(d.getAge()).append(",");
//            doctors.append(d.getQualification()).append(",");
//            doctors.append(d.getBodyPart()).append(",");
//            doctors.append(d.isSurgeon()).append(",");
//            if(d.getBodyPart().equals("inima"))
//            {
//                Cardiologist c = (Cardiologist) d;
//                doctors.append(c.getRecommentdations()).append("\n");
//            }
//            else if(d.getBodyPart().equals("piele"))
//            {
//                Dermatologist c = (Dermatologist) d;
//                doctors.append(c.getTreatedCases()).append("\n");
//            }
//            else if(d.getBodyPart().equals("plamani"))
//            {
//                Pulmonologist c = (Pulmonologist) d;
//                doctors.append(c.getBestTreatment()).append("\n");
//            }
//        }
//
//        writeCSVFile(path, doctors.toString());
//    }
//
//    public void writeAppointment(DoctorOffice doctorOffice)
//    {
//        String path = "C:/Users/alexa/Desktop/PAO/CabinetMedical/src/files/appointment.csv";
//
//        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
//
//        StringBuilder appointments = new StringBuilder();
//        for(Appointment a : doctorOffice.getAppointments())
//        {
//            appointments.append(a.getPacientLastName()).append(",");
//            appointments.append(a.getPacientFirstName()).append(",");
//            appointments.append(a.getDoctorLastName()).append(",");
//            appointments.append(a.getDoctorFirstName()).append(",");
//            appointments.append(a.getHour()).append(",");
//            appointments.append(format.format(a.getDate())).append(",");
//            appointments.append(a.getReason()).append(",");
//            appointments.append(a.getPrice()).append("\n");
//        }
//
//        writeCSVFile(path, appointments.toString());
//    }


}
