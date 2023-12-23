package com.yj.countries_yan.models

import java.util.UUID

data class Country(
    var id: String = "",
    var name: Name = Name(common = "")
)