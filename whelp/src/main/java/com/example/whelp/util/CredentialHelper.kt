package com.example.whelp.util

import com.example.whelp.model.SkyTech

object CredentialHelper {
    val credential = SingleLiveEvent<SkyTech.Builder>()
}