package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller //@RestController
@RequestMapping("/students") //http://localhost:8080/SpringMvc/students
//class level:tum methodlar icin gecerli
//method level: sadece o method icin gecerli
public class StudentController {

    @Autowired
    private StudentService service;

    //controller gelen requeste gore modelandview(data+view name) ya da String olarak sadece view name doner.
    @GetMapping("/hi") //http://localhost:8080/SpringMvc/students/hi
    public ModelAndView sayHi(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message","Hi;");
        mav.addObject("messagebody","I'm a Student Management System");
        mav.setViewName("hi"); //hi.jsp
        return mav;
    }
    //view resolver mav icindeki model i view name i verilen dosyayi bulup icine bind(yerlestirmek) eder.

    //1-Student Creation
    //http://localhost:8080/SpringMvc/students/new
    //kullanicidan bilgileri almak icin form gosterelim
    @GetMapping("/new")
    public String sendStudentForm(@ModelAttribute("student")Student student){
        return "studentForm";
    }

    //@ModelAttribute view katmani ile controller arasinda data transferini saglar.


    //student i DB ye kaydedince tum ogrenciler listeleyelim.
    //http://localhost:8080/SpringMvc/students/saveStudent+POST
    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student){

        //service de student i kaydet
        service.saveStudent(student);

        return "redirect:/students"; //http://localhost:8080/SpringMvc/students/ bu istege yonlendirelim
    }




}
