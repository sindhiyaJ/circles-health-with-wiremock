package com.example.demo.Controllers;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.MediaType;
import org.apache.tomcat.util.json.JSONParser;
import org.bson.Document;
import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.bson.types.ObjectId;
// import org.json.JSONObject;

import com.example.demo.Services.fileHandling;
import com.example.demo.Services.helper;
// import com.example.demo.models.employeeModel;
import com.fasterxml.jackson.databind.util.JSONPObject;
// import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
// import java.awt.PageAttributes.MediaType;
import org.bson.json.*;

@Controller
@Component
public class entity {
  @Autowired
  private com.example.demo.Services.entityService entityService;  
  @Autowired 
  private com.example.demo.Services.helper helper;
  @Autowired
  private com.example.demo.Services.fileHandling fileHandle;
  @Autowired
  private GridFsTemplate template;

  @PostMapping("/register/{entity}")
  @ResponseBody
  public Map<String, String> registerEntity(
    @PathVariable("entity") String dbname,
    @RequestPart Map<String, String> params,
    @RequestPart(value = "images" ,required = false) MultipartFile images[],
    @RequestPart(value =  "keysForImages",required = false) String labels
  ){
    
    List<String> keysForUploadedFiles  = helper.convertCsvToList(labels);
    
    List<String> locationsOfUploadedFiles = fileHandle.uploadMutipleFiles(images);
    for(int i = 0 ;i<keysForUploadedFiles.size();i++){
         params.put(keysForUploadedFiles.get(i), locationsOfUploadedFiles.get(i));
    }
    System.out.println(params);
    entityService.addEntity(dbname,params);
    return params;
  }
  @PostMapping("/get/{entity}")
  @ResponseBody  
  public List<Document> getEntity(@PathVariable("entity") String dbname, @RequestParam Map<String, String> params) {
      params = helper.removeEmptyFields(params);
      List<Document> entitysList = entityService.getEntity(dbname,params);
      return  entitysList;   
  }
  @PostMapping("/delete/{entity}")
  @ResponseBody 
  public String deleteEntity(@PathVariable("entity") String dbname, @RequestParam Map<String, String> filter_params) {
    filter_params = helper.removeEmptyFields(filter_params);
    entityService.deleteEntity(dbname,filter_params);
    return  "deleted";
  }
  @PutMapping(value = "/update/{entity}")
  @ResponseBody 
  public String updateEntity( 
   @PathVariable("entity") String dbname,
   @RequestParam Map<String,String> filter_params,
   @RequestPart Map<String,String> setter_params,
   @RequestPart(value = "images" ,required = false) MultipartFile images[],
   @RequestPart(value =  "keysForImages",required = false) String labels
  ){       
        filter_params = helper.removeEmptyFields(filter_params);
        setter_params = helper.removeEmptyFields(setter_params);
        List<String> keysForUploadedFiles  = helper.convertCsvToList(labels);
        List<String> locationsOfUploadedFiles = fileHandle.uploadMutipleFiles(images);
        for(int i = 0 ;i<keysForUploadedFiles.size();i++){
            setter_params.put(keysForUploadedFiles.get(i), locationsOfUploadedFiles.get(i));
        }
        entityService.updateEntity(dbname,filter_params,setter_params);
        return "updated";
  }
}





