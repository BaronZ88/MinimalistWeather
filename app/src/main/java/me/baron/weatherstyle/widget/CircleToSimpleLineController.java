package me.baron.weatherstyle.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/4/5
 */
public class CircleToSimpleLineController extends BaseController {

    private int cx, cy, cr;
    private RectF mRectF;
    private int j = 10;

    public CircleToSimpleLineController() {
        mRectF = new RectF();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        String mColor = "#4CAF50";
        canvas.drawColor(Color.parseColor(mColor));
        switch (mState) {
            case STATE_ANIM_NONE:
                drawNormalView(paint, canvas);
                break;
            case STATE_ANIM_START:
                drawStartAnimView(paint, canvas);
                break;
            case STATE_ANIM_STOP:
                drawStopAnimView(paint, canvas);
                break;
        }
    }

    private void drawStopAnimView(Paint paint, Canvas canvas) {
        canvas.save();
        canvas.drawLine((mRectF.right + cr - j) * (mPro >= 0.2f ? mPro : 0.2f),
                mRectF.bottom + cr - j, mRectF.right + cr - j, mRectF.bottom + cr - j, paint);
        if (mPro > 0.5f) {
            canvas.drawArc(mRectF, 45, -((mPro - 0.5f) * 360 * 2), false, paint);
            canvas.drawLine(mRectF.right - j, mRectF.bottom - j,
                    mRectF.right + cr - j, mRectF.bottom + cr - j, paint);
        } else {
            canvas.drawLine(mRectF.right - j + cr * (1 - mPro), mRectF.bottom - j +
                    cr * (1 - mPro), mRectF.right + cr - j, mRectF.bottom + cr - j, paint);
        }
        canvas.restore();
        mRectF.left = cx - cr + 240 * (1 - mPro);
        mRectF.right = cx + cr + 240 * (1 - mPro);
        mRectF.top = cy - cr;
        mRectF.bottom = cy + cr;
    }

    private void drawStartAnimView(Paint paint, Canvas canvas) {
        canvas.save();
        if (mPro <= 0.5f) {
            canvas.drawArc(mRectF, 45, -(360 - 360 * 2 * (mPro == -1 ? 1 : mPro)), false, paint);
            canvas.drawLine(mRectF.right - j, mRectF.bottom - j,
                    mRectF.right + cr - j, mRectF.bottom + cr - j, paint);
        } else {
            canvas.drawLine(mRectF.right - j + cr * mPro, mRectF.bottom - j + cr * mPro,
                    mRectF.right + cr - j, mRectF.bottom + cr - j, paint);
        }

        canvas.drawLine((mRectF.right + cr - j) * (1 - mPro * .8f), mRectF.bottom + cr - j,
                mRectF.right + cr - j, mRectF.bottom + cr - j, paint);
        canvas.restore();

        mRectF.left = cx - cr + 240 * mPro;
        mRectF.right = cx + cr + 240 * mPro;
        mRectF.top = cy - cr;
        mRectF.bottom = cy + cr;
    }

    private void drawNormalView(Paint paint, Canvas canvas) {
        cr = getWidth() / 24;
        cx = getWidth() / 2;
        cy = getHeight() / 2;
        mRectF.left = cx - cr;
        mRectF.right = cx + cr;
        mRectF.top = cy - cr;
        mRectF.bottom = cy + cr;

        canvas.save();

        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);

        canvas.rotate(45, cx, cy);
        canvas.drawLine(cx + cr, cy, cx + cr * 2, cy, paint);
        canvas.drawArc(mRectF, 0, 360, false, paint);
        canvas.restore();
    }

    @Override
    public void startAnim() {
        if (mState == STATE_ANIM_START) return;
        mState = STATE_ANIM_START;
        startSearchViewAnim();
    }

    @Override
    public void recetAnim() {
        if (mState == STATE_ANIM_STOP) return;
        mState = STATE_ANIM_STOP;
        startSearchViewAnim();
    }
}
