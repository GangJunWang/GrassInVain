package bluetooth.inuker.com.grassinvain.common.widget.button;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

public class BaseButton extends Button {

	private boolean clickAble;// 是否能点击

	public BaseButton(Context context) {
		super(context);
	}

	public BaseButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		clickAble = attrs
				.getAttributeBooleanValue(
						"http://schemas.android.com/apk/res/android",
						"clickable", true);
//		initView();
	}

	public BaseButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	@Override
	public void setEnabled(boolean enabled) {
		clickAble = enabled;
		super.setEnabled(enabled);
		if (enabled) {
			this.setTextColor(Color.WHITE);
		}else {
			this.setTextColor(0xffe2e2e2);
		}
//		initView();
	}

	private void initView() {
		if (clickAble) {// 能点击
			this.setPadding(0, 0, 0, 2);
		} else {// 不能点击
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (clickAble) {// 能点击
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
//				this.setPadding(2, 2, 0, 0);
				invalidate();
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
//				this.setPadding(0, 0, 0, 0);
				invalidate();
			}
		} else {// 不能点击
			// this.setBackgroundResource(R.drawable.btn_disable);
		}

		if (!clickAble) {// 不能点击，则不响应
			return false;
		} else {
			return super.onTouchEvent(event);
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//将drawableLeft居中显示
		Drawable[] drawables = getCompoundDrawables();
		if (drawables != null) {
			Drawable drawableLeft = drawables[0];
			if (drawableLeft != null) {
				float textWidth = getPaint().measureText(getText().toString());
				int drawablePadding = getCompoundDrawablePadding();
				int drawableWidth = drawableLeft.getIntrinsicWidth();
				float bodyWidth = textWidth + drawableWidth + drawablePadding;
				setPadding(0, 0, (int)(getWidth() - bodyWidth), 0);
				canvas.translate((getWidth() - bodyWidth) / 2, 0);
			}
		}
		super.onDraw(canvas);
	}
}
