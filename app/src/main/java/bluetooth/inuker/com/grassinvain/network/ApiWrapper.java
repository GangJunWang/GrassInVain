package bluetooth.inuker.com.grassinvain.network;


import java.util.List;

import bluetooth.inuker.com.grassinvain.common.model.OssAuth;
import bluetooth.inuker.com.grassinvain.network.body.UserInfo;
import bluetooth.inuker.com.grassinvain.network.body.request.AddAdressBody;
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
import bluetooth.inuker.com.grassinvain.network.http.RetrofitManager;
import bluetooth.inuker.com.grassinvain.network.model.RequestModel.UserBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;


/**
 * Created by 1 on 2016/12/26.
 */
public class ApiWrapper {

    final Observable.Transformer transformer = new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
            return ((Observable) observable)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .flatMap(new Func1() {
                        @Override
                        public Object call(Object response) {
                            return flatResponse((Response<Object>) response);
                        }
                    });
        }
    };

    /**
     * 对网络接口返回的Response进行分割操作
     *
     * @param response
     * @param <T>
     * @return
     */
    public <T> Observable<T> flatResponse(final Response<T> response) {
        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    subscriber.onNext(response.result);
                }

                if (!subscriber.isUnsubscribed()) {
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * 登陆
     *
     * @param userBody
     * @return UserInfo
     */
    public Observable<UserInfo> login(UserBody userBody) {
        return RetrofitManager.getInstance().getUserService().login(userBody)
                .compose(this.<UserInfo>applySchedulers());
    }

    /**
     * 注册
     *
     * @param userBody
     * @return UserInfo
     */
    public Observable<UserInfo> register(UserBody userBody) {
        return RetrofitManager.getInstance().getUserService().register(userBody)
                .compose(this.<UserInfo>applySchedulers());
    }

    /**
     * 短信验证码
     *
     * @param userBody
     * @return captchaToken
     */
    public Observable<String> smsCaptcha(UserBody userBody) {
        return RetrofitManager.getInstance().getUserService().smsCaptcha(userBody)
                .compose(this.<String>applySchedulers());
    }


    /**
     * 文件上传-认证
     *
     * @param mediaType
     * @return captchaToken
     */
    public Observable<OssAuth> ossAuth(String mediaType) {
        return RetrofitManager.getInstance().getUserService().ossAuth(mediaType)
                .compose(this.<OssAuth>applySchedulers());
    }

    /**
     * 编辑用户信息
     *
     * @return captchaToken
     */
    public Observable<UserInfo> updateUserInfo(UserInfo userinfo) {
        return RetrofitManager.getInstance().getUserService().updateUserInfo(userinfo)
                .compose(this.<UserInfo>applySchedulers());
    }


    /**
     * 头部banner
     */
    public Observable<List<BannerBody>> getBanner() {
        return RetrofitManager.getInstance().getUserService().getBanner()
                .compose(this.<List<BannerBody>>applySchedulers());
    }

    /**
     * 首页列表
     */
    public Observable<ProductBody> getProductList(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getProductList(pageBody)
                .compose(this.<ProductBody>applySchedulers());
    }

    /**
     * 商品详情
     */
    public Observable<List<ProductSDeatilBody>> getProductDetail(long Id) {
        return RetrofitManager.getInstance().getUserService().getProductDetail(Id)
                .compose(this.<List<ProductSDeatilBody>>applySchedulers());
    }

    /**
     * 商品评价
     */

    public Observable<ProductSpreakBody> getPSpeakList(long Id, PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getPSpeakList(Id, pageBody)
                .compose(this.<ProductSpreakBody>applySchedulers());
    }

    /**
     * 加入购物车
     */


    public Observable<String> getJoinShoppingCar(JoinShoppingCarBody joinShoppingCarBody) {
        return RetrofitManager.getInstance().getUserService().getJoinShoppingCar(joinShoppingCarBody)
                .compose(this.<String>applySchedulers());
    }


    /**
     * 个人中心数据
     */
    public Observable<UserInfo> getPersonCentern() {
        return RetrofitManager.getInstance().getUserService().getPersonCentern()
                .compose(this.<UserInfo>applySchedulers());
    }

    /**
     * 获取地址列表
     */
    public Observable<UserAddressListBody> getAddressList(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getAddressList(pageBody)
                .compose(this.<UserAddressListBody>applySchedulers());
    }

    /**
     * 删除地址
     */
    public Observable<Object> getDeleteAddress(String Id) {
        return RetrofitManager.getInstance().getUserService().getDeleteAddress(Id)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 增加地址
     */
    public Observable<Object> getAddAdress(AddAdressBody addAdressBody) {
        return RetrofitManager.getInstance().getUserService().getAddAdress(addAdressBody)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 编辑地址
     *
     * @param addAdressBody
     * @return
     */
    public Observable<Object> getChangeAdress(AddAdressBody addAdressBody) {
        return RetrofitManager.getInstance().getUserService().getChangeAdress(addAdressBody)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 设置默认地址
     *
     * @param addAdressBody
     * @return
     */

    public Observable<Object> getSetMorenAdress(AddressDetailBody addAdressBody) {
        return RetrofitManager.getInstance().getUserService().getSetMorenAdress(addAdressBody)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 获取购物车列表
     */
    public Observable<ShopCarListBody> getShopCarList(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getShopCarList(pageBody)
                .compose(this.<ShopCarListBody>applySchedulers());
    }

    /**
     * 获取新消息列表
     */
    public Observable<NewMessageBody> getNewMessageList(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getNewMessageList(pageBody)
                .compose(this.<NewMessageBody>applySchedulers());
    }

    /**
     * 个人中心-账单列表
     */
    public Observable<PersonShouyiZhangdanBody> getPerZhangDList(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getPerZhangDList(pageBody)
                .compose(this.<PersonShouyiZhangdanBody>applySchedulers());
    }


    /**
     * 更新用户信息
     */
    public Observable<Object> getUpdateUser(UserInfo userInfo) {
        return RetrofitManager.getInstance().getUserService().getUpdateUser(userInfo)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 申请离职
     */

    public Observable<Object> getThrowWork(UserInfo userInfo) {
        return RetrofitManager.getInstance().getUserService().getThrowWork(userInfo)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 获取银行卡列表
     */
    public Observable<BankCardBody> getBankCardList(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getBankCardList(pageBody)
                .compose(this.<BankCardBody>applySchedulers());

    }

    /**
     * 获取默认地址
     */

    public Observable<MorenAddressBody> getMorenAddress() {
        return RetrofitManager.getInstance().getUserService().getMorenAddress()
                .compose(this.<MorenAddressBody>applySchedulers());

    }

    /**
     * 提交订单
     */

    public Observable<SubmitOrderBody> getSubmitOrder(SubmitOrderBody submitOrderBody) {
        return RetrofitManager.getInstance().getUserService().getSubmitOrder(submitOrderBody)
                .compose(this.<SubmitOrderBody>applySchedulers());

    }

    /**
     * 提交订单号
     */
    public Observable<Object> getSubmitOrdernumber(String oederNo) {
        return RetrofitManager.getInstance().getUserService().getSubmitOrdernumber(oederNo)
                .compose(this.<Object>applySchedulers());
    }


    /**
     * 获取银行卡列表
     */
    public Observable<AllOrderBody> getAllOrder(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getAllOrder(pageBody)
                .compose(this.<AllOrderBody>applySchedulers());

    }

    /**
     * 取消订单
     */

    public Observable<Object> getCancelOrder(String oederNo) {
        return RetrofitManager.getInstance().getUserService().getCancelOrder(oederNo)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 确认收货
     */

    public Observable<Object> getQuerenOrder(String oederNo) {
        return RetrofitManager.getInstance().getUserService().getQuerenOrder(oederNo)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 系统意见反馈
     */
    public Observable<Object> getSubmitSysMessage(SysmessageBody sysmessageBody) {
        return RetrofitManager.getInstance().getUserService().getSubmitSysMessage(sysmessageBody)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 提现到银行卡
     */

    public Observable<Object> getTixian(TixianBody tixianBody) {
        return RetrofitManager.getInstance().getUserService().getTixian(tixianBody)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 删除购物车商品
     */
    public Observable<Object> getdeleteProduct(List list) {
        return RetrofitManager.getInstance().getUserService().getdeleteProduct(list)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 获取商品评价列表
     */
    public Observable<ProductSpeakList> getproductSpeaklist(String oederNo) {
        return RetrofitManager.getInstance().getUserService().getproductSpeaklist(oederNo)
                .compose(this.<ProductSpeakList>applySchedulers());
    }

    /**
     * 提交评价
     */
    public Observable<Object> getSubSpeak(SubSpeakBody subSpeakBody) {
        return RetrofitManager.getInstance().getUserService().getSubSpeak(subSpeakBody)
                .compose(this.<Object>applySchedulers());
    }

    /**
     * 个人收益列表
     */

    public Observable<PersonTeamShouyi> getPersonTeamshouyi(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getPersonTeamshouyi(pageBody)
                .compose(this.<PersonTeamShouyi>applySchedulers());
    }

    /**
     * 团队收益列表
     */

    public Observable<PersonTeamShouyi> getPersonTeamshouyi2(PageBody pageBody) {
        return RetrofitManager.getInstance().getUserService().getPersonTeamshouyi2(pageBody)
                .compose(this.<PersonTeamShouyi>applySchedulers());
    }
    /**
     * 添加银行卡
     */

    public Observable<Object> getAddBankCard(AddbankCardBody addbankCardBody) {
        return RetrofitManager.getInstance().getUserService().getAddBankCard(addbankCardBody)
                .compose(this.<Object>applySchedulers());
    }


    /**
     * http://www.jianshu.com/p/e9e03194199e
     * Transformer实际上就是一个Func1<Observable<T>, Observable<R>>，
     * 换言之就是：可以通过它将一种类型的Observable转换成另一种类型的Observable，
     * 和调用一系列的内联操作符是一模一样的。
     * @param <T>
     * @return
     */
    protected <T> Observable.Transformer<Response<T>, T> applySchedulers() {
        return (Observable.Transformer<Response<T>, T>) transformer;
    }
}
