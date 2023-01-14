package battleroyale.battleroyale.db;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;

public class MyResultSet implements ResultSet{
    private ResultSet set;
    private boolean closed = false;

    MyResultSet(ResultSet original) {
        this.set = original;
    }

    public boolean next() throws SQLException {
        return this.set.next();
    }

    public void close() throws SQLException {
        if (!this.closed) {
            this.closed = true;
            Statement statement = this.getStatement();
            if (statement == null) {
                this.set.close();
            } else {
                Connection connection = statement.getConnection();
                connection.close();
                statement.close();
            }
        }
    }

   public boolean wasNull() throws SQLException {
        return this.set.wasNull();
    }

    public String getString(int columnIndex) throws SQLException {
        return this.set.getString(columnIndex);
    }

    public boolean getBoolean(int columnIndex) throws SQLException {
        return this.set.getBoolean(columnIndex);
    }

    public byte getByte(int columnIndex) throws SQLException {
        return this.set.getByte(columnIndex);
    }

    public short getShort(int columnIndex) throws SQLException {
        return this.set.getShort(columnIndex);
    }

    public int getInt(int columnIndex) throws SQLException {
        return this.set.getInt(columnIndex);
    }

    public long getLong(int columnIndex) throws SQLException {
        return this.set.getLong(columnIndex);
    }

    public float getFloat(int columnIndex) throws SQLException {
        return this.set.getFloat(columnIndex);
    }

    public double getDouble(int columnIndex) throws SQLException {
        return this.set.getDouble(columnIndex);
    }

