package com.rqbank.eelection.config.msgloader;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "message")
public class Message {
    @XmlAttribute
    public String id;
    @XmlElement(name = "msg-lang")
    public List<MsgLang> msgLangs;

    public String getMsgLang(String lang) {
        for (MsgLang msgLang : msgLangs) {
            if (lang.equals(msgLang.lang))
                return msgLang.message;
        }
        return "";
    }
}
