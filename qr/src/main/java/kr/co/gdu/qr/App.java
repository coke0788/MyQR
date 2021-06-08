package kr.co.gdu.qr;

import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.BufferCapabilities.FlipContents;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class App extends JFrame{
	static Logger log = LoggerFactory.getLogger(App.class);
    public static void main( String[] args ) throws WriterException, IOException{
        log.info("Hello");
        
        QRService qrService = new QRService();
        String userName = qrService.getUserName();
        String systemInfo = qrService.getSystemInfo();
        String networkInfo = qrService.getNetworkInfo();
        
        //QR 코드 한글 인코딩 코드. --> userName(String) 을 바이트코드로 인코딩.
        //파생 클래스에서 재정의되면 지정한 문자열의 모든 문자를 바이트 시퀀스로 인코딩합니다.
        userName = new String(userName.getBytes("UTF-8"),"ISO-8859-1");
        
        //인코딩 후 스트링버퍼에 저장. 이제 한글이 나온다!
        StringBuffer contents = new StringBuffer();
        //출력된? 저장된? 문자들 합치기
        contents.append(userName);
        log.info(userName);
        contents.append(","+systemInfo);
        log.info(systemInfo);
        contents.append(","+networkInfo);
        log.info(networkInfo);
        
        //QR 생성
        QRCodeWriter qrWriter = new QRCodeWriter();
        //encode(콘텐츠(데이터), 어떻게 생겼는지, 가로사이즈(픽셀), 세로사이즈(픽셀))
        BitMatrix matrix = qrWriter.encode(contents.toString(), BarcodeFormat.QR_CODE , 300, 300);
        // QR 설정 --> 보통 색상 MatrixToImageConfig(QR색상, 배경색상)
        MatrixToImageConfig config = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);
        // 위 설정 ㅣ용해서 이미지 생성
        BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix, config);
        // QR 저장 , write(메모리 안의 이미지, 컨텐츠 타입(확장자), 파일)
        String imageFileName = "myQR.png";
        ImageIO.write(qrImage, "png", new File(imageFileName));
        // 출력 web이면 jsp view, pc 앱이면 swing frame, android면 activity
        
        App app = new App();
        app.setTitle("QR");
        app.setLayout(new FlowLayout());
        
        //매개변수에 이미지 이름
        ImageIcon icon = new ImageIcon(imageFileName);
        JLabel imageLabel = new JLabel(icon);
        app.add(imageLabel);
        
        app.setSize(300, 300);
        app.setVisible(true);
        //끝내기.
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
