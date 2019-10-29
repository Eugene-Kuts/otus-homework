package ru.otus.hw10;

import lombok.SneakyThrows;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw10.dataClasses.Account;
import ru.otus.hw10.dataClasses.AddressDataSet;
import ru.otus.hw10.dataClasses.PhoneDataSet;
import ru.otus.hw10.dataClasses.User;
import ru.otus.hw10.executor.DBExecutorHibernate;
import ru.otus.hw10.executor.DBExecutorHibernateImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("DBExecutorHibernateTests")
public class DBExecutorHibernateTest {
    private static final String HIBERNATE_CFG_FILE = "hibernate-test.cfg.xml";

    private DBExecutorHibernate dbExecutor;

    @BeforeEach
   // @SneakyThrows
    void setUp() {
        final Configuration configuration = new Configuration().configure(HIBERNATE_CFG_FILE);
        final StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        final Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(AddressDataSet.class)
                .addAnnotatedClass(PhoneDataSet.class)
                .addAnnotatedClass(Account.class)
                .getMetadataBuilder().build();

        dbExecutor = new DBExecutorHibernateImpl(metadata.getSessionFactoryBuilder().build());
    }

    @Test
    @DisplayName("Создаем User'а с полями null")
    void createNullUserTest() {
        final User user = new User();
        dbExecutor.create(user);
        User loadedUser = (User) dbExecutor.load(user.getId(), User.class);
        System.out.println("=" + dbExecutor.load(user.getId(), User.class));
        //assertThat(dbExecutor.load(user.getId(), User.class)).isEqualToComparingFieldByField(user);
        assertThat(loadedUser).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    @DisplayName("Создаем User'а")
    void createUser() {
        final User user = new User();
        user.setName("Илья");
        user.setAge(77);
        user.setAddressDataSet(new AddressDataSet("Остоженка"));
        final List<PhoneDataSet> phoneDataSets1 = new ArrayList<>();
        phoneDataSets1.add(new PhoneDataSet("84957777777"));
        phoneDataSets1.add(new PhoneDataSet("84955555555"));
        user.setPhoneDataSet(phoneDataSets1);

        dbExecutor.create(user);
        assertThat(dbExecutor.load(user.getId(), User.class)).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    @DisplayName("Создаем и обновляем User'а")
    void createAndUpdateUser() {
        final User user = new User();
        user.setName("Илья");
        user.setAge(77);
        user.setAddressDataSet(new AddressDataSet("Остоженка"));
        final List<PhoneDataSet> phoneDataSets1 = new ArrayList<>();
        phoneDataSets1.add(new PhoneDataSet("84957777777"));
        phoneDataSets1.add(new PhoneDataSet("84955555555"));
        user.setPhoneDataSet(phoneDataSets1);

        dbExecutor.create(user);

        user.setName("Федор");
        user.getAddressDataSet().setStreet("Никольский переулок");
        final List<PhoneDataSet> phoneDataSets2 = new ArrayList<>();
        phoneDataSets2.add(new PhoneDataSet("5555555555"));
        phoneDataSets2.add(new PhoneDataSet("7777777777"));
        user.getPhoneDataSet().remove(phoneDataSets2);

        dbExecutor.update(user);

        assertThat(dbExecutor.load(user.getId(), User.class)).isEqualToComparingFieldByFieldRecursively(user);
    }

    @Test
    @DisplayName("Создаем Account")
    void createAccount() {
        final Account account = new Account("Премиальный", new BigDecimal("555684.71"));
        dbExecutor.create(account);
        assertThat(dbExecutor.load(account.getNo(), Account.class)).isEqualToComparingFieldByField(account);
    }

    @Test
    @DisplayName("Создаем Account с полями null")
    void createNullAccount() {
        final Account account = new Account();
        dbExecutor.create(account);
        assertThat(dbExecutor.load(account.getNo(), Account.class)).isEqualToComparingFieldByField(account);
    }

    @Test
    @DisplayName("Создаем и обновляем Account")
    void updateAccount() {
        final Account account = new Account();
        account.setType("Максимальный");
        account.setRest(new BigDecimal("555.00"));

        dbExecutor.create(account);

        account.setType("Премиальный");
        account.setRest(new BigDecimal("7777.00"));

        dbExecutor.update(account);

        assertThat(dbExecutor.load(account.getNo(), Account.class)).isEqualTo(account);
    }

    @Test
    @DisplayName("Пытаемся получить User'а по несуществующему ID")
    void loadUserForNotExistingId() {
        assertThat(dbExecutor.load(333, User.class)).isNull();
    }
}
