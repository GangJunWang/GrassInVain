package bluetooth.inuker.com.grassinvain.common.widget;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.youth.banner.loader.ImageLoader;

/**
 * Created by 1 on 2017/3/28.
 */

public class GlideImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object o, ImageView imageView) {
        Picasso.with(context).load(o.toString()).into(imageView);
    }

  /*  @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //Glide.with(context).load(path).placeholder(R.drawable.shop_image_waiting).into(imageView);
        Picasso.with(context).load(path).into(imageView);
    }*/
}
