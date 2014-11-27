package me.chengtx.webapp.dao;

import me.chengtx.webapp.model.User;

import java.util.List;

/**
 * Created by chengt4 on 11/26/2014.
 */
public interface UserStore {

    public List<User> getAllUsers();
    public boolean createUser();
    public boolean updateUser(User user);
    public boolean deleteUser(String uid);
}
