package dev.willyelton.pillagerdeterrent.mixin;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.HashSet;
import java.util.Set;

@Mixin(BlockEntityType.Builder.class)
public class BlockEntityTypeBuilderMixin {
    @ModifyArg(method = "of", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/BlockEntityType$Builder;<init>(Lnet/minecraft/world/level/block/entity/BlockEntityType$BlockEntitySupplier;Ljava/util/Set;)V"), index = -1)
    private static Set<Block> pillagerDeterrent$ValidBlocks$0(Set<Block> set) {
        return new HashSet<>(set);
    }
}
