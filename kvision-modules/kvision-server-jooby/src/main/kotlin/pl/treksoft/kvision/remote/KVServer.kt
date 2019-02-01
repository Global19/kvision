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
package pl.treksoft.kvision.remote

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.jooby.Kooby
import org.jooby.json.Jackson
import pl.treksoft.kvision.types.KV_JSON_DATE_FORMAT
import java.text.SimpleDateFormat

/**
 * A Jooby based server.
 */
open class KVServer(init: KVServer.() -> Unit) : Kooby() {
    init {
        @Suppress("LeakingThis")
        assets("/", "index.html")
        @Suppress("LeakingThis")
        assets("/**").onMissing(0)
        val mapper = jacksonObjectMapper().apply {
            dateFormat = SimpleDateFormat(KV_JSON_DATE_FORMAT)
        }
        @Suppress("LeakingThis")
        use(Jackson(mapper))
        @Suppress("LeakingThis")
        init.invoke(this)
    }
}
