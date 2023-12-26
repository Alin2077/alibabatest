package com.zgm.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zgm.bean.Depart;

@FeignClient(value = "depart-provider", path = "/provider/depart")  //指定调用的微服务的名称
public interface DepartService {
    
    @PostMapping("/")
     boolean saveDepart(@RequestBody Depart depart);

    @DeleteMapping("/{id}")
     boolean removeDepartById(@PathVariable("id") int id);

    @PutMapping("/")
     boolean modifyDepart(@RequestBody Depart depart);

    @GetMapping("/{id}")
     Depart getDepartById(@PathVariable("id") int id);

    @GetMapping("/list")
     List<Depart> getAllDepart();

}
