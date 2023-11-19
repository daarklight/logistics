package com.tsystems.logistics.logistics_vp.controller;

import com.tsystems.logistics.logistics_vp.code.model.AuthenticationInfoDto;
import com.tsystems.logistics.logistics_vp.service.interfaces.AuthenticationInfoService;
import com.tsystems.logistics.logistics_vp.validator.AuthenticationInfoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authenticationInfo")
@RequiredArgsConstructor
@SessionAttributes("authenticationInfo")
@CrossOrigin
public class AuthenticationInfoPageController {

    private final AuthenticationInfoService authenticationInfoService;

    @Autowired
    AuthenticationInfoValidator authenticationInfoValidator;

    //http://localhost:8080/authenticationInfo/find/1201
    @RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
    public ModelAndView getAuthenticationInfo(@PathVariable(name = "id") Integer id) {
        AuthenticationInfoDto authenticationInfo = authenticationInfoService.authenticationInfoFindById(id);
        ModelAndView modelAndView = new ModelAndView("authenticationInfo-show-details");
        modelAndView.addObject("authenticationInfo", authenticationInfo);
        return modelAndView;
    }
}
