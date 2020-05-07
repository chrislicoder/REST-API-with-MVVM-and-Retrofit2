package com.chrislicoder.foorecipes.models

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    @SerializedName("title") @Expose val title: String,
    @SerializedName("publisher") @Expose val publisher: String,
    @SerializedName("ingredients") @Expose val ingredients: Array<String>,
    @SerializedName("recipe_id") @Expose val recipe_id: String,
    @SerializedName("image_url") @Expose val image_url: String,
    @SerializedName("social_rank") @Expose val social_rank: Float
) : Parcelable