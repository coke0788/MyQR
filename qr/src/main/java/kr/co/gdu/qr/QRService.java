package kr.co.gdu.qr;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class QRService {
	public String getUserName() {
		return "남궁혜영";
	}
	
	public String getSystemInfo() {
		StringBuffer sf = new StringBuffer();
		String s = System.getProperty("os.name");  //지정된 이름(os.name)의 속성을 검색
		sf.append(s);
		s = System.getProperty("os.version"); //지정된 이름(os.version)의 속성을 검색
		sf.append(","+s);
		return sf.toString();
	}
	
	public String  getNetworkInfo() throws UnknownHostException {
		StringBuffer sf = new StringBuffer();
		InetAddress address = InetAddress.getLocalHost(); //InetAddress : IP주소 관련 클래스. 네트워크 정보를 받아온다.
		String s = address.getHostName(); //컴퓨터(IP주소의) 사용자 이름.
		sf.append(s);
		s = address.getHostAddress(); //IP 주소.
		sf.append(","+s);
		return sf.toString();
	}
}