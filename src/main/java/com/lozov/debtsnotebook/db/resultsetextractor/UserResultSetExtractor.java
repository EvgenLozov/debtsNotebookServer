package com.lozov.debtsnotebook.db.resultsetextractor;

import com.lozov.debtsnotebook.db.ResultSetExtractor;
import com.lozov.debtsnotebook.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class UserResultSetExtractor implements ResultSetExtractor<User> {
    @Override
    public User read(ResultSet resultSet) throws SQLException {
        User user = null;
        if (resultSet.next()){
            user = new User();
            user.setId(resultSet.getString(1));
            user.setUsername(resultSet.getString(2));
            user.setPassword(resultSet.getNString(3));
            user.setEmail(resultSet.getString(4));
        }

        return user;
    }
}
