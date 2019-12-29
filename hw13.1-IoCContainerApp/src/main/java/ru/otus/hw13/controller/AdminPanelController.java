package ru.otus.hw13.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.otus.hw13.db.domain.AddressDataSet;
import ru.otus.hw13.db.domain.PhoneDataSet;
import ru.otus.hw13.db.domain.User;
import ru.otus.hw13.db.executor.DBExecutorHibernate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class AdminPanelController {

    private DBExecutorHibernate<User> userDBExecutor;

    public AdminPanelController(DBExecutorHibernate<User> userDBExecutor) {
        this.userDBExecutor = userDBExecutor;
    }

    @GetMapping(value = "/")
    public String showAdminPanel(Model model) {
        model.addAttribute("userList", userDBExecutor.loadAll());
        return "adminPage.html";
    }

    @RequestMapping(value = "/addNewUserPage", method = RequestMethod.GET)
    public String showAddNewUserPage() {
        return "addNewUser.html";
    }

    @PostMapping("/createNewUser")
    public String handleUserCreation(@RequestParam(value = "userName") String name,
                                     @RequestParam(value = "userAge") int age,
                                     @RequestParam(value = "userAddress") String address,
                                     @RequestParam(value = "userPhone") String phone){
        List<PhoneDataSet> phoneDataSet = new ArrayList<>();
        phoneDataSet.add(new PhoneDataSet(phone));
        User user = new User(name, age, new AddressDataSet(address), phoneDataSet);
        userDBExecutor.create(user);
        return "redirect:/";
    }
}
