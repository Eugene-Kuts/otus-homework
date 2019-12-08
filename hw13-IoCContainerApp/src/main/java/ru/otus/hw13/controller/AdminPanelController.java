package ru.otus.hw13.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.hw13.db.dataClasses.AddressDataSet;
import ru.otus.hw13.db.dataClasses.PhoneDataSet;
import ru.otus.hw13.db.dataClasses.User;
import ru.otus.hw13.db.executor.DBExecutorHibernate;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminPanelController {

    private DBExecutorHibernate<User> userDBExecutor;

    public AdminPanelController(DBExecutorHibernate<User> userDBExecutor) {
        this.userDBExecutor = userDBExecutor;
    }

    @GetMapping(value = "/")
    public String showAdminPanel(Model model) {
        model.addAttribute("userList", userDBExecutor.loadAll());
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
