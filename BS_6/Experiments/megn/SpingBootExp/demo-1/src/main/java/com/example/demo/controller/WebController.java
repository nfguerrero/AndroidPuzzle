package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Pet;
import com.example.demo.repository.petRepository;

import org.springframework.data.repository.CrudRepository;
 
@RestController
public class WebController {
    @Autowired
    @Qualifier("petRepository")
    petRepository repository;
      
    @RequestMapping("/save")
    public String process(){
        repository.save(new Pet("Matrix"));
        return "Pet Added.";
    }
      
      
    @RequestMapping("/findall")
    public String findAll(){
        String result = "<html>";
          
        for(Pet pet : repository.findAll()){
            result += "<div>" + pet.toString() + "</div>";
        }
          
        return result + "</html>";
    }
      
//    @RequestMapping("/findbyid")
//    public String findById(@RequestParam("id") int id){
//        String result = "";
//        result = repository.findOne(id).toString();
//        return result;
//    }
      
    @RequestMapping("/findbyname")
    public String fetchDataByLastName(@RequestParam("name") String name){
        String result = "<html>";
          
        for(Pet pet: repository.findByName(name)){
            result += pet.toString(); 
        }
          
        return result + "</html>";
    }
}