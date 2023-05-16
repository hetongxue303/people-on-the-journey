package com.journey;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.journey.domain.common.Result;
import com.journey.domain.common.ResultPage;
import com.journey.domain.entity.Share;
import com.journey.domain.entity.UserInfo;
import com.journey.domain.vo.SearchVo;
import com.journey.domain.vo.ShareVo;
import com.journey.domain.vo.UserInfoVo;
import com.journey.mapper.ShareMapper;
import com.journey.mapper.UserinfoMapper;
import com.journey.utils.BeanCopyUtil;
import com.journey.utils.MBPUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author hy
 * @version 1.0
 */
@SpringBootTest
public class TestApplication {

    @Resource
    private ShareMapper shareMapper;

    @Resource
    private UserinfoMapper userinfoMapper;

    @Test
    void test() {
        LambdaQueryWrapper<Share> wrapper = new LambdaQueryWrapper<>();
        SearchVo searchVo = new SearchVo().setCurrent(1L).setSize(10L);
        wrapper.orderByDesc(Share::getId);
        Page<Share> data = shareMapper.selectPage(MBPUtil.generatePage(searchVo, Share.class), wrapper);
        System.out.println(Result.success(new ResultPage(data.getTotal(), BeanCopyUtil.copyBeanList(data.getRecords(), ShareVo.class))));

        System.out.println("====================");
        List<ShareVo> collect = Optional.ofNullable(data.getRecords())
                .orElse(new ArrayList<>())
                .stream()
                .map(item -> BeanCopyUtil.copyBean(item, ShareVo.class)
                        .setUserinfo(BeanCopyUtil.copyBean(userinfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>().eq(UserInfo::getId, item.getUserId())), UserInfoVo.class)))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


}