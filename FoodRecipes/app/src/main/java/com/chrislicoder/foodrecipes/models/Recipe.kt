package com.chrislicoder.foodrecipes.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("title") @Expose var title: String? = null,
    @SerializedName("publisher") @Expose val publisher: String? = null,
    @SerializedName("ingredients") @Expose val ingredients: Array<String>? = null,
    @SerializedName("recipe_id") @Expose val recipe_id: String? = null,
    @SerializedName("image_url") @Expose val image_url: String? = null,
    @SerializedName("social_rank") @Expose val social_rank: Float? = null
) : Parcelable
