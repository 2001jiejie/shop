package com.example.controller;
import com.example.context.BaseContext;
import com.example.entity.*;
import com.example.service.AdminService;
import com.example.service.GoodsService;
import com.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AdminUsercontroller {

    @Autowired
    private AdminService adminservice;

    @Autowired
    private GoodsService goodsService;

    // 管理员登录
    @PostMapping("/adminlogin")
    public Result<String> Login(@RequestParam("ausername") String ausername,
            @RequestParam("apassword") String apassword,
            HttpServletRequest request) {
        aUser user = adminservice.login(ausername, apassword);
        if (user != null) {
            String jwt = JwtUtils.generateToken(user.getId());
            return Result.success(jwt);
        } else {
            return Result.error("管理员用户名或密码错误");
        }
    }

    // 管理员退出
    @GetMapping("/adminlogout")
    public Result<String> logout() {
        return Result.success();
    }

    // 查询管理员个人信息
    @GetMapping("/adminuser")
    public Result<aUser> getUser() {
        Integer auserId = BaseContext.getCurrentId();
        aUser auser = adminservice.getUserById(auserId);
        if (auser != null) {
            return Result.success(auser);
        } else {
            return Result.error("用户不存在");
        }
    }

    // 所有商品
    @GetMapping("/admin/goods")
    public Result<List<goodstable>> getGoods() {
        List<goodstable> goodsList = goodsService.ListAllGoods();
        if (goodsList != null && !goodsList.isEmpty()) {
            return Result.success(goodsList);
        } else {
            return Result.error("没找到商品");
        }
    }

    // 查询商品
    @GetMapping("/admin/searchgoods")
    public Result<List<goodstable>> Searchgoods(@RequestParam(value = "gname", required = false) String gname,
                                                @RequestParam(value = "goodstype_id", required = false) Integer goodstype_id) {
        List<goodstable> goodsList = adminservice.SearchGoods(gname, goodstype_id);
        if (goodsList != null && !goodsList.isEmpty()) {
            return Result.success(goodsList);
        } else {
            return Result.error("没有查询到结果");
        }
    }

    // 增加商品
    @PostMapping("/admin/addgoods")
    public Result<String> AddGoods(@RequestBody goodstable goods) {
        adminservice.AddGoods(goods);
        return Result.success("添加成功");
    }

    // 修改商品
    @PutMapping("/admin/updategoods")
    public Result<String> UpdateGoods(@RequestBody goodstable goods) {
        adminservice.UpdateGoods(goods);
        return Result.success("修改成功");
    }

    // 根据id删除商品
    @DeleteMapping("/admin/deletegood")
    public Result<String> DeleteGoods(@RequestParam("id") Integer id) {
        if (adminservice.DeleteGood(id)){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    //批量删除商品
    @DeleteMapping("/admin/deletegoods")
    public Result<String> DeleteGoods(@RequestBody List<Integer> ids) {
        if (adminservice.DeleteGoods(ids)){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    // 所有商品类型
    @GetMapping("/admin/goodstypes")
    public Result<List<goodstype>> getGoodstypes() {
        List<goodstype> goodstypesList = adminservice.ListAllGoodstypes();
        if (goodstypesList != null && !goodstypesList.isEmpty()) {
            return Result.success(goodstypesList);
        } else {
            return Result.error("没找到商品类型");
        }
    }

    // 查询商品类型
    @GetMapping("/admin/searchgoodstype")
    public Result<List<goodstype>> Searchgoodstypes(@RequestParam("typename") String typename){
        List<goodstype> goodstypesList = adminservice.SearchGoodstype(typename);
        if (goodstypesList != null && !goodstypesList.isEmpty()) {
            return Result.success(goodstypesList);
        } else {
            return Result.error("没找到商品类型");
        }
    }

    //新增商品类型
    @PostMapping("/admin/addgoodstype")
    public Result<String> AddGoodstype(@RequestBody goodstype goodstype){
        adminservice.AddGoodstype(goodstype);
        return Result.success("添加成功");
    }

    //删除单个商品类型
    @DeleteMapping("/admin/deletegoodstype")
    public Result<String> DeleteGoodstype(@RequestParam("id") Integer id) {
        if (adminservice.DeleteGoodstype(id)){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
    //批量删除商品类型
    @DeleteMapping("/admin/deletegoodstypes")
    public Result<String> DeleteGoodstypes(@RequestBody List<Integer> ids) {
        if (adminservice.DeleteGoodstypes(ids)){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }

    //所有用户
    @GetMapping("/admin/users")
    public Result<List<bUser>> getUsers() {
        List<bUser> usersList = adminservice.ListAllUsers();
        if (usersList != null && !usersList.isEmpty()) {
            return Result.success(usersList);
        } else {
            return Result.error("没找到用户");
        }
    }

    //查询用户
    @GetMapping("/admin/searchuser")
    public Result<List<bUser>> Searchusers(@RequestParam("id") Integer id){
        List<bUser> userList = adminservice.SearchUser(id);
        if (userList != null && !userList.isEmpty()) {
            return Result.success(userList);
        } else {
            return Result.error("没找到用户");
        }
    }

    //删除单个用户
    @DeleteMapping("/admin/deleteuser")
    public Result<String> DeleteUser(@RequestParam("id") Integer id) {
        if (adminservice.DeleteUser(id)){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
    //批量删除用户
    @DeleteMapping("/admin/deleteusers")
    public Result<String> DeleteUsers(@RequestBody List<Integer> ids) {
        if (adminservice.DeleteUsers(ids)){
            return Result.success("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }


    // 所有订单
    @GetMapping("/admin/orders")
    public Result<List<orderbase>> getOrders(){
        List<orderbase> ordersList = adminservice.ListAllOrders();
        if (ordersList != null && !ordersList.isEmpty()) {
            return Result.success(ordersList);
        } else {
            return Result.error("没找到订单");
        }
    }

    // 查询订单
    @GetMapping("/admin/searchorder")
    public Result<List<orderbase>> Searchorders(@RequestParam("id") Integer id){
        List<orderbase> ordersList = adminservice.SearchOrder(id);
        if (ordersList != null && !ordersList.isEmpty())
            return Result.success(ordersList);
        else
            return Result.error("没找到订单");
    }

    //图片上传
    @PostMapping("/upload")
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return Result.error("没有文件");
        }
        try {
            // 获取项目根目录
            String projectRoot = System.getProperty("user.dir");
            System.out.println("p:" + projectRoot);

            // 创建一个File对象
            File afile = new File(projectRoot);
            // 使用getParentFile()方法获取父目录
            File parentFile = afile.getParentFile();
            if (parentFile != null) {
                // 输出父目录
                System.out.println("父目录: " + parentFile.getAbsolutePath());
                // 指定要保存文件的相对路径
                String relativePath = "frontend\\src\\assets";
                // 构建完整的保存路径
                Path fullUploadDir = Paths.get(parentFile.getAbsolutePath(), relativePath);
                // 生成随机文件名
                String randomFileName = Math.random() + ".png";
                Path path = fullUploadDir.resolve(randomFileName); // 使用resolve来组合路径和文件名

                // 确保目标目录存在
                Files.createDirectories(fullUploadDir);
                // 写入文件
                Files.write(path, file.getBytes());
                // 返回成功结果
                return Result.success(randomFileName);
            } else {
                System.out.println("没有更上一级的目录了。");
                return Result.error("无法找到合适的父目录来保存文件。");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Result.error("File upload failed: " + e.getMessage());
        }
    }
}
