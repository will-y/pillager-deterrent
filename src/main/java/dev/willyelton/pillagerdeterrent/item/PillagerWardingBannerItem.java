package dev.willyelton.pillagerdeterrent.item;

import dev.willyelton.pillagerdeterrent.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

import java.util.List;

public class PillagerWardingBannerItem extends BannerItem {
    public static final Style STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE).withItalic(true);

    public PillagerWardingBannerItem(Block block, Block wallBlock, Properties properties) {
        super(block, wallBlock, properties.stacksTo(16));
    }

    @Override
    public void appendHoverText(ItemStack stack, Level level, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("lore.pillager_deterrent.banner", Config.BANNER_RANGE.get()).withStyle(STYLE));
    }
}
