package com.fresh.compass;


import com.sixth.adwoad.AdListener;
import com.sixth.adwoad.AdwoAdView;
import com.sixth.adwoad.ErrorCode;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * @author �������� troyjie@yahoo.cn
 * @version 1.0
 */
public class Compass extends Activity implements SensorEventListener,AdListener {

	public static RelativeLayout layout;
	static AdwoAdView adView = null;
	String Adwo_PID = "937ed3ab070946858a33e27285f70642";
	LayoutParams params = null;
	private String LOG_TAG = "fresh";
	
	

	ImageView image; // ָ����ͼƬ
	float currentDegree = 0f; // ָ����ͼƬת���ĽǶ�

	SensorManager mSensorManager; // ������

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		layout = (RelativeLayout) findViewById(R.id.layoutbanner);
		
		
		params = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//		�������ù����������Ļ��ʱ��������ڸ������м�
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		// ʵ����������
		adView = new AdwoAdView(Compass.this, Adwo_PID,false, 20);
		
		adView.setListener(Compass.this);
		// �ѹ����������沼��
		layout.addView(adView, params);
		
		

		image = (ImageView) findViewById(R.id.znzImage);
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE); // ��ȡ�������
	}

	@Override
	protected void onResume() {
		super.onResume();
		// ע�������
		mSensorManager.registerListener(this,
				mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
				SensorManager.SENSOR_DELAY_GAME);
	}

	// ȡ��ע��
	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(this);
		super.onPause();

	}

	@Override
	protected void onStop() {
		mSensorManager.unregisterListener(this);
		super.onStop();

	}

	// ������ֵ�ı�
	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	// ���ȸı�
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		// ��ȡ����event�Ĵ���������
		int sensorType = event.sensor.getType();

		switch (sensorType) {
		case Sensor.TYPE_ORIENTATION:
			float degree = event.values[0]; // ��ȡzת���ĽǶ�
			// ������ת����
			RotateAnimation ra = new RotateAnimation(currentDegree, -degree,
					Animation.RELATIVE_TO_SELF, 0.5f,
					Animation.RELATIVE_TO_SELF, 0.5f);
			ra.setDuration(100);// ��������ʱ��
			image.startAnimation(ra);
			currentDegree = -degree;
			break;

		}
	}

	@Override
	public void onDismissScreen() {
		Log.e(LOG_TAG, "onDismissScreen");
	}

	@Override
	public void onFailedToReceiveAd(View adView, ErrorCode errorCode) {
		Log.e(LOG_TAG, "onFailedToReceiveAd");
	}

	@Override
	public void onPresentScreen() {
		Log.e(LOG_TAG, "onPresentScreen");
	}

	@Override
	public void onReceiveAd(Object arg0) {
		Log.e(LOG_TAG, "onReceiveAd");
	}
}