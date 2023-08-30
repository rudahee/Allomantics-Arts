package net.rudahee.metallics_arts.setup;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rudahee.metallics_arts.MetallicsArts;
import net.rudahee.metallics_arts.data.providers.*;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderEN;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderES;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderJP;
import net.rudahee.metallics_arts.data.providers.language_providers.ModLanguageProviderPL;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModBannerTagProvider;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModBeaconTagProvider;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModBlockTagProvider;
import net.rudahee.metallics_arts.data.providers.tags_providers.ModItemTagsProvider;
import net.rudahee.metallics_arts.modules.book.DemoBookProvider;
import net.rudahee.metallics_arts.modules.custom_entities.ExampleEntity;
import net.rudahee.metallics_arts.setup.registries.ModEntityTypesRegister;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = MetallicsArts.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();



        gen.addProvider(event.includeServer(), new ModBlockStateProvider(packOutput, existingFileHelper));
        gen.addProvider(event.includeServer(), new ModItemModelProvider(gen, existingFileHelper));
        //gen.addProvider(event.includeServer(), new ModLootTableProvider(gen)); //todo mirar lo de brayan

        gen.addProvider(event.includeServer(), new ModRecipeProvider(gen));

        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_es"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_ar"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_mx"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_uy"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderES(gen, "es_ve"));

        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_us"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_au"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_ca"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderEN(gen, "en_gb"));

        gen.addProvider(event.includeServer(), new ModLanguageProviderJP(gen, "ja_jp"));
        gen.addProvider(event.includeServer(), new ModLanguageProviderPL(gen, "pl_pl"));

        gen.addProvider(event.includeServer(), new DemoBookProvider(gen, MetallicsArts.MOD_ID, null));
        //gen.addProvider(event.includeClient(), null);

        gen.addProvider(event.includeServer(), new ModWorldGenerationProvider(packOutput, lookupProvider)); // TODO Revisar jsons, generar geodas

/*
        ModBlockTagProvider blockTags = new  ModBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        gen.addProvider(event.includeServer(), blockTags);
        gen.addProvider(event.includeServer(), new ModItemTagsProvider(packOutput,lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        gen.addProvider(event.includeServer(), new ModBannerTagProvider(packOutput, lookupProvider, event.getExistingFileHelper()));
        gen.addProvider(event.includeServer(), new ModBeaconTagProvider(packOutput, lookupProvider, existingFileHelper));*/
    }

    @SubscribeEvent
    public static void entityAtributes(EntityAttributeCreationEvent event){
        event.put(ModEntityTypesRegister.EXAMPLE.get(), ExampleEntity.getExampleAttributes().build());
    }
}
