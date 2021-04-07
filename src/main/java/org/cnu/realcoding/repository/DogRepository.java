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
        System.out.println("Insert start!");
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
        System.out.println("switch 통과 !");
        cri.is(name); // 밸류 입력
        Query query = new Query(cri);
        System.out.println("query 입력 !");
        System.out.println(query);
        Dog dog =  mongoTemplate.findOne(query, Dog.class); // 조회 후 데이터 반환
        System.out.println("도그 반환 !");
        return dog;
    }
    public List<Dog> getAllDogs(){
        List<Dog> dogs = mongoTemplate.findAll(Dog.class);
        return dogs;
    }

    public void changeDogKind(String newKind) {
        // return changedDog

    }

    public void addMedicalRecords(Dog dog,String newMedicalRecords) {
        // return added new List;
    }

    public void changeAllInfo(String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber) {

        //mongoTemplate.insert(dog); // 데이터 추
    }

    public boolean checkDogName(String name){ // 이름으로 검색
        for(Dog dog : dogs){
            if (dog.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkDogOwner(String Owner){ // 주인이름으로 검색
        for(Dog dog : dogs){
            if (dog.getOwnerName().equals(Owner)) {
                return true;
            }
        }
        return false;
    }
    public boolean checkDogOwnerPhone(String number){ // 주인 번호로 검
        for(Dog dog : dogs){
            if (dog.getOwnerPhoneNumber().equals(number)) {
                return true;
            }
        }
        return false;

    }
}
