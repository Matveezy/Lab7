package client.user;

import server.Response;

import java.util.Locale;
import java.util.Scanner;

public class UserAuthReg {
    private UserFieldsReader userFieldsReader;
    private User user;
    private Scanner scanner;

    public UserAuthReg(User user, Scanner scanner) {
        this.user = user;
        this.userFieldsReader = new UserFieldsReader(this.user);
        this.scanner = scanner;
    }
    public void userAuthReg(){
        System.out.println("Введите register, если хотите зарегистрироваться\n" +
                "Введите authorize , если хотите войти!");
        while (true){
            String userInput = scanner.nextLine();
            if (userInput.trim().toLowerCase(Locale.ROOT).equals("register")){
                userRegistration();
                break;
            }
            if (userInput.trim().toLowerCase(Locale.ROOT).equals("authorize")){
                authorize();
                break;
            }
            System.err.println("Введите register или authorize!");
        }
    }
    public void authorize() {
        user.setRegistration(false);
        user.setAuthorization(true);
        System.out.println("Авторизация");
        user.setUsername(userFieldsReader.readUsername());
        user.setPassword(userFieldsReader.readPassword());
        System.out.println("-------------------------------------------------");
    }

    public void userRegistration() {
        user.setRegistration(true);
        System.out.println("Регистрация пользователя!\n");
        user.setUsername(userFieldsReader.readUsername());
        user.setPassword(userFieldsReader.readPassword());
        System.out.println("-------------------------------------------------");
    }

    public boolean userExist(Response response){
        if (response.getBody().equals("Пользователя с таким именем нет!")) return true;
        return false;
    }
}
