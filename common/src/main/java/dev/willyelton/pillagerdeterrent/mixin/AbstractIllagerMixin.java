package dev.willyelton.pillagerdeterrent.mixin;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.raid.Raider;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

import static dev.willyelton.pillagerdeterrent.Constants.hasPillagerWard;

@Mixin(AbstractIllager.class)
public abstract class AbstractIllagerMixin extends Raider {
    protected AbstractIllagerMixin(EntityType<? extends Raider> type, Level level) {
        super(type, level);
    }

    @Inject(method = "registerGoals", at = @At(value = "TAIL"))
    protected void registerGoals(CallbackInfo ci) {
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 12.0F, 1.0, 1.2, pillager_deterrent$avoidPredicate()));
    }

    @Unique
    private Predicate<LivingEntity> pillager_deterrent$avoidPredicate() {
        return livingEntity -> {
            if (livingEntity instanceof Player player) {
                return hasPillagerWard(player) && this.raid == null;
            }

            return false;
        };
    }
}
