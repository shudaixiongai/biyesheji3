package com.example.android_activity.uitity;

import com.example.android_activity.dao.InaccountManager;
import com.example.android_activity.model.Tb_inaccount;

public final class Uitits {

	public Uitits() {
		// TODO Auto-generated constructor stub
	}

	public Tb_inaccount getDataFromDb(long id) {
		InaccountManager inaccountManager = new InaccountManager();

		return null;

	}

	/***
	 * 获取spinner所选着的ＩＤ号
	 * 
	 * @param type
	 * @param types
	 * @return
	 */
	public static int TypeSelectId(String type, String[] types) {

		int type_id = 0;
		for (int i = 0; i < types.length; i++) {
			if (type.equals(types[i])) {
				type_id = i;

			}
		}

		return type_id;

	}
/***
 * 判断用户名，密码是否为空
 * @param userName
 * @param userPassword
 * @return
 */
	public static boolean isEmpty(String userName, String userPassword) {

		if (userName.equals("") || userPassword.equals("")) {
			return true;
		}

		return false;

	}
	
	
		
	

}
