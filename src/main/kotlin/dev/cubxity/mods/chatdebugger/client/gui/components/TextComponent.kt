package dev.cubxity.mods.chatdebugger.client.gui.components

import gg.essential.elementa.UIComponent
import gg.essential.elementa.dsl.pixels
import gg.essential.elementa.state.BasicState
import gg.essential.elementa.state.State
import gg.essential.elementa.state.pixels
import gg.essential.universal.UMatrixStack
import net.minecraft.client.MinecraftClient
import net.minecraft.text.Text

class TextComponent(private val textState: State<Text>) : UIComponent() {
    constructor(text: Text) : this(BasicState(text))

    private var textWidthState = textState.map { text ->
        MinecraftClient.getInstance().textRenderer.getWidth(text).toFloat()
    }

    init {
        setWidth(textWidthState.pixels())
        setHeight(9.pixels)
    }

    override fun draw(matrixStack: UMatrixStack) {
        val renderer = MinecraftClient.getInstance().textRenderer
        val matrices = matrixStack.peek().toMCStack()
        val x = getLeft()
        val y = getTop()
        renderer.draw(matrices, textState.get(), x, y, 0xFFFFFF)
    }
}