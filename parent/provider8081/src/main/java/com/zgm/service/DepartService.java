package com.zgm.service;

import java.util.List;

import com.zgm.bean.Depart;

public interface DepartService {

    boolean saveDepart(Depart depart);

    boolean removeDepartById(int id);

    boolean modifyDepart(Depart depart);

    Depart getDepartById(int id);
    
    List<Depart> getAllDepart();
    
}
