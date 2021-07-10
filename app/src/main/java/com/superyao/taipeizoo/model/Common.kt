package com.superyao.taipeizoo.model

import com.google.gson.annotations.SerializedName

data class Headers(
    val accept: String? = null,
    val date: String? = null,

    @SerializedName("content-length")
    val contentLength: String? = null,

    val server: String? = null,
    val expires: String? = null,

    @SerializedName("access-control-allow-headers")
    val accessControlAllowHeaders: String? = null,

    @SerializedName("x-frame-options")
    val xFrameOptions: String? = null,

    @SerializedName("access-control-allow-methods")
    val accessControlAllowMethods: String? = null,

    @SerializedName("pragma")
    val pragma: String? = null,

    @SerializedName("access-control-allow-origin")
    val accessControlAllowOrigin: String? = null,

    @SerializedName("content-security-policy")
    val contentSecurityPolicy: String? = null,

    @SerializedName("x-content-type-options")
    val xContentTypeOptions: String? = null,

    @SerializedName("x-xss-protection")
    val xXssProtection: String? = null,

    @SerializedName("connection")
    val connection: String? = null,

    @SerializedName("content-type")
    val contentType: String? = null,

    @SerializedName("cache-control")
    val cacheControl: String? = null
)

data class Config(
    val headers: Headers? = null,
    val maxContentLength: Int? = null,
    val method: String? = null,
    val transformRequest: Any? = null,
    val params: Params? = null,
    val xsrfHeaderName: String? = null,
    val transformResponse: Any? = null,
    val timeout: Int? = null,
    val xsrfCookieName: String? = null,
    val url: String? = null
)

data class Params(
    val q: String? = null,
    val offset: String? = null,
    val limit: String? = null
)