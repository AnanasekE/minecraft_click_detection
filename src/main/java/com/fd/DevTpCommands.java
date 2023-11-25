package com.fd;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Map;

import static net.minecraft.server.command.CommandManager.*;

public class DevTpCommands implements ModInitializer {
    @Override
    public void onInitialize() {
        // teleport the player to coords
        for (Map.Entry<String, Map<String, ArrayList<Integer>>> entry : DevTpCoords.getCoords().entrySet()
             ) {
            CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> dispatcher.register(literal("dsa")
                    .executes(context -> {
                        context.getSource().sendFeedback(() -> Text.literal("Teleported to %s".formatted("test")), false);
                        return 1;
                    })));
        }

    }
}
