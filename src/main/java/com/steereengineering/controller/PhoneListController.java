package com.steereengineering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.steereengineering.command.PhoneListCommand;
import com.steereengineering.service.PhoneListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PhoneListController {
	
	private static final String PHONELIST_PHONELISTFORM_URL = "/apps/phonelist/phonelistform";
	public final PhoneListService phoneListService;

	public PhoneListController(PhoneListService phoneListService) {
		this.phoneListService = phoneListService;
	}

	@GetMapping
	@RequestMapping("/apps/phonelist/{id}/show")
	public String showById(@PathVariable String id, Model model){

		model.addAttribute("phonelist", phoneListService.findById(new Long(id)));
		model.addAttribute("document", phoneListService.BuildPOIPhonelistWordDocument());
		return "/apps/phonelist/show";
	}
	@PostMapping("/apps/phonelist")
    public String saveOrUpdate(@Validated @ModelAttribute("phonelist") PhoneListCommand command, BindingResult bindingResult){
		System.out.println("inside saveOrUpdate");
		log.debug("Binding Results has errors = " + bindingResult.hasErrors());
		if(bindingResult.hasErrors()){
			bindingResult.getAllErrors().forEach(objectError-> {
			log.debug(objectError.toString());
		});
		return 	PHONELIST_PHONELISTFORM_URL;
		}	
		
        PhoneListCommand savedCommand = phoneListService.savePhoneListCommand(command);
        savedCommand.getId();
     /*   return "redirect:/phonelist/" + savedCommand.getId() + "/show";*/
        return "redirect:/apps/phonelist/index";

    }

	@GetMapping
	@RequestMapping("/apps/phonelist/new")
	public String newPhoneList(Model model){
		model.addAttribute("phonelist", new PhoneListCommand());
		return PHONELIST_PHONELISTFORM_URL;
	}

	@GetMapping
	@RequestMapping("/apps/phonelist/{id}/update")
	public String updatePhoneList(@PathVariable String id, Model model){
		model.addAttribute("phonelist", phoneListService.findCommandById(Long.valueOf(id)));
		return PHONELIST_PHONELISTFORM_URL;
	}
	@GetMapping
	@RequestMapping("/apps/phonelist/{id}/delete")
	public String deleteById(@PathVariable String id){

		log.debug("Deleting id: " + id);

		phoneListService.deleteById(Long.valueOf(id));
		return "redirect:/apps/phonelist/index";
	}
}

