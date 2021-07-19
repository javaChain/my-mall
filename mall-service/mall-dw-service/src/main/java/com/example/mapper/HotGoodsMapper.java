package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.model.HotGoods;
import com.example.util.DruidPage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author: chenq
 * @Description:
 * @Date: Created in 2021-07-19 10:22
 */
public interface HotGoodsMapper extends BaseMapper<HotGoods> {
    /**
     * 排除指定数据
     */
    @Select("select ip,uri,__time as accesstime from msitemslog where __time >= TIMESTAMP '${time}' and uri not in ('${urls}') limit #{size}")
    List<HotGoods> searchExclude(@Param("size") Integer size,@Param("time") String time,@Param("urls")String urls);

    /**
     * 查询过去N小时记录
     * @param size
     * @param time
     * @return
     */
    @Select("select ip,uri,__time as accesstime from msitemslog where __time >= TIMESTAMP '${time}' limit #{size}")
    List<HotGoods> search(@Param("size") Integer size,@Param("time") String time);

    /**
     * 前N条记录
     */
    @Select("select ip,uri,__time as accesstime from msitemslog limit #{size}")
    List<HotGoods> topNum(@Param("size") Integer size);


    /**
     * 分页查询
     */
    @Select("select ip,uri,__time as accesstime from msitemslog limit #{size} offset #{offset}")
    List<HotGoods> pageList(@Param("size") Integer size, @Param("offset") Long offset);

    /**
     * 排序+分页查询
     */
    @Select("select ip,uri,__time as accesstime from msitemslog order by ${sort} ${sortType} limit #{size} offset #{offset}")
    List<HotGoods> pageListSort(DruidPage druidPage);
}