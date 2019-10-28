package ru.otus.hw09;

import org.junit.jupiter.api.*;
import ru.otus.hw09.dataClasses.Account;
import ru.otus.hw09.dataClasses.User;
import ru.otus.hw09.executor.DBExecutor;
import ru.otus.hw09.executor.DBExecutorImpl;
import lombok.SneakyThrows;
import java.math.BigDecimal;
import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;

public class DBExecutorTests {
    private DBExecutor<User> dbExecutorUser;
    private DBExecutor<Account> dbExecutorAccount;
    private Connection connection;
    private static final String URL = "jdbc:h2:mem:";

    @BeforeEach
    @SneakyThrows
    void setUp() {
        connection = DriverManager.getConnection(URL);
        connection.setAutoCommit(false);

        final PreparedStatement preparedStatementUser = connection.prepareStatement("create table User(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))");
        preparedStatementUser.executeUpdate();

        final PreparedStatement preparedStatementAccount = connection.prepareStatement("create table Account(no bigint(20) NOT NULL auto_increment, type varchar(255), rest number)");
        preparedStatementAccount.executeUpdate();

        connection.commit();

        dbExecutorUser = new DBExecutorImpl(connection, User.class);
        dbExecutorAccount = new DBExecutorImpl(connection, Account.class);
    }

    @AfterEach
    @SneakyThrows
    void closeConnection() {
        connection.close();
    }

    @Test
    @DisplayName("User: Create and Load")
    @SneakyThrows
    void createAndLoadUser() {
        final User user = new User(1, "Иван", 22);
        dbExecutorUser.create(user);
        assertThat(dbExecutorUser.load(1, User.class)).isEqualTo(user);
    }

    @Test
    @DisplayName("Account: Сreate and Load")
    @SneakyThrows
    void createAndSelectAccount() {
        final Account account = new Account(1, "Премиальный", new BigDecimal(777));
        dbExecutorAccount.create(account);
        assertThat(dbExecutorAccount.load(1, Account.class)).isEqualTo(account);
    }

    @Test
    @DisplayName("User: Create, Update and Load")
    @SneakyThrows
    void updateUser() {
        final User firstUser = new User(1, "Пётр", 21);
        dbExecutorUser.create(firstUser);
        final User userForUpdate = new User(1, "Николай", 30);
        dbExecutorUser.update(userForUpdate);
        assertThat(dbExecutorUser.load(1, User.class)).isEqualTo(userForUpdate);
    }

    @Test
    @DisplayName("Account: Create, Update and Load")
    @SneakyThrows
    void updateAccount() {
        final Account firstAccount = new Account(1, "Классический", new BigDecimal("555.55"));
        dbExecutorAccount.create(firstAccount);
        final Account accountForUpdate = new Account(1, "Популярный", new BigDecimal("444.44"));
        dbExecutorAccount.update(accountForUpdate);
        assertThat(dbExecutorAccount.load(1, Account.class)).isEqualTo(accountForUpdate);
    }

}
