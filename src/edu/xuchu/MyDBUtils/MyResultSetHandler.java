package edu.xuchu.MyDBUtils;

import java.sql.ResultSet;

/**
 * CallBack specification
 * @author xuchu
 *
 */
public interface MyResultSetHandler<T> {
	T query(ResultSet rs);
}
