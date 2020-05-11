package com.zs.campusblog.controller.admin;

import com.zs.campusblog.common.CommonPage;
import com.zs.campusblog.common.Result;
import com.zs.campusblog.common.aop.LogAop;
import com.zs.campusblog.mbg.model.Menu;
import com.zs.campusblog.mbg.model.Role;
import com.zs.campusblog.service.RoleService;
import com.zs.campusblog.vo.RoleVO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zs
 * @date 2020/2/28
 * 角色相关的接口
 */
@Slf4j
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色列表")
    @GetMapping(value = "/list")
    public Result<List<Role>> getRoleList() {
        List<Role> roleList = roleService.list();
        return Result.success(roleList);
    }

    @ApiOperation("分页获取角色列表")
    @GetMapping(value = "/list/page")
    public Result<CommonPage<Role>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Role> roleList = roleService.list(keyword, pageSize, pageNum);
        return Result.success(CommonPage.restPage(roleList));
    }

    @LogAop(value = "新增角色信息")
    @ApiOperation("新增角色信息")
    @PostMapping("/add")
    public Result add(@RequestBody RoleVO roleVO) {
        String roleName = roleVO.getRoleName();
        List<Role> roles = roleService.getRoleByRoleName(roleName);
        if (roles.size() == 0) {
            Role role = new Role();
            BeanUtils.copyProperties(roleVO, role);
            roleService.add(role);
            return Result.success("", "添加成功");
        }
        return Result.failed("添加失败");
    }

    /**
     * 根据id删除角色
     */
    @LogAop(value = "删除角色信息")
    @ApiOperation("根据id删除角色信息")
    @PostMapping("/delete")
    public Result deleteById(Integer id) {
        int result = roleService.deleteById(id);
        if (result > 0) {
            return Result.success("", "删除成功");
        }
        return Result.failed("删除失败");
    }

    /**
     * 更新角色信息
     */
    @LogAop(value = "更新角色信息")
    @ApiOperation("更新角色信息")
    @PostMapping("/update")
    public Result updateRole(@RequestBody RoleVO roleVO) {
        Role role = new Role();
        BeanUtils.copyProperties(roleVO, role);
        int result = roleService.update(role);
        if (result > 0) {
            return Result.success("", "更新成功");
        }
        return Result.failed("更新失败");
    }

    /**
     * 根据用户id获取角色信息
     */
    @ApiOperation("根据用户信息获取角色列表")
    @GetMapping("")
    public Result getRoleList(Integer id) {
        return Result.success(roleService.getRoleListByUserId(id));
    }

    /**
     * 更新用户角色
     */
    @ApiOperation("更新用户角色")
    @PostMapping("/updateRole")
    public Result updateUserRole(Integer roleId, Integer userId) {
        int result = roleService.updateUserRole(roleId, userId);
        if (result > 0) {
            return Result.success(" ", "更新成功");
        }
        return Result.failed("更新失败");
    }

    @ApiOperation("根据角色id获取相关菜单")
    @GetMapping(value = "/listMenu/{roleId}")
    public Result<List<Menu>> listMenu(@PathVariable Integer roleId) {
        List<Menu> roleList = roleService.listMenu(roleId);
        return Result.success(roleList);
    }

    @ApiOperation("给角色分配菜单")
    @PostMapping("/allocMenu")
    public Result allocMenu(@RequestParam Integer roleId, @RequestParam List<Integer> menuIds) {
        int count  = roleService.allocMenu(roleId, menuIds);
        return Result.success(count);
    }

    @ApiOperation("给角色分配资源")
    @PostMapping("/allocResource")
    public Result allocResource(@RequestParam Integer roleId, @RequestParam List<Integer> resourceIds) {
        int count = roleService.allocResource(roleId, resourceIds);
        return Result.success(count);
    }
}
