package com.davis.ktprogram.views.viewlineloop;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewConfigurationCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import com.davis.sdj.R;
import com.davis.sdj.util.CommonManager;
import com.davis.sdj.views.loopbanner.LoopPageAdapter;
import com.davis.sdj.views.viewpagerindicator.PageIndicator;
import com.davis.sdj.views.viewpagerindicator.scrollbar.ScrollBar;

import static android.graphics.Paint.ANTI_ALIAS_FLAG;
import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

public class BannerPageIndicator extends View implements PageIndicator {
    private static final int INVALID_POINTER = -1;

    private float mRadius;
    private float spacing;// ���
    private final Paint mPaintPageFill = new Paint(ANTI_ALIAS_FLAG);
    private final Paint mPaintStroke = new Paint(ANTI_ALIAS_FLAG);
    private final Paint mPaintFill = new Paint(ANTI_ALIAS_FLAG);
    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;
    public int mCurrentPage;
    private int mSnapPage;
    private float mPageOffset;
    private int mScrollState;
    private int mOrientation;
    private boolean mCentered;
    private boolean mSnap;

    private int mTouchSlop;
    private float mLastMotionX = -1;
    private int mActivePointerId = INVALID_POINTER;
    private boolean mIsDragging;

    private LoopPageAdapter mAdapter;
    private boolean isType = false;

    public BannerPageIndicator(Context context) {
        this(context, null);
    }

    public void setIsType(boolean isType) {
        this.isType = isType;
    }

    public BannerPageIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BannerPageIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        if (isInEditMode())
            return;
        mCentered = true;
        mOrientation = HORIZONTAL;
        mPaintPageFill.setStyle(Style.FILL);
        mPaintPageFill.setColor(0xffffffff);//正常状态下的填充颜色
        mPaintStroke.setStyle(Style.STROKE);
        mPaintStroke.setColor(0xFFffffff);//环形边缘颜色
        mPaintStroke.setStrokeWidth(dip2px(context, 1));//环形边缘宽度
        mPaintFill.setStyle(Style.FILL);
        mPaintFill.setColor(getResources().getColor(R.color.colormain));//选中的中间及移动的滑块颜色
        mRadius = dip2px(context, 12);//最外层圆半径，包括了边缘宽度
        mSnap = true;
        spacing = mRadius;
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = ViewConfigurationCompat
                .getScaledPagingTouchSlop(configuration);
        setPadding(0, dip2px(context, 10), 0, dip2px(context, 10));
    }

    private int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setCentered(boolean centered) {
        mCentered = centered;
        invalidate();
    }

    public boolean isCentered() {
        return mCentered;
    }

    public void setPageColor(int pageColor) {
        mPaintPageFill.setColor(pageColor);
        invalidate();
    }

    public int getPageColor() {
        return mPaintPageFill.getColor();
    }

    public void setFillColor(int fillColor) {
        mPaintFill.setColor(fillColor);
        invalidate();
    }

    public int getFillColor() {
        return mPaintFill.getColor();
    }

    public void setOrientation(int orientation) {
        switch (orientation) {
            case HORIZONTAL:
            case VERTICAL:
                mOrientation = orientation;
                requestLayout();
                break;

            default:
                throw new IllegalArgumentException(
                        "Orientation must be either HORIZONTAL or VERTICAL.");
        }
    }

    public int getOrientation() {
        return mOrientation;
    }

    public void setStrokeColor(int strokeColor) {
        mPaintStroke.setColor(strokeColor);
        invalidate();
    }

    public int getStrokeColor() {
        return mPaintStroke.getColor();
    }

    public void setStrokeWidth(float strokeWidth) {
        mPaintStroke.setStrokeWidth(strokeWidth);
        invalidate();
    }

    public float getStrokeWidth() {
        return mPaintStroke.getStrokeWidth();
    }

    public void setRadius(float radius) {
        mRadius = radius;
        invalidate();
    }

    public float getRadius() {
        return mRadius;
    }

    public void setSnap(boolean snap) {
        mSnap = snap;
        invalidate();
    }

    public boolean isSnap() {
        return mSnap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mViewPager == null) {
            return;
        }
        final int count = mAdapter.getRealCount();
        Log.i("123", "qqcount-" + count);
        if (count <= 1) {
            return;
        }

        if (mCurrentPage >= count) {
            setCurrentItem(count - 1);
            return;
        }

        int longSize;
        int longPaddingBefore;
        int longPaddingAfter;
        int shortPaddingBefore;

        float shortOffset;
        float twoRadius = mRadius * 2;
        float longOffset;
        if (mOrientation == HORIZONTAL) {
            longSize = getWidth();
            longPaddingBefore = getPaddingLeft();
            longPaddingAfter = getPaddingRight();
            shortPaddingBefore = getPaddingTop();
            shortOffset = (getHeight() / 2.0f);
        } else {
            longSize = getHeight();
            longPaddingBefore = getPaddingTop();
            longPaddingAfter = getPaddingBottom();
            shortPaddingBefore = getPaddingLeft();
            shortOffset = (getWidth() / 2.0f);
        }

        if (mCentered) {
            longOffset = (longSize / 2.0f)
                    - ((count * twoRadius + (count - 1) * spacing) / 2.0f);
            if (longOffset < longPaddingBefore) {
                longOffset = longPaddingBefore;
            }
            if (shortOffset < shortPaddingBefore) {
                shortOffset = shortPaddingBefore;
            }

        } else {
            shortOffset = shortPaddingBefore + mRadius;
            longOffset = longPaddingBefore;
        }

        float dX;
        float dY;
        longOffset += mRadius;
        float pageFillRadius = mRadius;
        float pageStokeRadius = mRadius;
