package com.lozov.debtsnotebook.db.sqloperation;

import com.lozov.debtsnotebook.db.SqlOperation;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

/**
 * Created by Yevhen on 2015-05-25.
 */
public class GetUsersByIdsSqlOperation implements SqlOperation {

    private Set<String> ids;

    public GetUsersByIdsSqlOperation(Set<String> ids) {
        this.ids = ids;
    }

    @Override
    public String getRawSql() {
        String sqlQuery = "SELECT * FROM user where id in (";
        for( String id : ids ){
            sqlQuery += "?,";
        }
        return sqlQuery.substring( 0, sqlQuery.length()  ) + ");";
    }

    @Override
    public void prepare(PreparedStatement statement) throws SQLException {
        int index = 0;
        for (String id : ids) {
            statement.setString(index++, id);
        }
    }
}
