package ru.otus.hw09.executor;

import java.lang.reflect.Field;
import java.sql.*;
import static ru.otus.hw09.utils.query.QueryHelper.*;
import static ru.otus.hw09.utils.reflection.ReflectionHelper.*;

/** {@inheritDoc} */
public class DBExecutorImpl<T> implements DBExecutor<T>{

    private final Connection connection;
    private final Class<T> clazz;
    private String createQuery;
    private String updateQuery;
    private String selectQuery;
    private Field[] allFields;
    private Field annotatedField;
    private static final String SAVEPOINT_NAME = "savePointName";

    public DBExecutorImpl(Connection connection, Class<T> clazz) {
        this.connection = connection;
        this.clazz = clazz;
        getReflectionParameters();
        createQueryes();
    }

    private void getReflectionParameters() {
        allFields = getObjectFields(clazz);
        annotatedField = getAnnotatedField(clazz);
    }

    private void createQueryes() {
        createQuery = makeCreateQuery(clazz, allFields, annotatedField);
        updateQuery = makeUpdateQuery(clazz, allFields, annotatedField);
        selectQuery = makeSelectQuery(clazz, annotatedField);
    }

    /** {@inheritDoc} */
    @Override
    public void create(T objectData) throws SQLException {
        Savepoint savePoint = connection.setSavepoint(SAVEPOINT_NAME);
        if (annotatedField.getName() != null) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS)) {
                for (int i = 0; i < allFields.length; i++)
                    if (!allFields[i].getName().equals(annotatedField.getName())) {
                        preparedStatement.setObject(i, allFields[i].get(objectData));
                    }
                preparedStatement.executeUpdate();
            } catch (Exception exeption) {
                connection.rollback(savePoint);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void update(T objectData) throws SQLException {
        Savepoint savePoint = connection.setSavepoint(SAVEPOINT_NAME);
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            for (int i = 0; i < allFields.length; i++)
                if (!allFields[i].getName().equals(annotatedField.getName())) {
                    preparedStatement.setObject(i, allFields[i].get(objectData));
                }
            preparedStatement.setLong(allFields.length, (long) annotatedField.get(objectData));
            preparedStatement.executeUpdate();
        } catch (Exception exeption) {
            this.connection.rollback(savePoint);
        }
    }

    /** {@inheritDoc} */
    @Override
    public <T1> T1 load(long id, Class<T1> clazz) throws SQLException {
        Savepoint savePoint = connection.setSavepoint(SAVEPOINT_NAME);
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {
            preparedStatement.setLong(1, id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next())
                    return setObjectFields(clazz, rs);
            }
        } catch (Exception exeption) {
            connection.rollback(savePoint);
        }
        return null;
    }
}

