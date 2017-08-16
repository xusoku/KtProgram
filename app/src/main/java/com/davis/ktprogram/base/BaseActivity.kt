package com.davis.ktprogram.base

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewStub
import android.view.WindowManager
import android.widget.*
import com.davis.ktprogram.R
import com.davis.ktprogram.util.AppManager
import com.davis.ktprogram.util.LogUtils
import com.davis.ktprogram.views.CustomTypefaceTextView
import com.davis.ktprogram.views.ProgressWheel

abstract class BaseActivity : PermissionActivity() {

    var mContext: Context? = null
    var mActivity: Activity?  = null
    var mInflater: LayoutInflater?  = null

    private var layTopBar: RelativeLayout? = null
    private var tvTitle: TextView? = null
    private var btnLeft: ImageView? = null
    private var btnRight: ImageButton? = null
    private var btnRight1: ImageButton? = null
    private var btnRightTitle: TextView? = null
    private var layBody: FrameLayout? = null
    private var stubLoadingFailed: ViewStub? = null
    private var layLoadingFailed: FrameLayout? = null
    private var layClickReload: LinearLayout? = null
    private var loadingProgress: ProgressWheel? = null
    private var contentViewBase: View? = null
    private var isFirstLoading = false
    private var toast: Toast? = null

    var TAG = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        LogUtils.e("getSimpleName", this.javaClass.simpleName)
        setTranslucentStatusBar(R.color.colormain)
        AppManager.getAppManager().addActivity(this)
        initBase()
        addContentView()
        initVariable()
        findViews()
        initData()
        setListener()
    }

    private fun initBase() {
        mContext = applicationContext
        mActivity = this
        mInflater = layoutInflater
        // topbar相关
        layTopBar = findViewById(R.id.layTopBar) as RelativeLayout
        tvTitle = findViewById(R.id.tvTopBarTitle) as TextView
        btnLeft = findViewById(R.id.btnLeft) as ImageView
        btnRight = findViewById(R.id.btnRight) as ImageButton
        btnRight1 = findViewById(R.id.btnRight1) as ImageButton
        btnRightTitle = findViewById(R.id.btnRightTitle) as TextView

        // 内容区
        layBody = findViewById(R.id.layBody) as FrameLayout
        stubLoadingFailed = findViewById(R.id.stubLoadingFailed) as ViewStub

        hideTopBar()

        btnLeft?.setOnClickListener(View.OnClickListener { finish() })
    }

    fun hideTopBar() {
        layTopBar?.visibility=(View.GONE)
    }

    fun getTopBar(): RelativeLayout? {
        return layTopBar
    }

    fun showTopBar() {
        layTopBar?.visibility=(View.VISIBLE)
    }


    /**
     * 得到左边的按钮
     */
    fun getLeftButton(): ImageView? {
        btnLeft?.setVisibility(View.VISIBLE)
        return btnLeft
    }

    /**
     * 得到右边的按钮
     */
    fun getRightButton(): ImageButton? {
        btnRight?.setVisibility(View.VISIBLE)
        return btnRight
    }

    /**
     * 得到右边的按钮
     */
    fun getRightButton1(): ImageButton ?{
        btnRight1?.setVisibility(View.VISIBLE)
        return btnRight1
    }

    /**
     * 得到右边的文字按钮
     */
    fun getRightTextButton(): TextView? {
        btnRightTitle?.setVisibility(View.VISIBLE)
        return btnRightTitle
    }

    /**
     * 设置标题
     */
    fun setTitle(title: String) {
        tvTitle?.setText(title)
    }

    /**
     * 获取标题
     */
    fun getTitleView(): TextView? {
        return tvTitle
    }

    /**
     * 设置自定义view
     */
    fun setCustomTopBar(resId: Int) {
        val view = mInflater?.inflate(resId, null)
        layTopBar?.removeAllViews()
        layTopBar?.addView(view)
    }

    fun isTranslucentStatusBar(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            return true
        }
        return false
    }

    fun setTranslucentStatusBar(ResId: Int) {
        val customStatusBarView = `$`<View>(R.id.customStatusBarView)
        if (customStatusBarView != null) {
            if (isTranslucentStatusBar()) {
                customStatusBarView!!.setVisibility(View.VISIBLE)
                customStatusBarView!!.setBackgroundResource(ResId)
            }
        }
    }

    fun setTranslucentStatusBarGone() {
        val customStatusBarView = `$`<View>(R.id.customStatusBarView)
        if (customStatusBarView != null) {
            if (isTranslucentStatusBar()) {
                customStatusBarView!!.setVisibility(View.GONE)
            }
        }
    }


    protected fun <T : View> `$`(@IdRes id: Int): T {
        return findViewById(id) as T
    }

    protected fun <T : View> `$`(view: View, @IdRes id: Int): T {
        return view.findViewById(id) as T
    }








}

