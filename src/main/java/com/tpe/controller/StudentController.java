package com.tpe.controller;


import com.tpe.domain.Student;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

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
    //http://localhost:8080/SpringMvc/students/saveStudent + POST
//    @PostMapping("/saveStudent")
//    public String saveStudent(@ModelAttribute Student student){
//
//        //service de student i kaydet
//        service.saveStudent(student);
//        return "redirect:/students"; //http://localhost:8080/SpringMvc/students/ bu istege yonlendirelim
//    }


    @PostMapping("/saveStudent")
    public String saveStudent(@Valid @ModelAttribute Student student,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "studentForm";
        }

        //service de student i kaydet
        service.saveStudent(student);
        return "redirect:/students"; //http://localhost:8080/SpringMvc/students/ bu istege yonlendirelim
    }


    //http://localhost:8080/SpringMvc/students/ + GET
    //2-list all student
    @GetMapping
    public ModelAndView listAllStudents(){
        List<Student> studentList = service.getAllStudent();
        ModelAndView mav = new ModelAndView();
        mav.addObject("students",studentList);
        mav.setViewName("students");
        return mav;
    }

    //3-update student
    //http://localhost:8080/SpringMvc/students/update?id=1
    @GetMapping("/update")
    public String showUpdateFprm(@RequestParam("id") Long id, Model model){
        Student foundStudent = service.getStudentById(id);
        model.addAttribute("student",foundStudent);
        return "studentForm";
    }

    //2.yontem
//    @GetMapping("/update")
//    public ModelAndView showUpdateFprm(@RequestParam("id") Long id, Model model){
//        Student foundStudent = service.getStudentById(id);
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("student",foundStudent);
//        mav.setViewName("studentForm");
//        return mav;
//    }

    //4-delete Student
    //http://localhost:8080/SpringMvc/students/delete/5
    //silme isleminden sonra tum kayitlari listeleyelim
    @GetMapping("/delete/{id}")
    public String deleteStudents(@PathVariable("id") Long id){
        service.deleteStudent(id);
        return "redirect:/students";

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ModelAndView handleException(Exception ex){
        ModelAndView mav = new ModelAndView();
        mav.addObject("message",ex.getMessage());
        mav.setViewName("notFound");
        return mav;
    }

    //EceptionHandler: belirtilen exception sinifi icin bir method belirlememizi saglar
     // bu method yakalanan exception icin ozel bir islem icerir(notFound u icinde mesaj ile gosterme)





}
