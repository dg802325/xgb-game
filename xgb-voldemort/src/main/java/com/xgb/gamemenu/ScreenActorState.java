package com.xgb.gamemenu;

import java.util.List;

import com.xgb.android.Canvas;
import com.xgb.GameView;
import com.xgb.Global;
import com.xgb.characters.Player;
import com.xgb.graphics.Util;
import com.xgb.scene.ScreenMainGame;
import com.xgb.views.BaseScreen;

public class ScreenActorState extends BaseScreen {
	
	private int mPage = 0;
	
	private List<Player> mPlayerList = ScreenMainGame.instance.getPlayerList();
	
	private int mCurPlayer = 0;

	@Override
	public void update(long delta) {
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Global.COLOR_WHITE);
		
		int i = 0;
		while (i < mPlayerList.size()) {
			mPlayerList.get(i).drawHead(canvas, 10, 2 + 32 * i);
			++i;
		}
		
		if (mPlayerList.size() > 0) {
			mPlayerList.get(mCurPlayer).drawState(canvas, mPage);
			Util.drawTriangleCursor(canvas, 3, 10 + 32 * mCurPlayer);
		}
	}

	@Override
	public void onKeyDown(int key) {
		if (key == Global.KEY_PAGEDOWN || key == Global.KEY_PAGEUP) {
			mPage = 1 - mPage;
		} else if (key == Global.KEY_DOWN) {
			++mCurPlayer;
			if (mCurPlayer >= mPlayerList.size()) {
				mCurPlayer = 0;
			}
		} else if (key == Global.KEY_UP) {
			--mCurPlayer;
			if (mCurPlayer < 0) {
				mCurPlayer = mPlayerList.size() - 1;
			}
		}
	}

	@Override
	public void onKeyUp(int key) {
		if (key == Global.KEY_CANCEL) {
			GameView.getInstance().popScreen();
		}
	}

}
