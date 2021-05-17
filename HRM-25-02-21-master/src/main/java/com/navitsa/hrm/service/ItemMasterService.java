package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Assestclass;
import com.navitsa.hrm.entity.AssetTransfer;
import com.navitsa.hrm.entity.Depreciationgroup;
import com.navitsa.hrm.entity.ItemGroup;
import com.navitsa.hrm.entity.ItemMaster;
import com.navitsa.hrm.entity.Location;
import com.navitsa.hrm.entity.UOM;
import com.navitsa.hrm.repository.AssestclassRepository;
import com.navitsa.hrm.repository.DepreciationgroupRepository;
import com.navitsa.hrm.repository.ItemMasterRepository;
import com.navitsa.hrm.repository.ItemRepository;
import com.navitsa.hrm.repository.LocationRepository;
import com.navitsa.hrm.repository.UOMRepository;


@Service
public class ItemMasterService {

	
	
	
	@Autowired
	private UOMRepository uomrepository ;
	@Autowired
	private ItemMasterRepository itemmasterrepository ;
	@Autowired
	private ItemRepository  itemrepository ;
	@Autowired
	private LocationRepository   locationrepository ;
	
	@Autowired
	private AssestclassRepository   assestclassrepository ;
	
	@Autowired
	private DepreciationgroupRepository   depreciationgrouprepository ;
	
	
	public  void saveitm(ItemMaster itmm) 
	{
		itemmasterrepository.save(itmm);
	}

	public List<UOM> getAlldata() {
		return (List<UOM>) uomrepository.findAll();
	}

	public List<ItemGroup> getAll() {
		return (List<ItemGroup>) itemrepository.findAll();
	}
	
	public List<Location> getAlllocation() {
		return (List<Location>)  locationrepository.findAll();
	}
	public List<Assestclass> getAllcode() {
		return (List<Assestclass>) assestclassrepository.findAll();
	}
	
	public List<Depreciationgroup> getAlldep() {
		return (List<Depreciationgroup>) depreciationgrouprepository.findAll();
	}

	public List<ItemMaster> getAllRm() 
	{
		
		return (List<ItemMaster>)  itemmasterrepository.findAll();
	}

	public ItemMaster getRm(String itemcode) {
		
		return itemmasterrepository.findById(itemcode).get();
	}

	public ItemMaster getDetails(String itemcode) {
		return itemmasterrepository.getDetails(itemcode);
	}
	
}