package com.xgb.combat.actions;

import com.xgb.characters.FightingCharacter;

import java.util.List;

import com.xgb.android.Canvas;

public class ActionDefend extends ActionSingleTarget {
	
	public ActionDefend(FightingCharacter fc) {
		super(fc, null);
	}

	@Override
	public void preproccess() {
	}

	@Override
	public void postExecute() {
	}

	@Override
	public boolean isTargetAlive() {
		return true;
	}
	
	@Override
	public boolean isTargetsMoreThanOne() {
		return false;
	}

	@Override
	public boolean targetIsMonster() {
		return true;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return super.getPriority();
	}

	@Override
	public boolean update(long delta) {
		return false;
	}

	@Override
	public void draw(Canvas canvas) {
	}

}
