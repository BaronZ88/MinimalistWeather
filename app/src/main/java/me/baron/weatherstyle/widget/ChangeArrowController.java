package me.baron.weatherstyle.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * @author baronzhang (baron[dot]zhanglei[at]gmail[dot]com)
 *         16/4/5
 */
public class ChangeArrowController extends BaseController {

    private float cx, cy, cr, scr, scx, scy;
    private RectF mRectF, mOutRectF;
    private float sign = 0.707f;

    public ChangeArrowController() {
        mRectF = new RectF();
        mOutRectF = new RectF();
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        String mColor = "#E91E63";
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
        if (mPro <= 0.25) {
            canvas.drawLine(cx - cr, scy - scr, scx, scy - scr, paint);
            canvas.drawLine(cx - cr, scy - scr, cx - cr + scr * (0.25f - mPro),
                    scy - scr - scr * (0.25f - mPro), paint);
            canvas.drawLine(cx - cr, scy - scr, cx - cr + scr * (0.25f - mPro),
                    scy - scr + scr * (0.25f - mPro), paint);
        } else if (mPro > 0.25 && mPro <= 0.5f) {
            canvas.drawArc(mRectF, -90, 180 * (mPro - 0.25f) * 4, false, paint);
            canvas.drawLine(cx - cr + (scx - cx + cr) * (mPro - 0.25f) * 4, scy - scr,
                    scx, scy - scr, paint);
        } else {
            canvas.drawLine(cx + cr * sign + cr * sign * (1 - (mPro - 0.5f) * 2),
                    cy + cr * sign + cr * sign * (1 - (mPro - 0.5f) * 2),
                    cx + cr * 2 * sign, cy + cr * 2 * sign, paint);
            canvas.drawArc(mOutRectF, 45, 720 * (mPro - 0.5f), false, paint);
        }
        canvas.restore();
    }

    private void drawStartAnimView(Paint paint, Canvas canvas) {
        canvas.save();
        if (mPro <= 0.75) {
            canvas.drawArc(mOutRectF, 45, 360 * (1 - mPro / 0.75f), false, paint);
        }
        if (mPro <= 0.25) {
            canvas.drawLine(cx + cr * sign + cr * sign * mPro * 4, cy + cr * sign + cr * sign
                    * mPro * 4, cx + cr * 2 * sign, cy + cr * 2 * sign, paint);
            canvas.drawArc(mRectF, 90, -180 * mPro * 4, false, paint);
        } else if (mPro > 0.25 && mPro <= 0.5f) {
            canvas.drawArc(mRectF, -90, 180 * (1 - (mPro - 0.25f) * 4), false, paint);
            canvas.drawLine(cx - cr * (mPro - 0.25f) * 4, scy - scr, scx, scy - scr, paint);
        } else if (mPro > 0.5f && mPro < 0.75f) {
            canvas.drawLine(cx - cr * (mPro - 0.5f) * 4, scy - scr, scx - 20, scy - scr, paint);
        } else {
            canvas.drawLine(cx - cr, scy - scr, scx - 20, scy - scr, paint);
            canvas.drawLine(cx - cr, scy - scr, cx - cr + scr * mPro, scy - scr - scr * mPro, paint);
            canvas.drawLine(cx - cr, scy - scr, cx - cr + scr * mPro, scy - scr + scr * mPro, paint);
        }
        canvas.restore();
    }

    private void drawNormalView(Paint paint, Canvas canvas) {
        cr = getWidth() / 10;
        scr = getWidth() / 15;
        cx = getWidth() / 2;
        cy = getHeight() / 2;
        scx = cx + cr * 2 * sign;
        scy = cy + (cr * 2 * sign - scr);
        mRectF.left = scx - scr;
        mRectF.right = scx + scr;
        mRectF.top = scy - scr;
        mRectF.bottom = scy + scr;
        mOutRectF.left = cx - cr;
        mOutRectF.right = cx + cr;
        mOutRectF.top = cy - cr;
        mOutRectF.bottom = cy + cr;

        paint.reset();
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.save();
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(cx, cy, cr, paint);
        canvas.drawLine(cx + cr * sign, cy + cr * sign, scx, cy + cr * 2 * sign, paint);
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
