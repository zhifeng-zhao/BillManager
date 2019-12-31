package com.njwb.dao.rowsmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowsMapper {
    public abstract Object objectMapper(ResultSet rs) throws SQLException;
}
