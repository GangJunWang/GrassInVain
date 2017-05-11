package bluetooth.inuker.com.grassinvain.network.model.port;


import java.util.List;

import bluetooth.inuker.com.grassinvain.common.model.OssAuth;
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
import bluetooth.inuker.com.grassinvain.network.model.RequestModel.UserBody;
import bluetooth.inuker.com.grassinvain.network.model.callback.Callback;

/**
 * 用户相关
 */
public interface IUserModel {

    /**
     * 用户登录
     */
    void login(UserBody userBody, Callback<UserInfo> callback);

    /**
     * 用户注册
     *
     * @param userBody 请求实体
     */
    void register(UserBody userBody, Callback<UserInfo> callback);
    /**
     * 短信验证码
     */
    void smsCaptcha(UserBody userBody, Callback<String> callback);


    void ossAuth(String mediaType, Callback<OssAuth> callback);

    void updateUserInfo(UserInfo userInfo, Callback<UserInfo> callback);

    void getBanner(Callback<List<BannerBody>> callback);

    void getProductList(PageBody pageBody, Callback<ProductBody> callback);

    void getProductDetail(long Id, Callback<List<ProductSDeatilBody>> callback);

    void getPSpeakList(long Id, PageBody pageBody, Callback<ProductSpreakBody> callback);

    void getJoinShoppingCar(JoinShoppingCarBody joinShoppingCarBody, Callback<String> callback);

    void getPersonCentern(Callback<UserInfo> callback);

    void getAddressList(PageBody pageBody, Callback<UserAddressListBody> callback);

    void getDeleteAddress(String Id, Callback<Object> callback);

    void getAddAdress(AddAdressBody addAdressBody, Callback<Object> callback);

    void getChangeAdress(AddAdressBody addAdressBody, Callback<Object> callback);

    void getSetMorenAdress(AddressDetailBody addAdressBody, Callback<Object> callback);

    void getShopCarList(PageBody pageBody, Callback<ShopCarListBody> callback);

    void getNewMessageList(PageBody pageBody, Callback<NewMessageBody> callback);

    void getPerZhangDList(PageBody pageBody, Callback<PersonShouyiZhangdanBody> callback);

    void getUpdateUser(UserInfo userInfo, Callback<Object> callback);

    void getThrowWork(UserInfo userInfo, Callback<Object> callback);

    void getBankCardList(PageBody pageBody, Callback<BankCardBody> callback);

    void getMorenAddress(Callback<MorenAddressBody> callback);

    void getSubmitOrder(SubmitOrderBody submitOrderBody, Callback<SubmitOrderBody> callback);

    void getSubmitOrdernumber(String orderNo, Callback<Object> callback);

    void getAllOrder(PageBody pageBody, Callback<AllOrderBody> callback);

    void getCancelOrder(String orderNo, Callback<Object> callback);

    void getQuerenOrder(String orderNo, Callback<Object> callback);

    void getSubmitSysMessage(SysmessageBody sysmessageBody, Callback<Object> callback);

    void getTixian(TixianBody tixianBody, Callback<Object> callback);


    void getdeleteProduct(List list, Callback<Object> callback);

    void getproductSpeaklist(String orderNo, Callback<ProductSpeakList> callback);

    void getSubSpeak(SubSpeakBody subSpeakBody, Callback<Object> callback);
}
