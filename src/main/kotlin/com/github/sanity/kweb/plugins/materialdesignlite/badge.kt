package com.github.sanity.kweb.plugins.materialdesignlite

import com.github.sanity.kweb.dom.element.Element
import com.github.sanity.kweb.dom.element.modification.addClasses
import com.github.sanity.kweb.dom.element.modification.setAttribute

/**
 * Created by ian on 1/21/17.
 */

fun MDLReceiver.badge(value: String? = null, overlap: Boolean = false, noBackground : Boolean = false): Element {
    if (parent.tag != null && parent.tag.toLowerCase() in setOf("span", "link") && value == null) {
        throw IllegalArgumentException("value parameter must be present for badge on a <span> or <link> element")
    }
    return parent.addClasses("mdl-badge")
            .addClasses("mdl-badge--overlap", onlyIf = overlap)
            .addClasses("mdl-badge--no-background", onlyIf = noBackground)
            .setAttribute("data-badge", value)
}