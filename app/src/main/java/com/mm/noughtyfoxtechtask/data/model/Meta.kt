package com.mm.noughtyfoxtechtask.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    val found: Int,
    val returned: Int,
    val limit: Int,
    val page: Int
)