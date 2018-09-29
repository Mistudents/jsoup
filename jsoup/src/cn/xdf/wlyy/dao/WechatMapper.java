package cn.xdf.wlyy.dao;

import cn.xdf.wlyy.domain.Wechat;
import cn.xdf.wlyy.domain.WechatExample;
import java.util.List;

public interface WechatMapper {
    int countByExample(WechatExample example);

    int deleteByExample(WechatExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Wechat record);

    int insertSelective(Wechat record);

    List<Wechat> selectByExample(WechatExample example);

    Wechat selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Wechat record);

    int updateByPrimaryKey(Wechat record);
}