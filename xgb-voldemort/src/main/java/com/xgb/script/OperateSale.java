package com.xgb.script;

import com.xgb.GameView;
import com.xgb.Global;
import com.xgb.characters.Player;
import com.xgb.gamemenu.ScreenGoodsList;
import com.xgb.gamemenu.ScreenGoodsList.Mode;
import com.xgb.goods.BaseGoods;
import com.xgb.goods.GoodsDrama;
import com.xgb.graphics.TextRender;
import com.xgb.graphics.Util;
import com.xgb.views.BaseScreen;

import java.util.LinkedList;
import java.util.List;

import com.xgb.android.Bitmap;
import com.xgb.android.Canvas;

public class OperateSale extends Operate implements ScreenGoodsList.OnItemSelectedListener {
	
	public OperateSale() {
	}

	@Override
	public boolean process() {
		List<BaseGoods> list = new LinkedList<BaseGoods>();
		list.addAll(Player.sGoodsList.getGoodsList());
		list.addAll(Player.sGoodsList.getEquipList());
		GameView.getInstance().pushScreen(new ScreenGoodsList(list, this, Mode.Sale));
		return true;
	}

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

	@Override
	public void onItemSelected(BaseGoods goods) {
		if (goods instanceof GoodsDrama) {
			Util.showMessage("任务物品!", 1000);
		} else {
			mSaleScreen.init(goods);
			GameView.getInstance().pushScreen(mSaleScreen);
		}
	}

	private SaleGoodsScreen mSaleScreen = new SaleGoodsScreen();
	
	private class SaleGoodsScreen extends BaseScreen {
		
		private Bitmap bmpBg = Util.getFrameBitmap(136, 55);
		private BaseGoods goods;
		private int saleCnt;
		private int money;

		public void init(BaseGoods goods) {
			this.goods = goods;
			saleCnt = 0;
			money = Player.sMoney;
		}
		
		@Override
		public void update(long delta) {
		}
		
		@Override
		public void draw(Canvas canvas) {
			canvas.drawBitmap(bmpBg, 12, 21, null);
			TextRender.drawText(canvas, "金钱：" + money, 15, 24);
			TextRender.drawText(canvas, goods.getName(), 15, 40);
			TextRender.drawText(canvas, ": " + (goods.getGoodsNum() - saleCnt), 93, 40);
			TextRender.drawText(canvas, "卖出个数　：" + saleCnt, 15, 56);
		}
		
		@Override
		public void onKeyUp(int key) {
			if (key == Global.KEY_ENTER) {
				Player.sMoney = money;
				if (saleCnt > 0) {
					Player.sGoodsList.useGoodsNum(goods.getType(), goods.getIndex(), saleCnt);
				}
				GameView.getInstance().popScreen();
				// 重创物品选择界面，防止数量0还显示
				GameView.getInstance().popScreen();
				List<BaseGoods> list = new LinkedList<BaseGoods>();
				list.addAll(Player.sGoodsList.getGoodsList());
				list.addAll(Player.sGoodsList.getEquipList());
				GameView.getInstance().pushScreen(new ScreenGoodsList(list, OperateSale.this, Mode.Sale));
			} else if (key == Global.KEY_CANCEL) {
				GameView.getInstance().popScreen();
			}
		}
		
		@Override
		public void onKeyDown(int key) {
			if (key == Global.KEY_UP && saleCnt > 0) {
				--saleCnt;
				money -= goods.getSellPrice();
			} else if (key == Global.KEY_DOWN && goods.getGoodsNum() > saleCnt) {
				++saleCnt;
				money += goods.getSellPrice();
				if (money > 99999) {
					money = 99999;
				}
			}
		}

		@Override
		public boolean isPopup() {
			return true;
		}
	}
	
}
