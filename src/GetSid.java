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


public class GetSid {
	
static String  getSid(){
		String sid="";
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet get = new HttpGet("http://pt.3g.qq.com/s?aid=nLoginqz&amp;sid=AaKPKFGqXB_Ey4o4KZ6D_JVp&amp;KqqWap_Act=3&amp;g_ut=1&amp;go_url=http%3A%2F%2Fz.qq.com%2Findex.jsp");		
		HttpResponse response;
		try {
			response = httpClient.execute(get);
			System.out.println(response.getStatusLine());
			HttpEntity entity = response.getEntity();
			String s = EntityUtils.toString(entity);
			//System.out.println(s);
			
			String url = s.substring(s.indexOf("go href=")+9, s.indexOf(" method=")-1);
			sid = s.substring(s.indexOf("sid\" value=")+12,     s.indexOf("sid\" value=")+12+24);
			System.out.println(sid);
			
			HttpPost httpost = new HttpPost(url);
			List <NameValuePair> nvps = new ArrayList<NameValuePair>();
			
			String qq="402953842";		//fill your qq number
			String pwd="Alicalcg";		//fill your qq password

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
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			return sid;
		}

	}
}
