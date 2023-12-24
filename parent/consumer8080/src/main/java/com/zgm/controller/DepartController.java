package com.zgm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zgm.bean.Depart;

@RequestMapping("/consumer/depart")
@RestController
public class DepartController {
    
    @Autowired
    private RestTemplate restTemplate;

    // private static final String PROVIDER_URL = "http://localhost:8081/provider/depart/";  //直连

    //微服务方式 --  通过注册中心
    private static final String PROVIDER_URL = "http://depart-provider/provider/depart/";

    @PostMapping("/")
    public boolean saveHandle(@RequestBody Depart depart){
        return restTemplate.postForObject(PROVIDER_URL, depart, Boolean.class);
    }

    @DeleteMapping("/{id}")
    public void deleteHandle(@PathVariable("id") int id){
        restTemplate.delete(PROVIDER_URL,id);
    }

    @PutMapping("/")
    public void updateHandle(@RequestBody Depart depart){
        restTemplate.put(PROVIDER_URL, depart);
    }

    @GetMapping("/{id}")
    public Depart getHandle(@PathVariable("id") int id){
        return restTemplate.getForObject(PROVIDER_URL+id, Depart.class);
    }

    @GetMapping("/list")
    public List<Depart> getAllHandle(){
        return restTemplate.getForObject(PROVIDER_URL+"list", List.class);
    }

}
