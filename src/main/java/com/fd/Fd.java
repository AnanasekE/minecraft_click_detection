package com.fd;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.mixin.container.ServerPlayerEntityMixin;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityPose;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerEntityManager;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.apache.logging.log4j.core.jmx.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Fd implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("fd");
    public static AtomicBoolean test = new AtomicBoolean(false);

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");
//        AtomicReference<Boolean> RANDOMCooldown = new AtomicReference<>(false);

        // right click detection
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (player.preferredHand == null) {
                return TypedActionResult.pass(player.getStackInHand(hand));
            }
            // here add your code
            player.sendMessage(Text.of("Player " + player.getName() + " used " + player.getStackInHand(player.preferredHand).getName()), false);
            player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.3F, 1.0F);
            player.setPose(EntityPose.SLEEPING);

            if (test.get()) {
                test.set(false);
            } else {
                test.set(true);
            }


            return TypedActionResult.pass(player.getStackInHand(hand));
        });

        // left click detection
        AtomicReference<Boolean> handSwingCooldown = new AtomicReference<>(false);
        ServerTickEvents.START_SERVER_TICK.register(server -> server.getPlayerManager().getPlayerList().forEach(player -> {
            if (player.handSwinging) {
                if (!handSwingCooldown.get()) {
                    if (player.preferredHand == null) {
                        return;
                    }
                    if (Objects.equals(player.getStackInHand(player.preferredHand), new ItemStack(Items.DIAMOND_PICKAXE)))
                    {
                        player.playSound(SoundEvents.BLOCK_ANVIL_PLACE, 0.3F, 1.0F);
                        player.sendMessage(Text.of("Player " + player.getScoreboard()));
                    }
                    // here add your code
//                    LOGGER.info("Player " + player.getName().getString() + " attacked with " + player.getStackInHand(player.preferredHand).getName());
                    player.sendMessage(Text.of("Player " + player.getName() + " attacked with " + player.getStackInHand(player.preferredHand).getName()), false);
                    player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.3F, 1.0F);

                    // leave next line
                    handSwingCooldown.set(true);
                }
            } else {
                if (handSwingCooldown.get()) {
                    handSwingCooldown.set(false);
                }
            }
        }));
//        ServerTickEvents.START_SERVER_TICK.register(server -> server.getPlayerManager().getPlayerList().forEach(player -> {
//            if (test.get()) {
//                if (player.preferredHand == null) {
//                    return;
//                }
//                player.setPose(EntityPose.SLEEPING);
//            }
//        }));



    }

}