package com.partners.laboratorio7

import android.app.Application
import com.partners.laboratorio7.Models.Inventary

class App: Application() {
    companion object {
        var selectedInventary: Inventary?=null
        var indexInventary: Int = 0
//        var indexInventary: Int = 0
    }
}