package bluetooth.inuker.com.grassinvain.network.model;

import android.content.Context;

import java.util.List;

import bluetooth.inuker.com.grassinvain.common.cache.CacheCenter;
import bluetooth.inuker.com.grassinvain.common.model.OssAuth;
import bluetooth.inuker.com.grassinvain.network.ApiWrapper;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.body.request.AddAdressBody;
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
import bluetooth.inuker.com.grassinvain.network.body.response.ProductBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSDeatilBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSpeakList;
import bluetooth.inuker.com.grassinvain.network.body.response.ProductSpreakBody;
import bluetooth.inuker.com.grassinvain.network.body.response.ShopCarListBody;
import bluetooth.inuker.com.grassinvain.network.body.response.UserAddressListBody;
import bluetooth.inuker.com.grassinvain.network.http.subscriber.ApiException;
import bluetooth.inuker.com.grassinvain.network.http.subscriber.NewSubscriber;
import bluetooth.inuker.com.grassinvain.network.model.RequestModel.UserBody;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;
import bluetooth.inuker.com.grassinvain.network.model.port.IUserModel;
import rx.Subscription;

/**
 * 用户模块
 */
public class UserModel extends BaseModel implements IUserModel {


    public UserModel(Context context) {
        super(context);
    }


    @Override
    public void login(UserBody userBody, final Callback<UserInfo> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.login(userBody)
                .subscribe(new NewSubscriber<UserInfo>(context, true) {
                    @Override
                    public void onNext(UserInfo user) {
                        System.out.println("-------------用户的信息" + user.toString());
                        CacheCenter.getInstance().setCurrUser(user);
                        //登陆云信
                        callback.onSuccess(user);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    @Override
    public void register(UserBody userBody, final Callback<UserInfo> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.register(userBody)
                .subscribe(new NewSubscriber<UserInfo>(context, false) {
                    @Override
                    public void onNext(UserInfo user) {

                        CacheCenter.getInstance().setCurrUser(user);
                        //登陆云信
                        callback.onSuccess(user);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);

    }


    @Override
    public void smsCaptcha(UserBody userBody, final Callback<String> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.smsCaptcha(userBody)
                .subscribe(new NewSubscriber<String>(context, false) {
                    @Override
                    public void onNext(String smsCaptcha) {

                        callback.onSuccess(smsCaptcha);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }


    @Override
    public void ossAuth(String mediaType, final Callback<OssAuth> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.ossAuth(mediaType)
                .subscribe(new NewSubscriber<OssAuth>(context, true) {
                    @Override
                    public void onNext(OssAuth ossAuth) {
                        callback.onSuccess(ossAuth);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }


    @Override
    public void updateUserInfo(UserInfo userInfo, final Callback<UserInfo> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.updateUserInfo(userInfo)
                .subscribe(new NewSubscriber<UserInfo>(context, true) {
                    @Override
                    public void onNext(UserInfo userInfo) {
                        //更新用户信息，修改缓存
                        CacheCenter.getInstance().setCurrUser(userInfo);
                        callback.onSuccess(userInfo);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 头部Banner
     */

    @Override
    public void getBanner(final Callback<List<BannerBody>> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getBanner()
                .subscribe(new NewSubscriber<List<BannerBody>>(context, true) {
                    @Override
                    public void onNext(List<BannerBody> pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 头部Banner
     */

    @Override
    public void getProductList(PageBody pageBody, final Callback<ProductBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getProductList(pageBody)
                .subscribe(new NewSubscriber<ProductBody>(context, true) {
                    @Override
                    public void onNext(ProductBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 头部Banner
     */
    @Override
    public void getProductDetail(long Id, final Callback<List<ProductSDeatilBody>> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getProductDetail(Id)
                .subscribe(new NewSubscriber<List<ProductSDeatilBody>>(context, true) {
                    @Override
                    public void onNext(List<ProductSDeatilBody> pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 商品评价getPSpeakList
     */


    @Override
    public void getPSpeakList(long Id, PageBody pageBody, final Callback<ProductSpreakBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getPSpeakList(Id, pageBody)
                .subscribe(new NewSubscriber<ProductSpreakBody>(context, true) {
                    @Override
                    public void onNext(ProductSpreakBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 加入购物车joinShoppingCarBody
     */
    @Override
    public void getJoinShoppingCar(JoinShoppingCarBody joinShoppingCarBody, final Callback<String> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getJoinShoppingCar(joinShoppingCarBody)
                .subscribe(new NewSubscriber<String>(context, true) {
                    @Override
                    public void onNext(String pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 个人中心数据
     */

    @Override
    public void getPersonCentern(final Callback<UserInfo> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getPersonCentern()
                .subscribe(new NewSubscriber<UserInfo>(context, true) {
                    @Override
                    public void onNext(UserInfo pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }


    /**
     * 获取地址列表
     */

    @Override
    public void getAddressList(PageBody pageBody, final Callback<UserAddressListBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getAddressList(pageBody)
                .subscribe(new NewSubscriber<UserAddressListBody>(context, true) {
                    @Override
                    public void onNext(UserAddressListBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }


    /**
     * 删除地址
     */

    @Override
    public void getDeleteAddress(String Id, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getDeleteAddress(Id)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 删除地址
     */

    @Override
    public void getAddAdress(AddAdressBody addAdressBody, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getAddAdress(addAdressBody)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }


    /**
     * 编辑地址
     */

    @Override
    public void getChangeAdress(AddAdressBody addAdressBody, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getChangeAdress(addAdressBody)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 设置默认地址
     *
     * @param addAdressBody
     * @param callback
     */

    @Override
    public void getSetMorenAdress(AddressDetailBody addAdressBody, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getSetMorenAdress(addAdressBody)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 获取购物车列表
     */

    @Override
    public void getShopCarList(PageBody pageBody, final Callback<ShopCarListBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getShopCarList(pageBody)
                .subscribe(new NewSubscriber<ShopCarListBody>(context, true) {
                    @Override
                    public void onNext(ShopCarListBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 获取新消息列表
     */

    @Override
    public void getNewMessageList(PageBody pageBody, final Callback<NewMessageBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getNewMessageList(pageBody)
                .subscribe(new NewSubscriber<NewMessageBody>(context, true) {
                    @Override
                    public void onNext(NewMessageBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 个人中心 获取账单列表
     */

    @Override
    public void getPerZhangDList(PageBody pageBody, final Callback<PersonShouyiZhangdanBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getPerZhangDList(pageBody)
                .subscribe(new NewSubscriber<PersonShouyiZhangdanBody>(context, true) {
                    @Override
                    public void onNext(PersonShouyiZhangdanBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 更新用户信息
     */

    @Override
    public void getUpdateUser(UserInfo userInfo, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getUpdateUser(userInfo)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 申请离职
     */

    @Override
    public void getThrowWork(UserInfo userInfo, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getThrowWork(userInfo)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 获取银行卡列表
     */

    @Override
    public void getBankCardList(PageBody pageBody, final Callback<BankCardBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getBankCardList(pageBody)
                .subscribe(new NewSubscriber<BankCardBody>(context, true) {
                    @Override
                    public void onNext(BankCardBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 获取默认地址
     */

    @Override
    public void getMorenAddress(final Callback<MorenAddressBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getMorenAddress()
                .subscribe(new NewSubscriber<MorenAddressBody>(context, true) {
                    @Override
                    public void onNext(MorenAddressBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 提交订单
     */
    @Override
    public void getSubmitOrder(SubmitOrderBody submitOrderBody, final Callback<SubmitOrderBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getSubmitOrder(submitOrderBody)
                .subscribe(new NewSubscriber<SubmitOrderBody>(context, true) {
                    @Override
                    public void onNext(SubmitOrderBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 提交订单号
     */
    @Override
    public void getSubmitOrdernumber(String orderNo, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getSubmitOrdernumber(orderNo)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }


    /**
     * 获取所有/ 分类订单列表
     */

    @Override
    public void getAllOrder(PageBody pageBody, final Callback<AllOrderBody> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getAllOrder(pageBody)
                .subscribe(new NewSubscriber<AllOrderBody>(context, true) {
                    @Override
                    public void onNext(AllOrderBody pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 取消订单
     */

    @Override
    public void getCancelOrder(String orderNo, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getCancelOrder(orderNo)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 取消订单
     */

    @Override
    public void getQuerenOrder(String orderNo, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getQuerenOrder(orderNo)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 系统意反馈
     */

    @Override
    public void getSubmitSysMessage(SysmessageBody sysmessageBody, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getSubmitSysMessage(sysmessageBody)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }


    /**
     * 提现到银行卡
     */
    @Override
    public void getTixian(TixianBody tixianBody, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getTixian(tixianBody)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 删除购物车商品
     */


    @Override
    public void getdeleteProduct(List list, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getdeleteProduct(list)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 获取商品评价列表
     */
    @Override
    public void getproductSpeaklist(String orderNo, final Callback<ProductSpeakList> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getproductSpeaklist(orderNo)
                .subscribe(new NewSubscriber<ProductSpeakList>(context, true) {
                    @Override
                    public void onNext(ProductSpeakList pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }

    /**
     * 提交评价
     */

    @Override
    public void getSubSpeak(SubSpeakBody subSpeakBody, final Callback<Object> callback) {
        ApiWrapper apiWrapper = new ApiWrapper();
        Subscription subscription = apiWrapper.getSubSpeak(subSpeakBody)
                .subscribe(new NewSubscriber<Object>(context, true) {
                    @Override
                    public void onNext(Object pager) {
                        callback.onSuccess(pager);
                    }

                    @Override
                    protected void onError(ApiException ex) {
                        super.onError(ex);
                        callback.onFailure(ex.getCode(), ex.getErrMessage());
                    }
                });
        addSubscrebe(subscription);
    }
}
