package test.pl.treksoft.kvision.dropdown

import pl.treksoft.kvision.core.Root
import pl.treksoft.kvision.dropdown.DD
import pl.treksoft.kvision.dropdown.DropDown
import test.pl.treksoft.kvision.DomSpec
import kotlin.browser.document
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DropDownSpec : DomSpec {

    @Test
    fun render() {
        run {
            val root = Root("test")
            val dd = DropDown("Dropdown", listOf("abc" to "#!/x", "def" to "#!/y"), "flag")
            root.add(dd)
            val element = document.getElementById("test")
            assertEquals("<div class=\"dropdown\"><button class=\"dropdown btn btn-default\" id=\"kv_dropdown_0\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"><span class=\"glyphicon glyphicon-flag\"></span> Dropdown</button><ul class=\"dropdown-menu\" aria-labelledby=\"kv_dropdown_0\"><li><a href=\"#!/x\">abc</a></li><li><a href=\"#!/y\">def</a></li></ul></div>", element?.innerHTML, "Should render correct drop down")
        }
    }

    @Test
    fun render_DropUp() {
        run {
            val root = Root("test")
            val dd = DropDown("Dropdown", listOf("abc" to "#!/x", "def" to "#!/y"), "flag", dropup = true)
            root.add(dd)
            val element = document.getElementById("test")
            assertEquals("<div class=\"dropup\"><button class=\"dropdown btn btn-default\" id=\"kv_dropdown_1\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"><span class=\"glyphicon glyphicon-flag\"></span> Dropdown</button><ul class=\"dropdown-menu\" aria-labelledby=\"kv_dropdown_1\"><li><a href=\"#!/x\">abc</a></li><li><a href=\"#!/y\">def</a></li></ul></div>", element?.innerHTML, "Should render correct drop down")
        }
    }

    @Test
    fun render_HeaderElement() {
        run {
            val root = Root("test")
            val dd = DropDown("Dropdown", listOf("abc" to DD.HEADER.POS), "flag")
            root.add(dd)
            val element = document.getElementById("test")
            assertEquals("<div class=\"dropdown\"><button class=\"dropdown btn btn-default\" id=\"kv_dropdown_2\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"><span class=\"glyphicon glyphicon-flag\"></span> Dropdown</button><ul class=\"dropdown-menu\" aria-labelledby=\"kv_dropdown_2\"><li class=\"dropdown-header\">abc</li></ul></div>", element?.innerHTML, "Should render correct drop down")
        }
    }

    @Test
    fun render_SeparatorElement() {
        run {
            val root = Root("test")
            val dd = DropDown("Dropdown", listOf("abc" to DD.SEPARATOR.POS), "flag")
            root.add(dd)
            val element = document.getElementById("test")
            assertEquals("<div class=\"dropdown\"><button class=\"dropdown btn btn-default\" id=\"kv_dropdown_3\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"><span class=\"glyphicon glyphicon-flag\"></span> Dropdown</button><ul class=\"dropdown-menu\" aria-labelledby=\"kv_dropdown_3\"><li class=\"divider\" role=\"separator\">abc</li></ul></div>", element?.innerHTML, "Should render correct drop down")
        }
    }

    @Test
    fun render_DisabledElement() {
        run {
            val root = Root("test")
            val dd = DropDown("Dropdown", listOf("abc" to DD.DISABLED.POS), "flag")
            root.add(dd)
            val element = document.getElementById("test")
            assertEquals("<div class=\"dropdown\"><button class=\"dropdown btn btn-default\" id=\"kv_dropdown_4\" type=\"button\" data-toggle=\"dropdown\" aria-haspopup=\"true\" aria-expanded=\"false\"><span class=\"glyphicon glyphicon-flag\"></span> Dropdown</button><ul class=\"dropdown-menu\" aria-labelledby=\"kv_dropdown_4\"><li class=\"disabled\"><a href=\"#\">abc</a></li></ul></div>", element?.innerHTML, "Should render correct drop down")
        }
    }

    @Test
    fun toggle() {
        run {
            val root = Root("test")
            val dd = DropDown("Dropdown", listOf("abc" to "#!/x", "def" to "#!/y"), "flag")
            root.add(dd)
            val classes = dd.getElementJQuery()?.attr("class")
            assertTrue("Dropdown is hidden before toggle") { classes?.contains("open") == false }
            dd.toggle()
            val classes2 = dd.getElementJQuery()?.attr("class")
            assertTrue("Dropdown is visible after toggle") { classes2?.contains("open") == true }
            dd.toggle()
            val classes3 = dd.getElementJQuery()?.attr("class")
            assertTrue("Dropdown is hidden after second toggle") { classes3?.contains("open") == false }
        }
    }
}