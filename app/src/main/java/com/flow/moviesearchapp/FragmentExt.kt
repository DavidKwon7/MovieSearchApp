package com.flow.moviesearchapp

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateWithArgs(navDirections: NavDirections) {
    this.findNavController().navigate(navDirections)
}