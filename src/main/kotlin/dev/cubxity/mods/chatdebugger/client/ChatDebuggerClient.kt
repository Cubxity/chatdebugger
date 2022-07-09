package dev.cubxity.mods.chatdebugger.client

import com.mojang.brigadier.Command
import dev.cubxity.mods.chatdebugger.client.gui.ChatDebuggerScreen
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.minecraft.client.MinecraftClient

object ChatDebuggerClient : ClientModInitializer {
    override fun onInitializeClient() {
        ClientCommandRegistrationCallback.EVENT.register(
            ClientCommandRegistrationCallback { dispatcher, _ ->
                val literal = ClientCommandManager.literal("chatdebugger")
                    .executes {
                        val mc = MinecraftClient.getInstance()
                        mc.send {
                            mc.setScreen(ChatDebuggerScreen())
                        }
                        Command.SINGLE_SUCCESS
                    }
                dispatcher.register(literal)
            }
        );
    }
}