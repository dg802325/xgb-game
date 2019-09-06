package com.xgb.views;

import com.xgb.android.Canvas;

public abstract class BaseScreen {

	public abstract void update(long delta);
	
	public abstract void draw(Canvas canvas);
	
	public abstract void onKeyDown(int key);
	
	public abstract void onKeyUp(int key);
	
	public boolean isPopup() {
		return false;
	}
}
