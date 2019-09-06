package com.xgb.script;

import com.xgb.android.Canvas;

public abstract class OperateAdapter extends Operate{

	@Override
	public boolean update(long delta) {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
	}

	@Override
	public void onKeyDown(int key) {
	}

	@Override
	public void onKeyUp(int key) {
	}

}
