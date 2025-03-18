package dev.willyelton.pillagerdeterrent.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.tag.PillagerDeterrentTags;
import dev.willyelton.pillagerdeterrent.util.InventoryUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {
    // TODO: Target is the bytecode name (accesstransformer)
    @Inject(method = "tick", cancellable = true,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getCurrentDifficultyAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/DifficultyInstance;"))
    public void tick(ServerLevel level, boolean spawnEnemies, boolean spawnFriendlies, CallbackInfoReturnable<Integer> cir, @Local Player player) {
        if (!InventoryUtils.findItem(player.getInventory(), stack -> stack.is(PillagerDeterrentTags.PILLAGER_WARD)).isEmpty()) {
            PillagerDeterrent.LOGGER.info("Deterring pillager spawn");
            cir.setReturnValue(0);
        }
    }
}
