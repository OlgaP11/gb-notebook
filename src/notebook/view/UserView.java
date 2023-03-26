package notebook.view;

import notebook.controller.UserController;
import notebook.model.User;
import notebook.util.Commands;

import java.util.List;
import java.util.Scanner;

public class UserView {
    private final UserController userController;

    public UserView(UserController userController) {
        this.userController = userController;
    }

    public void run(){
        Commands com;

        while (true) {
            String command = prompt("������� �������: ");
            com = Commands.valueOf(command);
            if (com == Commands.EXIT) return;
            switch (com) {
                case CREATE:
                    userController.saveUser(requestData());
                    break;
                case READ:
                    String id = prompt("������������� ������������: ");
                    try {
                        User user = userController.readUser(Long.parseLong(id));
                        System.out.println(user);
                        System.out.println();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case LIST:
                    List <User> users = userController.getAllUsers();
                    for (User user: users) {
                        System.out.println(user);
                    }
                    break;
                case UPDATE:
                    long userId= Long.parseLong(prompt("������������� ������������: "));
                    User user = requestData();
                    userController.update(userId, user);
                    break;
                case DELETE:
                    long delId= Long.parseLong(prompt("������������� ������������ ��� ��� ��������: "));
                    userController.delete(delId);
                    break;
            }
        }
    }

    private String prompt(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();
    }

//    private User requestData(){
//        String userFName = prompt("���: ");
//        String userLName = prompt("�������: ");
//        String userPhone = prompt("����� ��������: ");
//        User user = new User(userFName, userLName, userPhone);
//        return user;
//    }
    private User requestData(){
        return User.builder()
                .firstName(prompt("���: "))
                .lastName(prompt("�������: "))
                .phone(prompt("����� ��������: ")).build();
    }
}

