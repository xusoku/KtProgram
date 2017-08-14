package com.davis.ktprogram.api


import com.davis.ktprogram.model.Address
import com.davis.ktprogram.model.BaseModel
import com.davis.ktprogram.model.BigPictrue
import com.davis.ktprogram.model.Cart
import com.davis.ktprogram.model.Category
import com.davis.ktprogram.model.Consume
import com.davis.ktprogram.model.Coupon
import com.davis.ktprogram.model.Extendedinfo
import com.davis.ktprogram.model.GroupBuy
import com.davis.ktprogram.model.Index
import com.davis.ktprogram.model.Order
import com.davis.ktprogram.model.OrderDetail
import com.davis.ktprogram.model.Page
import com.davis.ktprogram.model.Product
import com.davis.ktprogram.model.ProductDetail
import com.davis.ktprogram.model.Recharge
import com.davis.ktprogram.model.Region
import com.davis.ktprogram.model.Shop
import com.davis.ktprogram.model.TakeGoodsdate
import com.davis.ktprogram.model.Topic
import com.davis.ktprogram.model.UserInfo
import com.davis.ktprogram.util.DownLoadSoftUpdate

import java.util.ArrayList

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

//import retrofit.Call;
//import retrofit.http.Query;
//import retrofit.http.FormUrlEncoded;
//import retrofit.http.GET;
//import retrofit.http.POST;
//import retrofit.http.Query;

/**
 * Created by xusoku on 2016/4/5.
 */
interface ApiService {

    companion object {
        val baseurl = "http://m.shidangjia.com"
        val picurl = "http://img.kangpinhui.com/physic"
    }
    //http://www.tngou.net/tnfs/api/list?page=1&rows=10
    //        @GET("tnfs/api/list")
    //        Call<Grils> listGrils(@Query("id") int id,@Query("page") int page,@Query("rows") int rows);

    //    @FormUrlEncoded
    //    @POST("user/edit")
    //    Call<User> getUser(@Query("name") String name, @Query("password") String password);

    //1、用户登陆
    @POST("user/login.do")
    fun userLogin(
            @Query("apptype") apptype: String,
            @Query("phone") phone: String,
            @Query("password") password: String): Call<BaseModel<UserInfo>>

    //2、用户 注册
    @POST("user/register.do")
    fun userRegister(
            @Query("apptype") apptype: String,
            @Query("phone") phone: String,
            @Query("password") password: String,
            @Query("code") code: String): Call<BaseModel<*>>

    //3、一级分类
    @GET("category/level1.do")
    fun categoryLevel1(
            @Query("apptype") apptype: String): Call<BaseModel<ArrayList<Category>>>


    //4、二级分类
    @GET("category/level2.do")
    fun categoryLevel2(
            @Query("apptype") apptype: String,
            @Query("id") id: String): Call<BaseModel<ArrayList<Category>>>

    //5、商品分类列表
    @GET("product/list.do")
    fun getProductlist(
            @Query("apptype") apptype: String,
            @Query("iordertype") iordertype: String,
            @Query("rootid") rootid: String,
            @Query("classid") classid: String,
            @Query("shopid") shopid: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String
    ): Call<BaseModel<Page<ArrayList<Product>>>>
    //5.1、商品分类列表 /product/listbyids.do?ipagesize=10&ipage=1&rootid=79,80&shopid=2&iordertype=0&token=EAA8909090C6FEA76442E168CD47D7E1

    @GET("product/listbyids.do")
    fun getProductlistbyids(
            @Query("apptype") apptype: String,
            @Query("iordertype") iordertype: String,
            @Query("rootid") rootid: String,
            @Query("shopid") shopid: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String,
            @Query("token") token: String
    ): Call<BaseModel<Page<ArrayList<Product>>>>

    //6、商品搜索
    //    @FormUrlEncoded
    @POST("product/search.do")
    fun getSearchProductlist(
            @Query("apptype") apptype: String,
            @Query("iordertype") iordertype: String,
            @Query("shopid") shopid: String,
            @Query("keyword") keyword: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String
    ): Call<BaseModel<Page<ArrayList<Product>>>>

