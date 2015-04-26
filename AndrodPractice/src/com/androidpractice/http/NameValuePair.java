/*package com.androidpractice.http;

import java.util.ArrayList;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;

public class NameValuePair extends Activity {
	HttpPost request_ = new HttpPost();
	
	//nameValuePair란?
	ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();
	
	//설명
	
	
	 * NameValuePair is a special <Key, Value> pair which is used to represent parameters in http request, i.e. www.xxx.com?key=value.
	 *
	 * NameValuePair is an interface and is defined in apache http client, which is widely used in java to handle http operations. 
	 * A List<NameValuePair> is just a list of <key, value> pairs, and will be used as params in http post request.
	 * 
	 * 끝.
	 
	
	
	
	 * NameValuePair은 서버에 요청하는 쿼리 ex)www.xxx.com?key=value;   key=value값을 묶어준것!
	 * 
	 * Java에서 HashMap으로 처리했다면, Android에서는 NameValuePair을 제공해준다능! 쓰기 쉽다능!
	 * 요청 넘넘 쉽다 짱짱맨
	 
	String param1 = "지적이고 잘생기기까지한 조현권";
	String param2 = "search_type=이상형&"+"height_max=165cm&heignt_min=155cm";
	
	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	nameValuePairs.add(new BasicNameValuePair("user", param1));
	nameValuePairs.add(new BasicNameValuePair("target", param2));	
	
	HttpPost request = new HttpPost();
	
}
*/