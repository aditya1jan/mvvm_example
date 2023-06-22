package com.nexxo.loginexample.login.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "login")
data class LoginModel(
    @PrimaryKey(autoGenerate = false) val id: Int? = null,
    val accessToken: String,
    val businessInfo: BusinessInfo,
    val defaultProductId: Int,
    val defaultProductName: String,
    val message: String,
    val passwordReset: Any,
    val refreshToken: String,
    val roleTypeId: Int,
    val roleTypeName: String,
    val twoFactorRegistered: Boolean,
    val twoFactorRequired: Boolean,
    val twoFactorUid: Any
) {
    data class BusinessInfo(
        val entityTypeId: Int,
        val redirectCode: String
    )
}