package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.model.CreateTruckDto;
import com.tsystems.logistics.logistics_vp.code.model.TruckDto;
import com.tsystems.logistics.logistics_vp.enums.Busy;
import com.tsystems.logistics.logistics_vp.enums.TechnicalCondition;
import com.tsystems.logistics.logistics_vp.service.interfaces.TruckService;
import com.tsystems.logistics.logistics_vp.validator.TruckUpdateValidator;
import com.tsystems.logistics.logistics_vp.validator.TruckValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/trucks")
@RequiredArgsConstructor
@SessionAttributes("truck")
@CrossOrigin
public class TruckPageController {

    private final TruckService truckService;

    @Autowired
    TruckValidator truckValidator;

    @Autowired
    TruckUpdateValidator truckUpdateValidator;

    //http://localhost:8080/trucks/find/ANY3292
    @RequestMapping(path = "/find/{number}", method = RequestMethod.GET)
    public ModelAndView getTruck(@PathVariable(name = "number") String number) {
        TruckDto truck = truckService.truckFindByNumber(number);
        ModelAndView modelAndView = new ModelAndView("truck-show-details");
        modelAndView.addObject("truck", truck);
        return modelAndView;
    }

    //http://localhost:8080/trucks/delete/ANY3292
    @RequestMapping(path = "/delete/{number}", method = RequestMethod.GET)
    public ModelAndView deleteTruck(@PathVariable(name = "number") String number) {
        truckService.truckDelete(number);
        ModelAndView modelAndView = new ModelAndView("redirect:/trucks/showAll");
        //modelAndView.addObject("truck", truck);
        return modelAndView;
    }

    //http://localhost:8080/trucks/showAll
    @RequestMapping(path = "/showAll", method = RequestMethod.GET)
    public ModelAndView getAllTrucks() {
        List<TruckDto> trucks = truckService.trucksFindAll();
        ModelAndView modelAndView = new ModelAndView("trucks-show-all");
        modelAndView.addObject("trucks", trucks);
        return modelAndView;
    }


    //http://localhost:8080/trucks/filterByTechnicalConditionMain
    @RequestMapping(path = "/filterByTechnicalConditionMain", method = RequestMethod.GET)
    public ModelAndView getTrucksByTechnicalConditionMain() {
        ModelAndView modelAndView = new ModelAndView("trucks-filter-by-tech-condition-main");
        return modelAndView;
    }

    //http://localhost:8080/trucks/filterByTechnicalCondition/OK
    //http://localhost:8080/trucks/filterByTechnicalCondition/NOK
    @RequestMapping(path = "/filterByTechnicalCondition/{technicalCondition}", method = RequestMethod.GET)
    public ModelAndView getTrucksByTechnicalCondition(@PathVariable(name = "technicalCondition") String technicalCondition) {
        List<TruckDto> trucks = truckService.trucksFindAllByTechnicalCondition(
                TechnicalCondition.valueOf(technicalCondition));
        ModelAndView modelAndView = new ModelAndView("trucks-filter-by-tech-condition-results");
        modelAndView.addObject("trucks", trucks);
        return modelAndView;
    }


    //http://localhost:8080/trucks/filterByBusyMain
    @RequestMapping(path = "/filterByBusyMain", method = RequestMethod.GET)
    public ModelAndView getTrucksByBusyMain() {
        ModelAndView modelAndView = new ModelAndView("trucks-filter-by-busy-main");
        return modelAndView;
    }

    //http://localhost:8080/trucks/filterByBusy/YES
    //http://localhost:8080/trucks/filterByBusy/NO
    @RequestMapping(path = "/filterByBusy/{busy}", method = RequestMethod.GET)
    public ModelAndView getTrucksByBusy(@PathVariable(name = "busy") String busy) {
        List<TruckDto> trucks = truckService.trucksFindByBusyStatus(Busy.valueOf(busy));
        ModelAndView modelAndView = new ModelAndView("trucks-filter-by-busy-results");
        modelAndView.addObject("trucks", trucks);
        return modelAndView;
    }

    @RequestMapping(path = "/filterByCurrentCityAndStateMain", method = RequestMethod.GET)
    public ModelAndView getTrucksByLocationMain() {
        ModelAndView modelAndView = new ModelAndView("trucks-filter-by-location-main");
        return modelAndView;
    }

    //http://localhost:8080/trucks/filterByCurrentCityAndState?city=Houston&state=Texas
    //http://localhost:8080/trucks/filterByCurrentCityAndState?city=New+York&state=New+York
    @RequestMapping(path = "/filterByCurrentCityAndState", method = RequestMethod.GET)
    public ModelAndView getTrucksByLocation(@RequestParam(name = "city") String city,
                                            @RequestParam(name = "state") String state) {
        List<TruckDto> trucks = truckService.trucksFindAllByCurrentCityAndState(city, state);
        ModelAndView modelAndView = new ModelAndView("trucks-filter-by-location-results");
        modelAndView.addObject("trucks", trucks);
        return modelAndView;
    }

    //http://localhost:8080/trucks/filterForOrderMain
    @RequestMapping(path = "/filterForOrderMain", method = RequestMethod.GET)
    public ModelAndView getTrucksForOrderMain() {
        ModelAndView modelAndView = new ModelAndView("trucks-filter-for-order-main");
        return modelAndView;
    }

    @RequestMapping(path = "/filterForOrder", method = RequestMethod.GET)
    public ModelAndView getTrucksForOrder(
            @RequestParam(name = "orderId") Integer orderId,
            @RequestParam(name = "city") String city,
            @RequestParam(name = "state") String state,
            @RequestParam(name = "capacity") Double capacity,
            Model model) {
        List<TruckDto> trucks = truckService.findAllForOrder(orderId, city, state, capacity);
        ModelAndView modelAndView = new ModelAndView("trucks-filter-for-order-results");
        modelAndView.addObject("trucks", trucks);
        model.addAttribute("orderId", orderId);
        modelAndView.addObject("orderId", orderId);
        return modelAndView;
    }

    @GetMapping(path = "/create")
    public ModelAndView createTruck() {
        return new ModelAndView("truck-create", "truck", new CreateTruckDto());
    }

    @RequestMapping(path = "/registered", method = RequestMethod.POST)
    public String saveTruck(@Valid @ModelAttribute("truck") CreateTruckDto truck, BindingResult bindingResult, Model model) {
        truckValidator.validate(truck, bindingResult);
        if (bindingResult.hasErrors()) {
            return "truck-create";
        } else {
            truckService.truckCreate(truck);
            model.addAttribute("truck", truckService.truckFindByNumber(truck.getNumber()));
            //return "truck-details" + truckDto.getNumber();
            return "truck-show-details";
        }
    }

    //http://localhost:8080/trucks/update/ANY3292
    @GetMapping(path = "/update/{number}")
    public ModelAndView updateTruck(@PathVariable(name = "number") String number) {
        ModelAndView modelAndView = new ModelAndView("truck-update");
        TruckDto truck = truckService.truckFindByNumber(number);
        modelAndView.addObject("truck", truck);
        return modelAndView;
    }

    @PostMapping("/updated")
    public String saveUpdatedTruck(@Valid @ModelAttribute("truck") TruckDto truck,
                                   BindingResult bindingResult, Model model) {
        truckUpdateValidator.validate(truck, bindingResult);
        if (bindingResult.hasErrors()) {
            return "truck-update";
        } else {
            truckService.truckUpdateByLogisticianUi(truck.getNumber(), truck);
            model.addAttribute("truck", truckService.truckFindByNumber(truck.getNumber()));
            return "truck-show-details";
        }
    }
}
