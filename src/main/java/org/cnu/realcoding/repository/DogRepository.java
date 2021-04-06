package org.cnu.realcoding.repository;

import org.cnu.realcoding.exception.InvalidInput;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.cnu.realcoding.domain.Dog;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class DogRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    @Getter
    @Autowired
    private List<Dog> dogs = new ArrayList<>();

    public void insertDog(Dog dog) {

        //
    }

    public Dog findDog(String name, int i) {
        Criteria cri;
        switch (i) {
            case 1:
                cri = new Criteria("name");
                break;
            case 2:
                cri = new Criteria("ownerName");
                break;
            case 3:
                cri = new Criteria("ownerPhoneNumber");
                break;
            default: throw new InvalidInput();
        }
        cri.is(name); // 밸류 입력
        Query query = new Query(cri);
        Dog dog = (Dog) mongoTemplate.find(query, mongoTemplate.getClass()); // 조회 후 데이터 반환
        return dog;
    }

    public Dog changeDogKind(String newKind) {
        // return changedDog
    }

    public List<String> addMedicalRecords(String newMedicalRecords) {
        // return added new List;
    }

    public Dog changeAllInfo(String newName, String newKind, String newOwnerName, String newOwnerPhoneNumber) {

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
