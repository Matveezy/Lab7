package server.database;

import client.user.User;
import lib.collection.*;
import lib.collectionworker.CollectionManager;
import server.util.ConnectionManager;
import server.util.DateConverter;

import java.sql.*;
import java.time.LocalDate;

public class DataBaseManager {
    private static Connection connection = null;
    private static Statement statement = null;
    private static boolean connected;
    private static final String ADD = "INSERT INTO DRAGONS (username , name , x ,y,date,age,color,type,character,depth,treasures) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
    private static final String LOAD = "SELECT * from public.dragons";
    private static final String FIND_BY_USERNAME = "SELECT (username) from users WHERE username=?";
    private static final String INSERT_USER = "INSERT into users (username, password) VALUES (? , ?)";
    private static final String GET_PASSWORD_BY_USERNAME = "select (password) FROM users where username=?";
    private static final String DELETE = "DELETE from public.dragons WHERE username=?";
    private static final String DELETE_BY_ID = "DELETE from public.dragons WHERE id=?";
    private static final String UPDATE = "UPDATE public.dragons set (name,x,y,age,color,type,character,depth,treasures) = (?,?,?,?,?,?,?,?,?) where id=?";

    static {
        connect();
    }

    public static boolean connect() {
        try {
            connection = ConnectionManager.open();
            if (connection != null) {
                System.out.println("Успешное подключение к базе данных!");
                statement = connection.createStatement();
                connected = true;
                return true;
            }
            if (connection == null) {
                System.out.println("Connection is null!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean addDragon(Dragon dragon, User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, dragon.getName());
            preparedStatement.setInt(3, dragon.getCoordinates().getX());
            preparedStatement.setDouble(4, dragon.getCoordinates().getY());
            preparedStatement.setDate(5, DateConverter.convertToDate(dragon.getCreationDate()));
            preparedStatement.setInt(6, dragon.getAge());
            preparedStatement.setString(7, dragon.getColor().toString());
            preparedStatement.setString(8, dragon.getType().toString());
            preparedStatement.setString(9, dragon.getCharacter().toString());
            preparedStatement.setFloat(10, dragon.getCave().getDepth());
            preparedStatement.setFloat(11, dragon.getCave().getNumberOfTreasures());

            if (preparedStatement.executeUpdate() != 0) {
                var resultSet = preparedStatement.getGeneratedKeys();
                if (resultSet.next()) {
                    Long id = resultSet.getLong("id");
                    dragon.setId(id);
                }
                System.out.println("Dragon added!");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void load(CollectionManager collectionManager) {
        try {
            ResultSet resultSet = getStatement().executeQuery(LOAD);
            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String name = resultSet.getString("name");
                Long id = resultSet.getLong("id");
                Coordinates coordinates = new Coordinates(
                        resultSet.getInt("x"),
                        resultSet.getDouble("y")
                );
                LocalDate localDate = DateConverter.convertToLocalDate(resultSet.getDate("date"));
                Integer age = resultSet.getInt("age");
                Color color = Color.valueOf(resultSet.getString("color").toUpperCase());
                DragonType dragonType = DragonType.valueOf(resultSet.getString("type").toUpperCase());
                DragonCharacter dragonCharacter = DragonCharacter.valueOf(resultSet.getString("character").toUpperCase());
                DragonCave dragonCave = new DragonCave(
                        resultSet.getFloat("depth"),
                        resultSet.getFloat("treasures")
                );
                Dragon dragon = new Dragon(
                        id,
                        name,
                        coordinates,
                        localDate,
                        age,
                        color,
                        dragonType,
                        dragonCharacter,
                        dragonCave,
                        username
                );
                collectionManager.getDragons().add(dragon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isThatUserNameContains(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USERNAME);
            preparedStatement.setString(1, username);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return true;
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static boolean addUserToDataBase(String username, String password) {
        if (isThatUserNameContains(username)) {
            return false;
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static String authorize(String username, String password) {
        if (!isThatUserNameContains(username)) return "Пользователя с таким именем нет!";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_PASSWORD_BY_USERNAME);
            preparedStatement.setString(1, username);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    return "Авторазиция прошла успешно!";
                } else return "Неправильно введен пароль!";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static boolean clear(String username) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setString(1, username);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean removeById(Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID);
            preparedStatement.setLong(1, id);
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateById(Dragon dragon) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, dragon.getName());
            preparedStatement.setInt(2, dragon.getCoordinates().getX());
            preparedStatement.setDouble(3, dragon.getCoordinates().getY());
            preparedStatement.setInt(4, dragon.getAge());
            preparedStatement.setString(5, dragon.getColor().toString());
            preparedStatement.setString(6, dragon.getType().toString());
            preparedStatement.setString(7, dragon.getCharacter().toString());
            preparedStatement.setFloat(8, dragon.getCave().getDepth());
            preparedStatement.setFloat(9, dragon.getCave().getNumberOfTreasures());
            preparedStatement.setLong(10, dragon.getId());
            if (preparedStatement.executeUpdate() != 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Statement getStatement() {
        return statement;
    }

    public static boolean isConnected() {
        return connected;
    }

}
