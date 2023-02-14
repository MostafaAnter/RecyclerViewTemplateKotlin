package com.recyclerviewtemplate

class AbstractModel {
    var title: String? = null
    var message: String? = null

    constructor(title: String?, message: String?) {
        this.title = title
        this.message = message
    }

    constructor() {}
}