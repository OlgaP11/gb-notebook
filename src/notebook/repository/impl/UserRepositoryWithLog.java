package notebook.repository.impl;

import notebook.logger.Logger;
import notebook.logger.impl.ConsoleLog;
import notebook.model.User;
import notebook.repository.GBRepository;
import notebook.repository.dao.impl.FileOperation;
import notebook.repository.mapper.impl.UserMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryWithLog implements GBRepository<User, Long> {
    private final UserMapper mapper;
    private final FileOperation operation;
    private final Logger logger;

    public UserRepositoryWithLog(FileOperation operation) {
        this.mapper = new UserMapper();
        this.operation = operation;
        this.logger = new ConsoleLog();
    }

    @Override
    public List<User> findAll() {
        List<String> lines = operation.readAll();
        List<User> users = new ArrayList<>();
        for (String line : lines) {
            users.add(mapper.toOutput(line));
        }
        return users;
    }

    @Override
    public User create(User user) {
        List<User> users = findAll();
        long max = 0L;
        for (User u : users) {
            long id = u.getId();
            if (max < id){
                max = id;
            }
        }
        long next = max + 1;
        user.setId(next);
        users.add(user);
        List<String> lines = new ArrayList<>();
        for (User u: users) {
            lines.add(mapper.toInput(u));
        }
        operation.saveAll(lines);
        logger.log("User was created: " + user);
        return user;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> update(Long id, User update) {
        try {
            List<User> users = findAll();
            User updateUser = users.stream().filter(u -> u.getId().equals(id)).findFirst().get(); // получим обновляемого юзера
            updateUser.setFirstName(update.getFirstName());
            updateUser.setLastName(update.getLastName());
            updateUser.setPhone(update.getPhone());
            List<String> list = new ArrayList<>();
            for (User user: users) {
                list.add(mapper.toInput(user));
            }
            operation.saveAll(list);
            logger.log("User was update: " + update);
            return Optional.of(updateUser);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        try{
            List<User> users = findAll();
            User delUser = users.stream().filter(u -> u.getId().equals(id)).findFirst().get();
            for (User user: users) {
                if (user.getId() > delUser.getId())
                    user.setId(user.getId()-1);
            }
            users.remove(delUser);
            List<String> list = new ArrayList<>();
            for (User user: users) {
                list.add(mapper.toInput(user));
            }
            operation.saveAll(list);
            logger.log("User was deleted: " + delUser);
            return  true;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public UserMapper getMapper() {
        return mapper;
    }
}
