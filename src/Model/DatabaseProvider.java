package Model;

import java.util.HashMap;
import java.util.Objects;
import java.sql.*;

/**
 * This class handles the database
 */
public class DatabaseProvider {

    private String dbUser = "postgres";

    private String dbUrl = "jdbc:postgresql://185.162.248.237:5432/postgres";
    private String dbPassword = "apfel"; // TODO: change to your password

    // private String dbUrl = "jdbc:postgresql://localhost/phase10";
    // private String dbPassword = "giba"; // TODO: change to your password

    public boolean useDummy = false;
    public HashMap<String, String> dummyUserData = new HashMap<String, String>();

    /**
     * Constructor with additional option
     * @param useDummy indicates the use od a dummy
     */
    public DatabaseProvider(boolean useDummy) {
        this.useDummy = useDummy;
    }

    /**
     * Constructor which sets all options to default
     */
    public DatabaseProvider() {
        this.useDummy = false;
    }

    /**
     * Constructor with more parameters
     * @param dbUrl given url
     * @param dbPassword given password
     */
    public DatabaseProvider(String dbUrl, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbPassword = dbPassword;
    }

    
    /** 
     * @return Connection
     */
    private Connection connect() {
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

    /**
     * Method to add user
     * @param name of the user
     * @param pw password of the user
     */
    private void addUserDummy(String name, String pw) {
        this.dummyUserData.put(name, pw);
    }

    /**
     * Method to add a user
     * @param name username
     * @param pw user password
     */
    public void addUser(String name, String pw) {
        if (this.useDummy) {
            this.addUserDummy(name, pw);
            return;
        }
        Connection connection = this.connect();
        String query = "INSERT INTO users(username, password) VALUES (?, ?)";
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

    /**
     * Checker for dummy user
     * @param name username
     * @param pw user password
     * @return indicator for checkingresult
     */
    private boolean checkUserDummy(String name, String pw) {
        if (this.dummyUserData.containsKey(name)) {
            if (Objects.equals(this.dummyUserData.get(name), pw)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Mehtod to check User
     * @param name username
     * @param pw userpassword
     * @return indicator of checking result
     */
    public boolean checkUser(String name, String pw) {
        if (this.useDummy) {
            return this.checkUserDummy(name, pw);
        }
        Connection connection = this.connect();
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
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

    /**
     * Method to change dummy user name
     * @param currentUsername current name
     * @param newUsername new name
     */
    private void changeUsernameDummy(String currentUsername, String newUsername) {
        if (this.dummyUserData.containsKey(currentUsername)) {
            this.dummyUserData.put(newUsername, this.dummyUserData.get(currentUsername));
        }
        this.dummyUserData.remove(currentUsername);
    }

    /**
     * Method to change user name
     * @param currentUsername current user name
     * @param newUsername new user name
     */
    public void changeUsername(String currentUsername, String newUsername) {
        if (this.useDummy) {
            this.changeUsernameDummy(currentUsername, newUsername);
        }
        Connection connection = this.connect();
        String query = "UPDATE users SET username = ? WHERE username = ?";
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

    /**
     * Mthod to change dummy passwort
     * @param name dummy name
     * @param currentPassword current password
     * @param newPassword new password
     */
    private void changePasswordDummy(String name, String currentPassword, String newPassword) {
        if (this.checkUser(name, currentPassword)) {
            this.dummyUserData.put(name, newPassword);
        }
        this.dummyUserData.remove(name, currentPassword);
    }

    /**
     * method to change password
     * @param name username
     * @param currentPassword current password
     * @param newPassword the new tob password
     */
    public void changePassword(String name, String currentPassword, String newPassword) {
        if (this.useDummy) {
            this.changePasswordDummy(name, currentPassword, newPassword);
        }
        if (!this.checkUser(name, currentPassword)) {
            System.out.println("Invalid name or password");
            return;
        }
        Connection connection = this.connect();
        String query = "UPDATE users SET password = ? WHERE username = ?";
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

    /**
     * Method to delete dummy user
     * @param name name of dummy
     * @param password password of dummy
     */
    private void deleteUserDummy(String name, String password) {
        if (this.checkUser(name, password)) {
            this.dummyUserData.remove(name, password);
        }
    }

    /**
     * Method to delete user
     * @param name user name
     * @param password user password
     */
    public void deleteUser(String name, String password) {
        if (this.useDummy) {
            this.deleteUserDummy(name, password);
        }
        if (!this.checkUser(name, password)) {
            System.out.println("Invalid name or password");
            return;
        }
        Connection connection = this.connect();
        String query = "DELETE FROM users WHERE username = ? AND password = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("Failed to delete user!");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLError when trying to check user!");
            e.printStackTrace();
        }
    }

    /**
     * method to increase amount of played games
     * @param username name of the user
     */
    public void incrementGamesPlayed(String username) {
        if (this.useDummy) {
            return;
        }
        Connection connection = this.connect();
        String query = "UPDATE users SET games_played = games_played + 1 WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Games played incremented successfully!");
            } else {
                System.out.println("Failed to increment games played!");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLError when trying to increment games played!");
            e.printStackTrace();
        }
    }

    /**
     * method to increase the amount of won games
     * @param username name of the user
     */
    public void incrementGamesWon(String username) {
        if (this.useDummy) {
            return;
        }
        Connection connection = this.connect();
        String query = "UPDATE users SET games_won = games_won + 1 WHERE username = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, username);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Games won incremented successfully!");
            } else {
                System.out.println("Failed to increment games won!");
            }
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("SQLError when trying to increment games won!");
            e.printStackTrace();
        }
    }

    /**
     * Method to obtain user data
     * @return the user data
     */
    public UserData[] getUserData() {
        if (this.useDummy) {
            UserData dummyUser = new UserData("test", 1, 2);
            UserData[] dummyUserData = { dummyUser };
            return dummyUserData;
        }
        Connection connection = this.connect();
        String query = "SELECT * FROM users";
        try {
            PreparedStatement statement = connection.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ResultSet resultSet = statement.executeQuery();
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            UserData[] userData = new UserData[rowCount];
            resultSet.beforeFirst();
            int i = 0;
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                int gamesWon = resultSet.getInt("number_of_games_won");
                int gamesPlayed = resultSet.getInt("number_of_games_played");
                userData[i] = new UserData(username, gamesWon, gamesPlayed);
                i++;
            }
            statement.close();
            connection.close();
            return userData;
        } catch (SQLException e) {
            System.out.println("SQLError when trying to get user data!");
            e.printStackTrace();
            return null;
        }

    }
}
