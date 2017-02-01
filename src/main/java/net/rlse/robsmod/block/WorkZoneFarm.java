package net.rlse.robsmod.block;

import java.util.ArrayList;
import java.util.List;

public class WorkZoneFarm implements IWorkZone {

	private ArrayList<String> products;
	
	public WorkZoneFarm(String productName) {
		products = new ArrayList<String>(1);
		products.add(productName);
	}
	
	@Override
	public List<String> getOutputProducts() {
		return products;
	}
}
