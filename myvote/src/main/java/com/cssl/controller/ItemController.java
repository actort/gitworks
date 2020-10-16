package com.cssl.controller;

import com.cssl.entity.User;
import com.cssl.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/getItem")
    @ResponseBody
    public int getItem(Long sid) {
        return itemService.getItemBySidCount(sid);
    }

    @RequestMapping("/getItemByUid")
    @ResponseBody
    public int getItemByUid(Long sid) {
        return itemService.getUserCountBySid(sid);
    }

    @RequestMapping("/startVote")
    public String startVote(HttpSession session, Long sid, @RequestParam List<Long> oids) {
        User user = (User) session.getAttribute("user");
        boolean boo = itemService.addItemAndOption(user.getUserid(), sid, oids);
        if (boo) {
            return "votelist";
        } else {
            return "redirect:/vote.html?sid=" + sid;
        }
    }
}
