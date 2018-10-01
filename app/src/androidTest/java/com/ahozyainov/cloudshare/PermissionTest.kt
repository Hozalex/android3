package com.ahozyainov.cloudshare

import android.Manifest
import android.content.pm.PackageManager
import android.support.test.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test

class PermissionTest {
    @Test
    fun checkInternetPermission() {
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals(appContext.checkSelfPermission(Manifest.permission.INTERNET), PackageManager.PERMISSION_GRANTED)
        assertEquals(appContext.checkSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE), PackageManager.PERMISSION_GRANTED)
    }
}