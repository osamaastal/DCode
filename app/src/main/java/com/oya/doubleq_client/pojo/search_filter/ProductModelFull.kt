package com.oya.doubleq_client.pojo.search_filter

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.category.CategoryModel
import com.oya.doubleq_client.pojo.home.UnitModel
import com.oya.doubleq_client.pojo.sub_category.ColorsModel
import com.oya.doubleq_client.pojo.sub_category.SubCategoryModel
import com.oya.doubleq_client.pojo.types.TypeModel
//additional nodes: tags, colors
data class ProductModelFull(@SerializedName("image")
                     val image: String = "",
                            @SerializedName("images")
                     val images: List<String>?,
                            @SerializedName("type_id")
                     val typeId: TypeModel,
                            @SerializedName("costPrice")
                     val costPrice: Double = 0.0,
                            @SerializedName("discountPrice")
                     val discountPrice: Double = 0.0,
                            @SerializedName("guarantee")
                     val guarantee: String = "",
                            @SerializedName("colors")
                     val colors: List<ColorsModel>?,
                            @SerializedName("tags")
                     val tags: List<String>?,
                            @SerializedName("category_id")
                     val categoryId: CategoryModel,
                            @SerializedName("rate")
                     val rate: Double = 0.0,
                            @SerializedName("price")
                     val price: Double = 0.0,
                            @SerializedName("sub_category_id")
                     val subCategoryId: SubCategoryModel,
                            @SerializedName("name")
                     val name: String = "",
                            @SerializedName("details")
                     val details: String = "",
                            @SerializedName("_id")
                     val Id: String = "",
                            @SerializedName("favorite_id")
                     val favoriteId: String = "",
                            @SerializedName("SKU")
                     val sku: String = "",
                            @SerializedName("barcode")
                     val barcode: String = "",
                            @SerializedName("unit_id")
                     val unitId: UnitModel)