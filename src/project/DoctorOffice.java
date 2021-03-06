package project;

import project.repository.AppointmentRepository;
import project.repository.DoctorRepository;
import project.repository.PacientRepository;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DoctorOffice
{

    AppointmentRepository appointmentRepository = new AppointmentRepository();
    DoctorRepository doctorRepository = new DoctorRepository();
    PacientRepository pacientRepository = new PacientRepository();

    private ArrayList<Appointment> appointments = new ArrayList<>();
    private ArrayList<Pacient> pacients = new ArrayList<>();
    private ArrayList<Doctor> doctors = new ArrayList<>();
////
//    private AuditService as = AuditService.getInstance();
//
    public DoctorOffice () {};

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {
        this.appointments = appointments;
    }

    public ArrayList<Pacient> getPacients() {
        return pacients;
    }

    public void setPacients(ArrayList<Pacient> pacients) {
        this.pacients = pacients;
    }

    public ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<Doctor> doctors) {
        this.doctors = doctors;
    }

    //------------------------------------------------------------------------------------------------------------------
    // FUNCTII PACIENT
    //------------------------------------------------------------------------------------------------------------------

    public void menuPacient()
    {
        System.out.println("1. Afiseaza toti pacientii");
        System.out.println("2. Afiseaza pacientii minori");
        System.out.println("3. Adauga un pacient");
//        System.out.println("4. Adauga o afectiune pentru un anume pacient");
        System.out.println("4. Schimba varsta pentru un anume pacient");
        System.out.println("Optiunea ta : ");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        switch (op)
        {
            case 1:
            {
                //printPacients();
                pacientRepository.displayPacients();
                break;
            }
            case 2:
            {
                //printChildPacients();
                pacientRepository.displayChildPacients();
                break;
            }
            case 3:
            {
                addPacient();
                break;
            }
            case 4:
            {
//                System.out.println("Introduceti numele persoanei careia ii adauga o afectiune");
//                String nume = scanner.next();
//                System.out.println("Introduceti prenumele persoanei careia ii adauga o afectiune");
//                String prenume = scanner.next();
//                System.out.println("Introduceti afectiunea");
//                String afectiune = scanner.next();
//                addDisease(nume, prenume, afectiune);

                System.out.println("Introduceti numele pacientului");
                String nume = scanner.next();
                System.out.println("Introduceti prenumele pacientului");
                String prenume = scanner.next();
                System.out.println("Introduceti noua varsta");
                int varsta = scanner.nextInt();
                pacientRepository.updateAgePacient(varsta, nume, prenume);
            }
            default:
            {
                System.out.println("Introduceti o optiune valida");
            }
        }
    }

//    public void printPacients ()
//    {
//        for(Pacient p: pacients)
//            System.out.println(p.toString());
//        as.addAudit("print pacients");
//    }

//    public void printChildPacients ()
//    {
//        for(Pacient p: pacients)
//            if(p.isUnderAge())
//                System.out.println(p.toString());
//        as.addAudit("print child pacients");
//    }

    public void addPacient()
    {
        System.out.println("Adaugati datele pacientului");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Intrduceti numele :");
        String lastName = scanner.nextLine();
        System.out.println("Introduceti prenumele :");
        String firstName = scanner.next();
        System.out.println("Introduceti varsta : ");
        int age = scanner.nextInt();
        System.out.println("Introduceti sex :");
        String s = scanner.next();
        char sex = s.charAt(0);
//        System.out.println("Introduceti numarul de afectiuni :");
//        int n = scanner.nextInt();
        System.out.println("Introduceti afectiuni :");
        scanner.nextLine();
        String d = scanner.nextLine();
        String[] diseases = d.split(",");

//        String[] parts = );
//        for(int i = 0 ;i < n; i++)
//        {
//            //String d = scanner.next();
//            diseases[i] = parts[i];
//        }

        if(age < 18)
        {
            System.out.println("Intrduceti numele parintelui ");
            String parentName = scanner.nextLine();
            ChildPacient p = new ChildPacient(lastName, firstName, age, sex, diseases, parentName);
            pacientRepository.insertPacient(p);
            //pacients.add(p);
        }
        else
        {
            System.out.println("Intrduceti daca fumeaza sau nu : (true or false) ");
            boolean smoker = scanner.nextBoolean();
            AdultPacient p = new AdultPacient(lastName, firstName, age, sex, diseases, smoker);
            //pacients.add(p);
            pacientRepository.insertPacient(p);
        }

        //as.addAudit("add pacient");
    }

//    private String[] addElement (String[] s, String d)
//    {
//        String[] temp = new String[s.length + 1];
//        System.arraycopy(s, 0, temp, 0,s.length);
//        temp[s.length] = d;
//        return temp;
//    }
//
//    public void addDisease(String lastName, String firstName, String disease)
//    {
//        boolean flag = false;
//        for(Pacient p : pacients)
//            if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName))
//            {
//                p.diseases = addElement(p.getDiseases(), disease);
//                flag = true;
//            }
//        if(!flag)
//            System.out.println("Pacientul nu este in baza noastra de date si nu ii putem adauga o afectiune");
//    }

