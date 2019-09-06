package com.xgb.graphics;

import com.xgb.lib.DatLib;
import com.xgb.lib.ResImage;
import com.xgb.android.Canvas;

public class Tiles {
	/**
	 * 地图块的宽
	 */
	public static final int WIDTH = 16;
	/**
	 * 地图块的高
	 */
	public static final int HEIGHT = 16;

	private ResImage mTileRes;

	public Tiles(int index) {
		mTileRes = (ResImage) DatLib.getInstance().getRes(DatLib.RES_TIL, 1,
				index);
	}

	/**
	 * 
	 * @param canvas
	 * @param x
	 * @param y
	 * @param i
	 *            图块的序号
	 */
	public void draw(Canvas canvas, int x, int y, int i) {
		mTileRes.draw(canvas, i + 1, x, y);
	}
}
