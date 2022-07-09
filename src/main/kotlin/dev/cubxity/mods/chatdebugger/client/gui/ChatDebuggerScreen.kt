package dev.cubxity.mods.chatdebugger.client.gui

import dev.cubxity.mods.chatdebugger.client.gui.components.TextComponent
import dev.cubxity.mods.chatdebugger.client.gui.tree.ReceivedMessageNode
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.components.ScrollComponent
import gg.essential.elementa.components.TreeListComponent
import gg.essential.elementa.components.UIBlock
import gg.essential.elementa.components.inspector.Inspector
import gg.essential.elementa.constraints.ChildBasedMaxSizeConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import gg.essential.elementa.effects.OutlineEffect
import gg.essential.elementa.state.BasicState
import net.minecraft.client.MinecraftClient
import net.minecraft.client.report.ChatLog
import net.minecraft.client.report.ReceivedMessage
import java.awt.Color

class ChatDebuggerScreen : WindowScreen(ElementaVersion.V2) {
    private val messageState = BasicState<ReceivedMessage?>(null)

    private val container = UIBlock(Color(0x171519)).constrain {
        width = 100.percent
        height = 100.percent
    } childOf window

    private val chatList = ScrollComponent().constrain {
        x = 4.pixels
        y = 4.pixels
        width = 35.percent.minus(8.pixels)
        height = 100.percent.minus(4.pixels)
    } childOf container

    private val divider = UIBlock(Colors.Divider).constrain {
        x = SiblingConstraint(8f)
        width = 1.pixels
        height = 100.percent
    } childOf container

    private val detailsTree = TreeListComponent().constrain {
        x = SiblingConstraint(8f)
        y = 8.pixels
        width = 65.percent.minus(17.pixels)
        height = 100.pixels.minus(8.pixels)
    } childOf container

    init {
        Inspector(window).constrain {
            x = 10.pixels(true)
            y = 10.pixels(true)
        } childOf window

        val chatLog = MinecraftClient.getInstance().abuseReportContext.chatLog
        populateChat(chatLog)

        messageState.onSetValue { value ->
            if (value !== null) {
                val node = ReceivedMessageNode(value)
                node.displayComponent.open(true)
                detailsTree.setRoots(listOf(node))
            } else {
                detailsTree.clearChildren()
            }
        }
    }

    private fun populateChat(chatLog: ChatLog) {
        chatLog.streamForward().streamMessages().forEach { message ->
            val container = UIBlock(Colors.Background).constrain {
                y = SiblingConstraint(padding = 2f).plus(2.pixels)
                width = 100.percent
                height = ChildBasedMaxSizeConstraint().plus(2.pixels)
            }.onMouseClick {
                messageState.set(message)
            } childOf chatList effect OutlineEffect(
                BasicState(Colors.Divider),
                messageState.map { if (it == message) 1f else 0f }
            )
            TextComponent(message.content).constrain {
                y = 2.pixels
            } childOf container
        }
    }
}