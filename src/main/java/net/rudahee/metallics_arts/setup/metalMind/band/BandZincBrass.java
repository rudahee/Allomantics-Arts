package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class BandZincBrass extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandZincBrass (Item.Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".BandZincBrass.zinc",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandZincBrass.brass",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandZincBrass.capacityZinc",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandZincBrass.capacityBrass",100);
        setNbt(nbt);
    }
}