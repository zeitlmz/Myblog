package com.blogmanger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogmanger.dao.XuserDao;
import com.blogmanger.entity.Xuser;
import com.blogmanger.service.XuserService;
import org.springframework.stereotype.Service;

/**
 * (Xuser)表服务实现类
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@Service("xuserService")
public class XuserServiceImpl extends ServiceImpl<XuserDao, Xuser> implements XuserService {

}