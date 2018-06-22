package com.rqbank.eelection.config.msgloader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.List;

@XmlRootElement(name = "messages")
public class Messages {
    static {
        try {
            File file = new File("src/main/resources/static/messages.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Messages.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            inst = (Messages) jaxbUnmarshaller.unmarshal(file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Messages inst;
    @XmlElement(name = "message")
    private List<Message> messages;

    public static void main(String []args){
        System.out.println(Messages.getMessage("Hello","fa"));
        System.out.println(Messages.getMessage("Hello","en"));
    }
    public static String getMessage(String id, String lang) {
        for (Message message : inst.messages){
            if (id.equals(message.id)){
                return message.getMsgLang(lang);
            }

        }
    return "";
    }

    public static Messages getInst() {
        return inst;
    }

    public static void setInst(Messages inst) {
        Messages.inst = inst;
    }
}
