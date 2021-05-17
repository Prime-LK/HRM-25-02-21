package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.Assestclass;
import com.navitsa.hrm.entity.AssetTransfer;
import com.navitsa.hrm.entity.ItemGroup;
import com.navitsa.hrm.entity.UOM;
import com.navitsa.hrm.repository.ItemRepository;
import com.navitsa.hrm.service.ItemService;



@Controller
public class ItemGroupController {

	@Autowired
	private ItemRepository itemrepository;
	
	@Autowired
	private ItemService itemservice;
	
	
	
	@RequestMapping(value = "/itemgroup", method = RequestMethod.GET)
	public String loadAsPage(Map<String, Object> map) {
		map.put("Item", new ItemGroup());

		ItemGroup itemgroup = new ItemGroup();
		itemgroup .setItemgroupcode("00000".substring(itemservice.getItemMaxID().length()) + itemservice.getItemMaxID());
		map.put("Item", itemgroup );
		return "itemgroup";
	}

	
	
	
	@ModelAttribute ("itemtable")
	public List<UOM>showdeprection()
	{
		return itemservice.getAlldata();
		
	}
	
	
	@RequestMapping(value = "/ItemGroup", method = RequestMethod.POST)
	public String saveitem(@Valid @ModelAttribute("Item")  ItemGroup it ,BindingResult br) {
		
		if (br.hasErrors()) {
			return "hrm/itemgroup";
		} else {
			try {
				itemrepository.save(it);
				return "redirect:/hrm/itemgroup";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/itemgroup";
		
		
}
	@ModelAttribute("ItemGroup")
	public List<ItemGroup> getAllRm() {
		return itemservice.getAllRm();
	}
	@RequestMapping(value="/ItemGroup", method= RequestMethod.GET)
	public ModelAndView updatecode(@RequestParam("id") String id) {
		ModelAndView mav = new ModelAndView("hrm/itemgroup");
		ItemGroup it = itemservice.getRm(id);
		mav.addObject("Item",it);
		return mav;
	}
}