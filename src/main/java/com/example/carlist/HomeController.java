package com.example.carlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    CarRepository carRepository;

    @RequestMapping("/")
    public String listCars(Model model){
        model.addAttribute("cars", carRepository.findAll());
        return "list";

        // class, object, assigned variable... of value repo/list of obj
    }

    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("car", new Car());
        return "carform";
    }

    // class, object, variable... new object, an empty java bean

    @PostMapping("/process")
    public String processForm(@Valid Car car, BindingResult result){
        if (result.hasErrors()){
            return "carform";
        }
        carRepository.save(car);
        return "redirect:/";
    }

    // validate the class, object bean... using the class/obj check?

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "show";
    }

    // the pathvar...

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("car", carRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id, Model model){
        carRepository.deleteById(id);
        return "redirect:/";
    }

}
