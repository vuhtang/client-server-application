package repository;

import collection.entity.Worker;
import exceptions.InvalidInputException;
import parser.WorkerReader;
import transferring.Token;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlManager {
    private static final String URL = "jdbc:postgresql://localhost:1077/studs";
    private static final String addWithID = "INSERT INTO workers (id, name, \"coordinateX\", \"coordinateY\"," +
            " \"creationDate\", salary, position, status, \"personHeight\", \"personPassportId\"," +
            " \"personLocationX\", \"personLocationY\", \"personLocationZ\", " +
            "\"personLocationName\", \"user\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private static final String addWithoutID = "INSERT INTO workers (name, \"coordinateX\", \"coordinateY\"," +
            " \"creationDate\", salary, position, status, \"personHeight\", \"personPassportId\"," +
            " \"personLocationX\", \"personLocationY\", \"personLocationZ\", " +
            "\"personLocationName\", \"user\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
    private final Connection connection;

    public SqlManager(String adminLogin, String password) throws SQLException {
        this.connection = DriverManager.getConnection(URL, adminLogin, password);
    }

    public List<Worker> getWorkersFromDB() throws InvalidInputException, SQLException {
        Statement statement = connection.createStatement();
        List<Worker> workers = new ArrayList<>();
        ResultSet result = statement.executeQuery("SELECT * FROM workers;");
        while (result.next()) {
            String[] values = new String[15];
            for (int i = 1; i < 15; i++) {
                values[i] = result.getString(i);
            }
            workers.add(WorkerReader.readWorker(values));
        }
        statement.close();
        return workers;
    }

    public void addWorkerWithoutIdToDB(Worker worker, Token token) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(addWithoutID);
        statement.setString(1, worker.getName());
        statement.setLong(2, worker.getCoordinates().getX());
        statement.setFloat(3, worker.getCoordinates().getY());
        statement.setString(4, worker.getCreationDate().toString());
        statement.setLong(5, worker.getSalary());
        statement.setString(6, worker.getPosition().toString());
        statement.setString(7, worker.getStatus().toString());
        statement.setDouble(8, worker.getPerson().getHeight());
        statement.setString(9, worker.getPerson().getPassportID());
        statement.setFloat(10, worker.getPerson().getLocation().getX());
        statement.setInt(11, worker.getPerson().getLocation().getY());
        statement.setLong(12, worker.getPerson().getLocation().getZ());
        statement.setString(13, worker.getPerson().getLocation().getName());
        statement.setString(14, token.getUserName());
        statement.executeUpdate();
        statement.close();
    }

    public void addWorkerWithIdToDB(Worker worker, Token token) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(addWithID);
        statement.setInt(1,worker.getId());
        statement.setString(2, worker.getName());
        statement.setLong(3, worker.getCoordinates().getX());
        statement.setFloat(4, worker.getCoordinates().getY());
        statement.setString(5, worker.getCreationDate().toString());
        statement.setLong(6, worker.getSalary());
        statement.setString(7, worker.getPosition().toString());
        statement.setString(8, worker.getStatus().toString());
        statement.setDouble(9, worker.getPerson().getHeight());
        statement.setString(10, worker.getPerson().getPassportID());
        statement.setFloat(11, worker.getPerson().getLocation().getX());
        statement.setInt(12, worker.getPerson().getLocation().getY());
        statement.setLong(13, worker.getPerson().getLocation().getZ());
        statement.setString(14, worker.getPerson().getLocation().getName());
        statement.setString(15, token.getUserName());
        statement.executeUpdate();
        statement.close();
    }

    public int removeWorkerFromDB(Worker worker, Token token) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM workers WHERE id= ? AND \"user\"= ?;");
        statement.setInt(1, worker.getId());
        statement.setString(2, token.getUserName());
        int res = statement.executeUpdate();
        statement.close();
        return res;
    }

    public boolean checkUser(Token token) throws SQLException, InvalidInputException {
        Statement statement = connection.createStatement();
        String userName = token.getUserName();
        String userPassword = Encryptor.encryptString(token.getUserPassword());
        ResultSet result = statement.executeQuery("SELECT * FROM users;");
        boolean f = false;
        while (result.next()) {
            if (userName.equals(result.getString(1))) {
                if (userPassword.equals(result.getString(2))) {
                    f = true;
                } else {
                    throw new InvalidInputException("Wrong password");
                }
            }
        }
        statement.close();
        return f;
    }

    public void addUser(Token token) throws SQLException {
        String userName = token.getUserName();
        String userPassword = Encryptor.encryptString(token.getUserPassword());
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users (login, password) VALUES (?, ?);");
        statement.setString(1, userName);
        statement.setString(2, userPassword);
        statement.executeUpdate();
        statement.close();
    }

    public int clear(Token token) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM workers WHERE \"user\"= ?;");
        statement.setString(1, token.getUserName());
        int res = statement.executeUpdate();
        statement.close();
        return res;
    }

    public int getLastWorkerID() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT id FROM workers");
        ResultSet result = statement.executeQuery();
        int id = 0;
        while (result.next()) {
            id = result.getInt(1);
        }
        statement.close();
        return id;
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
