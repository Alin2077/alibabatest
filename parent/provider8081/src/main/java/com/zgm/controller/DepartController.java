package com.zgm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zgm.bean.Depart;
import com.zgm.service.DepartService;

@RequestMapping("/provider/depart")
@RestController
public class DepartController {
    
    @Autowired
    private DepartService departService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @PostMapping("/")
    public boolean saveHandle(@RequestBody Depart depart){
        return departService.saveDepart(depart);
    }

    @DeleteMapping("/{id}")
    public boolean deleteHandle(@PathVariable("id") int id){
        return departService.removeDepartById(id);
    }

    @PutMapping("/")
    public boolean updateHandle(@RequestBody Depart depart){
        return departService.modifyDepart(depart);
    }

    @GetMapping("/{id}")
    public Depart getHandle(@PathVariable("id") int id){

        return departService.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle(){
        return departService.getAllDepart();
    }

    @GetMapping("/discovery")
    public List<String> discoveryHandle(){
        return discoveryClient.getServices();
    }

}
