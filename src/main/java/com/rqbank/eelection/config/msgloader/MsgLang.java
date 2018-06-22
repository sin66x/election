package com.rqbank.eelection.config.msgloader;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

@XmlRootElement(name = "msg-lang")
public class MsgLang {
    @XmlAttribute
    public String lang;

    @XmlValue
    public String message;
}
