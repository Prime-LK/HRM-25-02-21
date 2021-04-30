package com.navitsa.hrm.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.navitsa.hrm.entity.Assestclass;
import com.navitsa.hrm.entity.AssetTransfer;
import com.navitsa.hrm.entity.ItemGroup;
import com.navitsa.hrm.entity.ItemMaster;
import com.navitsa.hrm.entity.Location;
import com.navitsa.hrm.repository.AssetTransferRepository;
import com.navitsa.hrm.repository.ItemMasterRepository;
import com.navitsa.hrm.service.AssetTransferService;
import com.navitsa.hrm.service.ItemMasterService;

@Controller
public class AssetTransferController {

	@Autowired
	private AssetTransferService assettransferservice;

	@Autowired
	private ItemMasterService itemmasterservice;

	@Autowired
	private AssetTransferRepository assetTransferrepository;

	@Autowired
	private ItemMasterRepository itemmasterrepository;

	
	@RequestMapping(value = "/assettransfer", method = RequestMethod.GET)
	public String loadAsstPage(Map<String, Object> map) {
		map.put("Transfer", new AssetTransfer());

		AssetTransfer assettransfer = new AssetTransfer();
		assettransfer.setTranferno(
				"00000".substring(assettransferservice.getTnMaxID().length()) + assettransferservice.getTnMaxID());
		map.put("Transfer", assettransfer);
		return "hrm/assettransfer";
	}

	@ModelAttribute("transfertable")
	public List<Location> showdeprection() {
		return assettransferservice.getAlldata();

	}

	@ModelAttribute("itemtable")
	public List<ItemMaster> showdeprectionn() {
		return assettransferservice.getAlldataaa();

	}

//	@RequestMapping(value = "/Transferdata", method = RequestMethod.POST)
//	public String savedat(@Valid @RequestParam("tranferno") String tranferno, @RequestParam("date") String date,
//			@RequestParam("locationcode1.locationcode") String locationcode1, @RequestParam("locationcode2.locationcode") String locationcode2,
//			@RequestParam("remarks") String remarks, @RequestParam("journalremarks") String journalremarks,
//			@RequestParam("itemcode") String itemcode, @RequestParam("qty") String qty,
//			@RequestParam("serialnumber") String serialnumber, BindingResult br)
//
//	{
//
//		if (br.hasErrors()) {
//			return "assettransfer";
//		} else {
//			try {
//
//				AssetTransfer1 as1 = new AssetTransfer1();
//				as1.setItemcode(itemcode);
//				as1.setQty(qty);
//				as1.setSerialnumber(serialnumber);
//				
//				AssetTransfer assettransfer = new AssetTransfer();
//				
//				assettransfer.setDate(date);
//				assettransfer.setTranferno(tranferno);
//				assettransfer.setRemarks(remarks);
//				assettransfer.setJournalremarks(journalremarks);
//				
//				Location lo = new Location();
//				lo.setDescription(locationcode1);
//				lo.setDescription(locationcode2);
//				
//				assettransfer.setLocationcode1(lo);
//				assettransfer.setLocationcode2(lo);
//
//
//				assTra1Service.savedat(as1);
//				assettransferservice.savedat(assettransfer);
//
//				return "redirect:/assettransfer";
//			} catch (Exception e) {
//				System.out.println("Details Not Saved");
//			}
//		}
//		return "assettransfer";
//
//	}
	@RequestMapping(value = "/Transferdata", method = RequestMethod.POST)
	public String savell(@Valid @ModelAttribute("Transfer") AssetTransfer at ,BindingResult br) {
		
		if (br.hasErrors()) {
			return "hrm/assettransfer";
		} else {
			try {
				
				assettransferservice.savedat(at);
				return "redirect:/hrm/assettransfer";
			} catch (Exception e) {
				System.out.println("Details Not Saved");
			}
		}
		return "hrm/assettransfer";
		
		
		
	}
	@ModelAttribute("AssetTransfer")
	public List<AssetTransfer> getAllRm() {
		return assettransferservice.getAllRmm();
	}
	
	@RequestMapping(value="AssetTransfer", method= RequestMethod.GET)
	public ModelAndView updatecode5285(@RequestParam ("id") String id) {
		ModelAndView mav = new ModelAndView("hrm/assettransfer");
		AssetTransfer transfer = assettransferservice.getRmm(id);
		mav.addObject("Transfer", transfer);
		return mav;
	}
	
	@GetMapping("/getDesc")
	@ResponseBody
	public ItemMaster getDetails(@RequestParam("itemcode")String itemcode) {
		ItemMaster itemDesc = itemmasterservice.getDetails(itemcode);
		return itemDesc;
	}

}
