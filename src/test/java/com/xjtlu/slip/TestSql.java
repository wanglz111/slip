package com.xjtlu.slip;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xjtlu.slip.mapper.CommentMapper;
import com.xjtlu.slip.mapper.UserMapper;
import com.xjtlu.slip.pojo.Comment;
import com.xjtlu.slip.pojo.Topic;
import com.xjtlu.slip.service.CommentService;
import com.xjtlu.slip.service.TopicService;
import com.xjtlu.slip.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static com.xjtlu.slip.utils.TimeToUnix.getCurrentTime;

@SpringBootTest
public class TestSql {
    @Autowired
    private TopicService topicService;

    @Autowired
    private CommentService commentService;

    @Test
    public void InsertTopics() throws Exception {
        ArrayList<Topic> topics = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Topic topic = new Topic();
            topic.setTitle("test" + i);
            topic.setContent("test content" + i);
            topic.setAuthorId(1L);
            topic.setAuthor("wangluzhi");
            //获取一个3000-10000的随机数
            int number = (int) (Math.random() * (10000 - 3000 + 1) + 3000);

            topic.setCreateUnixTime(getCurrentTime() - number);
            topic.setUpdateUnixTime(getCurrentTime() - number);
            topic.setLatestCommentUnixTime(getCurrentTime() - number);
            topic.setType("work");
            topics.add(topic);
        }

//        topicService.saveOrUpdateBatch(topics);

    }
    @Test
    public void testComment(){
        QueryWrapper<Comment> query = new QueryWrapper<>();
        query.eq("user_id",1);
        System.out.println(commentService.list(query));
    }

    @Test
    public void testSelectCommentAndUser(){
        System.out.println(commentService.getListByTopicId("85"));
    }
}