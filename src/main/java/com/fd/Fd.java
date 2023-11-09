package com.fd;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.TypedActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicReference;

public class Fd implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("fd");

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        LOGGER.info("Hello Fabric world!");

        // right click detection
        AtomicReference<Boolean> ItemUseCooldown = new AtomicReference<>(true);
        UseItemCallback.EVENT.register((player, world, hand) -> {
            if (ItemUseCooldown.get()) {
                // here add your code
                LOGGER.info("Player " + player.getName().getString() + " used " + player.getStackInHand(player.preferredHand).getName());
                player.sendMessage(Text.of("Player " + player.getName() + " used " + player.getStackInHand(player.preferredHand).getName()), false);
                player.playSound(SoundEvents.BLOCK_FIRE_EXTINGUISH, 0.3F, 1.0F);

                // leave next line
                ItemUseCooldown.set(false);
            } else {
                ItemUseCooldown.set(true);
            }
            return TypedActionResult.pass(player.getStackInHand(hand));
        });
        // left click detection
        AtomicReference<Boolean> handSwingCooldown = new AtomicReference<>(false);
        ServerTickEvents.START_SERVER_TICK.register(server -> server.getPlayerManager().getPlayerList().forEach(player -> {
            if (player.handSwinging) {
                if (!handSwingCooldown.get()) {
                    // here add your code
                    LOGGER.info("Player " + player.getName().getString() + " attacked with " + player.getStackInHand(player.preferredHand).getName());
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


    }
}