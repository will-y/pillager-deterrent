package dev.willyelton.pillagerdeterrent.block;

import dev.willyelton.pillagerdeterrent.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BannerBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class WardingBannerBlock extends BannerBlock {
    public WardingBannerBlock(Properties properties) {
        super(DyeColor.WHITE, properties);
    }

//    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
//        if (level.getBlockEntity(pos) instanceof BannerBlockEntity bannerblockentity) {
//            ItemStack stack = new ItemStack(Registration.PILLAGER_WARDING_BANNER_BLOCK_ITEM);
//            stack.applyComponents(bannerblockentity.collectComponents());
//            return stack;
//        } else {
//            return super.getCloneItemStack(level, pos, state);
//        }
//    }
}
