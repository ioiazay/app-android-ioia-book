package com.ioia.book.network.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FCMResponse(
    @SerializedName("success") @Expose val success: Int? = 0,
    @SerializedName("failure") @Expose val failure: Int? = 0,
    @SerializedName("canonical_ids") @Expose val canonical_ids: Int? = 0
)