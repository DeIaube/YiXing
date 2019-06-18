package baselib.base2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.MotionEvent;
import android.view.TouchDelegate;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Group;
import androidx.core.content.ContextCompat;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.CustomViewTarget;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.transition.Transition;

import java.io.File;

import baselib.App;
import baselib.util.FastBlur;

public abstract class DataBindingConfig {

    @BindingAdapter("url")
    public static void setImageUrl(ImageView imageView, String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .into(imageView);
    }

    @BindingAdapter("onFocusChanged")
    public static void onFocusChanged(EditText view, View.OnFocusChangeListener onFocusChangeListener) {
        if (onFocusChangeListener != null) {
            view.setOnFocusChangeListener(onFocusChangeListener);
        }
    }

    @BindingAdapter("selected")
    public static void selected(View view, boolean selected) {
        view.setSelected(selected);
    }

    @BindingAdapter("enabled")
    public static void enable(View view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @BindingAdapter("visible")
    public static void setVisible(View v, boolean visible) {
        v.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @BindingAdapter("android:paddingBottom")
    public static void setPaddingBottom(View view, float padding) {
        view.setPadding(view.getPaddingStart(),
                view.getPaddingTop(),
                view.getPaddingEnd(),
                (int) padding);
    }

    @BindingAdapter("android:layout_marginStart")
    public static void setMarginStart(View v, float margin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.leftMargin = (int) margin;
        v.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_marginStart")
    public static void setMarginStart(View v, int margin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.leftMargin = margin;
        v.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_marginTop")
    public static void setMarginTop(View v, float margin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.topMargin = (int) margin;
        v.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_marginTop")
    public static void setMarginTop(View v, int margin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.topMargin = margin;
        v.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_marginBottom")
    public static void setMarginBottom(View v, float margin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.bottomMargin = (int) margin;
        v.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_marginEnd")
    public static void setMarginEnd(View v, float margin) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        params.rightMargin = (int) margin;
        v.setLayoutParams(params);
    }

    @BindingAdapter("android:layout_height")
    public static void setLayoutHeight(View view, float height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) height;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_width")
    public static void setLayoutWidth(View view, float width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = (int) width;
        view.setLayoutParams(layoutParams);
    }

    @BindingAdapter("android:layout_weight")
    public static void setWeight(View view, float weight) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        layoutParams.weight = weight;
        view.requestLayout();
    }

    @BindingAdapter({"android:drawableStart"})
    public static void setDrawableStart(TextView view, @DrawableRes int resourceId) {
        Drawable[] drawables = view.getCompoundDrawables();
        if (resourceId == 0) {
            view.setCompoundDrawables(null, drawables[1], drawables[2], drawables[3]);
        } else {
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceId);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            view.setCompoundDrawables(drawable, drawables[1], drawables[2], drawables[3]);
        }
    }

    @BindingAdapter({"android:drawableEnd"})
    public static void setDrawableEnd(TextView view, @DrawableRes int resourceId) {
        Drawable[] drawables = view.getCompoundDrawables();
        if (resourceId == 0) {
            view.setCompoundDrawables(drawables[0], drawables[1], null, drawables[3]);
        } else {
            Drawable drawable = ContextCompat.getDrawable(view.getContext(), resourceId);
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            }
            view.setCompoundDrawables(drawables[0], drawables[1], drawable, drawables[3]);
        }
    }

    @BindingAdapter({"bold"})
    public static void setBold(TextView textView, boolean bold) {
        if (bold) {
            textView.setTypeface(textView.getTypeface(), Typeface.BOLD);
        } else {
            textView.setTypeface(textView.getTypeface(), Typeface.NORMAL);
        }
    }

    @BindingAdapter({"android:textColor"})
    public static void setTextColor(TextView textView, @ColorInt int color) {
        textView.setTextColor(color);
    }

    @BindingAdapter({"android:drawablePadding"})
    public static void setDrawablePaddingStart(TextView view, float padding) {
        view.setCompoundDrawablePadding((int) padding);
    }

    @BindingAdapter({"android:onClick"})
    public static void setGroupOnClickListener(Group group, View.OnClickListener listener) {
        int refIds[] = group.getReferencedIds();
        for (int id : refIds) {
            ViewGroup parent = (ViewGroup) group.getParent();
            parent.findViewById(id).setOnClickListener(listener);
        }
    }

    @BindingAdapter({"content", "subcontent1", "subcontent2", "subcontent3", "color", "backColor", "onClick1", "onClick2", "onClick3"})
    public static void setTextSpecialColorAndClickListener(View view, String content, String subcontent1, String subcontent2, String subcontent3,
                                                           int color, int backColor, View.OnClickListener listener1,
                                                           View.OnClickListener listener2, View.OnClickListener listener3) {
        if (!(view instanceof TextView)) {
            return;
        }

        SpannableStringBuilder stringBuilder = new SpannableStringBuilder(content);
        int start1 = 0;
        int end1 = 0;
        if (!TextUtils.isEmpty(subcontent1) && content.contains(subcontent1)) {
            ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(color);
            start1 = content.indexOf(subcontent1);
            end1 = start1 + subcontent1.length();
            if (start1 < end1) {
                stringBuilder.setSpan(foregroundColorSpan1, start1, end1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        int start2 = 0;
        int end2 = 0;
        if (!TextUtils.isEmpty(subcontent2) && content.contains(subcontent2)) {
            ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(color);
            start2 = content.indexOf(subcontent2);
            end2 = start2 + subcontent2.length();
            if (start2 < end2) {
                stringBuilder.setSpan(foregroundColorSpan2, start2, end2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        int start3 = 0;
        int end3 = 0;
        if (!TextUtils.isEmpty(subcontent3) && content.contains(subcontent3)) {
            ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(color);
            start3 = content.indexOf(subcontent3);
            end3 = start3 + subcontent3.length();
            if (start3 < end3) {
                stringBuilder.setSpan(foregroundColorSpan3, start3, end3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                listener1.onClick(widget);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                listener2.onClick(widget);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };

        ClickableSpan clickableSpan3 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                listener3.onClick(widget);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                ds.setUnderlineText(false);
            }
        };

        stringBuilder.setSpan(clickableSpan1, start1, end1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(clickableSpan2, start2, end2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        stringBuilder.setSpan(clickableSpan3, start3, end3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ((TextView) view).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) view).setHighlightColor(backColor);
        ((TextView) view).setText(stringBuilder);
    }

    @BindingAdapter({"fade"})
    public static void setVisibilityAnim(View view, int visibility) {
        if (view.getVisibility() == visibility) {
            return;
        }
        if (visibility == View.VISIBLE) {
            view.clearAnimation();
            view.setVisibility(View.VISIBLE);
            view.animate().alpha(1).setDuration(200).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.clearAnimation();
                    view.animate().setListener(null);
                }
            }).start();
        } else {
            view.clearAnimation();
            view.animate().alpha(0).setDuration(200).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.clearAnimation();
                    view.setVisibility(visibility);
                }
            });
        }
    }

    @BindingAdapter({"fade"})
    public static void setGroupFade(Group group, int visibility) {
        int refIds[] = group.getReferencedIds();
        for (int id : refIds) {
            ViewGroup parent = (ViewGroup) group.getParent();
            View view = parent.findViewById(id);
            if (visibility == View.VISIBLE) {
                view.clearAnimation();
                group.setVisibility(View.VISIBLE);
                view.animate().alpha(1).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.clearAnimation();
                    }
                }).start();
            } else {
                view.clearAnimation();
                view.animate().alpha(0).setDuration(200).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.clearAnimation();
                        group.setVisibility(visibility);
                    }
                });
            }
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    setVisibilityAnim(viewGroup.getChildAt(i), visibility);
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @BindingAdapter("pressAlpha")
    public static void setPressAlpha(View view, float alpha) {
        view.setOnTouchListener((v, event) -> {
            if (view.isEnabled()) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.setAlpha(alpha);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        view.setAlpha(1.0f);
                        break;
                }
            }
            return false;
        });

    }

    @BindingAdapter("gray")
    public static void setGray(ImageView v, boolean gray) {
        ColorFilter filter = null;
        if (gray) {
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0);
            filter = new ColorMatrixColorFilter(matrix);
        }
        v.setColorFilter(filter);
    }

    /**
     * 用于url图片加载，并且实现高斯模糊效果
     */
    @BindingAdapter({"url", "blur"})
    public static void loadImage(ImageView view, String url, int blur) {
        if (!TextUtils.isEmpty(url) && blur > 0) {
            Glide.with(App.getAppalication()).asBitmap().load(url).into(new ImageViewTarget<Bitmap>(view) {
                @Override
                protected void setResource(@Nullable Bitmap resource) {
                    if (resource != null) {
                        Bitmap blurBg = FastBlur.doBlur(resource, blur, false);
                        view.setImageBitmap(blurBg);
                    } else {
                        view.setImageBitmap(null);
                    }
                }
            });
        }
    }

    @BindingAdapter({"android:src"})
    public static void loadImage(ImageView view, int res) {
        if (res > 0) {
            view.setImageResource(res);
        }
    }

    @BindingAdapter({"path"})
    public static void loadImageFromPath(ImageView view, String path) {
        if (!TextUtils.isEmpty(path)) {
            Glide.with(view).load(new File(path)).into(view);
        }
    }

    @BindingAdapter({"url_withNoCache"})
    public static void loadImageNoCache(ImageView view, String url) {
        Glide.with(view).load(url).diskCacheStrategy(DiskCacheStrategy.NONE).into(view);
    }

    @BindingAdapter({"layout_height"})
    public static void setHeight(View view, int height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = height;
            view.requestLayout();
        } else {
            view.setLayoutParams(new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        }
    }

    /**
     * 加载view的背景图
     *
     * @param view        任意view
     * @param background  背景图url
     * @param placeHolder 占位图
     */
    @BindingAdapter(value = {"android:background", "bgPlaceHolder"}, requireAll = false)
    public static void loadBackgroundImage(View view, String background, Drawable placeHolder) {
        view.setBackground(placeHolder);
        if (!TextUtils.isEmpty(background)) {
            Glide.with(view)
                    .asBitmap()
                    .load(background)
                    .placeholder(placeHolder)
                    .error(placeHolder)
                    .into(new CustomViewTarget<View, Bitmap>(view) {
                        @Override
                        protected void onResourceCleared(@Nullable Drawable placeholder) {
                            view.setBackground(null);
                        }

                        @Override
                        public void onLoadFailed(@Nullable Drawable errorDrawable) {
                            view.setBackground(errorDrawable);
                        }

                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            Drawable drawable = new BitmapDrawable(view.getResources(), resource);
                            view.setBackground(drawable);
                        }
                    });
        }
    }

    @BindingAdapter("spanCount")
    public static void setSpanCount(RecyclerView recyclerView, int spanCount) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            ((GridLayoutManager) recyclerView.getLayoutManager()).setSpanCount(spanCount);
        }
    }

    @BindingAdapter("touchArea")
    public static void expandTouchArea(View view, int touchArea) {
        try {
            view.post(() -> {
                View viewParent = (View) view.getParent();
                Rect rect = new Rect();
                view.getHitRect(rect);
                rect.top = rect.top - touchArea >= 0 ? rect.top - touchArea : rect.top;
                rect.bottom += touchArea;
                rect.left = rect.left - touchArea >= 0 ? rect.left - touchArea : rect.left;
                rect.right += touchArea;
                if (viewParent != null) {
                    viewParent.setTouchDelegate(new TouchDelegate(rect, view));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BindingAdapter("touchArea")
    public static void expandTouchArea(View view, float touchArea) {
        expandTouchArea(view, (int) touchArea);
    }

    @BindingAdapter("nested_scrolling_enabled")
    public static void setNestedScrollingEnable(RecyclerView recyclerView, Boolean enable) {
        recyclerView.setNestedScrollingEnabled(enable);
    }

}
