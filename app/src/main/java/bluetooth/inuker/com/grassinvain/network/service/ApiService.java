package bluetooth.inuker.com.grassinvain.network.service;


import java.util.List;

import bluetooth.inuker.com.grassinvain.common.model.OssAuth;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.body.request.AddAdressBody;
import bluetooth.inuker.com.grassinvain.network.body.request.AddCardentry;
import bluetooth.inuker.com.grassinvain.network.body.request.AddbankCardBody;
import bluetooth.inuker.com.grassinvain.network.body.request.JoinShoppingCarBody;
import bluetooth.inuker.com.grassinvain.network.body.request.SubSpeakBody;
import bluetooth.inuker.com.grassinvain.network.body.request.SubmitOrderBody;
import bluetooth.inuker.com.grassinvain.network.body.request.SysmessageBody;
import bluetooth.inuker.com.grassinvain.network.body.request.TixianBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AddressDetailBody;
import bluetooth.inuker.com.grassinvain.network.body.response.AllOrderBody;
import bluetooth.inuker.com.grassinvain.network.body.response.BankCardBody;
import bluetooth.inuker.com.grassinvain.network.body.response.BannerBody;
import bluetooth.inuker.com.grassinvain.network.body.response.MorenAddressBody;
import bluetooth.inuker.com.grassinvain.network.body.response.NewMessageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PageBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonShouyiZhangdanBody;
import bluetooth.inuker.com.grassinvain.network.body.response.PersonTeamShouyi;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSDeatilBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSpeakList;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSpreakBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ShopCarListBody;
import bluetooth.inuker.com.grassinvain.network.body.response.UserAddressListBody;
import bluetooth.inuker.com.grassinvain.network.http.Response;
import bluetooth.inuker.com.grassinvain.network.model.RequestModel.UserBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;


/**
 * Created by Sunflower on 2015/11/4.
 */
public interface ApiService {

    /**
     * 登陆
     *
     * @param userBody
     * @return
     */
    @POST("service/login/0")
    Observable<Response<UserInfo>> login(@Body UserBody userBody);

    /**
     * 注册
     *
     * @param userBody
     * @return
     */
    @POST("service/register/0")
    Observable<Response<UserInfo>> register(@Body UserBody userBody);



    /**
     * 发送验证码
     *
     * @param userBody
     * @return
     */
    @POST("service/smscaptcha/0")
    Observable<Response<String>> smsCaptcha(@Body UserBody userBody);


    /**
     * 重置密码
     *
     * @return
     */
    @POST("service/restPwd/0")
    Observable<Response<String>> restPwd(@Body UserBody userBody);
    /**
     * 文件上传-认证
     *
     * @return
     */
    @GET("oss/auth/{mediaType}/0")
    Observable<Response<OssAuth>> ossAuth(@Path("mediaType") String mediaType);

    /**
     * 编辑/更新 用户信息
     *
     * @param userInfo
     * @return
     */
    @POST("user/updateUserInfo/0")
    Observable<Response<UserInfo>> updateUserInfo(@Body UserInfo userInfo);


    /**
     * 添加油卡/银行卡
     *
     * @return
     */

    @POST("banks/addBanks/0")
    Observable<Response<String>> getAddCard(@Body AddCardentry addCardentry);

    /**
     * 头部广告栏
     */

    @POST("service/getAdsInfo/0")
    Observable<Response<List<BannerBody>>> getBanner();

    /**
     * 首页商品列表
     */

    @POST("/products/getProductPage/0")
    Observable<Response<ProductBody>> getProductList(@Body PageBody pageBody);

    /**
     * 商品详情
     */
    @POST("/products/getProductInfo/{productsId}/0")
    Observable<Response<List<ProductSDeatilBody>>> getProductDetail(@Path("productsId") long Id);

    /**
     * 商品评论列表
     */
    @POST("/products/getProductEvaluate/{productsId}/0")
    Observable<Response<ProductSpreakBody>> getPSpeakList(@Path("productsId") long Id, @Body PageBody pageBody);

    /**
     * 加入购物车
     */

    @POST("/shopCar/saveUserShopCar/0")
    Observable<Response<String>> getJoinShoppingCar(@Body JoinShoppingCarBody joinShoppingCarBody);

    /**
     * 个人中心数据
     */
    @POST("/user/getUserInfo/0")
    Observable<Response<UserInfo>> getPersonCentern();

    /**
     * 获取地址列表
     */
    @POST("/user/addressList/0")
    Observable<Response<UserAddressListBody>> getAddressList(@Body PageBody pageBody);

    /**
     * 删除地址
     */
    @POST("/user/addressDelete/{userAddressId}/0")
    Observable<Response<Object>> getDeleteAddress(@Path("userAddressId") String Id);

