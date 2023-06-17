package Model;

import java.util.HashMap;
import java.util.Objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

public class DatabaseProvider {

    private final String dbUrl = "jdbc:postgresql://localhost/phase10";
    private final String dbUser = "postgres";
    private final String dbPassword = "giba"; // TODO: change to your password

    public boolean useDummy = false;
    public HashMap<String, String> dummyUserData = new HashMap<String, String>();

    public DatabaseProvider(boolean useDummy) {
        this.useDummy = useDummy;
    }

    public DatabaseProvider() {
        this.useDummy = false;
    }

    public Connection connect() {
        try {
            // Load the PostgreSQL JDBC driver
            Class.forName("org.postgresql.Driver");

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            if (connection != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            return connection;

        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC driver not found!");
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    public void addUserDummy(String name, String pw) {
        this.dummyUserData.put(name, pw);
    }

    public void addUser(String name, String pw) {
        if (this.useDummy) {
            this.addUserDummy(name, pw);
            return;
        }
        Connection connection = this.connect();
        String query = "INSERT INTO public.\"user\"(username, password) VALUES (?, ?)";
        try {
            // sanitize inputs using preparedstatement to prevent injection attacks
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, pw);
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLError when trying to add user!");
            e.printStackTrace();
        }
    }

    public boolean checkUserDummy(String name, String pw) {
        if (this.dummyUserData.containsKey(name)) {
            if (Objects.equals(this.dummyUserData.get(name), pw)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUser(String name, String pw) {
        if (this.useDummy) {
            return this.checkUserDummy(name, pw);
        }
        Connection connection = this.connect();
        String query = "SELECT * FROM public.\"user\" WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, pw);
            ResultSet resultSet = statement.executeQuery();
            boolean userExists = resultSet.next();
            return userExists;
        } catch (SQLException e) {
            System.out.println("SQLError when trying to check user!");
            e.printStackTrace();
            return false;
        }
    }

    public void changeUsernameDummy(String currentUsername, String newUsername) {
        if (this.dummyUserData.containsKey(currentUsername)) {
            this.dummyUserData.put(newUsername, this.dummyUserData.get(currentUsername));
        }
        this.dummyUserData.remove(currentUsername);
    }

    public void changeUsername(String currentUsername, String newUsername) {
        if (this.useDummy) {
            this.changeUsernameDummy(currentUsername, newUsername);
        }
        Connection connection = this.connect();
        String query = "UPDATE public.\"user\" SET username = ? WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newUsername);
            statement.setString(2, currentUsername);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Username changed successfully!");
            } else {
                System.out.println("Failed to change username!");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLError when trying to check user!");
            e.printStackTrace();
        }
    }

    public void changePasswordDummy(String name, String currentPassword, String newPassword) {
        if (this.checkUser(name, currentPassword)) {
            this.dummyUserData.put(name, newPassword);
        }
        this.dummyUserData.remove(name, currentPassword);
    }

    public void changePassword(String name, String currentPassword, String newPassword) {
        if (this.useDummy) {
            this.changePasswordDummy(name, currentPassword, newPassword);
        }
        if (!this.checkUser(name, currentPassword)) {
            System.out.println("Invalid name or password");
            return;
        }
        Connection connection = this.connect();
        String query = "UPDATE public.\"user\" SET username = ? WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, newPassword);
            statement.setString(2, name);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Password changed successfully!");
            } else {
                System.out.println("Failed to change password!");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLError when trying to check user!");
            e.printStackTrace();
        }
    }

}
