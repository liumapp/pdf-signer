package com.liumapp.signature.pdf.worker;

import com.liumapp.DNSQueen.worker.ready.StandReadyWorker;
import com.liumapp.ali.oss.utils.OssUtil;
import com.liumapp.pattern.sign.SignatureAreaPattern;
import com.liumapp.signature.pdf.config.Params;
import com.liumapp.signature.pdf.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 获取合同pdf
 * 生成签名区域
 * Created by liumapp on 11/29/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class Builder extends StandReadyWorker {

    @Autowired
    private OssUtil ossUtil;

    @Autowired
    private Params params;

    @Autowired
    private FileUtil fileUtil;

    @Override
    public String doWhatYouShouldDo(String whatQueenSays) {
        try {
            SignatureAreaPattern signatureAreaPattern = SignatureAreaPattern.parse(whatQueenSays);
            
            return "success";
        } catch (Exception e) {
            System.out.println("maybe not builder's job....");
            e.printStackTrace();
            return null;
        }
    }



}
