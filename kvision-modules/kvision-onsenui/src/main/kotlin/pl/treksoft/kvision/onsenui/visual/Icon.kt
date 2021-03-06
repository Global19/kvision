/*
 * Copyright (c) 2017-present Robert Jaros
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package pl.treksoft.kvision.onsenui.visual

import com.github.snabbdom.VNode
import pl.treksoft.kvision.core.Container
import pl.treksoft.kvision.core.StringPair
import pl.treksoft.kvision.core.Widget
import pl.treksoft.kvision.utils.set

/**
 * An icon component.
 *
 * @constructor Creates an icon component.
 * @param icon the name of the icon
 * @param size the size of the icon
 * @param rotate a number of degrees to rotate the icon - valid values are 90, 180 and 270
 * @param fixedWidth whether the icons to have the same width
 * @param spin whether the icon should be spinning
 * @param classes a set of CSS class names
 * @param init an initializer extension function
 */
open class Icon(
    icon: String,
    size: String? = null,
    rotate: Number? = null,
    fixedWidth: Boolean? = null,
    spin: Boolean? = null,
    classes: Set<String> = setOf(),
    init: (Icon.() -> Unit)? = null
) : Widget(classes) {

    /**
     * The name of the icon.
     */
    var icon: String by refreshOnUpdate(icon)

    /**
     * The size of the icon.
     */
    var size: String? by refreshOnUpdate(size)

    /**
     * A number of degrees to rotate the icon. Valid values are 90, 180 and 270.
     */
    var rotate: Number? by refreshOnUpdate(rotate)

    /**
     * Whether the icons to have the same width.
     */
    var fixedWidth: Boolean? by refreshOnUpdate(fixedWidth)

    /**
     * Whether the icons should be spinning.
     */
    var spin: Boolean? by refreshOnUpdate(spin)

    init {
        @Suppress("LeakingThis")
        init?.invoke(this)
    }

    override fun render(): VNode {
        return render("ons-icon")
    }

    override fun getSnAttrs(): List<StringPair> {
        val sn = super.getSnAttrs().toMutableList()
        sn.add("icon" to icon)
        size?.let {
            sn.add("size" to it)
        }
        rotate?.let {
            sn.add("rotate" to it.toString())
        }
        if (fixedWidth == true) {
            sn.add("fixed-width" to "fixed-width")
        }
        if (spin == true) {
            sn.add("spin" to "spin")
        }
        return sn
    }
}

/**
 * DSL builder extension function.
 *
 * It takes the same parameters as the constructor of the built component.
 */
fun Container.icon(
    icon: String,
    size: String? = null,
    rotate: Number? = null,
    fixedWidth: Boolean? = null,
    spin: Boolean? = null,
    classes: Set<String>? = null,
    className: String? = null,
    init: (Icon.() -> Unit)? = null
): Icon {
    val iconComp = Icon(icon, size, rotate, fixedWidth, spin, classes ?: className.set, init)
    this.add(iconComp)
    return iconComp
}
