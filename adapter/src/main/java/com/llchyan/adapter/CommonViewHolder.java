package com.llchyan.adapter;

import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressWarnings("unused")
public class CommonViewHolder
{
    public static final String TAG = CommonViewHolder.class.getSimpleName();
    private final SparseArray<View> mViews;
    private View mConvertView;

    private CommonViewHolder(ViewGroup parent, @LayoutRes int layoutId, int position)
    {
        this.mViews = new SparseArray<>();
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        mConvertView.setTag(this);
    }

    /**
     * 拿到一个ViewHolder对象
     */
    public static CommonViewHolder get(View convertView, ViewGroup parent, @LayoutRes int layoutId, int position)
    {
        CommonViewHolder holder = null;
        try
        {
            if (null == convertView)
            {
                holder = new CommonViewHolder(parent, layoutId, position);
            } else
            {
                holder = (CommonViewHolder) convertView.getTag();
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            Log.e(TAG, "CommonViewHolder异常 layoutId" + layoutId);
        }
        return holder;
    }

    public View getConvertView()
    {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
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
        if (null == view)
        {
            Log.e(TAG, "获取View失败,viewId==0x" + Integer.toHexString(viewId));
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     */
    public CommonViewHolder setText(@IdRes int viewId, @Nullable String text)
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
    public CommonViewHolder setImageColor(@IdRes int viewId, @ColorRes int colorId)
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
    public CommonViewHolder setImageResource(@IdRes int viewId, @DrawableRes int drawableId)
    {
        android.widget.ImageView view = getView(viewId);
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
    public CommonViewHolder setImageBitmap(@IdRes int viewId, @Nullable Bitmap bm)
    {
        android.widget.ImageView view = getView(viewId);
        if (null == view)
        {
            Log.e(TAG, "获取ImageView失败,viewId==0x" + Integer.toHexString(viewId));
            return this;
        }
        view.setImageBitmap(bm);
        return this;
    }


    /**
     * 为ImageView设置图片
     */
    public CommonViewHolder setImageDrawable(@IdRes int viewId, @Nullable Drawable drawable)
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

    //    public CommonViewHolder setImageFresco(int viewId, String url)
    //    {
    //        final SimpleDraweeView view = getView(viewId);
    //        if (view == null || TextUtils.isEmpty(url))
    //        {
    //            Log.e(TAG, "获取SimpleDraweeView失败,viewId==0x" + Integer.toHexString(viewId));
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
    public CommonViewHolder setOnClickListener(@IdRes int viewId, @Nullable View.OnClickListener listener)
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
    public CommonViewHolder setOnConvertViewClickListener(@Nullable View.OnClickListener listener)
    {
        mConvertView.setOnClickListener(listener);
        return this;
    }
}
