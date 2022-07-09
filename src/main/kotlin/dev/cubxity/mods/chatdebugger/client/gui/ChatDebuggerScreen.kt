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
import gg.essential.elementa.constraints.animation.Animations
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
        x = 0.pixels
        y = 4.pixels
        width = 35.percent
        height = 100.percent - 4.pixels
    } childOf container

    private val divider = UIBlock(Colors.Divider).constrain {
        x = SiblingConstraint()
        width = 1.pixels
        height = 100.percent
    } childOf container

    private val chatListScrollBar = UIBlock(Colors.Divider).constrain {
        x = 35.percent - 3.pixels
        y = 2.pixels
        width = 3.pixels
        height = 80.percent
    } childOf container

    private val detailsTree = TreeListComponent().constrain {
        x = SiblingConstraint(8f)
        y = 8.pixels
        width = 65.percent - 17.pixels
        height = 100.pixels - 8.pixels
    } childOf container

    init {
        chatList.setVerticalScrollBarComponent(chatListScrollBar, hideWhenUseless = true)

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
                y = SiblingConstraint()
                width = 100.percent
                height = ChildBasedMaxSizeConstraint().plus(4.pixels)
            }.onMouseClick {
                messageState.set(message)
            }.onMouseEnter {
                animate {
                    setColorAnimation(Animations.OUT_EXP, 0.3f, Colors.Divider.toConstraint())
                }
            }.onMouseLeave {
                animate {
                    setColorAnimation(Animations.OUT_EXP, 0.3f, Colors.Background.toConstraint())
                }
            } childOf chatList effect OutlineEffect(
                BasicState(Colors.Primary),
                messageState.map { if (it == message) 0.5f else 0f },
                drawInsideChildren = true
            )

            TextComponent(message.content).constrain {
                x = 4.pixels
                y = 4.pixels
                width = 100.pixels - 8.pixels
                height = 13.pixels
            } childOf container
        }
    }
}