    public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
        return this.set.getBigDecimal(columnIndex, scale);
    }

    public byte[] getBytes(int columnIndex) throws SQLException {
        return this.set.getBytes(columnIndex);
    }

    public Date getDate(int columnIndex) throws SQLException {
        return this.set.getDate(columnIndex);
    }

    public Time getTime(int columnIndex) throws SQLException {
        return this.set.getTime(columnIndex);
    }

    public Timestamp getTimestamp(int columnIndex) throws SQLException {
        return this.set.getTimestamp(columnIndex);
    }

    public InputStream getAsciiStream(int columnIndex) throws SQLException {
        return this.set.getAsciiStream(columnIndex);
    }

    public InputStream getUnicodeStream(int columnIndex) throws SQLException {
        return this.set.getUnicodeStream(columnIndex);
    }

    public InputStream getBinaryStream(int columnIndex) throws SQLException {
        return this.set.getBinaryStream(columnIndex);
    }

    public String getString(String columnLabel) throws SQLException {
        return this.set.getString(columnLabel);
    }

    public boolean getBoolean(String columnLabel) throws SQLException {
        return this.set.getBoolean(columnLabel);
    }

    public byte getByte(String columnLabel) throws SQLException {
        return this.set.getByte(columnLabel);
    }

    public short getShort(String columnLabel) throws SQLException {
        return this.set.getShort(columnLabel);
    }

    public int getInt(String columnLabel) throws SQLException {
        return this.set.getInt(columnLabel);
    }

    public long getLong(String columnLabel) throws SQLException {
        return this.set.getLong(columnLabel);
    }

    public float getFloat(String columnLabel) throws SQLException {
        return this.set.getFloat(columnLabel);
    }

    public double getDouble(String columnLabel) throws SQLException {
        return this.set.getDouble(columnLabel);
    }

    public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
        return this.set.getBigDecimal(columnLabel, scale);
    }

    public byte[] getBytes(String columnLabel) throws SQLException {
        return this.set.getBytes(columnLabel);
    }

    public Date getDate(String columnLabel) throws SQLException {
        return this.set.getDate(columnLabel);
    }

    public Time getTime(String columnLabel) throws SQLException {
        return this.set.getTime(columnLabel);
    }

    public Timestamp getTimestamp(String columnLabel) throws SQLException {
        return this.set.getTimestamp(columnLabel);
    }

    public InputStream getAsciiStream(String columnLabel) throws SQLException {
        return this.set.getAsciiStream(columnLabel);
    }

    public InputStream getUnicodeStream(String columnLabel) throws SQLException {
        return this.set.getUnicodeStream(columnLabel);
    }

    public InputStream getBinaryStream(String columnLabel) throws SQLException {
        return this.set.getBinaryStream(columnLabel);
    }

    public SQLWarning getWarnings() throws SQLException {
        return this.set.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        this.set.clearWarnings();
    }

    public String getCursorName() throws SQLException {
        return this.set.getCursorName();
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return this.set.getMetaData();
    }

    public Object getObject(int columnIndex) throws SQLException {
        return this.set.getObject(columnIndex);
    }

    public Object getObject(String columnLabel) throws SQLException {
        return this.set.getObject(columnLabel);
    }

    public int findColumn(String columnLabel) throws SQLException {
        return this.set.findColumn(columnLabel);
    }

    public Reader getCharacterStream(int columnIndex) throws SQLException {
        return this.set.getCharacterStream(columnIndex);
    }

    public Reader getCharacterStream(String columnLabel) throws SQLException {
        return this.set.getCharacterStream(columnLabel);
    }

    public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
        return this.set.getBigDecimal(columnIndex);
    }

    public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
        return this.set.getBigDecimal(columnLabel);
    }

    public boolean isBeforeFirst() throws SQLException {
        return this.set.isBeforeFirst();
    }

    public boolean isAfterLast() throws SQLException {
        return this.set.isAfterLast();
    }

    public boolean isFirst() throws SQLException {
        return this.set.isFirst();
    }

    public boolean isLast() throws SQLException {
        return this.set.isLast();
    }

    public void beforeFirst() throws SQLException {
        this.set.beforeFirst();
    }

    public void afterLast() throws SQLException {
        this.set.afterLast();
    }

    public boolean first() throws SQLException {
        return this.set.first();
    }

    public boolean last() throws SQLException {
        return this.set.last();
    }

    public int getRow() throws SQLException {
        return this.set.getRow();
    }

    public boolean absolute(int row) throws SQLException {
        return this.set.absolute(row);
    }

    public boolean relative(int rows) throws SQLException {
        return this.set.relative(rows);
    }

    public boolean previous() throws SQLException {
        return this.set.previous();
    }

    public void setFetchDirection(int direction) throws SQLException {
        this.set.setFetchDirection(direction);
    }

    public int getFetchDirection() throws SQLException {
        return this.set.getFetchDirection();
    }

    public void setFetchSize(int rows) throws SQLException {
        this.set.setFetchSize(rows);
    }

    public int getFetchSize() throws SQLException {
        return this.set.getFetchSize();
    }

    public int getType() throws SQLException {
        return this.set.getType();
    }

    public int getConcurrency() throws SQLException {
        return this.set.getConcurrency();
    }

    public boolean rowUpdated() throws SQLException {
        return this.set.rowUpdated();
    }

    public boolean rowInserted() throws SQLException {
        return this.set.rowInserted();
    }

    public boolean rowDeleted() throws SQLException {
        return this.set.rowDeleted();
    }

    public void updateNull(int columnIndex) throws SQLException {
        this.set.updateNull(columnIndex);
    }

    public void updateBoolean(int columnIndex, boolean x) throws SQLException {
        this.set.updateBoolean(columnIndex, x);
    }

    public void updateByte(int columnIndex, byte x) throws SQLException {
        this.set.updateByte(columnIndex, x);
    }

    public void updateShort(int columnIndex, short x) throws SQLException {
        this.set.updateShort(columnIndex, x);
    }

    public void updateInt(int columnIndex, int x) throws SQLException {
        this.set.updateInt(columnIndex, x);
    }

    public void updateLong(int columnIndex, long x) throws SQLException {
        this.set.updateLong(columnIndex, x);
    }

    public void updateFloat(int columnIndex, float x) throws SQLException {
        this.set.updateFloat(columnIndex, x);
    }

    public void updateDouble(int columnIndex, double x) throws SQLException {
        this.set.updateDouble(columnIndex, x);
    }

    public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
        this.set.updateBigDecimal(columnIndex, x);
    }

    public void updateString(int columnIndex, String x) throws SQLException {
        this.set.updateString(columnIndex, x);
    }

    public void updateBytes(int columnIndex, byte[] x) throws SQLException {
        this.set.updateBytes(columnIndex, x);
    }

    public void updateDate(int columnIndex, Date x) throws SQLException {
        this.set.updateDate(columnIndex, x);
    }

    public void updateTime(int columnIndex, Time x) throws SQLException {
        this.set.updateTime(columnIndex, x);
    }

    public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
        this.set.updateTimestamp(columnIndex, x);
    }

    public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
        this.set.updateAsciiStream(columnIndex, x, length);
    }

    public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
        this.set.updateBinaryStream(columnIndex, x, length);
    }

    public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
        this.set.updateCharacterStream(columnIndex, x, length);
    }

    public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
        this.set.updateObject(columnIndex, x, scaleOrLength);
    }

    public void updateObject(int columnIndex, Object x) throws SQLException {
        this.set.updateObject(columnIndex, x);
    }

    public void updateNull(String columnLabel) throws SQLException {
        this.set.updateNull(columnLabel);
    }

    public void updateBoolean(String columnLabel, boolean x) throws SQLException {
        this.set.updateBoolean(columnLabel, x);
    }

    public void updateByte(String columnLabel, byte x) throws SQLException {
        this.set.updateByte(columnLabel, x);
    }

    public void updateShort(String columnLabel, short x) throws SQLException {
        this.set.updateShort(columnLabel, x);
    }

    public void updateInt(String columnLabel, int x) throws SQLException {
        this.set.updateInt(columnLabel, x);
    }

    public void updateLong(String columnLabel, long x) throws SQLException {
        this.set.updateLong(columnLabel, x);
    }

    public void updateFloat(String columnLabel, float x) throws SQLException {
        this.set.updateFloat(columnLabel, x);
    }

    public void updateDouble(String columnLabel, double x) throws SQLException {
        this.set.updateDouble(columnLabel, x);
    }

    public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
        this.set.updateBigDecimal(columnLabel, x);
    }

    public void updateString(String columnLabel, String x) throws SQLException {
        this.set.updateString(columnLabel, x);
    }

    public void updateBytes(String columnLabel, byte[] x) throws SQLException {
        this.set.updateBytes(columnLabel, x);
    }

    public void updateDate(String columnLabel, Date x) throws SQLException {
        this.set.updateDate(columnLabel, x);
    }

    public void updateTime(String columnLabel, Time x) throws SQLException {
        this.set.updateTime(columnLabel, x);
    }

    public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
        this.set.updateTimestamp(columnLabel, x);
    }

    public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
        this.set.updateAsciiStream(columnLabel, x, length);
    }

    public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
        this.set.updateBinaryStream(columnLabel, x, length);
    }

    public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
        this.set.updateCharacterStream(columnLabel, reader, length);
    }

    public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
        this.set.updateObject(columnLabel, x, scaleOrLength);
    }

    public void updateObject(String columnLabel, Object x) throws SQLException {
        this.set.updateObject(columnLabel, x);
    }

    public void insertRow() throws SQLException {
        this.set.insertRow();
    }

    public void updateRow() throws SQLException {
        this.set.updateRow();
    }

    public void deleteRow() throws SQLException {
        this.set.deleteRow();
    }

    public void refreshRow() throws SQLException {
        this.set.refreshRow();
    }

    public void cancelRowUpdates() throws SQLException {
        this.set.cancelRowUpdates();
    }

    public void moveToInsertRow() throws SQLException {
        this.set.moveToInsertRow();
    }

    public void moveToCurrentRow() throws SQLException {
        this.set.moveToCurrentRow();
    }

    public Statement getStatement() throws SQLException {
        return this.set.getStatement();
    }

    public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
        return this.set.getObject(columnIndex, map);
    }

    public Ref getRef(int columnIndex) throws SQLException {
        return this.set.getRef(columnIndex);
    }

    public Blob getBlob(int columnIndex) throws SQLException {
        return this.set.getBlob(columnIndex);
    }

    public Clob getClob(int columnIndex) throws SQLException {
        return this.set.getClob(columnIndex);
    }

    public Array getArray(int columnIndex) throws SQLException {
        return this.set.getArray(columnIndex);
    }

    public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
        return this.set.getObject(columnLabel, map);
    }

    public Ref getRef(String columnLabel) throws SQLException {
        return this.set.getRef(columnLabel);
    }

    public Blob getBlob(String columnLabel) throws SQLException {
        return this.set.getBlob(columnLabel);
    }

    public Clob getClob(String columnLabel) throws SQLException {
        return this.set.getClob(columnLabel);
    }

    public Array getArray(String columnLabel) throws SQLException {
        return this.set.getArray(columnLabel);
    }

    public Date getDate(int columnIndex, Calendar cal) throws SQLException {
        return this.set.getDate(columnIndex, cal);
    }

    public Date getDate(String columnLabel, Calendar cal) throws SQLException {
        return this.set.getDate(columnLabel, cal);
    }

    public Time getTime(int columnIndex, Calendar cal) throws SQLException {
        return this.set.getTime(columnIndex, cal);
    }

    public Time getTime(String columnLabel, Calendar cal) throws SQLException {
        return this.set.getTime(columnLabel, cal);
    }

    public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
        return this.set.getTimestamp(columnIndex, cal);
    }

    public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
        return this.set.getTimestamp(columnLabel, cal);
    }

    public URL getURL(int columnIndex) throws SQLException {
        return this.set.getURL(columnIndex);
    }

    public URL getURL(String columnLabel) throws SQLException {
        return this.set.getURL(columnLabel);
    }

    public void updateRef(int columnIndex, Ref x) throws SQLException {
        this.set.updateRef(columnIndex, x);
    }

    public void updateRef(String columnLabel, Ref x) throws SQLException {
        this.set.updateRef(columnLabel, x);
    }

    public void updateBlob(int columnIndex, Blob x) throws SQLException {
        this.set.updateBlob(columnIndex, x);
    }

    public void updateBlob(String columnLabel, Blob x) throws SQLException {
        this.set.updateBlob(columnLabel, x);
    }

    public void updateClob(int columnIndex, Clob x) throws SQLException {
        this.set.updateClob(columnIndex, x);
    }

    public void updateClob(String columnLabel, Clob x) throws SQLException {
        this.set.updateClob(columnLabel, x);
    }

    public void updateArray(int columnIndex, Array x) throws SQLException {
        this.set.updateArray(columnIndex, x);
    }

    public void updateArray(String columnLabel, Array x) throws SQLException {
        this.set.updateArray(columnLabel, x);
    }

    public RowId getRowId(int columnIndex) throws SQLException {
        return this.set.getRowId(columnIndex);
    }

    public RowId getRowId(String columnLabel) throws SQLException {
        return this.set.getRowId(columnLabel);
    }

    public void updateRowId(int columnIndex, RowId x) throws SQLException {
        this.set.updateRowId(columnIndex, x);
    }

    public void updateRowId(String columnLabel, RowId x) throws SQLException {
        this.set.updateRowId(columnLabel, x);
    }

    public int getHoldability() throws SQLException {
        return this.set.getHoldability();
    }

    public boolean isClosed() throws SQLException {
        return this.set.isClosed();
    }

    public void updateNString(int columnIndex, String nString) throws SQLException {
        this.set.updateNString(columnIndex, nString);
    }

    public void updateNString(String columnLabel, String nString) throws SQLException {
        this.set.updateNString(columnLabel, nString);
    }

    public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
        this.set.updateNClob(columnIndex, nClob);
    }

    public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
        this.set.updateNClob(columnLabel, nClob);
    }

    public NClob getNClob(int columnIndex) throws SQLException {
        return this.set.getNClob(columnIndex);
    }

    public NClob getNClob(String columnLabel) throws SQLException {
        return this.set.getNClob(columnLabel);
    }

    public SQLXML getSQLXML(int columnIndex) throws SQLException {
        return this.set.getSQLXML(columnIndex);
    }

    public SQLXML getSQLXML(String columnLabel) throws SQLException {
        return this.set.getSQLXML(columnLabel);
    }

    public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
        this.set.updateSQLXML(columnIndex, xmlObject);
    }

    public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
        this.set.updateSQLXML(columnLabel, xmlObject);
    }

    public String getNString(int columnIndex) throws SQLException {
        return this.set.getNString(columnIndex);
    }

    public String getNString(String columnLabel) throws SQLException {
        return this.set.getNString(columnLabel);
    }

    public Reader getNCharacterStream(int columnIndex) throws SQLException {
        return this.set.getNCharacterStream(columnIndex);
    }

    public Reader getNCharacterStream(String columnLabel) throws SQLException {
        return this.set.getNCharacterStream(columnLabel);
    }

    public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        this.set.updateNCharacterStream(columnIndex, x, length);
    }

    public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        this.set.updateNCharacterStream(columnLabel, reader, length);
    }

    public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
        this.set.updateAsciiStream(columnIndex, x, length);
    }

    public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
        this.set.updateBinaryStream(columnIndex, x, length);
    }

    public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
        this.set.updateCharacterStream(columnIndex, x, length);
    }

    public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
        this.set.updateAsciiStream(columnLabel, x, length);
    }

    public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
        this.set.updateBinaryStream(columnLabel, x, length);
    }

    public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
        this.set.updateCharacterStream(columnLabel, reader, length);
    }

    public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
        this.set.updateBlob(columnIndex, inputStream, length);
    }

    public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
        this.set.updateBlob(columnLabel, inputStream, length);
    }

    public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
        this.set.updateClob(columnIndex, reader, length);
    }

    public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
        this.set.updateClob(columnLabel, reader, length);
    }

    public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
        this.set.updateNClob(columnIndex, reader, length);
    }

    public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
        this.set.updateNClob(columnLabel, reader, length);
    }

    public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
        this.set.updateNCharacterStream(columnIndex, x);
    }

    public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
        this.set.updateNCharacterStream(columnLabel, reader);
    }

    public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
        this.set.updateAsciiStream(columnIndex, x);
    }

    public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
        this.set.updateBinaryStream(columnIndex, x);
    }

    public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
        this.set.updateCharacterStream(columnIndex, x);
    }

    public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
        this.set.updateAsciiStream(columnLabel, x);
    }

    public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
        this.set.updateBinaryStream(columnLabel, x);
    }

    public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
        this.set.updateCharacterStream(columnLabel, reader);
    }

    public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
        this.set.updateBlob(columnIndex, inputStream);
    }

    public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
        this.set.updateBlob(columnLabel, inputStream);
    }

    public void updateClob(int columnIndex, Reader reader) throws SQLException {
        this.set.updateClob(columnIndex, reader);
    }

    public void updateClob(String columnLabel, Reader reader) throws SQLException {
        this.set.updateClob(columnLabel, reader);
    }

    public void updateNClob(int columnIndex, Reader reader) throws SQLException {
        this.set.updateNClob(columnIndex, reader);
    }

    public void updateNClob(String columnLabel, Reader reader) throws SQLException {
        this.set.updateNClob(columnLabel, reader);
    }

    public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
        return this.set.getObject(columnIndex, type);
    }

    public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
        return this.set.getObject(columnLabel, type);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return this.set.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return this.set.isWrapperFor(iface);
    }
}
