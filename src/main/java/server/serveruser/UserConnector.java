package server.serveruser;

import client.user.User;
import server.Response;
import server.database.DataBaseManager;

public class UserConnector {

    public String connectUser(User user, Response response){
        if (user.isRegistration()){
            if (DataBaseManager.addUserToDataBase(user.getUsername(),user.getPassword())){
                response.setRegistered(true);
                return "Пользователь зарегистрирован!";
            } else {
                response.setRegistered(false);
                return "Пользователь с таким именем уже есть!";
            }
        }
        if (user.isAuthorization()){
                String resp = DataBaseManager.authorize(user.getUsername(),user.getPassword());
                if (resp.equals("Авторазиция прошла успешно!")) {
                    response.setAuthorized(true);
                    return "Авторазиция прошла успешно!";
                }
                return resp;
        }
        return "Authorize";
    }
}
