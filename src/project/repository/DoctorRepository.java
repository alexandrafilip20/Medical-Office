package project.repository;

import project.*;
import project.config.DatabaseConfiguration;

import javax.print.Doc;
import java.awt.print.Book;
import java.sql.*;

public class DoctorRepository
{
    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS doctors" +
                "(id int PRIMARY KEY AUTO_INCREMENT, lastName varchar(30)," +
                "firstName varchar(30), age int, " +
                "qualification varchar(30), bodyPart varchar(30), " +
                "isSurgeon boolean, bestTreatment varchar(30), treatedCases int, recommendation varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayDoctors()
    {
        String selectSql = "SELECT * FROM doctors";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("lastName:" + resultSet.getString(2));
                System.out.println("firstName:" + resultSet.getString(3));
                System.out.println("age:" + resultSet.getInt(4));
                System.out.println("qualification:" + resultSet.getString(5));
                System.out.println("bodyPart:" + resultSet.getString(6));
                System.out.println("isSurgeon:" + resultSet.getBoolean(7));
                System.out.println("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displaySurgeonDoctors()
    {
        String selectSql = "SELECT * FROM doctors WHERE isSurgeon is true";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(selectSql);
            while (resultSet.next())
            {
                System.out.println("Id:" + resultSet.getString(1));
                System.out.println("lastName:" + resultSet.getString(2));
                System.out.println("firstName:" + resultSet.getString(3));
                System.out.println("age:" + resultSet.getInt(4));
                System.out.println("qualification:" + resultSet.getString(5));
                System.out.println("bodyPart:" + resultSet.getString(6));
                System.out.println("isSurgeon:" + resultSet.getBoolean(7));
                System.out.println("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertDoctor (Doctor d)
    {
        String bodyPart = d.getBodyPart();
        String insertDoctorSql = "";

        switch(bodyPart){
            case "piele":
                insertDoctorSql = "INSERT INTO doctors" +
                        "(lastName, firstName, age, qualification, bodyPart, isSurgeon, treatedCases)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
                break;
            case "plamani":
                insertDoctorSql = "INSERT INTO doctors" +
                        "(lastName, firstName, age, qualification, bodyPart, isSurgeon, bestTreatment)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
                break;
            case "inima":
                insertDoctorSql = "INSERT INTO doctors" +
                        "(lastName, firstName, age, qualification, bodyPart, isSurgeon, recommendation)" +
                        "VALUES(?, ?, ?, ?, ?, ?, ?)";
                break;
        }

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertDoctorSql);
            preparedStatement.setString(1, d.getLastName());
            preparedStatement.setString(2, d.getFirstName());
            preparedStatement.setInt(3, d.getAge());
            preparedStatement.setString(4, d.getQualification());
            preparedStatement.setString(5, d.getBodyPart());
            preparedStatement.setBoolean(6, d.isSurgeon());

            switch (bodyPart)
            {
                case "piele":
                    Dermatologist dermatologist = (Dermatologist) d;
                    preparedStatement.setInt(7, dermatologist.getTreatedCases());
                    break;
                case "plamani":
                    Pulmonologist pulmonologist = (Pulmonologist) d;
                    preparedStatement.setString(7, pulmonologist.getBestTreatment());
                    break;
                case "inima":
                    Cardiologist cardiologist = (Cardiologist) d;
                    preparedStatement.setString(7, cardiologist.getRecommentdations());
                    break;
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctor(String qualification,String lastName, String firstName) {
        String updateNameSql = "UPDATE doctors SET qualification=? WHERE lastName=? and firstName=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setString(1, qualification);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, firstName);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Doctor getDoctorByName(String lastName, String firstName) {
        String selectSql = "SELECT * FROM doctors WHERE lastName=? and firstName=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToDoctor(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Doctor mapToDoctor(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next()) {

            switch (resultSet.getString(6)) {
                case "piele":
                    return new Dermatologist(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4),
                            resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7),
                            resultSet.getInt(9));
                case "plamani":
                    return new Pulmonologist(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4),
                            resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7),
                            resultSet.getString(8));
                case "inima":
                    return new Cardiologist(resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4),
                            resultSet.getString(5), resultSet.getString(6), resultSet.getBoolean(7),
                            resultSet.getString(10));

            }

        }
        return null;
    }

    public void deleteDoctorById(int id)
    {
        String updateNameSql = "DELETE FROM doctors  WHERE id=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
