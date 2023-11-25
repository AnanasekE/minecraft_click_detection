package com.fd;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.apache.logging.log4j.core.jmx.Server;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

import static net.minecraft.server.command.CommandManager.*;

public class DevTpCommands implements ModInitializer {
    @Override
    public void onInitialize() {
        // teleport the player to coords in the correct dimention
        for (Map.Entry<String, Map<String, ArrayList<Integer>>> entry : DevTpCoords.getCoords().entrySet()) {
            CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal(entry.getKey())
                    .executes(context -> {
                        if (context.getSource().getPlayer() == null) return 0;
                        // teleport the player to coords in dimention
//                        context.getSource().getPlayer().teleport();
                        if (entry.getValue().containsKey("nether")) {
                            // get nether world
//                            ServerWorld.NETHER.getRegistry()
                            context.getSource().getPlayer().getServerWorld().getDimension();
                        }
                        context.getSource().sendFeedback(() -> Text.literal("Teleported to %s".formatted("burning village")), false);
                        return 1;
                    })));
        }

    }
}