//		Log.i("123", "mRadius" + mRadius);
//		Log.i("123", "StrokeWidth" + mPaintStroke.getStrokeWidth());
        if (mPaintStroke.getStrokeWidth() > 0) {
            pageStokeRadius -= mPaintStroke.getStrokeWidth() / 2;
            pageFillRadius -= mPaintStroke.getStrokeWidth();
        }

        // Draw stroked circles

        for (int iLoop = 0; iLoop < count; iLoop++) {

            float drawLong = longOffset + (iLoop * twoRadius + iLoop * spacing);
            if (mOrientation == HORIZONTAL) {
                dX = drawLong;
                dY = shortOffset;
            } else {
                dX = shortOffset;
                dY = drawLong;
            }

            // Only paint stroke if a stroke width was non-zero
            if (pageFillRadius != mRadius) {
                if (isType) {
                    canvas.drawRect(dX - CommonManager.pxToDp(33), dY - CommonManager.pxToDp(3), dX + CommonManager.pxToDp(33), dY + CommonManager.pxToDp(3), mPaintStroke);
                } else {
                    canvas.drawCircle(dX, dY, pageStokeRadius, mPaintStroke);
                }
            }
            // Only paint fill if not completely transparent
            if (mPaintPageFill.getAlpha() > 0) {
                if (isType) {
                    canvas.drawRect(dX - CommonManager.pxToDp(33), dY - CommonManager.pxToDp(3), dX + CommonManager.pxToDp(33), dY + CommonManager.pxToDp(3), mPaintPageFill);
                } else {
                    canvas.drawCircle(dX, dY, pageFillRadius + 1, mPaintPageFill);
                }
            }
        }

        // Draw the filled circle according to the current scroll
        float cx = mCurrentPage * (twoRadius + spacing);
        if (mSnap) {
            cx += mPageOffset * (twoRadius + spacing);
        }
        if (mOrientation == HORIZONTAL) {
            dX = longOffset + cx;
            dY = shortOffset;
        } else {
            dX = shortOffset;
            dY = longOffset + cx;
        }
        if (isType) {
            canvas.drawRect(dX - CommonManager.pxToDp(33), dY - CommonManager.pxToDp(4), dX + CommonManager.pxToDp(35), dY + CommonManager.pxToDp(4), mPaintFill);
        } else {
            canvas.drawCircle(dX, dY, pageFillRadius + 1, mPaintFill);
        }
    }

    public boolean onTouchEvent(MotionEvent ev) {
        if (super.onTouchEvent(ev)) {
            return true;
        }
        if ((mViewPager == null) || (mAdapter.getRealCount() == 0)) {
            return false;
        }

        final int action = ev.getAction() & MotionEventCompat.ACTION_MASK;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mActivePointerId = MotionEventCompat.getPointerId(ev, 0);
                mLastMotionX = ev.getX();
                break;

            case MotionEvent.ACTION_MOVE: {
                final int activePointerIndex = MotionEventCompat.findPointerIndex(
                        ev, mActivePointerId);
                final float x = MotionEventCompat.getX(ev, activePointerIndex);
                final float deltaX = x - mLastMotionX;

                if (!mIsDragging) {
                    if (Math.abs(deltaX) > mTouchSlop) {
                        mIsDragging = true;
                    }
                }

                if (mIsDragging) {
                    mLastMotionX = x;
                    if (mViewPager.isFakeDragging() || mViewPager.beginFakeDrag()) {
                        mViewPager.fakeDragBy(deltaX);
                    }
                }

                break;
            }

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (!mIsDragging) {
                    final int count = mAdapter.getRealCount();
                    final int width = getWidth();
                    final float halfWidth = width / 2f;
                    final float sixthWidth = width / 6f;

                    if ((mCurrentPage > 0) && (ev.getX() < halfWidth - sixthWidth)) {
                        if (action != MotionEvent.ACTION_CANCEL) {
                            mViewPager.setCurrentItem(mCurrentPage - 1);
                        }
                        return true;
                    } else if ((mCurrentPage < count - 1)
                            && (ev.getX() > halfWidth + sixthWidth)) {
                        if (action != MotionEvent.ACTION_CANCEL) {
                            mViewPager.setCurrentItem(mCurrentPage + 1);
                        }
                        return true;
                    }
                }

                mIsDragging = false;
                mActivePointerId = INVALID_POINTER;
                if (mViewPager.isFakeDragging())
                    mViewPager.endFakeDrag();
                break;

            case MotionEventCompat.ACTION_POINTER_DOWN: {
                final int index = MotionEventCompat.getActionIndex(ev);
                mLastMotionX = MotionEventCompat.getX(ev, index);
                mActivePointerId = MotionEventCompat.getPointerId(ev, index);
                break;
            }

            case MotionEventCompat.ACTION_POINTER_UP:
                final int pointerIndex = MotionEventCompat.getActionIndex(ev);
                final int pointerId = MotionEventCompat.getPointerId(ev,
                        pointerIndex);
                if (pointerId == mActivePointerId) {
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mActivePointerId = MotionEventCompat.getPointerId(ev,
                            newPointerIndex);
                }
                mLastMotionX = MotionEventCompat.getX(ev,
                        MotionEventCompat.findPointerIndex(ev, mActivePointerId));
                break;
        }

        return true;
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        if (view.getAdapter() == null) {
            throw new IllegalStateException(
                    "ViewPager does not have adapter instance.");
        }
        mAdapter = (LoopPageAdapter) view.getAdapter();
        mViewPager = view;
        mViewPager.setOnPageChangeListener(this);
        invalidate();
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }


    public void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mViewPager.setCurrentItem(item);
        mCurrentPage = item;
        invalidate();
    }

    @Override
    public void notifyDataSetChanged() {
        requestLayout();

    }

    @Override
    public void setScrollBar(ScrollBar scrollBar) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mScrollState = state;

        if (mListener != null) {
            mListener.onPageScrollStateChanged(state);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels) {
        Log.i("123", "onPageScrolled");
        Log.i("123", "position-" + position);
        Log.i("123", "count-" + mAdapter.getRealCount());
        if (mSnap) {
            mCurrentPage = position;
            mPageOffset = positionOffset;
            invalidate();
        }
        if (mListener != null) {
            mListener.onPageScrolled(position, positionOffset,
                    positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        if (!mSnap) {
            mCurrentPage = position;
            invalidate();
        }
        if (mListener != null) {
            mListener.onPageSelected(position);
        }
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mListener = listener;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.view.View#onMeasure(int, int)
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mOrientation == HORIZONTAL) {
            setMeasuredDimension(measureLong(widthMeasureSpec),
                    measureShort(heightMeasureSpec));
        } else {
            setMeasuredDimension(measureShort(widthMeasureSpec),
                    measureLong(heightMeasureSpec));
        }
    }

    /**
     * Determines the width of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The width of the view, honoring constraints from measureSpec
     */
    private int measureLong(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if ((specMode == MeasureSpec.EXACTLY) || (mViewPager == null)) {
            // We were told how big to be
            result = specSize;
        } else {
            // Calculate the width according the views count
            final int count = mAdapter.getRealCount();
            result = (int) (getPaddingLeft() + getPaddingRight()
                    + (count * 2 * mRadius) + (count - 1) * mRadius + 1);
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * Determines the height of this view
     *
     * @param measureSpec A measureSpec packed into an int
     * @return The height of the view, honoring constraints from measureSpec
     */
    private int measureShort(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else {
            // Measure the height
            result = (int) (mRadius / 2 + getPaddingTop() + getPaddingBottom() + 1);
            // Respect AT_MOST value if that was what is called for by
            // measureSpec
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        SavedState savedState = (SavedState) state;
        super.onRestoreInstanceState(savedState.getSuperState());
        mCurrentPage = savedState.currentPage;
        mSnapPage = savedState.currentPage;
        requestLayout();
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();
        SavedState savedState = new SavedState(superState);
        savedState.currentPage = mCurrentPage;
        return savedState;
    }

    static class SavedState extends BaseSavedState {
        int currentPage;

        public SavedState(Parcelable superState) {
            super(superState);
        }

        private SavedState(Parcel in) {
            super(in);
            currentPage = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            super.writeToParcel(dest, flags);
            dest.writeInt(currentPage);
        }

        @SuppressWarnings("UnusedDeclaration")
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            @Override
            public SavedState createFromParcel(Parcel in) {
                return new SavedState(in);
            }

            @Override
            public SavedState[] newArray(int size) {
                return new SavedState[size];
            }
        };
    }


}
