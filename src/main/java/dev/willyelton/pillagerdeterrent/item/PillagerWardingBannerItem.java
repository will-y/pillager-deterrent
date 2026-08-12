package dev.willyelton.pillagerdeterrent.item;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

import static dev.willyelton.pillagerdeterrent.PillagerDeterrent.getPatch;

public class PillagerWardingBannerItem extends BannerItem {
    public static final Style STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE).withItalic(true);

    public PillagerWardingBannerItem(Block block, Block wallBlock, Properties properties) {
        super(block, wallBlock, properties.stacksTo(16));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext tooltipContext, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("lore.pillager_deterrent.banner", PillagerDeterrent.CONFIG.bannerRange()).withStyle(STYLE));
    }

    @Override
    public void onCraftedBy(ItemStack itemStack, Level level, Player player) {
        itemStack.applyComponents(getPatch(level.holderLookup(Registries.BANNER_PATTERN)));
    }
}
