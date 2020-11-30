package chen.mongon.service.Impl;

import chen.mongon.model.JsonResult;
import chen.mongon.model.User;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import java.util.Date;
import java.util.List;

/**
 * @Author Chen
 * @Date 2020/3/12 11:42
 **/
@Service
public class UserService implements chen.mongon.service.UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 添加用户和更新用户
     * @param user
     * @return
     */
    @Override
    public JsonResult save(User user) {
        String msg  = verifySaveForm(user);
        if(!StringUtils.isEmpty(msg)){
            return new JsonResult(false,msg);
        }
        if(user.getId() == null){
            user.setCreate_time(new Date());
            user.setLastUpdateTime(new Date());
            User newUser = mongoTemplate.insert(user,"user");
            return new JsonResult(true,newUser);
        }else{
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(user.getId()));

            Update update = new Update();
            update.set("name", user.getName());
            update.set("password", user.getPassword());
            update.set("address", user.getAddress());
            update.set("last_update_time", new Date());

            UpdateResult result = mongoTemplate.updateFirst(query,update,"user");
            return new JsonResult(true,result);
        }

    }


    /**
     * 查询所有的用户
     * @return
     */
    @Override
    public List<User> fingAll() {
        List<User> userList = mongoTemplate.findAll(User.class,"user");
        return userList;
    }

    /**
     * 删除用户的方法
     * @param id
     * @return
     */
    @Override
    public JsonResult DeleteUserById(String  id) {
        // 确认更新的id 值
        Criteria criteria = Criteria.where("id").is(id);
        // 查询这个id
        Query query = Query.query(criteria);
        DeleteResult deleteResult = mongoTemplate.remove(query,User.class,"user");
        return new JsonResult(true,deleteResult);
    }


//    @Override
//    public UpdateResult updateUser(Long id, String userName, String note) {
//        Criteria criteria = Criteria.where("id").is(id);
//        Query query = Query.query(criteria);
//        Update update = Update.update("userName",userName);
//        update.set("note",note);
//        // 更新第一个文档
//        UpdateResult result = mongoTemplate.updateFirst(query,update,User.class);
//
//        // 更新多个文档
//        //UpdateResult result = mongoTemplate.updateMulti(query,update,User.class);
//        return result;
//    }


    @Override
    public User getUser(Long id) {
        return mongoTemplate.findById(id,User.class);
    }

    /**
     * 验证用户的工具类
     * @param user
     * @return
     */
    private String verifySaveForm(User user){
        if(user == null || StringUtils.isEmpty(user.getName())){
            return "用户名密码不能为空";
        }else if(user.getPassword() == null){
            return  "密码不能为空";
        }
        return null;
    }
}
