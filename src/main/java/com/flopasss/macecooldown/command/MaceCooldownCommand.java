package com.flopasss.macecooldown.command;

import com.flopasss.macecooldown.MaceCooldown;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;

import static net.minecraft.commands.Commands.literal;
import static net.minecraft.commands.Commands.argument;

public class MaceCooldownCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(literal("macecooldown").requires(Commands.hasPermission(Commands.LEVEL_GAMEMASTERS))
                .then(argument("ticks", IntegerArgumentType.integer(0)).executes(ctx -> {
                    int ticks = IntegerArgumentType.getInteger(ctx, "ticks");

                    MaceCooldown.CONFIG.cooldownTicks = ticks;
                    MaceCooldown.CONFIG.save();

                    ctx.getSource().sendSuccess(
                            () -> Component.literal("Mace cooldown set to " + ticks + " ticks."),
                            true);
                    return 1;
                })));
    }
}
