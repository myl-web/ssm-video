package com.wm.video.service.impl;

import com.wm.video.dao.AdminMapper;
import com.wm.video.pojo.Admin;
import com.wm.video.pojo.AdminExample;
import com.wm.video.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<Admin> findAdmin(String username, String password) {

        AdminExample adminExample = new AdminExample();

        AdminExample.Criteria criteria = adminExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);

        List<Admin> admins = adminMapper.selectByExample(adminExample);

        return admins;
    }


}
