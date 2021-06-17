package com.example.tddinandroid.cd.domain

import java.io.Serializable

data class Cd(val id: Int = 0, val albumName: String, val artistName: String) : Serializable
