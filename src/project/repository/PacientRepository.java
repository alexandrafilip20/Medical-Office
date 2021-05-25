package project.repository;

import project.*;
import project.config.DatabaseConfiguration;

import java.sql.*;
import java.util.Arrays;

public class PacientRepository
{
    public void createTable()
    {
        String createTableSql = "CREATE TABLE IF NOT EXISTS pacients" +
                "(id int PRIMARY KEY AUTO_INCREMENT, lastName varchar(30)," +
                "firstName varchar(30), age int, " +
                "sex varchar(30), diseases varchar(120), smoker boolean, parentName varchar(30))";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            Statement stmt = connection.createStatement();
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayPacients()
    {
        String selectSql = "SELECT * FROM pacients";

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
                System.out.println("sex:" + resultSet.getString(5));
                System.out.println("diseases:" + resultSet.getString(6));
                int age = resultSet.getInt(4);
                if(age < 18)
                    System.out.println("parentName:" + resultSet.getString(8));
                else
                    System.out.println("smoker:" + resultSet.getBoolean(7));
                System.out.println("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayChildPacients()
    {
        String selectSql = "SELECT * FROM pacients WHERE age<18";

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
                System.out.println("sex:" + resultSet.getString(5));
                System.out.println("diseases:" + resultSet.getString(6));
                System.out.println("parentName:" + resultSet.getString(8));
                System.out.println("\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPacient(Pacient p)
    {
        boolean b = p.isUnderAge();
        String insertPacientSql = "";

        if(b)
            insertPacientSql = "INSERT INTO pacients" +
                    "(lastName, firstName, age, sex, diseases, parentName)" +
                    "VALUES(?, ?, ?, ?, ?, ?)";
        else
            insertPacientSql = "INSERT INTO pacients" +
                    "(lastName, firstName, age, sex, diseases, smoker)" +
                    "VALUES(?, ?, ?, ?, ?, ?)";


        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertPacientSql);
            preparedStatement.setString(1, p.getLastName());
            preparedStatement.setString(2, p.getFirstName());
            preparedStatement.setInt(3, p.getAge());
            preparedStatement.setString(4, String.valueOf(p.getSex()));
            //preparedStatement.setString(5, String.valueOf(p.getDiseases()));

            String d = "";
            d = d.concat(p.getDiseases()[0]);
            for(int i=1;i< p.getDiseases().length;i++)
            {
                d = d.concat("-");
                d = d.concat(p.getDiseases()[i]);
            }
            preparedStatement.setString(5, d);

            if(p.getAge()<18)
            {
                ChildPacient c = (ChildPacient) p;
                preparedStatement.setString(6, c.getParentName());
            }
            else
            {
                AdultPacient c = (AdultPacient) p;
                preparedStatement.setBoolean(6, c.isSmoker());
            }

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAgePacient(int age,String lastName, String firstName) {
        String updateNameSql = "UPDATE pacients SET age=? WHERE lastName=? and firstName=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(updateNameSql);
            preparedStatement.setInt(1, age);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, firstName);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Pacient getPacientByName(String lastName, String firstName) {
        String selectSql = "SELECT * FROM pacients WHERE lastName=? and firstName=?";

        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);

            ResultSet resultSet = preparedStatement.executeQuery();
            return mapToPacient(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Pacient mapToPacient(ResultSet resultSet) throws SQLException
    {
        if (resultSet.next()) {

            if(resultSet.getInt(4) < 18)
                return new ChildPacient(resultSet.getString(2), resultSet.getString(3),  resultSet.getInt(4),
                            resultSet.getString(5).charAt(0), resultSet.getString(6).split("-"),   resultSet.getString(8));
            else
                return new AdultPacient(resultSet.getString(2), resultSet.getString(3),  resultSet.getInt(4),
                        resultSet.getString(5).charAt(0), resultSet.getString(6).split("-"),   resultSet.getBoolean(7));
        }

        return null;
    }

    public void deletePacientById(int id)
    {
        String updateNameSql = "DELETE FROM pacients  WHERE id=?";

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
