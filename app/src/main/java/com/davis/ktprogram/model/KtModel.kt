package com.davis.ktprogram.model

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable
import java.util.ArrayList

/**
 * Created by xushengfu on 2017/8/11.
 */

data class BaseModel<T>(var breturn: Boolean,
                        var errorinfo: String?,
                        var ireturn: String?,
                        var `object`: T?) : Serializable {}


data class Page<T>(var iTotalRecords: Int,
                   var iPageSize: Int,
                   var iCurrentPage: Int,
                   var iTotalPage: Int,
                   var className: String?,
                   var list: T?) : Serializable {}


data class Extendedinfo(var couponcount: String = "0",
                        var orderunpaid: String = "0",
                        var ordersending: String = "0",
                        var orderwaitsend: String = "0",
                        var orderall: String = "0",
                        var fcurrmoney: String = "0",
                        var cartcount: String = "0.0") : Serializable {}

data class Address(var addtime: String,
                   var edittime: String,
                   var slock: String,
                   var iuseraddressid: String,
                   var iuserid: String,
                   var susername: String,
                   var saddressname: String,
                   var saddress: String,
                   var smobile: String,
                   var slastuserd: String,
                   var daddtime: String,
                   var shopid: String,
                   var saddressperfix: String,
                   var useraddress: Address?) : Serializable {}

data class Banner(var title: String?) : Serializable {

    constructor(title: String?,
                picurl: String?,
                link: String?,
                tempvalue: String?,
                gotype: String?) : this("")
}

data class BigPictrue(var id: String?,
                      var name: String?) : Serializable {}

data class Cart(var addtime: String?,
                var edittime: String?,
                var slock: String?,
                var id: String?,
                var productName: String?,
                var iproductid: String?,
                var picurl: String?,
                var iprice: String?,
                var fmarketprice: String?,
                var fvipprice: String?,
                var inumber: Float = 0.0F,
                var iuserid: String?,
                var shopid: String?,
                var itgclassid: String?,
                var sproductcode: String?,
                var sstandard: String?,
                var vippaytype: String?,
                var mincount: Float = 0.toFloat(),
                var selltype: Int = 0,
                var flag: Boolean = true) : Serializable {}

data class Category(var id: String?,
                    var name: String?,
                    var picurl: String?,
                    var isOnclick: Boolean,
                    var clist: ArrayList<Category> = ArrayList<Category>()) : Serializable {}

data class Consume(var addtime: String?,
                   var edittime: String?,
                   var slock: String?,
                   var iusermoneyinfoid: String?,
                   var iuserid: String?,
                   var fmoney: String?,
                   var fbalance: String?,
                   var scontent: String?,
                   var stype: String?,
                   var daddtime: String?,
                   var flow_id: String?,
                   var sordernum: String?,
                   var iscoreid: String?,
                   var iscore: String?,
                   var scpos_handled: String?) : Serializable {}

data class Coupon(var addtime: String?,
                  var edittime: String,
                  var slock: String?,
                  var id: String?,
                  var title: String?,
                  var context: String?,
                  var starttime: String?,
                  var endtime: String?,
                  var ctype: String?,
                  var fmoney: String?,
                  var endtime2: String?,
                  var daystr: String?,
                  var ftotal: String?,
                  var usercouponid: String?) : Serializable {}

data class Ptlist(var sOrderNumber: String?,
                  var sUserName: String?,
                  var pintuanstate: Int = 0,
                  var sConsignee: String?,
                  var daddtime: String?,
                  var iuserid: Int = 0,
                  var pintuantype: Int = 0) {}

data class GroupBuy(
        var id: Int = 0,
        var pttitle: String?,
        var title: String?,
        var mincount: Int = 0,
        var endtime: Long = 0,
        var ptprice: String?,
        var price: String?,
        var coupontuanz: Int = 0,
        var couponnormal: Int = 0,
        var productid: Int = 0,
        var pids: Any?,
        var picurl: String?,
        var salecount: Int = 0,
        var sellcount: Int = 0,
        var buycount: Int = 0,
        var istuan: Int = 0,
        var ispay: Int = 0,
        var shijiansy: String?,
        var addtime: String?,
        var edittime: String?,
        var slock: String?,
        var marketprice: Double = 0.toDouble(),
        var desccontext: String?,
        var picurl2: String?,
        var ptstatus: String?,
        var sordernumber: String?,
        var pintuanodernum: String?,
        var tieshi: String?,
        var countdowntime: Long = 0,
        var qiangStr: String?,
        var product: ProductDetail?,
        var unptlist: ArrayList<Ptlist> = ArrayList<Ptlist>(),
        var ptlist: ArrayList<Ptlist> = ArrayList<Ptlist>(),
        var ptorder: Ptlist?,
        var piclist: ArrayList<String> = ArrayList<String>()) : Serializable {}


