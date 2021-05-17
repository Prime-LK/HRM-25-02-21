package com.navitsa.hrm.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Assestclass;
import com.navitsa.hrm.entity.ItemGroup;
import com.navitsa.hrm.entity.UOM;
import com.navitsa.hrm.repository.ItemRepository;
import com.navitsa.hrm.repository.UOMRepository;

@Service
public class ItemService {

	@Autowired
	private ItemRepository itemrepository;
	
	
	@Autowired
	private UOMRepository uomrepository;

	
	public  void saveitem(ItemGroup it) 
	{
		itemrepository.save(it);
	}

	public ItemGroup getDetailsofDeprection(String UOM_Code) {
		return itemrepository.findById(UOM_Code).get();
	}
	public List<UOM> getAlldata() {
		return (List<UOM>) uomrepository.findAll();
	}
	
	

	public String getItemMaxID() {
		if (itemrepository.getItemMaxID() == null) {
			return "1";
		} else {
			return itemrepository.getItemMaxID();
		}
	}
	
	
	public List<ItemGroup> getAllRm() {
		return (List<ItemGroup>) itemrepository.findAll();
		
	}
	public ItemGroup getRm(String uom) 
	{
		return itemrepository.findById(uom).get();
	}

	
	
}