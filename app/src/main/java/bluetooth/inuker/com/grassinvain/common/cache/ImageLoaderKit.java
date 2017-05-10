package bluetooth.inuker.com.grassinvain.common.cache;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.shaojun.utils.log.Logger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bluetooth.inuker.com.grassinvain.R;

/**
 * Created by zfdang on 2016-4-17.
 */
public class ImageLoaderKit {
    private static final String TAG = ImageLoaderKit.class.getSimpleName();
    private static final int M = 1024 * 1024;

    private Context mContext;

    private static List<String> uriSchemes;

    private static class ImageLoaderManagerHolder {
        static final ImageLoaderKit INSTANCE = new ImageLoaderKit();
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private ImageLoaderKit() {

    }

    public static ImageLoaderKit getInstance() {
        return ImageLoaderManagerHolder.INSTANCE;
    }

    public void init(Context context) {
        this.mContext = context;
//        Fresco.initialize(context);
        try {
            initImageLoader(context);
        } catch (Exception e) {
            Logger.e(TAG, "init ImageLoaderKit error, e=" + e.getMessage().toString());

        }
    }

    private static DisplayImageOptions defaultImageOptions = createImageOptions();

    private static final DisplayImageOptions createImageOptions() {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.chanpintu)
                .showImageForEmptyUri(R.mipmap.chanpintu)
                .showImageOnFail(R.mipmap.chanpintu)
                .cacheInMemory(true).cacheOnDisk(true)
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    }

    public void initImageLoader(Context context) throws IOException {
        int MAX_CACHE_MEMORY_SIZE = (int) (Runtime.getRuntime().maxMemory() / 8);
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, context.getPackageName() + "/cache/image/");

        Logger.i(TAG, "ImageLoader memory cache size = " + MAX_CACHE_MEMORY_SIZE / M + "M");
        Logger.i(TAG, "ImageLoader disk cache directory = " + cacheDir.getAbsolutePath());

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .threadPoolSize(3) // 线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2) // 降低线程的优先级，减小对UI主线程的影响
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(MAX_CACHE_MEMORY_SIZE))
                .diskCache(new LruDiskCache(cacheDir, new Md5FileNameGenerator(), 50 * 1024 * 1024))
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)超时时间
                .writeDebugLogs()
                .build();
        ImageLoader.getInstance().init(config);


    }

//    /**
//     * Fresco
//     * @param url
//     * @param imageView
//     */
//    public void displayImage(Uri uri, SimpleDraweeView draweeView) {
//        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
//                .setAutoRotateEnabled(true)
////                .setResizeOptions(new ResizeOptions(200, 200))
//                .build();
//
//        DraweeController controller = Fresco.newDraweeControllerBuilder()
//                .setImageRequest(request)
//                .setAutoPlayAnimations(true)
//                .setOldController(draweeView.getController())
//                .setControllerListener(new BaseControllerListener<ImageInfo>())
//                .build();
//        draweeView.setController(controller);
//    }


    public void displayImage(String url, ImageView imageView) {
        ImageLoader.getInstance().displayImage(url, imageView, defaultImageOptions);

//        Glide.with(mContext)
//                .load(url)
//                .placeholder(R.mipmap.icon_placeholder)
//                .crossFade()
//                .into(imageView);
    }

    public void displayImage(String url, final ImageView imageView, boolean isCircle) {
        if (isCircle) {
            ImageLoader.getInstance().displayImage(url, imageView, defaultImageOptions);

//            Glide.with(mContext).load(url).asBitmap().centerCrop().into(new BitmapImageViewTarget(imageView) {
//                @Override
//                protected void setResource(Bitmap resource) {
//                    RoundedBitmapDrawable circularBitmapDrawable =
//                            RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
//                    circularBitmapDrawable.setCircular(true);
//                    imageView.setImageDrawable(circularBitmapDrawable);
//                }
//            });
        } else {
            displayImage(url, imageView);
        }
    }

    /**
     * 清理图片缓存
     */
    public void clear() {
        ImageLoader.getInstance().clearMemoryCache();
    }

    public static Bitmap getBitmapFromCache(String uri, int width, int height) {
        if (TextUtils.isEmpty(uri)) {
            return null;
        }

        boolean cached = true;
        ImageDownloader.Scheme scheme = ImageDownloader.Scheme.ofUri(uri);
        if (scheme == ImageDownloader.Scheme.HTTP || scheme == ImageDownloader.Scheme.HTTPS || scheme ==
                ImageDownloader.Scheme.UNKNOWN) {
            // non local resource
            cached = MemoryCacheUtils.findCachedBitmapsForImageUri(uri, ImageLoader.getInstance()
                    .getMemoryCache()).size() > 0 || DiskCacheUtils.findInCache(uri, ImageLoader.getInstance()
                    .getDiskCache()) != null;
        }

        if (cached) {
            Bitmap bitmap = ImageLoader.getInstance().loadImageSync(uri, new ImageSize(width, height));
            if (bitmap == null) {
                Logger.e(TAG, "load cached image failed, uri =" + uri);
            }
            return bitmap;
        }

        return null;
    }

    /**
     * 判断图片地址是否合法，合法地址如下：
     * String uri = "http://site.com/image.png"; // from Web
     * String uri = "file:///mnt/sdcard/image.png"; // from SD card
     * String uri = "content://media/external/audio/albumart/13"; // from content provider
     * String uri = "assets://image.png"; // from assets
     * String uri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
     */
    public static boolean isImageUriValid(String uri) {
        if (TextUtils.isEmpty(uri)) {
            return false;
        }

        if (uriSchemes == null) {
            uriSchemes = new ArrayList<>();
            for (ImageDownloader.Scheme scheme : ImageDownloader.Scheme.values()) {
                uriSchemes.add(scheme.name().toLowerCase());
            }
        }

        for (String scheme : uriSchemes) {
            if (uri.toLowerCase().startsWith(scheme)) {
                return true;
            }
        }

        return false;
    }
}
