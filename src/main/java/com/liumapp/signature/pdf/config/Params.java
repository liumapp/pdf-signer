package com.liumapp.signature.pdf.config;

import com.liumapp.DNSQueen.worker.process.WokerEar;
import com.liumapp.DNSQueen.worker.tcp.TCPSocketMonitor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by liumapp on 11/28/17.
 * E-mail:liumapp.com@gmail.com
 * home-page:http://www.liumapp.com
 */
@Component
public class Params {

    private Integer port = 9999;

    private Integer startDelay = 3000;

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getStartDelay() {
        return startDelay;
    }

    public void setStartDelay(Integer startDelay) {
        this.startDelay = startDelay;
    }
}
