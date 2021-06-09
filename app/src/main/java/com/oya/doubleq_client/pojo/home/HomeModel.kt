package com.oya.doubleq_client.pojo.home

import com.google.gson.annotations.SerializedName
import com.oya.doubleq_client.pojo.category.CategoryModel
import com.oya.doubleq_client.pojo.product.ProductModel
import com.oya.doubleq_client.pojo.sub_category.SubCategoryModel
import com.oya.doubleq_client.pojo.types.TypeModel

data class HomeModel(@SerializedName("types")
                 val types: List<TypeModel>?,
                     @SerializedName("recomended")
                 val recomended: List<ProductModel>?,
                     @SerializedName("categories")
                 val categories: List<CategoryModel>?,
                     @SerializedName("advs")
                 val advs: List<AdvsModel>?,
                     @SerializedName("subCategories")
                 val subCategories: List<SubCategoryModel>?)