import java.io.*;
import java.security.*;
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

public class Main {
	
	static void gao() throws ClientProtocolException, IOException {
		HttpClient httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(ClientPNames.COOKIE_POLICY,
				CookiePolicy.BEST_MATCH);
		String sid = "AfgWOtwbDZr-Wc4tTX0pjjpR";
		String url = "http://ish.z.qq.com/infocenter_v2.jsp?B_UID=402953842&amp;amp;sid="
				+ sid;

		HttpGet get = new HttpGet(url);
		HttpResponse response = httpClient.execute(get);
		HttpEntity entity = response.getEntity();
		String s = EntityUtils.toString(entity);
		System.out.println(s);

		int k = s.indexOf("赞");
		int cnt = 0;
		while (k != -1) {
			if (s.charAt(k - 1) == '>') {
				int j = k - 1;
				while (s.charAt(j) != '<')
					--j;
				url = s.substring(j + 9, k - 2);
				System.out.println(url);

				List<NameValuePair> nvps = new ArrayList<NameValuePair>();

				if (url.indexOf("curKey=h") == -1
						|| url.indexOf(".1&amp;uinKey") == -1
						|| url.indexOf("uinKey=htt") == -1
						|| url.indexOf(".1&amp;from=") == -1
						|| url.indexOf("user.qzone.qq.com/") == -1
						|| url.indexOf("/mood") == -1) {
					cnt++;
					k = s.indexOf("赞", k + 1);
					System.out.println(url);
					continue;
				}

				String cur = url.substring(url.indexOf("curKey=h") + 7,
						url.indexOf(".1&amp;uinKey"));
				String uin = url.substring(url.indexOf("uinKey=htt") + 7,
						url.indexOf(".1&amp;from="));
				String uid = url.substring(
						url.indexOf("user.qzone.qq.com/") + 18,
						url.indexOf("/mood"));

				nvps.add(new BasicNameValuePair("opr_type", "like"));
				nvps.add(new BasicNameValuePair("action", "0"));
				nvps.add(new BasicNameValuePair("res_uin", uid));
				nvps.add(new BasicNameValuePair("res_type", "311"));
				nvps.add(new BasicNameValuePair("uin_key", uin));
				nvps.add(new BasicNameValuePair("cur_key", cur));
				nvps.add(new BasicNameValuePair("format", "json"));
				nvps.add(new BasicNameValuePair("sid", sid));

				// System.out.println(uid);
				// System.out.println(uin);
				// System.out.println(cur);

				url = "http://m.qzone.com/praise/like";
				HttpPost httpost = new HttpPost(url);
				httpost.setHeader("Accept", "application/json");
				httpost.setHeader(
						"User-Agent",
						"Mozilla/5.0 (Linux; U; Android 2.3; en-us; SAMSUNG-SGH-I717 Build/GINGERBREAD) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
				httpost.setHeader("X-Requested-With", "XMLHttpRequest");

				httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
				response = httpClient.execute(httpost);
				entity = response.getEntity();
				s = EntityUtils.toString(entity);
				System.out.println(s);

				httpost.releaseConnection();
			}
			cnt++;
			k = s.indexOf("赞", k + 1);
		}
		System.out.println(cnt);

	}

	public static void main(String args[]) throws InterruptedException,
			ClientProtocolException, IOException {
		

		while (true) {
			gao();
			Thread.sleep(1000);
		}

	}

}
