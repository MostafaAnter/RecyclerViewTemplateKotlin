package com.recyclerviewtemplate

import java.util.*

class AbstractGooglePlay {
    var title: String? = null
    var message: String? = null
    var singleItemArrayList: ArrayList<AbstractGooglePlay>? = null

    constructor(title: String?, message: String?) {
        this.title = title
        this.message = message
    }

    constructor(
        title: String?,
        message: String?,
        singleItemModelArrayList: ArrayList<AbstractGooglePlay>?
    ) {
        this.title = title
        this.message = message
        singleItemArrayList = singleItemModelArrayList
    }

    constructor() {}
}