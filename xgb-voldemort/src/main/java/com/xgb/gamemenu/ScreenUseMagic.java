package com.xgb.gamemenu;

import com.xgb.GameView;
import com.xgb.Global;
import com.xgb.characters.Player;
import com.xgb.graphics.TextRender;
import com.xgb.magic.MagicRestore;
import com.xgb.scene.ScreenMainGame;
import com.xgb.views.BaseScreen;
import com.xgb.android.Canvas;
import com.xgb.android.Rect;

public class ScreenUseMagic extends BaseScreen {
	
	private static Rect sNameRect = new Rect(4, 4, 37, 96);
	private MagicRestore mMagic;
	
	Player mScr;
	
	private int mCurPage = 0;
	private int mCurActor = 0;
	
	public ScreenUseMagic(MagicRestore magic, Player scr) {
		mMagic = magic;
		mScr = scr;
	}

	@Override
	public void update(long delta) {
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawColor(Global.COLOR_WHITE);
		TextRender.drawText(canvas, mMagic.getMagicName(), 0, sNameRect);
		Player actor = ScreenMainGame.sPlayerList.get(mCurActor);
		actor.drawState(canvas, mCurPage);
		actor.drawHead(canvas, 5, 60);
	}

	@Override
	public void onKeyDown(int key) {
		if (key == Global.KEY_RIGHT && mCurActor < ScreenMainGame.sPlayerList.size() - 1) {
			++mCurActor;
		} else if (key == Global.KEY_LEFT && mCurActor > 0) {
			--mCurActor;
		} else if (key == Global.KEY_PAGEDOWN || key == Global.KEY_PAGEUP) {
			mCurPage = 1 - mCurPage;
		}
	}

	@Override
	public void onKeyUp(int key) {
		if (key == Global.KEY_CANCEL) {
			GameView.getInstance().popScreen();
		} else if (key == Global.KEY_ENTER) {
			mMagic.use(mScr, ScreenMainGame.sPlayerList.get(mCurActor));
			GameView.getInstance().popScreen();
		}
	}

}
