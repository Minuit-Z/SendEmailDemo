package com.kk.sendemaildemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static boolean isSend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button sendBt = (Button) findViewById(R.id.main_send_btn);

        sendBt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!isSend) {
                    thread.start();
                } else {
                    Toast.makeText(MainActivity.this, "邮件已发送", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button otherBtn = (Button) findViewById(R.id.main_other_btn);
        /**调用外部程序发送邮件*/
        otherBtn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent data = new Intent(Intent.ACTION_SENDTO);
				data.setData(Uri.parse("mailto:19565580@qq.com"));
				data.putExtra(Intent.EXTRA_SUBJECT, "这是标题");
				data.putExtra(Intent.EXTRA_TEXT, "这是内容");
				startActivity(Intent.createChooser(data, "请选择应用程序"));
			}
		});
    }

    public static Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            //这个类主要是设置邮件
            MultiMailsender.MultiMailSenderInfo mailInfo=initQQ();
            mailInfo.setValidate(true);
//            mailInfo.setUserName("用户名");
//            mailInfo.setPassword("邮箱密码或授权码"); //许多邮箱使用smtp服务登录第三方软件需要使用授权码而不是密码
//            mailInfo.setFromAddress("发件邮箱地址");
//            mailInfo.setToAddress("收件邮箱地址");
//            mailInfo.setSubject("邮件的主题");
//            mailInfo.setContent("邮件的内容");
//            String[] receivers = new String[]{"群发邮件收件地址列表"};
//            mailInfo.setUserName("15513881370@163.com");
//            mailInfo.setPassword("qcz1zzz1"); //许多邮箱使用smtp服务登录第三方软件需要使用授权码而不是密码
            //这个类主要来发送邮件
            MultiMailsender sms = new MultiMailsender();
            boolean isSuccess=sms.sendTextMail(mailInfo);//发送文体格式
//            MultiMailsender.sendHtmlMail(mailInfo);//发送html格式
//            boolean isSuccess = MultiMailsender.sendMailtoMultiCC(mailInfo);//
            if (isSuccess) {
                isSend = true;
            }
        }
    });

    //使用qq邮箱发送
    static MultiMailsender.MultiMailSenderInfo initQQ(){
        MultiMailsender.MultiMailSenderInfo mailInfo = new MultiMailsender.MultiMailSenderInfo();
        mailInfo.setMailServerHost("smtp.qq.com");
        mailInfo.setMailServerPort("587");

        mailInfo.setUserName("2772388894@qq.com");
        //qq的授权码
        mailInfo.setPassword("ybzkbvjkjaoxdhbb"); //许多邮箱使用smtp服务登录第三方软件需要使用授权码而不是密码
        mailInfo.setFromAddress("2772388894@qq.com");
        mailInfo.setToAddress("liangxiaopeng@bjzjmy.com");
        mailInfo.setSubject("邮件的主题");
        mailInfo.setContent("邮件的内容");
        return mailInfo;
    }

    //使用163邮箱发送
    static MultiMailsender.MultiMailSenderInfo init163(){
        MultiMailsender.MultiMailSenderInfo mailInfo = new MultiMailsender.MultiMailSenderInfo();
        mailInfo.setMailServerHost("smtp.163.com");
        mailInfo.setMailServerPort("25");

        mailInfo.setUserName("15513881370@163.com");
        //163的授权码
        mailInfo.setPassword("qcz1zzz1"); //许多邮箱使用smtp服务登录第三方软件需要使用授权码而不是密码
        mailInfo.setFromAddress("15513881370@163.com");
        mailInfo.setToAddress("15513881370@163.com");
        mailInfo.setSubject("邮件的主题");
        mailInfo.setContent("邮件的内容");
        return mailInfo;
    }

    //使用企业邮箱发送
    static MultiMailsender.MultiMailSenderInfo initZJMY(){
        MultiMailsender.MultiMailSenderInfo mailInfo = new MultiMailsender.MultiMailSenderInfo();
        mailInfo.setMailServerHost("pop.qiye.163.com");
        mailInfo.setMailServerPort("995");

        mailInfo.setUserName("liangxiaopeng@bjzjmy.com");
        //163的授权码
        mailInfo.setPassword("lxp9354_Admin"); //许多邮箱使用smtp服务登录第三方软件需要使用授权码而不是密码
        mailInfo.setFromAddress("liangxiaopeng@bjzjmy.com");
        mailInfo.setToAddress("15513881370@163.com");
        mailInfo.setSubject("邮件的主题");
        mailInfo.setContent("邮件的内容");
        return mailInfo;
    }

}