data class Productlist(
        var id: String?,
        var title: String?,
        var picurl: String?,
        var link: String?,
        var tempvalue: String?,
        var gotype: String?,
        var list: ArrayList<Product> = ArrayList<Product>()) : Serializable {}

data class Index(
        var bannerList: ArrayList<Banner> = ArrayList<Banner>(),
        var recommandList: ArrayList<Productlist> = ArrayList<Productlist>(),
        var productList: ArrayList<Product> = ArrayList<Product>(),
        var bannerListAd: ArrayList<Banner> = ArrayList<Banner>(),
        var iconBannerList: ArrayList<Banner> = ArrayList<Banner>()) : Serializable {}

data class Order<T>(var addtime: String?,
                    var edittime: String?,
                    var slock: String?,
                    var itgorderid: String?,
                    var iuserid: String?,
                    var suserName: String?,
                    var snickName: String?,
                    var sordernumber: String?,
                    var daddtime: String?,
                    var saddress: String?,
                    var spostcode: String?,
                    var sconsignee: String?,
                    var stelephone: String?,
                    var smobile: String?,
                    var semail: String?,
                    var stype: String?,
                    var dedittime: String?,
                    var dsendproduct: String?,
                    var icount: String?,
                    var fprice: String?,
                    var fpostprice: String?,
                    var fmoney: String?,
                    var ssendtime: String?,
                    var sinvoice: String?,
                    var sremark: String?,
                    var spaytype: String?,
                    var icoinnum: String?,
                    var stradeno: String?,
                    var ssendname: String?,
                    var fvipmoney: String?,
                    var iordertype: String?,
                    var ssendtype: String?,
                    var freturnmoney: String?,
                    var spayname: String?,
                    var mendian: String?,
                    var couponid: String?,
                    var coupontotal: String?,
                    var activeid: String?,
                    var quancode: String?,
                    var refundno: String?,
                    var fvipbalance: String?,
                    var totalscore: String?,
                    var list: T?) : Serializable {}

data class OrderDetail(var addtime: String?,
                       var edittime: String?,
                       var slock: String?,
                       var itgproductid: String?,
                       var iuserid: String?,
                       var sordernumber: String?,
                       var iproductid: String?,
                       var sproductname: String?,
                       var fprostand: String?,
                       var sstandard: String?,
                       var daddtime: String?,
                       var icount: Float = 0.toFloat(),
                       var fprice: String?,
                       var fmoney: String?,
                       var sproductcode: String?,
                       var picurl: String?,
                       var fotherprice: String?,
                       var vippaytype: String?) : Serializable {}

data class Product(var iproductid: String?,
                   var productname: String?,
                   var fmarkerprice: String?,
                   var fvipprice: String?,
                   var fprice: String?,
                   var sstandard: String?,
                   var picurl: String?,
                   var orderby: String?,
                   var count: String?,
                   var productcode: String?,
                   var srequire: String?,
                   var mincount: Float = 0.toFloat(),
                   var prostate: String?,
                   var score: Int = 0) : Serializable {}

data class Recharge(var itgrechargeid: String?,
                    var iuserid: String?,
                    var saccount: String?,
                    var fmoney: String?,
                    var schargenumber: String?,
                    var saddress: String?,
                    var sconsignee: String?,
                    var smobile: String?,
                    var stype: String?, //0：未付款  3：已付款成功  6：已取消
                    var srechargetype: String?,
                    var daddtime: String?,
                    var dedittime: String?,
                    var sinvoice: String?,
                    var sremark: String?,
                    var spaytype: String?,
                    var slock: String?,
                    var stradeno: String?) : Serializable {}

data class Region(var ilocationid: String?,
                  var slocationname: String?,
                  var slock: String?) : Serializable {}

data class TakeGoodsdate(
        var date: String?,
        var time: ArrayList<String> = ArrayList<String>()) : Serializable {}

data class Topic<T>(var id: String?,
                    var title: String?,
                    var picurl: String?,
                    var list: T?) : Serializable {}

data class UserInfo(var addtime: String?,
                    var edittime: String?,
                    var slock: String?,
                    var iuserid: String?,
                    var susername: String?,
                    var spassword: String?,
                    var daddtime: String?,
                    var snickname: String?,
                    var dlastdatetime: String?,
                    var dedtime: String?,
                    var slastloginip: String?,
                    var isex: String?,
                    var saddress: String?,
                    var ilogintimes: String?,
                    var iage: String?,
                    var imessagenum: String?,
                    var smobile: String?,
                    var sregisttype: String?,
                    var sviptype: String?,
                    var fcurramount: String?,
                    var svipnumber: String?,
                    var utype: String?,
                    var scoretotal: String?,
                    var token: String?,
                    var useraddress: Address?) : Serializable {}
































