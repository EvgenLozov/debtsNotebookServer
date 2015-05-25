package com.lozov.debtsnotebook.db.resultsetextractor;

import com.lozov.debtsnotebook.db.ResultSetExtractor;
import com.lozov.debtsnotebook.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class UsersResultSetExtractor implements ResultSetExtractor<List<User>> {
    @Override
    public List<User> read(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getString(1));
            user.setUsername(resultSet.getString(2));
            user.setPassword(resultSet.getNString(3));
            user.setEmail(resultSet.getString(4));
            users.add(user);
        }
        return users;
    }
}
