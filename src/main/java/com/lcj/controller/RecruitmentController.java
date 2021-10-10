package com.lcj.controller;

import com.lcj.constant.CommonConst;
import com.lcj.util.JsoupSpiderUtil;
import com.lcj.util.RecruitmentResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @projectName: githubdemopractice
 * @see: com.lcj.controller
 * @author: lichunjiang
 * @createTime: 2021/10/10 20:52
 * @version: 1.0
 */
@RestController
public class RecruitmentController {
   private Logger logger= LoggerFactory.getLogger(LoggerFactory.class);

    @GetMapping("/getData/{dateData}")
    public <T> RecruitmentResultEntity getRecruitmentData(@PathVariable("dateData") String dateData)  {
        Map map = new HashMap();
        RecruitmentResultEntity recruitmentResultEntity = null;
        try {
            recruitmentResultEntity=RecruitmentResultEntity.ok(JsoupSpiderUtil.httpPost(dateData,CommonConst.SPIDER_URL, map,
                    CommonConst.COOKIE_VALUE));
        } catch (IOException e) {
            logger.error("{}"+e);
        }
        return recruitmentResultEntity;
    }
}
