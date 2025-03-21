package dev.willyelton.pillagerdeterrent.item;

import dev.willyelton.pillagerdeterrent.Config;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

import java.util.List;

public class PillagerWardingBannerItem extends BannerItem {
    private static final Style STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE).withItalic(true);

    public PillagerWardingBannerItem(Block block, Block wallBlock, Properties properties) {
        super(block, wallBlock, properties.stacksTo(16)
                .component(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("lore.pillager_deterrent.banner", Config.BANNER_RANGE.get()).withStyle(STYLE));
    }
}
