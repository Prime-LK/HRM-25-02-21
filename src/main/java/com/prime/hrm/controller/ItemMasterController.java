package com.prime.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prime.hrm.entity.Assestclass;
import com.prime.hrm.entity.Depreciationgroup;
import com.prime.hrm.entity.ItemGroup;
import com.prime.hrm.entity.ItemMaster;
import com.prime.hrm.entity.Location;
import com.prime.hrm.entity.UOM;
import com.prime.hrm.repository.ItemMasterRepository;
import com.prime.hrm.service.ItemMasterService;

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
		return "assetmaster";
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
			return "assetmaster";
		} else {

			itemmasterrepository.save(itmm);
			return "redirect:/assetmaster";
		}

	}

}
