package com.xgb.gamemenu;

import com.xgb.GameView;
import com.xgb.Global;
import com.xgb.graphics.TextRender;
import com.xgb.graphics.Util;
import com.xgb.views.BaseScreen;
import com.xgb.android.Bitmap;
import com.xgb.android.Canvas;

public class ScreenMenuProperties extends BaseScreen {

	private Bitmap mFrameBmp = Util.getFrameBitmap(77 - 39 + 1, 54 - 16 + 1);
	private String[] strs = {"状态", "穿戴"};
	private int mSelId = 0;
	
	@Override
	public void update(long delta) {
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(mFrameBmp, 39, 16, null);
		if (mSelId == 0) {
			TextRender.drawSelText(canvas, strs[0], 39 + 3, 16 + 3);
			TextRender.drawText(canvas, strs[1], 39 + 3, 16 + 3 + 16);
		} else if (mSelId == 1) {
			TextRender.drawText(canvas, strs[0], 39 + 3, 16 + 3);
			TextRender.drawSelText(canvas, strs[1], 39 + 3, 16 + 3 + 16);
		}
	}

	@Override
	public void onKeyDown(int key) {
		if (key == Global.KEY_UP || key == Global.KEY_DOWN) {
			mSelId = 1 - mSelId;
		}
	}

	@Override
	public void onKeyUp(int key) {
		if (key == Global.KEY_CANCEL) {
			GameView.getInstance().popScreen();
		} else if (key == Global.KEY_ENTER) {
			GameView.getInstance().popScreen();
			if (mSelId == 0) {
				GameView.getInstance().pushScreen(new ScreenActorState());
			} else {
				GameView.getInstance().pushScreen(new ScreenActorWearing());
			}
		}
	}

	@Override
	public boolean isPopup() {
		return true;
	}

}
