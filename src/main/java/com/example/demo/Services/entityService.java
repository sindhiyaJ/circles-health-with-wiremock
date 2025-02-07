package com.example.demo.Services;

import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.query.UpdateDefinition;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

// import com.example.demo.models.employeeModel;

@Service
@Component
public class entityService {
   @Autowired
   private MongoTemplate rep ;
   // @Autowired
   // private employeeModel ep;
   @Autowired 
   private com.example.demo.Services.helper helper;
   public void addEntity(String dbname,Map<String, String> params){
       
       rep.insert(helper.getDocument(params),dbname);
   }
   public List<Document> getEntity(String dbname,Map<String, String> params){
        Document document = new Document();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String val = entry.getValue();
            val = val.strip();
            if(val.length() == 0){
               continue;
            }
            document.append(entry.getKey(),entry.getValue());
        }
        BasicQuery query = new BasicQuery(document);
        // System.out.println("gggggggggggggggggggggggggggggggggg");
        // System.out.println(query);
        return rep.find(query,Document.class, dbname);
   }
   public void deleteEntity(String dbname,Map<String, String> params){
    Document document = new Document();
    document = helper.getDocument(helper.removeEmptyFields(params));
    // for (Map.Entry<String, String> entry : params.entrySet()) {
    //     String val = entry.getValue();
    //     val = val.strip();
    //     if(val.length() == 0){
    //        continue;
    //     }
    //     document.append(entry.getKey(),entry.getValue());
    // }
    BasicQuery query = new BasicQuery(document);
    // System.out.println("gggggggggggggggggggggggggggggggggg");
    // System.out.println(query);
    // rep.remove(query)
    rep.remove(query,dbname);
   }
   public void updateEntity(String dbname,Map<String,String> filters,Map<String,String> setters){
    Document document = new Document();
    document = helper.getDocument(filters);
    BasicQuery query = new BasicQuery(document);
    Update upd = new Update();
    for (Map.Entry<String, String> entry : setters.entrySet()) {
      //     String val = entry.getValue();
      //     val = val.strip();
      //     if(val.length() == 0){
      //        continue;
      //     }
      //     document.append(entry.getKey(),entry.getValue());
         upd.set(entry.getKey(),entry.getValue());
      }
      rep.updateMulti(query, upd, Document.class, dbname);
   }
  
}
