package com.ippon.rickandmorty.extensions

import androidx.compose.ui.graphics.Color
import com.ippon.rickandmorty.extension.black
import com.ippon.rickandmorty.extension.darkGray
import com.ippon.rickandmorty.extension.lightGray
import com.ippon.rickandmorty.extension.white
import org.junit.Test
import kotlin.test.assertEquals

class ColorExtensionsTest {
    @Test
    fun testAppThemeColors() {
        assertEquals(Color.darkGray, Color(0xFF222222))
        assertEquals(Color.lightGray, Color(0xFFAAAAAA))
        assertEquals(Color.white, Color(0xFFFFFFFF))
        assertEquals(Color.black, Color(0xFF000000))
    }
}