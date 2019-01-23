package mblog.nblog.controller;

import mblog.nblog.entity.IP;
import mblog.nblog.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import mblog.nblog.mapper.ipMapper;

import java.util.ArrayList;
import java.util.List;
import mblog.nblog.utils.iputils;

@Controller
@ResponseBody
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    private ipMapper ipMapper;

    @ResponseBody
    @RequestMapping("/selectPerson")
    public Object selectPerson(){
        return personService.selectPerson();
    }

    @ResponseBody
    @RequestMapping("/searchip")
    public List<IP> searchip(){
        List<IP> list = ipMapper.searchIP();
        List newList = new ArrayList();
//        for(int i=0;i<list.size();i++){
//            System.out.println(iputils.getAddresses(list.get(i).getIPAddress().toString(),"utf-8") + ":--" + list.get(i).getLoginTime());
//        }


        int i=0;
        System.out.println("第" + i++ + "次--:" +  iputils.getAddresses(list.get(i).getIPAddress().toString(),"utf-8") + ":--" + list.get(i).getLoginTime());
        System.out.println("第" + i++ + "次--:" +  iputils.getAddresses(list.get(i).getIPAddress().toString(),"utf-8") + ":--" + list.get(i).getLoginTime());
        System.out.println("第" + i++ + "次--:" +  iputils.getAddresses(list.get(i).getIPAddress().toString(),"utf-8") + ":--" + list.get(i).getLoginTime());
        System.out.println("第" + i++ + "次--:" +  iputils.getAddresses(list.get(i).getIPAddress().toString(),"utf-8") + ":--" + list.get(i).getLoginTime());

//        Runnable r1 = new MyThread();
//        Thread t1 = new Thread(r1);
//        t1.setName("1号");
//        t1.start();
//
//
//        Thread threads[]=new Thread[100];
//        for(int i=0;i<1000;i++){
//            threads[i]=new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println(Thread.currentThread().getId());
//                    System.out.println(iputils.getAddresses(list.get((int)Thread.currentThread().getId()).getIPAddress().toString(),"utf-8") + ":--" + list.get((int)Thread.currentThread().getId()).getLoginTime());
//                }
//            });
//                    threads[i].start();
//        }

        return newList;
    }

}

class MyThread implements Runnable{


    public void run() {
        //for(int i=0;i<10;i++){
            System.out.println("----");
        //}


    }

}