    //7、商品详情
    @GET("product/get.do")
    fun getProductDetail(
            @Query("apptype") apptype: String,
            @Query("id") id: String,
            @Query("shopid") shopid: String
    ): Call<BaseModel<ProductDetail>>

    //8.1、获取全部门店
    @GET("shop/list.do")
    fun getShoplist(
            @Query("apptype") apptype: String
    ): Call<BaseModel<ArrayList<Shop>>>

    //8.2、获取代收
    @GET("shop/list.do")
    fun getShoplist(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String
    ): Call<BaseModel<ArrayList<Shop>>>

    //8.2、根据门店ID获取门店详情
    @GET("shop/get.do")
    fun getShopDetail(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String
    ): Call<BaseModel<Shop>>

    //9	用户收货地址列表接口
    @GET("address/list.do")
    fun getAddresslist(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("token") token: String
    ): Call<BaseModel<ArrayList<Address>>>

    //9	用户地址区域接口
    @GET("address/location/list.do")
    fun getAddressRegion(
            @Query("apptype") apptype: String
    ): Call<BaseModel<ArrayList<Region>>>

    //10 用户收货地址根据ID获取接口
    @GET("address/get.do")
    fun getAddressById(
            @Query("apptype") apptype: String,
            @Query("token") token: String,
            @Query("id") id: String
    ): Call<BaseModel<Address>>

    //11.1 添加用户收货地址接口
    @GET("address/save.do")
    fun Addaddress(
            @Query("apptype") apptype: String,
            @Query("token") token: String,
            @Query("iuseraddressid") iuseraddressid: String,
            @Query("saddressname") saddressname: String,
            @Query("saddress") saddress: String,
            @Query("smobile") smobile: String,
            @Query("shopid") shopid: String,
            @Query("ilocationid") ilocationid: String,
            @Query("saddressperfix") saddressperfix: String
    ): Call<BaseModel<Address>>

    //11.2 修改用户收货地址接口
    @GET("address/update.do")
    fun updateAddress(
            @Query("apptype") apptype: String,
            @Query("token") token: String,
            @Query("iuseraddressid") iuseraddressid: String,
            @Query("saddressname") saddressname: String,
            @Query("saddress") saddress: String,
            @Query("smobile") smobile: String,
            @Query("shopid") shopid: String,
            @Query("ilocationid") ilocationid: String,
            @Query("saddressperfix") saddressperfix: String
    ): Call<BaseModel<Address>>

    //11.3 删除用户收货地址接口
    @GET("address/delete.do")
    fun deleteAddress(
            @Query("apptype") apptype: String,
            @Query("token") token: String,
            @Query("id") iuseraddressid: String
    ): Call<BaseModel<Address>>

    //12 我的订单
    @GET("order/list.do")
    fun myOrderlist(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String,
            @Query("token") token: String,
            @Query("orderstatus") orderstatus: String
    ): Call<BaseModel<Page<ArrayList<Order<ArrayList<OrderDetail>>>>>>

    //12.1 我的订单详情
    @GET("order/get.do")
    fun myOrderDetail(
            @Query("apptype") apptype: String,
            @Query("ordernum") ordernum: String,
            @Query("token") token: String
    ): Call<BaseModel<Order<ArrayList<OrderDetail>>>>

