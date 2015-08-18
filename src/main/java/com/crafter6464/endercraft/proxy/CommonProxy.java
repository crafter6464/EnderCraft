package com.crafter6464.endercraft.proxy;

import com.crafter6464.endercraft.block.BlockEC;
import com.crafter6464.endercraft.item.ItemEC;

public class CommonProxy implements IProxy {

	@Override
	public void registerRenders() {
ItemEC.registerRenders();	
BlockEC.registerRenders();
	}

}
