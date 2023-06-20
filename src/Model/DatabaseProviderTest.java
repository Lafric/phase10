package Model;

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
        DatabaseProvider dbp = new DatabaseProvider();
        assertTrue(dbp.checkUser("username1", "password1"));
        assertFalse(dbp.checkUser("Nietzsche", "ToterGott"));
    }

    public void testAddUser() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider();
        dbp.addUser("username3", "password3");
        assertTrue(dbp.checkUser("username3", "password3"));
    }

    public void testChangeUsername() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider();
        dbp.changeUsername("username2", "username2x");
        assertTrue(dbp.checkUser("username2x", "password2"));
        assertFalse(dbp.checkUser("username2", "password2"));
    }

    public void testChangePassword() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider();
        dbp.changePassword("username2", "password2", "password2x");
        assertTrue(dbp.checkUser("username2", "password2x"));
        assertFalse(dbp.checkUser("username2", "password2"));
    }

    public void testDeleteUser() throws Exception {
        DatabaseProvider dbp = new DatabaseProvider();
        dbp.deleteUser("username1", "password1");
        assertFalse(dbp.checkUser("username1", "password1"));
    }
}
