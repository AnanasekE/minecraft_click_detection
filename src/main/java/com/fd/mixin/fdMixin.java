package com.fd.mixin;

import net.minecraft.entity.EntityPose;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import com.fd.Fd;

import javax.swing.text.html.parser.Entity;

@Mixin(Entity.class)
public class fdMixin {
    @ModifyVariable(
            method = "setPose",
            at = @At(
                    value = "LOAD"
            ),
            argsOnly = true
    )
    private EntityPose testMod$onlyChangePoseIfAllowed(final EntityPose original) {


        if ((Object) this instanceof PlayerEntity player && Fd.test.get()) {
            return EntityPose.SLEEPING;
        }

        return original;
    }
}
