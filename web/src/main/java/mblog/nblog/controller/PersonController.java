package mblog.nblog.controller;

import mblog.nblog.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @ResponseBody
    @RequestMapping("/selectPerson")
    public Object selectPerson(){
        return personService.selectPerson();
    }

}
