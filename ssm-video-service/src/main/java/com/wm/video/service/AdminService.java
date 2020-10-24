package com.wm.video.service;

import com.wm.video.pojo.Admin;

import java.util.List;

public interface AdminService {


    List<Admin> findAdmin(String username, String password);
}
