package com.prime.hrm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prime.hrm.entity.Assestclass;
import com.prime.hrm.entity.AssetTransfer;
import com.prime.hrm.entity.ItemMaster;
import com.prime.hrm.entity.Location;
import com.prime.hrm.repository.AssetTransferRepository;
import com.prime.hrm.repository.ItemMasterRepository;
import com.prime.hrm.repository.LocationRepository;

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