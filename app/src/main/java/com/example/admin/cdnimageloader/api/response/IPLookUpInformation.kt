package com.example.admin.cdnimageloader.api.response

data class IPLookUpInformation(
        val ip_address: String,
        val country: String,
        val country_code: String,
        val continent: String,
        val continent_code: String,
        val city: String,
        val county: String,
        val region: String,
        val region_code: String,
        val timezone: String,
        val owner: Any,
        val longitude: Double,
        val latitude: Double,
        val currency: String,
        val languages: List<String>,
        val warning: String
)