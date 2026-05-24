package dev.willyelton.pillagerdeterrent.item;

import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PillagerWardingBannerItem extends BannerItem {
    public static final Style STYLE = Style.EMPTY.withColor(ChatFormatting.DARK_PURPLE).withItalic(true);

    public PillagerWardingBannerItem(Block block, Block wallBlock, Properties properties) {
        super(block, wallBlock, properties.stacksTo(16));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag tooltipFlag) {
        list.add(Component.translatable("lore.pillager_deterrent.banner", 128).withStyle(STYLE));
    }

    @Override
    public void onCraftedBy(ItemStack itemStack, Level level, Player player) {
        if (itemStack.getTag() == null || !itemStack.getTag().contains("BlockEntityTag")) {
            if (itemStack.getTag() == null) {
                itemStack.setTag(new CompoundTag());
            }

            itemStack.getTag().put("BlockEntityTag", PillagerDeterrent.getTag(level.holderLookup(Registries.BANNER_PATTERN)));
        }
    }
}
