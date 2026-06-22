package dev.willyelton.pillagerdeterrent.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.willyelton.pillagerdeterrent.item.PillagerWardingBannerItem;
import dev.willyelton.pillagerdeterrent.platform.Services;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static dev.willyelton.pillagerdeterrent.Constants.PILLAGER_DETERRENT_POI_KEY;
import static dev.willyelton.pillagerdeterrent.Constants.hasPillagerWard;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {
    @Inject(method = "tick", cancellable = true,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getCurrentDifficultyAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/DifficultyInstance;"))
    public void tick(ServerLevel serverLevel, boolean spawnEnemies, CallbackInfo ci, @Local Player player, @Local BlockPos.MutableBlockPos pos) {
        if (hasPillagerWard(player) || pillager_deterrent$findWardingBlock(serverLevel, pos)) {
            player.sendOverlayMessage(Component.translatable("chat.pillager_deterrent.deterred").withStyle(PillagerWardingBannerItem.STYLE));
            ci.cancel();
        }
    }

    @Unique
    private static boolean pillager_deterrent$findWardingBlock(ServerLevel level, BlockPos spawnPosition) {
        return level.getPoiManager().findClosest(poiTypeHolder -> poiTypeHolder.is(PILLAGER_DETERRENT_POI_KEY),
                spawnPosition, Services.REGISTRATION.bannerRange(), PoiManager.Occupancy.ANY).isPresent();
    }
}
