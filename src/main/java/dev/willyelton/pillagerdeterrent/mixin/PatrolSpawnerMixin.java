package dev.willyelton.pillagerdeterrent.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import dev.willyelton.pillagerdeterrent.Config;
import dev.willyelton.pillagerdeterrent.Registration;
import dev.willyelton.pillagerdeterrent.compat.CuriosCompatability;
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
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

import static dev.willyelton.pillagerdeterrent.item.PillagerWardingBannerItem.STYLE;

@Mixin(PatrolSpawner.class)
public abstract class PatrolSpawnerMixin {
    @Inject(method = "tick", cancellable = true,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getCurrentDifficultyAt(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/DifficultyInstance;"))
    public void tick(ServerLevel serverLevel, boolean spawnEnemies, boolean spawnFriendlies, CallbackInfo ci, @Local Player player, @Local BlockPos.MutableBlockPos pos) {
        if (!pillager_deterrent$findPillagerWard(player).isEmpty() || pillager_deterrent$findWardingBlock(serverLevel, pos)) {
            player.displayClientMessage(Component.translatable("chat.pillager_deterrent.deterred").withStyle(STYLE), true);
            ci.cancel();
        }
    }

    @Unique
    private static ItemStack pillager_deterrent$findPillagerWard(Player player) {
        Predicate<ItemStack> wardPredicate = stack -> stack.is(PillagerDeterrentTags.PILLAGER_WARD);
        return CuriosCompatability.getCuriosItems(player, wardPredicate)
                .orElse(InventoryUtils.findItem(player.getInventory(), stack -> stack.is(PillagerDeterrentTags.PILLAGER_WARD)));
    }

    @Unique
    private static boolean pillager_deterrent$findWardingBlock(ServerLevel level, BlockPos spawnPosition) {
        return level.getPoiManager().findClosest(poiTypeHolder -> poiTypeHolder.is(Registration.PILLAGER_WARDING_BANNER_POI.getKey()),
                spawnPosition, Config.BANNER_RANGE.get(), PoiManager.Occupancy.ANY).isPresent();
    }
}
