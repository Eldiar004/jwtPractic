package com.example.spring_rest_api_session_java7.converter.group;

import com.example.spring_rest_api_session_java7.dto.request.GroupRequest;
import com.example.spring_rest_api_session_java7.entity.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupConverterRequest {

    public Group create(GroupRequest groupRequest) {
        if (groupRequest == null) return null;
        Group group = new Group();
        group.setGroupName(groupRequest.getGroupName());
        group.setImage(groupRequest.getImage());
        return group;
    }

    public void update(Group group, GroupRequest groupRequest) {
        if (groupRequest.getGroupName() != null){
            group.setGroupName(groupRequest.getGroupName());
        }if (groupRequest.getImage() != null){
            group.setImage(groupRequest.getImage());
        }



    }
}