//    public Pacient findPacient(String lastName, String firstName)
//    {
//        for(Pacient p: pacients)
//            if(p.getLastName().equals(lastName) && p.getFirstName().equals(firstName))
//                return p;
//        return null;
//    }


    //------------------------------------------------------------------------------------------------------------------
    // FUNCTII MEDICI
    //------------------------------------------------------------------------------------------------------------------

    public void menuDoctors()
    {
        System.out.println("1. Afiseaza toti medicii");
        System.out.println("2. Afiseaza toti medicii speicialisti chirurgi");
        System.out.println("3. Adauga un medic");
        //System.out.println("4. Sorteaza medicii dupa nume si varsta: ");
        System.out.println("4. Schimba calificarea unui doctor: ");
        System.out.println("Optiunea ta : ");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        switch (op) {
            case 1: {
                //printDoctors();
                doctorRepository.displayDoctors();
                break;
            }
            case 2: {
                //printSpecialistSurgeon();
                doctorRepository.displaySurgeonDoctors();
                break;
            }
            case 3: {
                addDoctor();
                break;
            }
            case 4: {
//                Comparator<Doctor> comparator = Comparator.comparing(Doctor::getLastName);
//                Collections.sort(doctors, comparator);
//                printDoctors();
                System.out.println("Intrduceti numele doctorului :");
                scanner.nextLine();
                String lastName = scanner.nextLine();
                System.out.println("Introduceti prenumele doctorului:");
                String firstName = scanner.next();
                System.out.println("Introduceti noua calificare ");
                String qualification = scanner.next();
                doctorRepository.updateDoctor(qualification, lastName, firstName);
                break;
            }
            default: {
                System.out.println("Introduceti o optiune valida");
            }
        }
    }

//    public void printDoctors()
//    {
//        for(Doctor d: doctors)
//            System.out.println(d.toString());
//    }

//    public void printSpecialistSurgeon()
//    {
//        for(Doctor d: doctors)
//            if(d.getQualification().toLowerCase().charAt(0) == 's' && d.isSurgeon())
//                System.out.println(d.toString());
//    }

    public void addDoctor()
    {
        System.out.println("Adaugati datele medicului");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Intrduceti numele :");
        String lastName = scanner.nextLine();
        System.out.println("Introduceti prenumele :");
        String firstName = scanner.next();
        System.out.println("Introduceti varsta : ");
        int age = scanner.nextInt();
        System.out.println("Introduceti calificarea : (specilist sau primar) ");
        String qualification = scanner.next();
        System.out.println("Introduceti daca este si chirurg : (true sau false)");
        boolean surgeon = scanner.nextBoolean();
        System.out.println("Introduceti ce organ/parte a corpului trateaza: inima, plamani, piele ");
        String part = scanner.next();
        if(part.toLowerCase().charAt(0) == 'i')
        {
            System.out.println("Introduceti recomandarea doctorului: ");
            scanner.nextLine();
            String recommendation = scanner.nextLine();
            Cardiologist c = new Cardiologist(lastName, firstName, age, qualification, part, surgeon, recommendation);
            doctorRepository.insertDoctor(c);
            //doctors.add(c);
        }
        else if(part.startsWith("pla"))
        {
            System.out.println("Introduceti tratamentul doctorului: ");
            String treatment = scanner.nextLine();
            Pulmonologist p = new Pulmonologist(lastName, firstName, age, qualification, part, surgeon, treatment);
            //doctors.add(p);
            doctorRepository.insertDoctor(p);
        }
        else if(part.startsWith("pi"))
        {
            System.out.println("Introduceti numarul de cazuri tratate ale doctorului: ");
            int nr = scanner.nextInt();
            Dermatologist d = new Dermatologist(lastName, firstName, age, qualification, part, surgeon, nr);
            //doctors.add(d);
            doctorRepository.insertDoctor(d);
        }
    }

