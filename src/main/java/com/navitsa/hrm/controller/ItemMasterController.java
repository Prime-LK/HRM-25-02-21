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

import com.navitsa.hrm.entity.Assestclass;
import com.navitsa.hrm.entity.Depreciationgroup;
import com.navitsa.hrm.entity.ItemGroup;
import com.navitsa.hrm.entity.ItemMaster;
import com.navitsa.hrm.entity.Location;
import com.navitsa.hrm.entity.UOM;
import com.navitsa.hrm.repository.ItemMasterRepository;
import com.navitsa.hrm.service.ItemMasterService;

@Controller

public class ItemMasterController {

	@Autowired
	private ItemMasterRepository itemmasterrepository;

	@Autowired
	private ItemMasterService itemmasterservice;

	@RequestMapping(value = "/assetmaster", method = RequestMethod.GET)

	public String loaddpPage(Map<String, Object> map) {
		map.put("Saveit", new ItemMaster());
		ItemMaster itm = new ItemMaster();
		// map.put("id", asset);
		return "hrm/assetmaster";
	}

	@ModelAttribute("itemtable")
	public List<UOM> showdeprection() {
		return itemmasterservice.getAlldata();

	}

	@ModelAttribute("itemgroupstable")
	public List<ItemGroup> showitemgroup() {
		return itemmasterservice.getAll();

	}

	@ModelAttribute("locationtable")
	public List<Location> showlocation() {
		return itemmasterservice.getAlllocation();

	}
	@ModelAttribute("classgroupstable")
	public List<Assestclass> showcode() {
		return itemmasterservice.getAllcode();

	}
	@ModelAttribute("depreciationgroupstable")
	public List<Depreciationgroup> showdep() {
		return itemmasterservice.getAlldep();

	}

	@RequestMapping(value = "/saveitem", method = RequestMethod.POST)
	public String saveitm(@Valid @ModelAttribute("Saveit") ItemMaster itmm, BindingResult br) {
		if (br.hasErrors()) {
			return "hrm/assetmaster";
		} else {

			itemmasterrepository.save(itmm);
			return "redirect:/hrm/assetmaster";
		}

	}

}
