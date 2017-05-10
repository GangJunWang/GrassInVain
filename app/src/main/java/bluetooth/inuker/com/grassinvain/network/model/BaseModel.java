package bluetooth.inuker.com.grassinvain.network.model;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * C/reated by qinwei on 16/7/26 下午4:26
 */
public abstract class BaseModel {
    protected Context context;

    public BaseModel(Context context) {
        this.context = context;
    }

    private CompositeSubscription mCompositeSubscription;

    /**
     * 解除订阅
     */
    public void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    /**
     * 添加订阅
     */
    public void addSubscrebe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

}
