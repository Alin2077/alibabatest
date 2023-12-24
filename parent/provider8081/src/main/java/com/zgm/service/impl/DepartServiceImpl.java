package com.zgm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zgm.bean.Depart;
import com.zgm.repository.DepartRepository;
import com.zgm.service.DepartService;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartRepository departRepository;

    @Override
    public boolean saveDepart(Depart depart) {
        Depart save = departRepository.save(depart);

        return  save != null?true : false;
    }

    @Override
    public boolean removeDepartById(int id) {
        if(departRepository.existsById(id)){
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyDepart(Depart depart) {
        Depart save = departRepository.save(depart);
        return  save != null?true : false;
    }

    @Override
    public Depart getDepartById(int id) {
        if(departRepository.existsById(id)){
            return departRepository.getReferenceById(id);
        }
        return new Depart();
    }

    @Override
    public List<Depart> getAllDepart() {
        return departRepository.findAll();
    }
    
}
