package com.xgb;

import com.xgb.common.Utils;

import java.io.IOException;

import javax.swing.JFrame;


/**
 * 汤姆猫
 * @author G
 *
 */
public class MyTom {

	public static void main(String[] args) throws Exception {
		
		JFrame frame = new JFrame("汤姆猫");
		Utils.initFrame(frame, 430, 700);
		
		MyTomPanel panel = new MyTomPanel();
		frame.add(panel);
		frame.addMouseListener(panel);
		
		Thread thread = new Thread(panel);
		thread.start();
		
		
	}
}
