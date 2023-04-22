package net.rudahee.metallics_arts.setup.registries.items;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.custom_tiers.PostNetheriteMaterials;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.ArmorPiecesEnum;
import net.rudahee.metallics_arts.data.enums.implementations.custom_items.MetalMindEnum;
import net.rudahee.metallics_arts.modules.custom_items.armors.MistCloak;
import net.rudahee.metallics_arts.modules.custom_items.metal_minds.bands.BandAluminumDuralumin;
import net.rudahee.metallics_arts.setup.registries.ModItemsRegister;

public class ArmorRegister {

    private static final Item.Properties PROPERTIES = new Item.Properties().tab(MetallicsArts.MA_TAB);

    public static void register() {
        RegistryObject<Item> steelHelmet = MetallicsArts.registerItem("steel_helmet",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, EquipmentSlot.HEAD, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.HELMET, steelHelmet);

        RegistryObject<Item> steelChesplate = MetallicsArts.registerItem("steel_chestplate",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, EquipmentSlot.CHEST, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, steelChesplate);

        RegistryObject<Item> steelLeggins = MetallicsArts.registerItem("steel_leggins",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, EquipmentSlot.LEGS, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.LEGGINGS, steelLeggins);

        RegistryObject<Item> steelShoes = MetallicsArts.registerItem("steel_shoes",
                () -> new ArmorItem(PostNetheriteMaterials.STEEL, EquipmentSlot.FEET, PROPERTIES));
        ModItemsRegister.STEEL_ARMOR.put(ArmorPiecesEnum.BOOTS, steelShoes);

        RegistryObject<Item> aluminumHelmet = MetallicsArts.registerItem("aluminum_helmet",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, EquipmentSlot.HEAD, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.HELMET, aluminumHelmet);

        RegistryObject<Item> aluminumChesplate = MetallicsArts.registerItem("aluminum_chestplate",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, EquipmentSlot.CHEST, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.CHESTPLATE, aluminumChesplate);

        RegistryObject<Item> aluminumLeggins = MetallicsArts.registerItem("aluminum_leggins",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, EquipmentSlot.LEGS, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.LEGGINGS, aluminumLeggins);

        RegistryObject<Item> aluminumShoes = MetallicsArts.registerItem("aluminum_shoes",
                () -> new ArmorItem(PostNetheriteMaterials.ALUMINUM, EquipmentSlot.FEET, PROPERTIES));
        ModItemsRegister.ALUMINUM_ARMOR.put(ArmorPiecesEnum.BOOTS, aluminumShoes);

        ModItemsRegister.MISTCLOACK = MetallicsArts.registerItem("mistcloak", () -> new MistCloak(new Item.Properties().tab(MetallicsArts.MA_TAB).stacksTo(1)));

    }
}
