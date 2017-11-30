package com.liumapp.signature.pdf.worker;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.liumapp.DNSQueen.worker.ready.StandReadyWorker;
import com.liumapp.ali.oss.utils.OssUtil;
import com.liumapp.pattern.sign.SignatureAreaPattern;
import com.liumapp.signature.pdf.config.Params;
import com.liumapp.signature.pdf.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;

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
            String fileName = fileUtil.getTmpFileName();
            String fileResultName = fileUtil.getTmpFileName();

            fileName = params.getTmpDir() + "/" + fileName;
            fileResultName = params.getTmpDir() + "/" + fileName;

            ossUtil.downloadFile(signatureAreaPattern.getFileKey() , new File(fileName));
            PdfReader pdfReader = new PdfReader(fileName);
            FileOutputStream out = new FileOutputStream(fileResultName);
            PdfStamper pdfStamper = new PdfStamper(pdfReader , out);

            pdfStamper.addSignature(signatureAreaPattern.getName() , signatureAreaPattern.getPageNumber() , signatureAreaPattern.getFirstX().floatValue() , signatureAreaPattern.getFirstY().floatValue() , signatureAreaPattern.getSecondX().floatValue() , signatureAreaPattern.getSecondY().floatValue());
            pdfStamper.close();

            ossUtil.uploadFile(signatureAreaPattern.getFileKey() , new File(fileResultName));
            return "success";
        } catch (Exception e) {
            System.out.println("maybe not builder's job....");
            e.printStackTrace();
            return null;
        }
    }

}
