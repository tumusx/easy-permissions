package com.github.tumusx.easy_permissions.permissions

interface PermissionsListener  {
    fun onSuccessPermission()
    fun onErrorPermission()
    fun onRejectPermission()

    companion object {
        lateinit var initPermissionsListener: PermissionsListener
        fun permissionListenerReceive(listenerPermission: PermissionsListener) {
            initPermissionsListener = listenerPermission
        }
    }
}