package ru.otus.hw13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw13.db.cache.Cache;
import ru.otus.hw13.db.cache.CacheImpl;
import ru.otus.hw13.db.dataClasses.AddressDataSet;
import ru.otus.hw13.db.dataClasses.PhoneDataSet;
import ru.otus.hw13.db.dataClasses.User;
import ru.otus.hw13.db.executor.DBExecutorHibernate;
import ru.otus.hw13.db.executor.UserDBExecutorHibernateImpl;
import ru.otus.hw13.db.utils.SessionFactories;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPanelController {
    private Cache<Long, User> cache = new CacheImpl<>();
    private DBExecutorHibernate<User> userDBExecutor = new UserDBExecutorHibernateImpl(SessionFactories.get(), cache);

    @PostConstruct
    public void loadData() {
        for (int i = 0; i < 5; i++) {
            List<PhoneDataSet> phoneDataSet = new ArrayList<>();
            phoneDataSet.add(new PhoneDataSet("495-777-77-77"));
            User user = new User("Ivan " + i + 1, 25 + i * 2, new AddressDataSet("Seligerslaya d." + (1 + i + i * 2)), phoneDataSet);
            userDBExecutor.create(user);
        }
    }

    @GetMapping(value = "/")
    public String showAdminPanel(Model model) {
        List<User> users = userDBExecutor.loadAll();
        model.addAttribute("userList", users);
        return "adminPanel";
    }

    @RequestMapping(value = "/addNewUserPage", method = RequestMethod.GET)
    public String showAddNewUserPage() {
        return "addNewUser";
    }

    @GetMapping(value = "/createNewUser")
    public String createNewUser(@RequestParam(value = "userName") String name,
                                @RequestParam(value = "userAge") int age,
                                @RequestParam(value = "userAddress") String address,
                                @RequestParam(value = "userPhone") String phone) {
        List<PhoneDataSet> phoneDataSet = new ArrayList<>();
        phoneDataSet.add(new PhoneDataSet(phone));
        User user = new User(name, age, new AddressDataSet(address), phoneDataSet);
        userDBExecutor.create(user);
        return "redirect:/";
    }
}