//    public Doctor findDoctor(String lastName, String firstName)
//    {
//        for(Doctor d:doctors)
//            if(d.getLastName().equals(lastName) && d.getFirstName().equals(firstName))
//                return d;
//        return null;
//    }


    //------------------------------------------------------------------------------------------------------------
    //FUNCTII PROGRAMARI
    //------------------------------------------------------------------------------------------------------------

    public void menuAppointments() throws ParseException
    {
        System.out.println("1. Afiseaza toate programarile");
        System.out.println("2. Afiseaza primele 3 cele mai scumpe programari");
        System.out.println("3. Adauga o programare");
        System.out.println("4. Sterge o programare");
        System.out.println("Optiunea ta : ");
        Scanner scanner = new Scanner(System.in);
        int op = scanner.nextInt();
        switch (op)
        {
            case 1:
            {
                //printAppointments();
                appointmentRepository.displayAppointments();
                break;
            }
            case 2:
            {
                //printExpensiveAppointments();
                appointmentRepository.displayExpensiveAppointments();
                break;
            }
            case 3:
            {
                addAppointment();

                break;
            }
            case 4:
            {
                //SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                System.out.println("Introduceti datele programarii");
                System.out.println("Introduceti numele pacientului");
                String pacientLastName = scanner.next();
                System.out.println("Introduceti prenumele pacientului");
                String pacientFirstName = scanner.next();
                System.out.println("Introduceti numele doctorului");
                String doctorLastName = scanner.next();
                System.out.println("Introduceti prenumele doctorului");
                String doctorFirstName = scanner.next();
                System.out.println("Introduceti data programarii :");
                String data = scanner.next();
                //Date date = format.parse(data);
                //String date =

                //deleteAppointment(pacientLastName, pacientFirstName, doctorLastName, doctorFirstName, date);
                appointmentRepository.deleteAppointment(pacientLastName, pacientFirstName, doctorLastName, doctorFirstName, data);
                break;
            }
            default:
            {
                System.out.println("Introduceti o optiune valida");
            }
        }
    }

//    public void printAppointments()
//    {
//        for(Appointment a : appointments)
//            System.out.println(a.toString());
//    }

//    public void printExpensiveAppointments()
//    {
//        Collections.sort(appointments, new Comparator<Appointment>()
//        {
//            @Override
//            public int compare(Appointment a1, Appointment a2)
//            {
//                if(a1.getPrice() > a2.getPrice())
//                    return -1;
//                else if (a1.getPrice() == a2.getPrice())
//                    return 0;
//                else return 1;
//            }
//        });
//        for(int i = 0; i <= 2; i++)
//            System.out.println(appointments.get(i).toString());
//    }

//    public void addExistingAppointment(Appointment a)
//    {
//        appointments.add(a);
//
//        Pacient p = findPacient(a.getPacientLastName(), a.getPacientFirstName());
//        p.getAppointments().add(a);
//
//        Doctor d = findDoctor(a.getDoctorLastName(), a.getDoctorFirstName());
//        d.getAppointmentsPQ().add(a);
//    }

    public void addAppointment() throws ParseException
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Adaugati datele programarii");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Intrduceti numele pacientului :");
        String lastName = scanner.nextLine();
        System.out.println("Introduceti prenumele pacientului :");
        String firstName = scanner.next();

        //Pacient p = findPacient(lastName, firstName);

        Pacient p = pacientRepository.getPacientByName(lastName, firstName);
        if(p != null)
        {
            System.out.println("Introduceti nume doctorului : ");
            String lastNameDoctor = scanner.next();
            System.out.println("Introduceti prenumele doctorului :");
            String firstNameDoctor = scanner.next();

            //Doctor d = findDoctor(lastNameDoctor, firstNameDoctor);

            Doctor d = doctorRepository.getDoctorByName(lastNameDoctor, firstNameDoctor);
            if(d !=null)
            {
                System.out.println("Introduceti ora programarii :");
                int hour = scanner.nextInt();
                System.out.println("Introduceti data programarii :");
                String data = scanner.next();
                //Date date = format.parse(data);
                //String date =
                System.out.println("Introduceti motivul programarii :");
                String reason = scanner.next();
                System.out.println("Introduceti pretul programarii :");
                double price = scanner.nextDouble();

                Appointment a = new Appointment(lastName, firstName, lastNameDoctor, firstNameDoctor, hour, data, reason, price);
                //appointments.add(a);
                appointmentRepository.insertAppointment(a);
                //d.getAppointmentsPQ().add(a);
                //p.getAppointments().add(a);
                System.out.println("Programare inregistrata cu succes");
            }
            else System.out.println("Acest doctor nu este inregistrat inca.");
        }
        else System.out.println("Acest pacient nu este inregistrat inca.");
    }

//    public void deleteAppointment(String pacientLastName, String pacientFirstName, String doctorLastName, String doctorFirstName, Date date)
//    {
//        boolean flag = true;
//        Iterator<Appointment> iterator = appointments.iterator();
//        while(iterator.hasNext() && flag)
//        {
//            Appointment a = iterator.next();
//            if(a.getPacientFirstName().equals(pacientFirstName) && a.getPacientLastName().equals(pacientLastName)
//                    && a.getDoctorLastName().equals(doctorLastName) && a.getDoctorFirstName().equals(doctorFirstName) && a.getDate().equals(date))
//                {
//                    iterator.remove();
//                    flag = false;
//
//                    Pacient p = findPacient(pacientLastName, pacientFirstName);
//                    Doctor d = findDoctor(doctorLastName, doctorFirstName);
//                    //p.getAppointments().remove(a);
//                    //d.getAppointmentsPQ().remove(a);
//                    System.out.println("Programarea a fost stearsa cu succes");
//                }
//        }
//        if(flag)
//            System.out.println("Nu exista aceasta programare");
//    }
}
