package com.davis.ktprogram.base

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity

import com.davis.ktprogram.util.PermissionPool


/**
 * Created by xushengfu on 2017/8/6.
 */

open class PermissionActivity : AppCompatActivity() {

    /**
     * android6.0权限处理
     * @param code             权限标记Code
     * *
     * @param permissionName    权限名称
     */
    fun permissionDispose(@PermissionPool.PermissionCode code: Int, @PermissionPool.PermissionName permissionName: String) {

        if (ContextCompat.checkSelfPermission(this, permissionName) != PackageManager.PERMISSION_GRANTED) {
            //没有权限,开始申请
            ActivityCompat.requestPermissions(this, arrayOf(permissionName), code)
        } else {
            //有权限
            onAccreditSucceed(code)
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //授权成功
            onAccreditSucceed(requestCode)
        } else if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
            //授权失败
            onAccreditFailure(requestCode)
        }
    }

    /**
     * 有授权执行的方法(子类重写)
     */
    fun onAccreditSucceed(requestCode: Int) {}

    /**
     * 没有授权执行的方法(子类重写)
     */
    fun onAccreditFailure(requestCode: Int) {}
}
