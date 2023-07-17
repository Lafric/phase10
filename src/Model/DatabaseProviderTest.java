package Model;

import java.util.Arrays;
import java.util.Comparator;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.Column;
import org.dbunit.dataset.DefaultDataSet;
import org.dbunit.dataset.DefaultTable;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.DataType;
import org.dbunit.operation.DatabaseOperation;

public class DatabaseProviderTest extends DBTestCase {

    public DatabaseProviderTest(String name) {
        super(name);
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.postgresql.Driver");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
                "jdbc:postgresql://localhost/phase10");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "postgres");
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "giba");
    }

    
    /** 
     * @return IDataSet
     * @throws Exception
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        DefaultDataSet dataSet = new DefaultDataSet();

        DefaultTable table = new DefaultTable(
                "users",
                new Column[] {
                        new Column("username", DataType.VARCHAR),
                        new Column("password", DataType.VARCHAR)
                });

        table.addRow(new Object[] { "username1", "password1" });
        table.addRow(new Object[] { "username2", "password2" });
        dataSet.addTable(table);

        return dataSet;
    }

    @Override
    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    @Override
    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    public void testCheckUser() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider("jdbc:postgresql://localhost/phase10","giba");
        assertTrue(dbp.checkUser("username1", "password1"));
        assertFalse(dbp.checkUser("Nietzsche", "ToterGott"));
    }

    public void testAddUser() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider("jdbc:postgresql://localhost/phase10","giba");
        dbp.addUser("username3", "password3");
        assertTrue(dbp.checkUser("username3", "password3"));
    }

    public void testChangeUsername() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider("jdbc:postgresql://localhost/phase10","giba");
        dbp.changeUsername("username2", "username2x");
        assertTrue(dbp.checkUser("username2x", "password2"));
        assertFalse(dbp.checkUser("username2", "password2"));
    }

    public void testChangePassword() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider("jdbc:postgresql://localhost/phase10","giba");
        dbp.changePassword("username2", "password2", "password2x");

        System.out.println(dbp.checkUser("username2", "password2"));
        System.out.println(dbp.checkUser("username2", "password2x"));

        assertTrue(dbp.checkUser("username2", "password2x"));
        assertFalse(dbp.checkUser("username2", "password2"));
    }

    public void testDeleteUser() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider("jdbc:postgresql://localhost/phase10","giba");
        dbp.deleteUser("username1", "password1");
        assertFalse(dbp.checkUser("username1", "password1"));
    }

    // public void testGetUserData() throws Exception {
    //     DatabaseProvider dbp = new DatabaseProvider("jdbc:postgresql://localhost/phase10","giba");
    //     dbp.addUser("fasf", "fafa");
    //     dbp.incrementGamesPlayed("username2");
    //     dbp.incrementGamesPlayed("username2");
    //     dbp.incrementGamesWon("username2");

    //     UserData[] userData = dbp.getUserData();
    //     Arrays.sort(userData, new Comparator<UserData>() {
    //         @Override
    //         public int compare(UserData o1, UserData o2) {
    //             return o1.getNutzername().compareTo(o2.getNutzername());
    //         }
    //     });
        
    //     assertTrue(userData[0].getNutzername().equals("username1"));
    //     assertTrue(userData[0].getGespielteSpiele() == 0);
    //     assertTrue(userData[0].getSiege() == 0);
    //     assertTrue(userData[0].getSiegesrate() == 0.0);

    //     assertTrue(userData[0].getNutzername().equals("username2"));
    //     assertTrue(userData[0].getGespielteSpiele() == 2);
    //     assertTrue(userData[0].getSiege() == 1);
    //     assertTrue(userData[0].getSiegesrate() == 0.5);

    // }
}
