package com.blogmanger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blogmanger.dao.AutorDao;
import com.blogmanger.entity.Autor;
import com.blogmanger.service.AutorService;
import org.springframework.stereotype.Service;

/**
 * (Autor)表服务实现类
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@Service("autorService")
public class AutorServiceImpl extends ServiceImpl<AutorDao, Autor> implements AutorService {

}