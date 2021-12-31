package net.rudahee.metallics_arts.setup.metalMind.band;

import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.rudahee.metallics_arts.MetallicsArts;

public class BandAluminumDuralumin extends BandMindAbstract {
    CompoundNBT nbt = new CompoundNBT();
    public BandAluminumDuralumin (Item.Properties properties){
        super(properties);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.aluminum",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.duralumin",0);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.capacityAluminum",100);
        nbt.putInt(MetallicsArts.MOD_ID+".BandAluminumDuralumin.capacityDuralumin",100);
        setNbt(nbt);
    }
}