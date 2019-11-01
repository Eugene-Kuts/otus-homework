package ru.otus.hw11;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.hw11.cache.Cache;
import ru.otus.hw11.cache.CacheImpl;
import ru.otus.hw11.dataClasses.AddressDataSet;
import ru.otus.hw11.dataClasses.PhoneDataSet;
import ru.otus.hw11.dataClasses.User;
import ru.otus.hw11.executor.DBExecutorHibernate;
import ru.otus.hw11.executor.UserDBExecutorHibernateImpl;
import ru.otus.hw11.utils.SessionFactories;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Сравнение скорости работы с кешом и медленным подключением к БД")
public class UserDBExecutorHibernateWithCacheTest {

    private DBExecutorHibernate<User> userDBExecutor;
    private Cache<Long, User> cache;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        cache = new CacheImpl<>();
        userDBExecutor = new UserDBExecutorHibernateImpl(SessionFactories.get(),cache);
    }

    @Test
    @DisplayName("Создаем двух User'ов и обновляем одного (чтобы он попал в Кеш). Сравниваем время load'а двух User'ов")
    void createAndUpdateUser() {
        //Первый User
        final User user1 = new User();
        user1.setName("Илья");
        user1.setAge(77);
        user1.setAddressDataSet(new AddressDataSet("Остоженка"));
        final List<PhoneDataSet> phoneDataSets1 = new ArrayList<>();
        phoneDataSets1.add(new PhoneDataSet("84957777777"));
        phoneDataSets1.add(new PhoneDataSet("84955555555"));
        user1.setPhoneDataSet(phoneDataSets1);

        userDBExecutor.create(user1);
        //assertThat(userDBExecutor.load(user1.getId())).isEqualToComparingFieldByFieldRecursively(user1);

        //Второй User
        final User user2 = new User();
        user2.setName("Петр");
        user2.setAge(77);
        user2.setAddressDataSet(new AddressDataSet("Мытная"));
        final List<PhoneDataSet> phoneDataSets2 = new ArrayList<>();
        phoneDataSets1.add(new PhoneDataSet("84957777777"));
        phoneDataSets1.add(new PhoneDataSet("84955555555"));
        user2.setPhoneDataSet(phoneDataSets1);

        userDBExecutor.create(user2);

        user2.setName("Федор");
        user2.getAddressDataSet().setStreet("Никольский переулок");
        final List<PhoneDataSet> phoneDataSets3 = new ArrayList<>();
        phoneDataSets2.add(new PhoneDataSet("5555555555"));
        phoneDataSets2.add(new PhoneDataSet("7777777777"));
        user2.getPhoneDataSet().remove(phoneDataSets3);

        userDBExecutor.update(user2);

        //assertThat(userDBExecutor.load(user2.getId())).isEqualToComparingFieldByFieldRecursively(user2);

        //Сравнение времени load'а
        long timestampWithCache = System.currentTimeMillis();
        userDBExecutor.load(user2.getId());
        long durationWithCache = System.currentTimeMillis() - timestampWithCache;

        long timestampWithoutCache = System.currentTimeMillis();
        userDBExecutor.load(user1.getId());
        long durationWithoutCache = System.currentTimeMillis() - timestampWithoutCache;

        //из-за "медленного подключения к БД" с кешем должно быть быстрее"
        assertThat(durationWithoutCache).isGreaterThan(durationWithCache);
    }
}
