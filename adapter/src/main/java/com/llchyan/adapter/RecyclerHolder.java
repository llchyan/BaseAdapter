package com.llchyan.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by LinLin on 2015/7/18. RecyclerHolder
 */
@SuppressWarnings("unused")
public class RecyclerHolder extends RecyclerView.ViewHolder
{
    public static final String TAG = RecyclerHolder.class.getSimpleName();

    private SparseArray<View> mViews = null;
    private View mConvertView;

    private RecyclerHolder(View itemView)
    {
        super(itemView);
        this.mConvertView = itemView;
        this.mViews = new SparseArray<>();
    }

    private RecyclerHolder(Context context, ViewGroup parent, @LayoutRes int layoutId)
    {
        this(LayoutInflater.from(context).inflate(layoutId, parent, false));
    }

    public static RecyclerHolder get(Context context, ViewGroup parent, @LayoutRes int layoutId)
    {
        return new RecyclerHolder(context, parent, layoutId);
    }

    public static RecyclerHolder get(View itemView)
    {
        return new RecyclerHolder(itemView);
    }

    public View getConvertView()
    {
        return mConvertView;
    }


    /**
     *
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(@IdRes int viewId)
    {
        View view = mViews.get(viewId);
        if (null == view)
        {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     */
    public RecyclerHolder setText(@IdRes int viewId, @Nullable String text)
    {
        TextView view = getView(viewId);
        if (null == view)
        {
            Log.e(TAG, "获取TextView失败,viewId==0x" + Integer.toHexString(viewId));
            return this;
        }
        if (TextUtils.isEmpty(text))
            text = "";
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public RecyclerHolder setImageColor(@IdRes int viewId, @ColorRes int colorId)
    {
        ImageView view = getView(viewId);
        if (null == view)
        {
            Log.e(TAG, "获取ImageView失败,viewId==0x" + Integer.toHexString(viewId));
            return this;
        }
        view.setImageDrawable(new ColorDrawable(view.getResources().getColor(colorId)));
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public RecyclerHolder setImageResource(@IdRes int viewId, @DrawableRes int drawableId)
    {
        ImageView view = getView(viewId);
        if (null == view)
        {
            Log.e(TAG, "获取ImageView失败,viewId==0x" + Integer.toHexString(viewId));
            return this;
        }
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public RecyclerHolder setImageBitmap(@IdRes int viewId, @Nullable Bitmap bmp)
    {
        ImageView view = getView(viewId);
        if (null == view || null == bmp)
        {
            Log.e(TAG, "获取ImageView失败,viewId==0x" + Integer.toHexString(viewId));
            return this;
        }
        view.setImageBitmap(bmp);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    public RecyclerHolder setImageDrawable(@IdRes int viewId, @Nullable Drawable drawable)
    {
        ImageView view = getView(viewId);
        if (null == view || null == drawable)
        {
            Log.e(TAG, "获取ImageView失败,viewId==0x" + Integer.toHexString(viewId));
            return this;
        }
        view.setImageDrawable(drawable);
        return this;
    }

    /**
     * 为ImageView设置图片
     */
    //    public RecyclerHolder setImageFresco(int viewId, String url)
    //    {
    //        final SimpleDraweeView view = getView(viewId);
    //        if (null == view || TextUtils.isEmpty(url))
    //        {
    //             Log.e(TAG,"获取SimpleDraweeView失败,viewId==0x" + Integer.toHexString(viewId));
    //            return this;
    //        }
    //        PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder()
    //                .setUri(Uri.parse(url));
    //        view.setController(builder.setAutoPlayAnimations(url.endsWith(".gif") || url.endsWith(".webp")).build());
    //        return this;
    //    }

    /**
     * 设置控件点击事件
     */
    public RecyclerHolder setOnClickListener(@IdRes int viewId, @Nullable View.OnClickListener listener)
    {
        final View view = getView(viewId);
        if (null == view)
        {
            Log.e(TAG, "获取view失败,viewId==0x" + Integer.toHexString(viewId));
            return this;
        }
        view.setOnClickListener(listener);
        return this;
    }

    /**
     * 设置父容器点击事件
     */
    public RecyclerHolder setOnConvertViewClickListener(@Nullable View.OnClickListener listener)
    {
        mConvertView.setOnClickListener(listener);
        return this;
    }
}
