//package server.database;
//
//import lib.collection.Dragon;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public final class DataBaseObjectUpdater {
//
//    public static boolean updateById(Dragon dragon, Connection connection) {
//        String sql = "UPDATE public.dragons set (name,x,y,age,color,type,character,depth,treasures) = (?,?,?,?,?,?,?,?,?) where id=?";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, dragon.getName());
//            preparedStatement.setInt(2, dragon.getCoordinates().getX());
//            preparedStatement.setDouble(3, dragon.getCoordinates().getY());
//            preparedStatement.setInt(4, dragon.getAge());
//            preparedStatement.setString(5, dragon.getColor().toString());
//            preparedStatement.setString(6, dragon.getType().toString());
//            preparedStatement.setString(7, dragon.getCharacter().toString());
//            preparedStatement.setFloat(8, dragon.getCave().getDepth());
//            preparedStatement.setFloat(9, dragon.getCave().getNumberOfTreasures());
//            preparedStatement.setLong(10, dragon.getId());
//            if (preparedStatement.executeUpdate() != 0) {
//                return true;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
//}
