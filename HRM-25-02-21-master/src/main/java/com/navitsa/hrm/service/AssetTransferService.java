package com.navitsa.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navitsa.hrm.entity.Assestclass;
import com.navitsa.hrm.entity.AssetTransfer;
import com.navitsa.hrm.entity.ItemMaster;
import com.navitsa.hrm.entity.Location;
import com.navitsa.hrm.repository.AssetTransferRepository;
import com.navitsa.hrm.repository.ItemMasterRepository;
import com.navitsa.hrm.repository.LocationRepository;

@Service
public class AssetTransferService {

	@Autowired
	private AssetTransferRepository assettransferrepository ;
	
	@Autowired
	private LocationRepository locationrepository ;
	
	@Autowired
	private ItemMasterRepository itemmasterrepository ;
	
	public String getTnMaxID() {
		if (assettransferrepository.getTnMaxID() == null) {
			return "1";
		} else {
			return assettransferrepository.getTnMaxID();
		}
	}
	public  void savedat(AssetTransfer assettransfer) 
	{
		assettransferrepository.save(assettransfer);
	}
	public List<AssetTransfer> getAllRm() {
		return (List<AssetTransfer>)  assettransferrepository.findAll();
		
	}
	
	public AssetTransfer getRm(String itemcode) 
	{
		return assettransferrepository.findById(itemcode).get();
	}
	public List<Location> getAlldata() {
		return (List<Location>) locationrepository.findAll();
	}
	public List<ItemMaster> getAlldataaa() {
		return (List<ItemMaster>) itemmasterrepository.findAll();
	}
	public AssetTransfer getRmm(String transferno) 
	{
		return assettransferrepository.findById(transferno).get();
	}
	
	public List<AssetTransfer> getAllRmm() 
	{
		
		return (List<AssetTransfer>)  assettransferrepository.findAll();
	}

}