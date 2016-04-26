package com.finance_drawerlayout;


import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Scroller;

import com.zhy.demo_zhy_17_drawerlayout.R;
/**
 * ScrollerӦ��
 * @author Ra
 * blog : http://blog.csdn.net/vipzjyno1/
 */
public class LoginView extends RelativeLayout {
	/** Scroller �϶��� */
	private Scroller mScroller;
	/** ��Ļ�߶�  */
	private int mScreenHeigh = 0;
	/** ��Ļ���  */
	private int mScreenWidth = 0;
	/** ���ʱ��Y������*/
	private int downY = 0;
	/** �϶�ʱ��Y������*/
	private int moveY = 0;
	/** �϶�ʱ��Y�ķ������*/
	private int scrollY = 0;
	/** �ɿ�ʱ��Y������*/
	private int upY = 0;
	/** �Ƿ����ƶ�*/
	private Boolean isMoving = false;
	/** ���ֵĸ߶�*/
	private int viewHeight = 0;
	/** �Ƿ��*/	
	public boolean isShow = false;
	/** �Ƿ�����϶�*/	
	public boolean mEnabled = true;
	/** ��������Ƿ�رոý���*/	
	public boolean mOutsideTouchable = true;
	/** ��������ʱ�� */
	private int mDuration = 800;
	private final static String TAG = "LoginView";
	public LoginView(Context context) {
		super(context);
		init(context);
	}

	public LoginView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public LoginView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		setDescendantFocusability(FOCUS_AFTER_DESCENDANTS);
		setFocusable(true);
		mScroller = new Scroller(context);
		mScreenHeigh = BaseTools.getWindowHeigh(context);
		mScreenWidth = BaseTools.getWindowWidth(context);
		// �������ó�͸��
		this.setBackgroundColor(Color.argb(0, 0, 0, 0));
		final View view = LayoutInflater.from(context).inflate(R.layout.view_login,null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);// �������������������Ĳ��ֵ�MATCH_PARENT�Ͳ�֪�����Ƕ���
		addView(view, params);// ViewGroup�Ĵ�С��
		// �������ó�͸��
		this.setBackgroundColor(Color.argb(0, 0, 0, 0));
		view.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				viewHeight = view.getHeight();
			}
		});
		LoginView.this.scrollTo(0, mScreenHeigh);
		ImageView btn_close = (ImageView)view.findViewById(R.id.btn_close);
		btn_close.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dismiss();
			}
		});
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		if(!mEnabled){
			return false;
		}
		return super.onInterceptTouchEvent(ev);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = (int) event.getY();
			Log.d(TAG, "downY = " + downY);
			//�����ȫ��ʾ��ʱ���ò��ֵõ������������������ʾ�������¼������أ����´���
			if(isShow){
				return true;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			moveY = (int) event.getY();
			scrollY = moveY - downY;
			//���»���
			if (scrollY > 0) {
				if(isShow){
					scrollTo(0, -Math.abs(scrollY));
				}
			}else{
				if(mScreenHeigh - this.getTop() <= viewHeight && !isShow){
					scrollTo(0, Math.abs(viewHeight - scrollY));
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			upY = (int) event.getY();
			if(isShow){
				if( this.getScrollY() <= -(viewHeight /2)){
					startMoveAnim(this.getScrollY(),-(viewHeight - this.getScrollY()), mDuration);
					isShow = false;
					Log.d("isShow", "false");
				} else {
					startMoveAnim(this.getScrollY(), -this.getScrollY(), mDuration);
					isShow = true;
					Log.d("isShow", "true");
				}
			}
			Log.d("this.getScrollY()", ""+this.getScrollY());
			changed();
			break;
		case MotionEvent.ACTION_OUTSIDE:
			Log.d(TAG, "ACTION_OUTSIDE");
			break;
		default:
			break;
		}
		return super.onTouchEvent(event);
	}
	
	/**
	 * �϶�����
	 * @param startY  
	 * @param dy  �ƶ���ĳ���Y�������
	 * @param duration ʱ��
	 */
	public void startMoveAnim(int startY, int dy, int duration) {
		isMoving = true;
		mScroller.startScroll(0, startY, 0, dy, duration);
		invalidate();//֪ͨUI�̵߳ĸ���
	}
	
	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
			// ���½���
			postInvalidate();
			isMoving = true;
		} else {
			isMoving = false;
		}
		super.computeScroll();
	}
	
	/** ������� */
	public void show(){
		if(!isShow && !isMoving){
			LoginView.this.startMoveAnim(-viewHeight,   viewHeight, mDuration);
			isShow = true;
			Log.d("isShow", "true");
			changed();
		}
	}
	
	/** �رս��� */
	public void dismiss(){
		if(isShow && !isMoving){
			LoginView.this.startMoveAnim(0, -viewHeight, mDuration);
			isShow = false;
			Log.d("isShow", "false");
			changed();
		}
	}
	
	/** �Ƿ�� */
	public boolean isShow(){
		return isShow;
	}
	
	/** ��ȡ�Ƿ�����϶�*/
	public boolean isSlidingEnabled() {
		return mEnabled;
	}
	
	/** �����Ƿ�����϶�*/
	public void setSlidingEnabled(boolean enabled) {
		mEnabled = enabled;
	}
	
	/**
	 * ���ü����ӿڣ�ʵ�����ֲ�Ч��
	 */
	public void setOnStatusListener(onStatusListener listener){
		this.statusListener = listener;
	}
	
    public void setOutsideTouchable(boolean touchable) {
        mOutsideTouchable = touchable;
    }
	/**
	 * ��ʾ״̬�����ı�ʱ��ִ�лص����
	 */
	public void changed(){
		if(statusListener != null){
			if(isShow){
				statusListener.onShow();
			}else{
				statusListener.onDismiss();
			}
		}
	}
	
	/** �����ӿ�*/
	public onStatusListener statusListener;
	
	/**
	 * �����ӿڣ������������������仯״̬
	 */
	public interface onStatusListener{
		/**  ����״̬  */
		public void onShow();
		/**  �ر�״̬  */
		public void onDismiss();
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
	}
}
