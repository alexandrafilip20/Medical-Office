package project.repository;

import project.Appointment;
import project.config.DatabaseConfiguration;

import java.sql.*;
import java.util.Date;

public class AppointmentRepository
{
    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS appointments" +
                "(id int PRIMARY KEY AUTO_INCREMENT, pacientLastName varchar(30)," +
                "pacientFirstName varchar(30), doctorLastName varchar(30), " +
                "doctorFirstName varchar(30), hour int, " +
                "date varchar(30), reason varchar(30), price double)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertAppointment(Appointment a) {
        String insertAuthorSql = "INSERT INTO appointments(pacientLastName, pacientFirstName, doctorLastName,doctorFirstName, hour, date, reason, price) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAuthorSql);
            preparedStatement.setString(1, a.getPacientLastName());
            preparedStatement.setString(2, a.getPacientFirstName());
            preparedStatement.setString(3, a.getDoctorLastName());
            preparedStatement.setString(4, a.getDoctorFirstName());
            preparedStatement.setInt(5, a.getHour());
            preparedStatement.setString(6, a.getDate());
            preparedStatement.setString(7, a.getReason());
            preparedStatement.setDouble(8, a.getPrice());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void deleteAppointment(String pacientLastName, String pacientFirstName, String doctorLastName, String doctorFirstName, String date) {
        String updateNameSql = "DELETE FROM appointments  WHERE pacientLastName=? and pacientFirstName=? and doctorLastName=? and doctorFirstName=? and date=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, pacientLastName);
            preparedStatement.setString(2, pacientFirstName);
            preparedStatement.setString(3, doctorLastName);
            preparedStatement.setString(4, doctorFirstName);
            preparedStatement.setString(5, date);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAppointmentById(int id)
    {
        String updateNameSql = "DELETE FROM appointments  WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void displayAppointments()
    {
        String selectSql = "SELECT * FROM appointments";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("pacientLastName:" + resultSet.getString(2));
                System.out.println("pacientFirstName:" + resultSet.getString(3));
                System.out.println("doctorLastName:" + resultSet.getString(4));
                System.out.println("doctorFirstName:" + resultSet.getString(5));
                System.out.println("hour:" + resultSet.getInt(6));
                System.out.println("date:" + resultSet.getString(7));
                System.out.println("reason:" + resultSet.getString(8));
                System.out.println("price:" + resultSet.getDouble(9));
                System.out.println("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayExpensiveAppointments()
    {
        String selectSql = "SELECT * FROM appointments ORDER BY price LIMIT 3";
        //String selectSql = "SELECT * FROM appointments ORDER BY price WHERE rownum <= 3";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("pacientLastName:" + resultSet.getString(2));
                System.out.println("pacientFirstName:" + resultSet.getString(3));
                System.out.println("doctorLastName:" + resultSet.getString(4));
                System.out.println("doctorFirstName:" + resultSet.getString(5));
                System.out.println("hour:" + resultSet.getInt(6));
                System.out.println("date:" + resultSet.getString(7));
                System.out.println("reason:" + resultSet.getString(8));
                System.out.println("price:" + resultSet.getDouble(9));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
