package com.example.demo.controller;


import com.example.demo.entity.Test;
import com.example.demo.service.TestService;
import com.springboot.shiro.base.ResponseEntity;
import com.springboot.shiro.base.ResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zt
 * @since 2018-09-30
 */
@Controller
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/get")
    @ResponseBody
    public ResponseEntity<Test> get() {

        ResponseEntity<Test> res= new ResponseEntity<>();
        Test test=testService.getById(1);
        res.setData(test);
        return  res;
    }

    @RequestMapping(value = "/getAll")
    @ResponseBody
    public ResponseList<Test> getAll() {

        ResponseList<Test> res= new ResponseList<>();
        List<Test> tests=testService.getAll();
        res.setData(tests);
        return  res;
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public ResponseEntity<Test> save() {

        ResponseEntity<Test> res= new ResponseEntity<>();
        Test test = new Test();
        test.setName("asdf");
        testService.save(test);
        res.setMsg("ok");
        return  res;
    }


}

