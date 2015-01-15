import java.io.*;
import java.security.*;
import java.util.*;

import javax.script.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;
import org.apache.http.protocol.*;
import org.apache.http.util.*;





public class Main {
	static HttpClient httpClient = new DefaultHttpClient();
	static void gao() throws ClientProtocolException, IOException{
	
			/*
			url = s.substring(s.indexOf("moved <a href=")+15,  s.indexOf(">here</a>")-1 );
			get = new HttpGet(url);
			response = httpClient.execute(get);
			entity = response.getEntity();
			 s = EntityUtils.toString(entity);
			System.out.println(s);
			
			url = s.substring(s.indexOf("ontimer=")+9,  s.indexOf("\"", s.indexOf("ontimer=")+9) );

			get = new HttpGet(url);
			response = httpClient.execute(get);
			entity = response.getEntity();
			s = EntityUtils.toString(entity);
			System.out.println(s);ATz4iMKBYaFSutDT04MxNI6m";
			
			url = s.substring(s.indexOf("ontimer=")+9,  s.indexOf("\"", s.indexOf("ontimer=")+9) );
			*/
			
			String sid = "AfgWOtwbDZr-Wc4tTX0pjjpR";
			String url = "http://ish.z.qq.com/infocenter_v2.jsp?B_UID=402953842&amp;amp;sid="+sid;
			
			
			HttpGet get = new HttpGet(url);
		//	get.setHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.3; en-us; SAMSUNG-SGH-I717 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
			
			HttpResponse response = httpClient.execute(get);
			HttpEntity entity = response.getEntity();
			String s = EntityUtils.toString(entity);
			System.out.println(s);
			
			int k = s.indexOf("赞");
			int cnt =0;
			while (k!=-1){
				if (s.charAt(k-1)=='>'){
					int j = k-1;
					while (s.charAt(j)!='<') --j;
					url = s.substring(j+9, k-2);
					System.out.println(url);
					
					
					List <NameValuePair> nvps = new ArrayList<NameValuePair>();
					
					if (url.indexOf("curKey=h")==-1 || url.indexOf(".1&amp;uinKey")==-1 || url.indexOf("uinKey=htt")==-1 ||
							url.indexOf(".1&amp;from=")==-1 ||  url.indexOf("user.qzone.qq.com/")==-1 || url.indexOf("/mood")==-1){
							cnt++;
							k = s.indexOf("赞", k+1);
							continue;
					}
							
					String cur = url.substring(url.indexOf("curKey=h")+7, url.indexOf(".1&amp;uinKey") );
					String uin = url.substring(url.indexOf("uinKey=htt")+7, url.indexOf(".1&amp;from="));
					String uid = url.substring(url.indexOf("user.qzone.qq.com/")+18 , url.indexOf("/mood"));

			
					nvps.add(new BasicNameValuePair("opr_type", "like"));
					nvps.add(new BasicNameValuePair("action", "0"));
					nvps.add(new BasicNameValuePair("res_uin", uid));
					nvps.add(new BasicNameValuePair("res_type", "311"));
					nvps.add(new BasicNameValuePair("uin_key", uin));
					nvps.add(new BasicNameValuePair("cur_key", cur));
					nvps.add(new BasicNameValuePair("format", "json"));
					nvps.add(new BasicNameValuePair("sid", sid));
					
					System.out.println(uid);					
					System.out.println(uin);
					System.out.println(cur);
					
					url = "http://m.qzone.com/praise/like";
					HttpPost httpost = new HttpPost(url);
					httpost.setHeader("Accept", "application/json");
					httpost.setHeader("User-Agent", "Mozilla/5.0 (Linux; U; Android 2.3; en-us; SAMSUNG-SGH-I717 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
					httpost.setHeader("X-Requested-With", "XMLHttpRequest");
					
					httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
					response = httpClient.execute(httpost);
					entity = response.getEntity();
					s = EntityUtils.toString(entity);
					System.out.println(s);					

					httpost.releaseConnection();
				}
				cnt++;
				k = s.indexOf("赞", k+1);
			}
			System.out.println(cnt);
			
			
			
			

	}

	static void getSid(){
		
		HttpGet get = new HttpGet("http://pt.3g.qq.com/s?aid=nLoginqz&amp;sid=AaKPKFGqXB_Ey4o4KZ6D_JVp&amp;KqqWap_Act=3&amp;g_ut=1&amp;go_url=http%3A%2F%2Fz.qq.com%2Findex.jsp");		
		HttpResponse response;
		try {
			response = httpClient.execute(get);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String s = EntityUtils.toString(entity);
			//System.out.println(s);
			
			String url = s.substring(s.indexOf("go href=")+9, s.indexOf(" method=")-1);
			String sid = s.substring(s.indexOf("sid\" value=")+12,     s.indexOf("sid\" value=")+12+24);
			System.out.println(sid);
			
			HttpPost httpost = new HttpPost(url);
			List <NameValuePair> nvps = new ArrayList<NameValuePair>();
			
			string qq="";		//fill your qq number
			string pwd="";		//fill your qq password

			nvps.add(new BasicNameValuePair("sid", sid));
			nvps.add(new BasicNameValuePair("qq", qq));
			nvps.add(new BasicNameValuePair("pwd", pwd));
			nvps.add(new BasicNameValuePair("sidtype", "1"));
			nvps.add(new BasicNameValuePair("hiddenPwd", "true"));
			nvps.add(new BasicNameValuePair("aid", "nLoginqz"));
			nvps.add(new BasicNameValuePair("go_url", "http://z.qq.com/index.jsp"));
			nvps.add(new BasicNameValuePair("login_url", "http://pt.3g.qq.com/s?aid=nLoginqz&amp;KqqWap_Act=3&amp;sid="+sid));
			
			
			
			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			response = httpClient.execute(httpost);
			entity = response.getEntity();
			 s = EntityUtils.toString(entity);
			System.out.println(s);
			sid = s.substring(s.indexOf("sid=")+4,     s.indexOf(">here</a>")-1);					
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	
	public static void main(String args[]) throws InterruptedException, ClientProtocolException, IOException{
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BEST_MATCH);
		
		while (true){
			gao();
			Thread.sleep(1000);
		}
		 
	//	gao2();

	}
	
	
	static void  gao2(){

		try {
			String url = "http://check.ptlogin2.qzone.com/check?pt_tea=1&uin=402953842&appid=549000929&ptlang=2052&r=0.8872456572766945";
			HttpGet get = new HttpGet(url);
			HttpResponse response;
			response = httpClient.execute(get);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String s = EntityUtils.toString(entity, "UTF-8");
			
			
			System.out.println(s);

			
		} catch (ClientProtocolException e) {
			System.out.println("protocol error");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO error");
			e.printStackTrace();
		}
	}	
}

