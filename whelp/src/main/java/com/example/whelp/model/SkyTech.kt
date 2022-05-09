package com.example.whelp.model

import android.content.Context
import android.content.Intent
import com.example.whelp.SkyTechActivity
import com.example.whelp.util.CredentialHelper

data class SkyTech private constructor(
//    val context: Context,
    val api_key: String?,
    val app_id: String?,
    val userCredentials: UserCredentials?
) {

    data class Builder(
//        var context: Context,
        var api_key: String? = null,
        var app_id: String? = null,
        var userCredentials: UserCredentials? = null
    ) {

        //        fun context(context: Context) = apply { this.context = context }
        fun key(api_key: String) = apply { this.api_key = api_key }
        fun appID(app_id: String) = apply { this.app_id = app_id }
        fun userCredentials(userCredentials: UserCredentials) =
            apply { this.userCredentials = userCredentials }

        fun open(context: Context) {
            val intent = Intent(context.applicationContext, SkyTechActivity::class.java)
            CredentialHelper.credential.value = Builder(api_key, app_id, userCredentials)

            context.startActivity(intent)
        }

    }
}