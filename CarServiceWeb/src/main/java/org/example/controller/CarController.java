package org.example.controller;

import org.example.domain.Car;
import org.example.formObjects.CarForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.example.service.GarageService;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/car")
public class CarController {

    private final GarageService garageService;

    @Autowired
    public CarController(GarageService garageService) {
        this.garageService = garageService;
    }

    @GetMapping("/create")
    public String registerCar(Model model){
        model.addAttribute(new CarForm());
        return "new-car-register-form";
    }

    @PostMapping("/create-details")
    public String createVolunteerFormDetails(@Valid @ModelAttribute("carForm") CarForm carForm, BindingResult br) {
        if (br.hasErrors()) {
            return "/new-car-register-form";
        } else {
            garageService.registerCar(carForm.getName(), carForm.getColor(), carForm.getProductionDate());
            return "redirect:/car/all";
        }
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Car> showAllActiveCars(){
        return garageService.getAllActiveCars();
    }

}