    //12.2 取消订单
    @GET("order/cancel.do")
    fun cancelOrder(
            @Query("apptype") apptype: String,
            @Query("ordernum") ordernum: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //13 用户消费信息接口
    @GET("moneyinfo/list.do")
    fun getConsumelist(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String,
            @Query("token") token: String
    ): Call<BaseModel<Page<ArrayList<Consume>>>>

    //14 意见反馈接口
    @GET("feedback/save.do")
    fun feedback(
            @Query("apptype") apptype: String,
            @Query("version") version: String,
            @Query("scontent") scontent: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //15 加入购物车
    @GET("cart/add.do")
    fun addCart(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("number") number: String,
            @Query("iproductid") iproductid: String,
            @Query("sp") sp: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //15.1 预售加入购物车
    @GET("cart/add.do")
    fun addCart(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("number") number: String,
            @Query("iproductid") iproductid: String,
            @Query("sp") sp: String,
            @Query("preproductid") preproductid: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //15.1 删除购物车
    @GET("cart/delete.do")
    fun deleteCart(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("ids") ids: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //15.2 获取购物车
    @GET("cart/list.do")
    fun getCartlist(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("ids") ids: String,
            @Query("token") token: String
    ): Call<BaseModel<ArrayList<Cart>>>

    //16 获取收货日期
    @GET("common/sendtime.do")
    fun getTakegoodtimelist(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("ids") ids: String,
            @Query("token") token: String
    ): Call<BaseModel<ArrayList<TakeGoodsdate>>>

    //17 购物验证
    @GET("order/check.do")
    fun getcheck(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("ids") ids: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //17.1 去结算、形成订单
    @GET("order/save.do")
    fun orderSave(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("ids") ids: String,
            @Query("addressid") addressid: String,
            @Query("paytype") paytype: String,
            @Query("sendtime") sendtime: String,
            @Query("sremark") sremark: String,
            @Query("couponid") couponid: String,
            @Query("password") password: String,
            @Query("orderpaytype") orderpaytype: String,
            @Query("count") count: String,
            @Query("token") token: String
    ): Call<BaseModel<Order<*>>>

    //17.2 去结算、形成订单
    @GET("order/save.do")
    fun orderSave(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("ids") ids: String,
            @Query("addressid") addressid: String,
            @Query("paytype") paytype: String,
            @Query("sendtime") sendtime: String,
            @Query("sremark") sremark: String,
            @Query("couponid") couponid: String,
            @Query("password") password: String,
            @Query("orderpaytype") orderpaytype: String,
            @Query("count") count: String,
            @Query("productName") productName: String,
            @Query("pintuanid") pintuanid: String,
            @Query("pintuanordernum") pintuanordernum: String,
            @Query("token") token: String
    ): Call<BaseModel<Order<*>>>

    //18 充值列表
    @GET("chongzhi/list.do")
    fun getRechargelist(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("pagesize") pagesize: String,
            @Query("token") token: String
    ): Call<BaseModel<Page<ArrayList<Recharge>>>>

    //18.1 添加充值订单
    @GET("chongzhi/save.do")
    fun saveRecharge(
            @Query("apptype") apptype: String,
            @Query("sinvoice") sinvoice: String,
            @Query("srechargetype") srechargetype: String,
            @Query("fmoney") fmoney: String,
            @Query("sremark") sremark: String,
            @Query("sconsignee") sconsignee: String,
            @Query("smobile") smobile: String,
            @Query("saddress") saddress: String,
            @Query("token") token: String
    ): Call<BaseModel<Recharge>>

    //18.2 获取充值订单
    @GET("chongzhi/get.do")
    fun getRecharge(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("ordernum") ordernum: String,
            @Query("token") token: String
    ): Call<BaseModel<Recharge>>

    //18.3 取消充值订单
    @GET("chongzhi/cancel.do")
    fun cancelRecharge(
            @Query("apptype") apptype: String,
            @Query("ordernum") ordernum: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //19 首页
    @GET("common/index.do")
    fun getIndex(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("token") token: String
    ): Call<BaseModel<Index>>

    //20 搜索标签
    @GET("common/keyword.do")
    fun getSearchTag(
            @Query("apptype") apptype: String
    ): Call<BaseModel<ArrayList<String>>>

    //21 手机注册验证码注册
    @GET("user/sendmsg.do")
    fun sendMsgRegister(
            @Query("apptype") apptype: String,
            @Query("phone") phone: String
    ): Call<BaseModel<*>>

    //22	提货券入库，验证提货code
    @GET("product/checkquancode.do")
    fun checkquancode(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("code") code: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //23根据提货券，获取商品数据
    @GET("product/getbyquancode.do")
    fun getProductByCode(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("code") code: String,
            @Query("token") token: String
    ): Call<BaseModel<ArrayList<Product>>>

    //24 创建提货券
    @GET("order/tihuoquan/save.do")
    fun saveProductCode(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("addressid") addressid: String,
            @Query("sendtime") sendtime: String,
            @Query("sremark") sremark: String,
            @Query("code") code: String,
            @Query("token") token: String
    ): Call<BaseModel<Order<*>>>

    //25 专题数据获取
    @GET("product/active.do")
    fun getActivelist(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("activeid") activeid: String
    ): Call<BaseModel<Topic<ArrayList<Product>>>>

    //25。1 Youlike数据获取
    @GET("user/gettop.do")
    fun getYoulikelist(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("token") activeid: String,
            @Query("ipagesize") ipagesize: String
    ): Call<BaseModel<ArrayList<Product>>>

    //25。2 团购吃货数据获取
    @GET("product/tuan.do")
    fun getTuanlist(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String
    ): Call<BaseModel<ArrayList<Product>>>

    //27 提交订单时获取用户的优惠券信息
    @GET("coupon/getbyuid.do")
    fun getCouponByUid(
            @Query("apptype") apptype: String,
            @Query("token") token: String
    ): Call<BaseModel<ArrayList<Coupon>>>

    //28 获取优惠券列表信息
    @GET("coupon/list.do")
    fun getCouponlist(
            @Query("apptype") apptype: String,
            @Query("type") type: String,
            @Query("ipage") ipage: String,
            @Query("pagesize") pagesize: String,
            @Query("token") token: String
    ): Call<BaseModel<Page<ArrayList<Coupon>>>>

    //29 获取用户扩展信息
    @GET("user/extended.do")
    fun getExtendedInfo(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("token") token: String
    ): Call<BaseModel<Extendedinfo>>

    //30 商品微信支付
    @GET("weixin/pay/product.do")
    fun getWeixinProductInfo(
            @Query("apptype") apptype: String,
            @Query("orderNum") orderid: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //31 商品充值支付
    @GET("weixin/pay/chongzhi.do")
    fun getWeixinChongzhiInfo(
            @Query("apptype") apptype: String,
            @Query("orderNum") shopid: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //31.1 余额支付
    @GET("order/changepay.do")
    fun getYueInfo(
            @Query("apptype") apptype: String,
            @Query("ordernum") ordernum: String,
            @Query("password") password: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>

    //32 微信登录
    @POST("wechat/login.do")
    fun weixinLogin(
            @Query("apptype") apptype: String,
            @Query("context") context: String
    ): Call<BaseModel<UserInfo>>

    //32 微信参数获取
    //    https://api.weixin.qq.com/sns/userinfo?access_token=V2m-4JQJZDhz9-lcI4nI_4DCU6cnGMSE0CgKd5r5uUHdK16ZLMfIbmBNf1ve_FQNAb_v5LWnZnYCynKgMMTN2INTl6ONUAXBXHdkttTe8Yk&openid=oxBZjuHK2IEpa2KwiVz2oP1EUnS8
    @GET("https://api.weixin.qq.com/sns/userinfo")
    fun weixinLoginParam(
            @Query("access_token") access_token: String,
            @Query("openid") openid: String

    ): Call<ResponseBody>
    //    |
    //    |用法
    //    |
    //    V
    //    Call<ResponseBody> calll=ApiInstant.getInstant().weixinLoginParam("","oxBZjuHK2IEpa2KwiVz2oP1EUnS8");
    //    calll.enqueue(new Callback<ResponseBody>() {
    //        @Override
    //        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
    //            StringBuilder sb = new StringBuilder();
    //            try {
    //                BufferedReader reader = new BufferedReader(
    //                        new InputStreamReader(response.body().byteStream()));
    //                String line;
    //                try {
    //                    while ((line = reader.readLine()) != null) {
    //                        sb.append(line);
    //                    }
    //                } catch (IOException e) {
    //                    e.printStackTrace();
    //                }
    //            } catch (Exception e) {
    //                e.printStackTrace();
    //            }
    //            String result = sb.toString();
    //            LogUtils.e("test", "==" + result);
    //        }
    //        @Override
    //        public void onFailure(Call<ResponseBody> call, Throwable t) {
    //            LogUtils.e("test","错误");
    //        }
    //    });

    //33test
    //    https://api.weixin.qq.com/sns/userinfo?access_token=V2m-4JQJZDhz9-lcI4nI_4DCU6cnGMSE0CgKd5r5uUHdK16ZLMfIbmBNf1ve_FQNAb_v5LWnZnYCynKgMMTN2INTl6ONUAXBXHdkttTe8Yk&openid=oxBZjuHK2IEpa2KwiVz2oP1EUnS8
    //    @GET("http://v5.pc.duomi.com/search-ajaxsearch-searchall")
    //    Call<TestModel> test(
    //            @Query("kw") String kw,
    //            @Query("pi") String pi,
    //            @Query("pz") String pz
    //
    //    );
    //34APP升级接口
    @GET("common/checkversion.do")
    fun update(
            @Query("apptype") apptype: String
    ): Call<BaseModel<DownLoadSoftUpdate.VersionInfo>>

    //35屏保接口
    @GET("common/forbigapp.do")
    fun getbigpic(
            @Query("apptype") apptype: String
    ): Call<BaseModel<ArrayList<BigPictrue>>>

    //36积分列表接口
    @GET("product/listscore.do")
    fun getScoreList(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String,
            @Query("iordertype") iordertype: String,
            @Query("token") token: String
    ): Call<BaseModel<Page<ArrayList<Product>>>>

    //37 我的积分列表接口
    @GET("userscore/list.do")
    fun getMyScoreList(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String,
            @Query("token") token: String
    ): Call<BaseModel<Page<ArrayList<Consume>>>>

    //===================
    //38 我的购物车推荐列表
    @GET("product/tuijian.do")
    fun getCartReCommetList(
            @Query("apptype") apptype: String,
            @Query("shopid") shopid: String,
            @Query("token") token: String
    ): Call<BaseModel<ArrayList<Product>>>

    //39 团购列表接口
    @GET("pintuan/list.do")
    fun getGroupBuyList(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String
    ): Call<BaseModel<Page<ArrayList<GroupBuy>>>>

    //40 我的团购列表接口
    @GET("pintuan/mytuan.do")
    fun getMyGroupBuyList(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String,
            @Query("token") token: String
    ): Call<BaseModel<Page<ArrayList<GroupBuy>>>>

    //41 团购详情接口
    @GET("pintuan/get.do")
    fun getGroupBuyDetail(
            @Query("apptype") apptype: String,
            @Query("id") id: String,
            @Query("group_id") group_id: String,
            @Query("token") token: String
    ): Call<BaseModel<GroupBuy>>

    //42 团购列表接口
    @GET("preproduct/list.do?")
    fun getPreSaleList(
            @Query("apptype") apptype: String,
            @Query("ipage") ipage: String,
            @Query("ipagesize") ipagesize: String
    ): Call<BaseModel<Page<ArrayList<GroupBuy>>>>

    //43 预售详情接口
    @GET("preproduct/get.do")
    fun getPreSaleDetail(
            @Query("apptype") apptype: String,
            @Query("id") id: String
    ): Call<BaseModel<GroupBuy>>

    //44 预售提醒接口
    @GET("warn/save.do")
    fun getPreSaleWarn(
            @Query("apptype") apptype: String,
            @Query("pid") pid: String,
            @Query("type") type: String,
            @Query("token") token: String
    ): Call<BaseModel<*>>




}

