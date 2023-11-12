package com.fd.mixin;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import com.fd.Fd;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.text.html.parser.Entity;

@Mixin(PlayerEntity.class)
public class fdMixin {
    @Inject(at = @At("HEAD"), method = "updatePose")
    private void updatePose(CallbackInfo ci) {
        if (Fd.test.get()) {
            ((PlayerEntity) (Object) this).setPose(EntityPose.SLEEPING);
            return;
        }
    }
}
