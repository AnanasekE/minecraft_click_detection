package com.fd.mixin;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import com.fd.Fd;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(PlayerEntity.class)
public abstract class fdMixin {
    @Inject(at = @At("RETURN"), method = "updatePose")
    private void updatePose(CallbackInfo info) {
            ((PlayerEntity) (Object) this).setPose(EntityPose.SLEEPING);
    }
}
