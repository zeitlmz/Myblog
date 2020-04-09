package com.blogmanger.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blogmanger.entity.Autor;
import com.blogmanger.service.AutorService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Autor)表控制层
 *
 * @author makejava
 * @since 2020-04-02 20:54:28
 */
@RestController
@RequestMapping("autor")
public class AutorController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private AutorService autorService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param autor 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<Autor> page, Autor autor) {
        return success(this.autorService.page(page, new QueryWrapper<>(autor)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.autorService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param autor 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Autor autor) {
        return success(this.autorService.save(autor));
    }

    /**
     * 修改数据
     *
     * @param autor 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Autor autor) {
        return success(this.autorService.updateById(autor));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.autorService.removeByIds(idList));
    }
}