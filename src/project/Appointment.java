package project;
import java.lang.Comparable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment implements Comparable<Appointment>
{
    private String pacientLastName;
    private String pacientFirstName;

    private String doctorLastName;
    private String doctorFirstName;

    private int hour;
    private String date;
    private String reason;
    private double price;

    public Appointment(String pacientLastName, String pacientFirstName, String doctorLastName, String doctorFirstName, int hour, String date,String reason, double price)
    {
        this.pacientLastName = pacientLastName;
        this.pacientFirstName = pacientFirstName;
        this.doctorLastName = doctorLastName;
        this.doctorFirstName = doctorFirstName;
        this.hour = hour;
        this.date = date;
        this.reason = reason;
        this.price = price;
    }

    public String getPacientLastName()
    {
        return pacientLastName;
    }

    public void setPacientLastName(String pacientLastName)
    {
        this.pacientLastName = pacientLastName;
    }

    public String getPacientFirstName()
    {
        return pacientFirstName;
    }

    public void setPacientFirstName(String pacientFirstName)
    {
        this.pacientFirstName = pacientFirstName;
    }

    public String getDoctorLastName()
    {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName)
    {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorFirstName()
    {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName)
    {
        this.doctorFirstName = doctorFirstName;
    }

    public int getHour()
    {
        return hour;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    @Override
    public int compareTo(Appointment a)
    {
        return this.date.compareTo(a.date);
    }

    @Override
    public String toString()
    {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return "Programarea pacientului " + pacientLastName + " " + pacientFirstName + ", la doctorul " + doctorLastName + " " + doctorFirstName + ", la ora " + hour + ", in data de " + format.format(date) +
                ", pentru " + reason + ", avand pretul de " + price + " lei.\n";
    }
}
