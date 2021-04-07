package org.cnu.realcoding.repository;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.cnu.realcoding.exception.InvalidInput;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.cnu.realcoding.domain.Dog;
import org.springframework.data.mongodb.core.query.*;

import java.util.List;

import lombok.Getter;
import org.cnu.realcoding.domain.Dog;
import org.cnu.realcoding.exception.DogNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Service
public class DogRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Getter
    private List<Dog> dogs = new ArrayList<>();
//    Update up = new Update();

    public void insertDog(Dog dog) {
//        System.out.println("Insert start!");
//        dogs.add(dog);
//        up.push("list").each(dogs);
        mongoTemplate.insert(dog);
        //
    }

    public Dog findDog(String name, int i) {
        Criteria cri;
        switch (i){
            case 1:
                cri = new Criteria("name"); // 키 입력
                break;
            case 2:
                cri = new Criteria("ownerName"); // 키 입력
                break;
            case 3:
                cri = new Criteria("ownerPhoneNumber"); // 키 입력
                break;
            default:
                throw new InvalidInput();

        }
        cri.is(name); // 밸류 입력
        Query query = new Query(cri);
        System.out.println(query);
        Dog dog =  mongoTemplate.findOne(query, Dog.class); // 조회 후 데이터 반환

        return dog;
    }
    public List<Dog> getAllDogs(){
        return mongoTemplate.findAll(Dog.class);
    }

    public void changeDogKind(String name,String newKind) {

        Query query = new Query(Criteria.where("name").is(name));
        Update update = Update.update("kind", newKind);

        mongoTemplate.updateFirst(query, update, Dog.class);
    }

    public void addMedicalRecords(Dog dog,String newMedicalRecords) {
        // return added new List;
    }

    public void changeAllInfo(String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber) {

        //mongoTemplate.insert(dog); // 데이터 추
    }

    public boolean checkDogName(String name){ // 이름으로 검색
        if(findDog(name,1) == null){
            return false;
        }
        return true;
    }
    public boolean checkDogOwner(String Owner){ // 주인이름으로 검색
        if(findDog(Owner,2) == null){
            return false;
        }
        return true;
    }
    public boolean checkDogOwnerPhone(String number){ // 주인 번호로 검
        if(findDog(number,3) == null){
            return false;
        }
        return true;

    }
}
