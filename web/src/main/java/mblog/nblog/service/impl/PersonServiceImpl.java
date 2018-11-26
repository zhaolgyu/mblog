package mblog.nblog.service.impl;

import mblog.nblog.entity.Person;
import mblog.nblog.mapper.PersonMapper;
import mblog.nblog.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService{

    @Autowired PersonMapper personDao;

    @Override
    public Person selectPerson(){
        Person p = personDao.selectPerson(1);
        System.out.println("吃屎0");
        System.out.println("吃屎0");
        System.out.println("吃屎0");
        System.out.println("吃屎0");
        System.out.println("haha");
        System.out.println("haha");
        System.out.println("haha");
        System.out.println("haha");

        System.out.println("11111");
        System.out.println("11111");
        System.out.println("11111");
        System.out.println("11111");
        return p;
    }
}
