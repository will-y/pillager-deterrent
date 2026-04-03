package dev.willyelton.pillagerdeterrent;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;

public class Constants {
    public static final String MODID = "pillager_deterrent";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static Identifier rl(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
}
