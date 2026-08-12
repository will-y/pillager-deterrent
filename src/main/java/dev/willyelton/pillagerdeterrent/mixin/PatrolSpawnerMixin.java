package dev.willyelton.pillagerdeterrent.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.willyelton.pillagerdeterrent.PillagerDeterrent;
import dev.willyelton.pillagerdeterrent.item.PillagerWardingBannerItem;
import dev.willyelton.pillagerdeterrent.tag.PillagerDeterrentTags;
import dev.willyelton.pillagerdeterrent.util.InventoryUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.levelgen.PatrolSpawner;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

import static dev.willyelton.pillagerdeterrent.PillagerDeterrent.PILLAGER_DETERRENT_POI_KEY;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {
    @Inject(method = "tick", cancellable = true,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getCurrentDifficultyAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/DifficultyInstance;"))
    public void tick(ServerLevel serverLevel, boolean bl, boolean bl2, CallbackInfoReturnable<Integer> cir, @Local Player player, @Local BlockPos.MutableBlockPos pos) {
        if (!pillager_deterrent$findPillagerWard(player).isEmpty() || pillager_deterrent$findWardingBlock(serverLevel, pos)) {
            player.displayClientMessage(Component.translatable("chat.pillager_deterrent.deterred").withStyle(PillagerWardingBannerItem.STYLE), true);
            cir.cancel();
        }
    }

    @Unique
    private static ItemStack pillager_deterrent$findPillagerWard(Player player) {
        Predicate<ItemStack> wardPredicate = stack -> stack.is(PillagerDeterrentTags.PILLAGER_WARD);
        return InventoryUtils.findItem(player, wardPredicate);
    }

    @Unique
    private static boolean pillager_deterrent$findWardingBlock(ServerLevel level, BlockPos spawnPosition) {
        return level.getPoiManager().findClosest(poiTypeHolder -> poiTypeHolder.is(PILLAGER_DETERRENT_POI_KEY),
                spawnPosition, PillagerDeterrent.CONFIG.bannerRange(), PoiManager.Occupancy.ANY).isPresent();
    }
}