    /**
     * 增加地址
     */
    @POST("/user/addressAdd/0")
    Observable<Response<Object>> getAddAdress(@Body AddAdressBody addAdressBody);

    /**
     * 编辑地址
     */

    @POST("/user/addressUpdate/0")
    Observable<Response<Object>> getChangeAdress(@Body AddAdressBody addAdressBody);

    /**
     * 设置默认地址
     */

    @POST("/user/addressUpdate/0")
    Observable<Response<Object>> getSetMorenAdress(@Body AddressDetailBody addAdressBody);

    /**
     * 获取购物车列表
     */
    @POST("/shopCar/getUserShopCarPage/0")
    Observable<Response<ShopCarListBody>> getShopCarList(@Body PageBody pageBody);

    /**
     * 删除购物车商品
     */
    @POST("/shopCar/delUserShopCar/0")
    Observable<Response<ShopCarListBody>> getDeleteShopCarList(@Body PageBody pageBody);

    /**
     * 获取新消息列表
     */

    @POST("/user/getUserNews/0")
    Observable<Response<NewMessageBody>> getNewMessageList(@Body PageBody pageBody);

    /**
     * 个人中心之账单详情
     */

    @POST("/accounts/getAccountDetailPage/0")
    Observable<Response<PersonShouyiZhangdanBody>> getPerZhangDList(@Body PageBody pageBody);

    /**
     * 更新用户信息
     */
    @POST("/user/addUserApply/0")
    Observable<Response<Object>> getUpdateUser(@Body UserInfo userInfo);

    /**
     * 申请离职
     */
    @POST("/user/addUserApply/0")
    Observable<Response<Object>> getThrowWork(@Body UserInfo userInfo);

    /**
     * 银行卡列表
     */
    @POST("/banks/getBanksPage/0")
    Observable<Response<BankCardBody>> getBankCardList(@Body PageBody pageBody);

    /**
     * 获取默认地址
     */
    @POST("/user/getAddressDefault/0")
    Observable<Response<MorenAddressBody>> getMorenAddress();

    /**
     * 提交订单列表
     */


    @POST("/orders/generateOrders/0")
    Observable<Response<SubmitOrderBody>> getSubmitOrder(@Body SubmitOrderBody submitOrderBody);

    /**
     * 提交订单号
     */
    @POST("/orders/cancelOrder/{orderNo}/0")
    Observable<Response<Object>> getSubmitOrdernumber(@Path("orderNo") String Id);

    /**
     * 获取 全部/待付款/已付款/待评价
     */
    @POST("/orders/list/0")
    Observable<Response<AllOrderBody>> getAllOrder(@Body PageBody pageBody);

    /**
     * 取消订单
     */

    @POST("/orders/cancelOrder/{orderNo}/0")
    Observable<Response<Object>> getCancelOrder(@Path("orderNo") String Id);


    /**
     * 确认收货
     */

    @POST("/orders/editOrder/{orderNo}/0")
    Observable<Response<Object>> getQuerenOrder(@Path("orderNo") String Id);

    /**
     * 系统意见反馈
     */

    @POST("/user/addUserFeedBacks/0")
    Observable<Response<Object>> getSubmitSysMessage(@Body SysmessageBody sysmessageBody);

    /**
     * 提现到银行卡
     */

    @POST("/accounts/addUserWithdraw")
    Observable<Response<Object>> getTixian(@Body TixianBody tixianBody);

    /**
     * 删除购物车商品
     */

    @POST("shopCar/delUserShopCar")
    Observable<Response<Object>> getdeleteProduct(@Body List list);

    /**
     * 获取商品评价列表
     */

    @POST("/products/getProductEvaluate/{orderNo}/0")
    Observable<Response<ProductSpeakList>> getproductSpeaklist(@Path("orderNo") String Id);

    /**
     * 提交评价
     */

    @POST("/products/addProductEvaluate/0")
    Observable<Response<Object>> getSubSpeak(@Body SubSpeakBody subSpeakBody);

    /**
     * 个人业绩列表
     */
    @POST("/accounts/getUserPerformancePage/0")
    Observable<Response<PersonTeamShouyi>> getPersonTeamshouyi(@Body PageBody pageBody);
    /**
     * 团队业绩列表
     */
    @POST("/accounts/getTeamPerformancePage/0")
    Observable<Response<PersonTeamShouyi>> getPersonTeamshouyi2(@Body PageBody pageBody);

    /**
     * 添加银行卡
     */

    @POST("/products/addProductEvaluate/0")
    Observable<Response<Object>> getAddBankCard(@Body AddbankCardBody addbankCardBody);
}

