package com.example.demo.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
@Component
public class helper {
    
  public  Map<String,String> removeEmptyFields(Map<String,String> params){
    Map<String,String> sender = new HashMap<>();
    for(Map.Entry<String, String> entry : params.entrySet()){
        String val = entry.getValue();
        val = val.strip();
        if(val.length() == 0){
          continue;
         }
        sender.put(entry.getKey(),val); 

    }
      return params;
      
  }
  public Document getDocument(Map<String,String> docs){
     Document dummy = new Document();
     for(Map.Entry<String, String> entry : docs.entrySet()){
            dummy.append(entry.getKey(), entry.getValue());
     }
     return dummy;
  }
  public List<String> convertCsvToList(String value){
      List<String> newValues = new ArrayList<>();
      for(String  curr : value.split(",")){
            newValues.add(curr);
      }
      return newValues;
  }
}
