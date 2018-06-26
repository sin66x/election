package com.rqbank.eelection.config.provinceloader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rqbank.eelection.config.msgloader.Messages;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

public class Provinces {
    static {
        try {
            File f = new File("src/main/resources/static/data/province-branch.json");
            String content = new String(Files.readAllBytes(f.toPath()));
            ObjectMapper mapper = new ObjectMapper();
            inst = mapper.readValue(content, Provinces.class);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    private static Provinces inst;
    public List<Province> provinces;

    public static String findProvinceCode(String name){
        for (Province p: inst.provinces){
            if (p.name.equals(name))
                return p.code;
        }
        return null;
    }

    public static String findBranchName(String branchCode){
        for (Province p: inst.provinces){
            for (Branch b: p.branches){
                if (b.code.equals(branchCode))
                    return b.name;
            }
        }
        return null;
    }
}
