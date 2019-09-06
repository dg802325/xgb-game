package com.xgb.combat.actions;

import com.xgb.characters.FightingCharacter;
import com.xgb.characters.Player;
import com.xgb.combat.anim.RaiseAnimation;
import com.xgb.goods.BaseGoods;
import com.xgb.goods.GoodsMedicine;
import com.xgb.graphics.TextRender;
import com.xgb.lib.DatLib;
import com.xgb.lib.ResSrs;
import com.xgb.magic.BaseMagic;

import java.util.List;

import com.xgb.android.Canvas;

public class ActionUseItemOne extends ActionSingleTarget {

	private static final int STATE_PRE = 1; // 起手动画
	private static final int STATE_ANI = 2; // 魔法动画
	private static final int STATE_AFT = 3; // 伤害动画
	
	private int mState = 1;
	
	ResSrs mAni;
	
	int mAnix, mAniy;
	
	int ox, oy;

	BaseGoods goods;

	public ActionUseItemOne(FightingCharacter attacker, FightingCharacter target, BaseGoods g) {
		super(attacker, target);
		goods = g;
	}

	@Override
	public void preproccess() {
		// TODO 记下伤害值、异常状态
		int hp = 0;
		if (goods instanceof GoodsMedicine) {
			mAni = ((GoodsMedicine)goods).getAni();
			hp = mTarget.getHP();
			((GoodsMedicine)goods).eat((Player)mTarget);
			hp = mTarget.getHP() - hp;
		} else {
			mAni = (ResSrs)DatLib.GetRes(DatLib.RES_SRS, 2, 1);
		}
		mAni.startAni();
		mAni.setIteratorNum(2);
		mAnix = mTarget.getCombatX();
		mAniy = mTarget.getCombatY();
		mRaiseAni = new RaiseAnimation(mTarget.getCombatX(), mTarget.getCombatTop(), hp, 0);
	}

	@Override
	public boolean update(long delta) {
		super.update(delta);
		switch (mState) {
		case STATE_PRE:
			if (mCurrentFrame < 10) {
				if (mAttacker instanceof Player) {
					mAttacker.getFightingSprite().setCurrentFrame(
						mCurrentFrame * 3 / 10 + 6);
				} else {
					mAttacker.setCombatPos(ox + 2, oy + 2);
				}
			} else {
				mState = STATE_ANI;
			}
			break;

		case STATE_ANI:
			if (!mAni.update(delta)) { // 魔法动画完成
				mState = STATE_AFT;
				if (mAttacker instanceof Player) {
					((Player)mAttacker).setFrameByState();
				} else {
					mAttacker.getFightingSprite().move(-2, -2);
				}
			}
			break;
			
		case STATE_AFT:
			return mRaiseAni.update(delta);
//			break;
		}
		return true;
	}

	@Override
	public void draw(Canvas canvas) {
		if (mState == STATE_ANI) {
			mAni.drawAbsolutely(canvas, mAnix, mAniy);
		} else if (mState == STATE_AFT) {
			mRaiseAni.draw(canvas);
		}
	}

}
