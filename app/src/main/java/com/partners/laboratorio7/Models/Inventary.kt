package com.partners.laboratorio7.Models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class Inventary(
    var rows: ArrayList<Row>,
    var name: String
)