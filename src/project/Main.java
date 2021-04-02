package project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Main
{
    public static void main(String[] args) throws ParseException
    {

        DoctorOffice doctorOffice = new DoctorOffice();

        AdultPacient p1 = new AdultPacient("Popescu", "Darius", 29, 'M', new String[]{"diabet", "insuficienta cardiaca"}, new ArrayList<>(), false);
        ChildPacient p2 = new ChildPacient("Ionescu", "Andrei", 16, 'M', new String[]{"dermatita"}, new ArrayList<>(), "Popescu");
        ChildPacient p3 = new ChildPacient("Dumitrescu", "Miruna", 8, 'F', new String[]{"dificultate in respiratie"}, new ArrayList<>(), "Popescu");

        doctorOffice.getPacients().add(p1);
        doctorOffice.getPacients().add(p2);
        doctorOffice.getPacients().add(p3);

        Pulmonologist d1 = new Pulmonologist("Dragomir", "Corina", 45, "specialist", "plamani", true, new PriorityQueue<>(), "fara fumat");
        Pulmonologist d2 = new Pulmonologist("Ion", "Maria", 52, "primar", "plamani", false, new PriorityQueue<>(), "mai multa miscare");
        Pulmonologist d3 = new Pulmonologist("Gheorghe", "Eduard", 38, "specialist", "plamani", false, new PriorityQueue<>(), "inot");

        Dermatologist d4 = new Dermatologist("Paraschiv", "Teodora", 31, "primar", "piele", false, new PriorityQueue<>(), 14);
        Dermatologist d5 = new Dermatologist("Dobre", "Monica", 49, "specialist", "piele", true, new PriorityQueue<>(), 29);

        Cardiologist d6 = new Cardiologist("Grigore", "Tudor", 42, "specialist", "inima", true, new PriorityQueue<>(), "mai putin zahar");

        doctorOffice.getDoctors().add(d1);
        doctorOffice.getDoctors().add(d2);
        doctorOffice.getDoctors().add(d3);
        doctorOffice.getDoctors().add(d4);
        doctorOffice.getDoctors().add(d5);
        doctorOffice.getDoctors().add(d6);

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        Appointment a1 = new Appointment("Popescu", "Darius", "Grigore", "Tudor", 14, format.parse("21/04/2021"), "Consultatie", 140);
        Appointment a2 = new Appointment("Ionescu", "Andrei", "Paraschiv", "Teodora", 9, format.parse("30/06/2021"), "Consultatie", 100.50);
        Appointment a3 = new Appointment("Ionescu", "Andrei", "Dobre", "Monica", 13, format.parse("14/05/2021"), "Consultatie", 190.7);
        Appointment a4 = new Appointment("Dumitrescu", "Miruna", "Ion", "Maria", 17, format.parse("08/06/2021"), "Operatie", 1240.25);
        Appointment a5 = new Appointment("Popescu", "Darius", "Ion", "Maria", 12, format.parse("05/05/2021"), "Operatie", 1003.75);

        doctorOffice.addExistingAppointment(a1);
        doctorOffice.addExistingAppointment(a2);
        doctorOffice.addExistingAppointment(a3);
        doctorOffice.addExistingAppointment(a4);
        doctorOffice.addExistingAppointment(a5);

        System.out.println("Bine ati venit in cabinetul nostru medical");

        while(true)
        {
            System.out.println("\n1: Operatii asupra medicilor");
            System.out.println("2: Operatii asupra pacientilor");
            System.out.println("3: Operatii asupra programarilor");
            System.out.println("4. Iesiti din cabinet");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Alegeti optiunea : ");
            int optiune = scanner.nextInt();

            switch (optiune)
            {
                case 1:
                {
                    doctorOffice.menuDoctors();
                    break;
                }

                case 2:
                {
                    doctorOffice.menuPacient();
                    break;
                }

                case 3:
                {
                    doctorOffice.menuAppointments();
                    break;
                }

                case 4:
                {
                    System.exit(0);
                }
                default:
                {
                    System.out.println("Introduceti o optiune valida\n");
                }
            }
        }
    }
}