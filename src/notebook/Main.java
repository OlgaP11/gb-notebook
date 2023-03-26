package notebook;

import notebook.controller.UserController;
import notebook.repository.dao.impl.FileOperation;
import notebook.model.User;
import notebook.repository.GBRepository;
import notebook.repository.impl.UserRepository;
import notebook.repository.impl.UserRepositoryWithLog;
import notebook.view.UserView;

import static notebook.util.DBConnector.DB_PATH;
import static notebook.util.DBConnector.createDB;

public class Main {
    public static void main(String[] args) {
        createDB();
        FileOperation fileOperation = new FileOperation(DB_PATH);
        GBRepository<User, Long> repository = new UserRepositoryWithLog(fileOperation);
        UserController controller = new UserController(repository);
        UserView view = new UserView(controller);
        view.run();
    }
}
