package com.example.graduatestudent.test;

public class HttpsCaTest {

    public static void main(String[] args) throws Exception {
//	String keystoreFile = "D:\\tomcat.keystore";
//    	String keystorePass = "ldysjhj";
////    	设置可通过ip地址访问https请求
//    	HttpsURLConnection.setDefaultHostnameVerifier(new NullHostNameVerifier());
//    	// 创建SSLContext对象，并使用我们指定的信任管理器初始化
//    	TrustManager[] tm = { new MyX509TrustManager(keystoreFile,keystorePass) };
//    	SSLContext sslContext = SSLContext.getInstance("TLS");
//    	sslContext.init(null, tm, new java.security.SecureRandom());
//    	// 从上述SSLContext对象中得到SSLSocketFactory对象
//    	SSLSocketFactory ssf = sslContext.getSocketFactory();
//    	String urlStr = "https://api.qz100.com/api-cpp/major/schools?school_name=&page=1&limit=10&sort_type=1&school_type=-1&is_edu=-1&is_local=-1&is_center=-1";
//    	URL url = new URL(urlStr);
//    	HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
//    	con.setSSLSocketFactory(ssf);
//    	con.setRequestMethod("POST"); // 设置以POST方式提交数据
//    	con.setDoInput(true); // 打开输入流，以便从服务器获取数据
//    	con.setDoOutput(true);// 打开输出流，以便向服务器提交数据
//    	//设置发送参数
//        String param = "sfmc=测试";
//        PrintWriter out = new PrintWriter(new OutputStreamWriter(con.getOutputStream(),"UTF-8"));
//        out.print(param);
//        out.flush();
//        out.close();
//        //读取请求返回值
//	InputStreamReader in = new InputStreamReader(con.getInputStream(),"UTF-8");
//	BufferedReader bfreader = new BufferedReader(in);
//	String result = "";
//	String line = "";
//	while ((line = bfreader.readLine()) != null) {
//	    result += line;
//	}
//	System.out.println("result:"+result);
    }

}
