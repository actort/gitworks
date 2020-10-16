package com.cssl.controller;

import com.cssl.entity.Subject;
import com.cssl.service.SubjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @RequestMapping("/getSub")
    @ResponseBody
    public PageInfo<Subject> getSub(Integer index) {
        index = index == null ? 1 : index;
        return subjectService.fenye(index, 6);
    }

    @RequestMapping("/addSub")
    public String addSub(Subject sub, @RequestParam List<String> options) {
        boolean boo = subjectService.addSubAndOption(sub, options);
        if (boo)
            return "redirect:/votelist.html";
        else
            return "redirect:/add.html";
    }

    @RequestMapping("/getSubBySid")
    @ResponseBody
    public Subject getSubBySid(long sid) {
        return subjectService.getSubBySid(sid);
    }

    @RequestMapping("/delSubAndOptionBySid")
    @ResponseBody
    public boolean delSubAndOptionBySid(Long sid) {
        return subjectService.delSubAndOptionBySid(sid);
    }

    @PostMapping("/updSubAndOptionBySid")
    public String updSubAndOptionBySid(Subject subject, @RequestParam List<Long> oids, @RequestParam List<String> options) {
        boolean b = subjectService.updSubAndOptionBySid(subject, oids, options);
        if (b) {
            return "redirect:/votemodify.html";
        } else {
            return "redirect:/upd.html?sid=" + subject.getSid();
        }
    }

}