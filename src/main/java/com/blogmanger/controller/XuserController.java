package com.blogmanger.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogmanger.entity.Xuser;
import com.blogmanger.service.XuserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Xuser)表控制层
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@RestController
@RequestMapping("xuser")
public class XuserController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private XuserService xuserService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param xuser 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Xuser> page, Xuser xuser) {
        return success(this.xuserService.page(page, new QueryWrapper<>(xuser)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.xuserService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param xuser 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Xuser xuser) {
        return success(this.xuserService.save(xuser));
    }

    /**
     * 修改数据
     *
     * @param xuser 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Xuser xuser) {
        return success(this.xuserService.updateById(xuser));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.xuserService.removeByIds(idList));
    }
}