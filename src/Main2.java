import java.io.*;
import java.security.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.*;
import org.apache.http.impl.client.*;
import org.apache.http.message.*;
import org.apache.http.protocol.*;
import org.apache.http.util.*;

public class Main2 {
	static void gao() throws InterruptedException, ClientProtocolException,
			IOException {

		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.BEST_MATCH);

		HttpGet get = new HttpGet(
				"http://m.qzone.com/infocenter?sid=AfgWOtwbDZr-Wc4tTX0pjjpR");
		HttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		String s = EntityUtils.toString(entity);
		// System.out.println(s);

		String sid = "AfgWOtwbDZr-Wc4tTX0pjjpR";
		int pre = 0;
		int k = s.indexOf("\"operation\"");
		int cnt = 0;

		while (k != -1) {
			int j = s.indexOf("\"isliked\":0", pre);
			int l = s.indexOf("\"like\":", pre);
			if (l < k && (j == -1 || j > k)) {
				pre = k;
				k = s.indexOf("\"operation\"", k + 10);
				cnt++;
				//System.out.println("FT1");
				continue;
			}
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			// System.out.println("FT2");

			String uin = s.substring(s.indexOf("\"5\":", k) + 5,
					s.indexOf("\"6\":", k) - 3);
			int tmp = s.indexOf("\"6\":", k) + 5;
			String cur = s.substring(tmp, s.indexOf("\"", tmp));
			k = s.indexOf("\"userinfo\"", tmp);
			tmp = s.indexOf("\"uin\"", k) + 7;
			String uid = s.substring(tmp, s.indexOf("\"", tmp));
			System.out.println(s.substring(k, k + 200));

			// System.out.println(uid);
			// System.out.println(uin);
			// System.out.println(cur);

			nvps.add(new BasicNameValuePair("opr_type", "like"));
			nvps.add(new BasicNameValuePair("action", "0"));
			nvps.add(new BasicNameValuePair("res_uin", uid));
			nvps.add(new BasicNameValuePair("res_type", "311"));
			nvps.add(new BasicNameValuePair("uin_key", uin));
			nvps.add(new BasicNameValuePair("cur_key", cur));
			nvps.add(new BasicNameValuePair("format", "json"));
			nvps.add(new BasicNameValuePair("sid", sid));

			String url = "http://m.qzone.com/praise/like";
			HttpPost httpost = new HttpPost(url);
			httpost.setHeader("Accept", "application/json");
			httpost.setHeader(
					"User-Agent",
					"Mozilla/5.0 (Linux; U; Android 2.3; en-us; SAMSUNG-SGH-I717 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
			httpost.setHeader("X-Requested-With", "XMLHttpRequest");

			httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
			response = httpClient.execute(httpost);
			entity = response.getEntity();
			String x = EntityUtils.toString(entity);
			System.out.println(x);
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			System.out.println(df.format(new Date()));// new Date()为获取当前系统时间

			httpost.releaseConnection();

			pre = k;
			k = s.indexOf("\"operation\"", k + 10);
			cnt++;
		}
		// System.out.println(cnt);

	}

	public static void main(String args[]) {
		int loop = 0;

		while (true) {
			try {
				gao();
				System.out.println("Loop " + (loop++) + " is finished...");
				Thread.sleep(5000);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
