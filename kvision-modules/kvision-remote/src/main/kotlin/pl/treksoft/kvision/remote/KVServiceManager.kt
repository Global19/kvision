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

import kotlin.reflect.KClass

/**
 * Multiplatform service manager.
 */
actual open class KVServiceManager<T : Any> actual constructor(serviceClass: KClass<T>) {

    protected val calls: MutableMap<String, Pair<String, RpcHttpMethod>> = mutableMapOf()
    var counter: Int = 0

    /**
     * Binds a given route with a function of the receiver.
     * @param function a function of the receiver
     * @param route a route
     * @param method a HTTP method
     */
    protected actual inline fun <reified RET> bind(
        noinline function: suspend T.() -> RET,
        route: String?, method: RpcHttpMethod
    ) {
        val routeDef = route ?: "route${this::class.simpleName}${counter++}"
        calls[function.toString().replace("\\s".toRegex(), "")] = Pair("/kv/$routeDef", method)
    }

    /**
     * Binds a given route with a function of the receiver.
     * @param function a function of the receiver
     * @param route a route
     * @param method a HTTP method
     */
    protected actual inline fun <reified PAR, reified RET> bind(
        noinline function: suspend T.(PAR) -> RET,
        route: String?, method: RpcHttpMethod
    ) {
        val routeDef = route ?: "route${this::class.simpleName}${counter++}"
        calls[function.toString().replace("\\s".toRegex(), "")] = Pair("/kv/$routeDef", method)
    }

    /**
     * Binds a given route with a function of the receiver.
     * @param function a function of the receiver
     * @param route a route
     * @param method a HTTP method
     */
    protected actual inline fun <reified PAR1, reified PAR2, reified RET> bind(
        noinline function: suspend T.(PAR1, PAR2) -> RET,
        route: String?, method: RpcHttpMethod
    ) {
        val routeDef = route ?: "route${this::class.simpleName}${counter++}"
        calls[function.toString().replace("\\s".toRegex(), "")] = Pair("/kv/$routeDef", method)
    }

    /**
     * Binds a given route with a function of the receiver.
     * @param function a function of the receiver
     * @param route a route
     * @param method a HTTP method
     */
    protected actual inline fun <reified PAR1, reified PAR2, reified PAR3, reified RET> bind(
        noinline function: suspend T.(PAR1, PAR2, PAR3) -> RET,
        route: String?, method: RpcHttpMethod
    ) {
        val routeDef = route ?: "route${this::class.simpleName}${counter++}"
        calls[function.toString().replace("\\s".toRegex(), "")] = Pair("/kv/$routeDef", method)
    }

    /**
     * Binds a given route with a function of the receiver.
     * @param function a function of the receiver
     * @param route a route
     * @param method a HTTP method
     */
    protected actual inline fun <reified PAR1, reified PAR2, reified PAR3, reified PAR4, reified RET> bind(
        noinline function: suspend T.(PAR1, PAR2, PAR3, PAR4) -> RET,
        route: String?, method: RpcHttpMethod
    ) {
        val routeDef = route ?: "route${this::class.simpleName}${counter++}"
        calls[function.toString().replace("\\s".toRegex(), "")] = Pair("/kv/$routeDef", method)
    }

    /**
     * Binds a given route with a function of the receiver.
     * @param function a function of the receiver
     * @param route a route
     * @param method a HTTP method
     */
    protected actual inline fun <reified PAR1, reified PAR2, reified PAR3,
            reified PAR4, reified PAR5, reified RET> bind(
        noinline function: suspend T.(PAR1, PAR2, PAR3, PAR4, PAR5) -> RET,
        route: String?,
        method: RpcHttpMethod
    ) {
        val routeDef = route ?: "route${this::class.simpleName}${counter++}"
        calls[function.toString().replace("\\s".toRegex(), "")] = Pair("/kv/$routeDef", method)
    }

    /**
     * Binds a given function of the receiver as a select options source
     * @param function a function of the receiver
     */
    protected actual fun bind(
        function: T.(String?, String?) -> List<RemoteSelectOption>
    ) {
        val routeDef = "route${this::class.simpleName}${counter++}"
        calls[function.toString().replace("\\s".toRegex(), "")] = Pair("/kv/$routeDef", RpcHttpMethod.POST)
    }

    /**
     * Returns the map of defined paths.
     */
    fun getCalls(): Map<String, Pair<String, RpcHttpMethod>> = calls

}
