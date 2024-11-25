package com.example.mapper;

import com.example.entity.*;

import java.util.List;

import org.apache.ibatis.annotations.*;

@Mapper
public interface Adminmapper {
    // 根据管理员用户名查找管理员
    @Select("select * from auser where aname = #{ausername} ")
    aUser findByAdminname(@Param("ausername") String ausername);

    // 查询商品(动态SQL)
    List<goodstable> searchGoods(@Param("gname") String gname,@Param("goodstype_id") Integer goodstype_id);

    // 根据商品类型名称获取商品类型id
    @Select("select id from goodstype where typename = #{typename}")
    Integer GetGoodtypeId(@Param("typename") String typename);

    // 添加商品
    void addGoods(goodstable good);

    // 修改商品
    void updateGoods(goodstable good);

    // 删除单个商品
    @Delete("delete from goodstable where id = #{id}")
    void deleteGood(Integer id);

    // 根据商品id查找是否存在
    @Select("select * from goodstable where id = #{id}")
    goodstable findGoodById(Integer id);

    // 批量删除商品
    public void deleteGoods(List<Integer> ids);

    // 查询所有商品类型
    @Select("select * from goodstype")
    List<goodstype> getAllGoodstype();

    // 查询商品类型
    @Select("select * from goodstype where typename = #{typename}")
    List<goodstype> searchGoodstype(String typename);

    //新增商品类型
    @Insert("INSERT INTO goodstype (typename) VALUES (#{typename})")
    void addGoodstypes(goodstype goodstype);

    //删除单个商品类型
    @Delete("delete from goodstype where id = #{id}")
    void deleteGoodstype(Integer id);

    // 根据商品类型id查找是否存在
    @Select("select * from goodstype where id = #{id}")
    goodstable findGoodstypeById(Integer id);

    // 批量删除商品类型
    public void deleteGoodstypes(List<Integer> ids);

    // 所有用户
    @Select("select id,bname from buser")
    List<bUser> getAllUsers();

    // 根据用户id查找用户
    @Select("select id,bname from buser where id = #{id}")
    List<bUser> searchUser(Integer id);

    //删除单个用户
    @Delete("delete from buser where id = #{id}")
    void deleteUser(Integer id);

    // 根据用户id查找是否存在
    @Select("select * from buser where id = #{id}")
    goodstable findUserById(Integer id);

    // 批量删除用户
    public void deleteUsers(List<Integer> ids);

    @Select("select aname from auser where id = #{id}")
    aUser getUserById(Integer id);

    // 根据订单编号查询订单
    @Select("select * from orderbase where id = #{id}")
    List<orderbase> searchOrder(Integer id);
}
