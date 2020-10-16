package com.cssl.controller;

import com.cssl.entity.User;
import com.cssl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login.action")
    public String login(User user, HttpSession session) {
        User loginUser = userService.login(user);
        if (loginUser == null) {
            return "login";
        } else {
            //将用户存储到session中
            session.setAttribute("user", loginUser);

            //得到session中存储的登录用户
            Object userObj = session.getAttribute("userList");
            //创建集合存储登录的用户
            List<User> userList = userObj == null ? new ArrayList<User>() : (ArrayList<User>) userObj;
            //将登录用户添加到集合中
            userList.add(loginUser);
            //将集合存储至session中
            session.setAttribute("userList", userList);
            return "votelist";
        }
    }

    @RequestMapping("/register")
    public String register(User user) {
        if (userService.register(user)) {
            return "redirect:/login.html";
        } else {
            return "regist";
        }
    }

    @GetMapping("/getUserByUname")
    @ResponseBody
    public boolean getUserByUname(String name) {
        return userService.getUserByUname(name);
    }

    @GetMapping("/loginValidate")
    @ResponseBody
    public boolean loginValidate(User user, HttpSession session) {
        //得到session中存储的登录用户
        Object userObj = session.getAttribute("userList");
        if (userObj == null) {
            return false;
        } else {
            //得到集合中存储的登录用户
            List<User> userList = (List<User>) userObj;
            //循环判断集合中是否存在该用户
            for (User u : userList) {
                if (u.getName().equals(user.getName()) && u.getPassword().equals(u.getPassword())) {
                    return true;
                }
            }
            return false;
        }
    }

    @RequestMapping("/del")
    public String del(HttpSession session) {
        User user = (User) session.getAttribute("user");
        session.removeAttribute("user");
        List<User> userList = (List<User>) session.getAttribute("userList");
        System.out.println("userList:" + userList);
        userList.remove(user);
        session.setAttribute("userList", userList);
        return "voteview";
    }

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(HttpSession session) {
        return (User) session.getAttribute("user");
    }
}
