package net.rudahee.metallics_arts.setup.world_generation.ore_generation;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.rudahee.metallics_arts.MetallicsArts;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> TIN_PLACED_STONE_KEY = createKey("tin_placed_stone");

    public static final ResourceKey<PlacedFeature> ZINC_PLACED_STONE_KEY = createKey("zinc_placed_stone");

    public static final ResourceKey<PlacedFeature> ALUMINUM_PLACED_STONE_KEY = createKey("aluminum_placed_stone");

    public static final ResourceKey<PlacedFeature> LEAD_PLACED_STONE_KEY = createKey("lead_placed_stone");

    public static final ResourceKey<PlacedFeature> CADMIUM_PLACED_DEEPSLATE_KEY = createKey("cadmium_placed_deepslate");

    public static final ResourceKey<PlacedFeature> ALUMINUM_PLACED_DEEPSLATE_KEY = createKey("aluminum_placed_deepslate");

    public static final ResourceKey<PlacedFeature> CHROMIUM_PLACED_DEEPSLATE_KEY = createKey("chromium_placed_deepslate");

    public static final ResourceKey<PlacedFeature> SILVER_PLACED_DEEPSLATE_KEY = createKey("silver_placed_deepslate");

    public static final ResourceKey<PlacedFeature> NICKEL_PLACED_DEEPSLATE_KEY = createKey("nickel_placed_deepslate");

    public static final ResourceKey<PlacedFeature> LEAD_PLACED_DEEPSLATE_KEY = createKey("lead_placed_deepslate");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, TIN_PLACED_STONE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TIN_ORE_STONE_KEY),
                ModOrePlacement.commonOrePlacement(16, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(120))));
        register(context, ZINC_PLACED_STONE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ZINC_ORE_STONE_KEY),
                ModOrePlacement.commonOrePlacement(14, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(120))));
        register(context, ALUMINUM_PLACED_STONE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMINUM_ORE_STONE_KEY),
                ModOrePlacement.commonOrePlacement(20, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(120))));
        register(context, LEAD_PLACED_STONE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAD_ORE_STONE_KEY),
                ModOrePlacement.commonOrePlacement(13, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.absolute(120))));
        register(context, CADMIUM_PLACED_DEEPSLATE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CADMIUM_ORE_DEEPSLATE_KEY),
                ModOrePlacement.commonOrePlacement(22, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, ALUMINUM_PLACED_DEEPSLATE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ALUMINUM_ORE_DEEPSLATE_KEY),
                ModOrePlacement.commonOrePlacement(10, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, CHROMIUM_PLACED_DEEPSLATE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.CHROMIUM_ORE_DEEPSLATE_KEY),
                ModOrePlacement.commonOrePlacement(15, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, SILVER_PLACED_DEEPSLATE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_ORE_DEEPSLATE_KEY),
                ModOrePlacement.commonOrePlacement(12, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, NICKEL_PLACED_DEEPSLATE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.NICKEL_ORE_DEEPSLATE_KEY),
                ModOrePlacement.commonOrePlacement(18, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
        register(context, LEAD_PLACED_DEEPSLATE_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.LEAD_ORE_DEEPSLATE_KEY),
                ModOrePlacement.commonOrePlacement(10, // veins per chunk
                        HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-64), VerticalAnchor.absolute(80))));
    }



        private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(MetallicsArts.MOD_ID, name));
    }
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

}
