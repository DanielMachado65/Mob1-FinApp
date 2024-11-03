package com.example.mob1_finapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class FinancialOperation(val type: String, val description: String, val value: Double) : Parcelable