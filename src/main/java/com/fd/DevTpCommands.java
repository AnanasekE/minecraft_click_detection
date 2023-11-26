package com.fd;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.apache.logging.log4j.core.jmx.Server;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static net.minecraft.server.command.CommandManager.*;

public class DevTpCommands implements ModInitializer {
    @Override
    public void onInitialize() {
        // teleport the player to coords in the correct dimention
        for (Map.Entry<String, Map<String, ArrayList<Integer>>> entry : DevTpCoords.getCoords().entrySet()) {
            CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("devTP:"+entry.getKey())
                    .executes(context -> {
                        if (context.getSource().getPlayer() == null) return 0;

                        PlayerEntity player = context.getSource().getPlayer();
                        ServerWorld nether = context.getSource().getServer().getWorld(ServerWorld.NETHER);
                        ServerWorld overworld = context.getSource().getServer().getWorld(ServerWorld.OVERWORLD);
                        Set<PositionFlag> flags = new HashSet<>();
                        if (entry.getValue().containsKey("nether")) {
                            player.teleport(nether, entry.getValue().get("nether").get(0).doubleValue(), entry.getValue().get("nether").get(1).doubleValue(), entry.getValue().get("nether").get(2).doubleValue(),flags, player.getYaw(), player.getPitch());
                        }
                        if (entry.getValue().containsKey("overworld")) {
                            player.teleport(overworld, entry.getValue().get("overworld").get(0).doubleValue(), entry.getValue().get("overworld").get(1).doubleValue(), entry.getValue().get("overworld").get(2).doubleValue(),flags, player.getYaw(), player.getPitch());
                        }

                        context.getSource().sendFeedback(() -> Text.literal("Teleported to %s".formatted(entry.getKey())), false);
                        return 1;
                    })));
        }

    }
}
