package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.Assestclass;
import com.prime.hrm.entity.AssetTransfer;
import com.prime.hrm.entity.Depreciationgroup;
import com.prime.hrm.entity.ItemGroup;
import com.prime.hrm.entity.ItemMaster;
import com.prime.hrm.entity.Location;
import com.prime.hrm.entity.UOM;
import com.prime.hrm.repository.AssestclassRepository;
import com.prime.hrm.repository.DepreciationgroupRepository;
import com.prime.hrm.repository.ItemMasterRepository;
import com.prime.hrm.repository.ItemRepository;
import com.prime.hrm.repository.LocationRepository;
import com.prime.hrm.repository.UOMRepository;


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