package dev.willyelton.pillagerdeterrent;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {

    public static ForgeConfigSpec.IntValue BANNER_RANGE;

    static final ForgeConfigSpec SPEC;

    static {
        ForgeConfigSpec.Builder configBuilder = new ForgeConfigSpec.Builder();
        setupConfig(configBuilder);
        SPEC = configBuilder.build();
    }

    private static void setupConfig(ForgeConfigSpec.Builder builder) {
        BANNER_RANGE = builder
                .comment("The number of blocks away that the pillager warding banner will prevent patrol spawns")
                .defineInRange("banner_range", 128, 0, 512);
    }
}
