package com.github.sanity.kweb.plugins.jqueryCore

import com.github.sanity.kweb.plugins.KWebPlugin

/**
 * Created by ian on 1/9/17.
 */
class JQueryCorePlugin : KWebPlugin() {
    override fun decorate(startHead: StringBuilder, endHead: StringBuilder) {
        // Include the plugin, this is straight from the JQuery documentation
        startHead.appendln("""
        <script
                src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>""".trimIndent()
        )
    }
}

// A convenience value
val jqueryCore = JQueryCorePlugin()
