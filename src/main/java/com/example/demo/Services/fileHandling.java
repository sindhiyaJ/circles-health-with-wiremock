package com.example.demo.Services;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class fileHandling {
  @Autowired
  private GridFsTemplate template;
  public String fileUpload(String location, MultipartFile file){
        DBObject metadata = new BasicDBObject();
        metadata.put("fileSize", file.getSize());
        
        try {
            String picLocation = template.store(file.getInputStream(),location, file.getContentType(), metadata).toString();
            return picLocation;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "Not available";
        }
  }
  public void deleteFiles(List<String> files){
    // Document document = new Document();
       for(String s : files){
          Document document = new Document();
          document.append("file_id",s);
          BasicQuery query = new BasicQuery(document);
          template.delete(query);
       }
  }
  public List<String> uploadMutipleFiles(MultipartFile files[]){
    List<String> locations = new ArrayList<>();
    for(MultipartFile file : files){
         String fileLocation  = fileUpload("gg",file);
         locations.add(fileLocation);
    }
    return locations;
  }
}
