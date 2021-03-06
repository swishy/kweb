package com.github.sanity.kweb.plugins.jqueryCore

import com.github.salomonbrys.kotson.fromJson
import com.github.sanity.kweb.dom.element.events.ONReceiver
import com.github.sanity.kweb.gson
import com.github.sanity.kweb.random
import com.github.sanity.kweb.toJson
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.full.memberProperties

/**
 * Created by ian on 2/22/17.
 */

open class JQueryOnReceiver(val parent : JQueryReceiver) {
    fun event(event: String, returnEventFields : Set<String> = Collections.emptySet(), callback: (String) -> Unit) : JQueryReceiver {
        val callbackId = Math.abs(random.nextInt())
        val eventObject = "{"+returnEventFields.map {"\"$it\" : event.$it"}.joinToString(separator = ", ")+"}"
        parent.rootReceiver.executeWithCallback("${parent.selectorExpression}.on(${event.toJson()}, function(event) {callbackWs($callbackId, $eventObject);})", callbackId) { payload ->
            callback.invoke(payload)
        }
        return parent
    }

    inline fun <reified T : Any> event(eventName : String, eventType : KClass<T>, crossinline callback : (T)-> Unit) : JQueryReceiver {
        // TODO: Should probably cache this rather than do the reflection every time
        val eventPropertyNames = T::class.memberProperties.map {it.name}.toSet()
        event(eventName, eventPropertyNames, { propertiesAsString ->
            val props : T = gson.fromJson(propertiesAsString)
            callback(props)
        })
        return parent
    }

    // From http://www.w3schools.com/jquery/jquery_ref_events.asp, incomplete
    fun blur(callback: (ONReceiver.MouseEvent) -> Unit) = event("blur", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun click(callback: (ONReceiver.MouseEvent) -> Unit) = event("click", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun dblclick(callback: (ONReceiver.MouseEvent) -> Unit) = event("dblclick", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun focus(callback: (ONReceiver.MouseEvent) -> Unit) = event("focus", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun focusin(callback: (ONReceiver.MouseEvent) -> Unit) = event("focusin", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun focusout(callback: (ONReceiver.MouseEvent) -> Unit) = event("focusout", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun hover(callback: (ONReceiver.MouseEvent) -> Unit) = event("hover", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun mouseup(callback: (ONReceiver.MouseEvent) -> Unit) = event("mouseup", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun mousedown(callback: (ONReceiver.MouseEvent) -> Unit) = event("mousedown", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun mouseenter(callback: (ONReceiver.MouseEvent) -> Unit) = event("mouseenter", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun mouseleave(callback: (ONReceiver.MouseEvent) -> Unit) = event("mouseleave", eventType = ONReceiver.MouseEvent::class, callback = callback)
    fun mousemove(callback: (ONReceiver.MouseEvent) -> Unit) = event("mousemove", eventType = ONReceiver.MouseEvent::class, callback = callback)
}