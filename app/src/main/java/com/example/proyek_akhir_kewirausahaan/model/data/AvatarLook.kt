package com.example.proyek_akhir_kewirausahaan.model.data

import org.json.JSONArray
import org.json.JSONObject

data class SavedLayer(
    val id: String,
    val offsetX: Float,
    val offsetY: Float,
    val scale: Float,
    val visible: Boolean
)

data class AvatarLook(
    val gender: String,
    val layers: List<SavedLayer>
) {
    fun toJson(): String {
        val arr = JSONArray()
        layers.forEach { l ->
            val o = JSONObject()
            o.put("id", l.id)
            o.put("x", l.offsetX.toDouble())
            o.put("y", l.offsetY.toDouble())
            o.put("scale", l.scale.toDouble())
            o.put("visible", l.visible)
            arr.put(o)
        }
        val root = JSONObject()
        root.put("gender", gender)
        root.put("layers", arr)
        return root.toString()
    }

    companion object {
        fun fromJson(json: String?): AvatarLook? {
            if (json.isNullOrBlank()) return null
            return try {
                val root = JSONObject(json)
                val gender = root.optString("gender", "MALE")
                val arr = root.optJSONArray("layers") ?: JSONArray()
                val list = mutableListOf<SavedLayer>()
                for (i in 0 until arr.length()) {
                    val o = arr.getJSONObject(i)
                    list.add(
                        SavedLayer(
                            id = o.getString("id"),
                            offsetX = o.optDouble("x", 0.0).toFloat(),
                            offsetY = o.optDouble("y", 0.0).toFloat(),
                            scale = o.optDouble("scale", 1.0).toFloat(),
                            visible = o.optBoolean("visible", false)
                        )
                    )
                }
                AvatarLook(gender, list)
            } catch (e: Exception) {
                null
            }
        }
    }
}