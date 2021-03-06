package com.service;

import com.base.service.CommonService;
import com.constant.SignType;
import com.model.UserSign;
import org.apache.ibatis.annotations.Param;

/**
 * @date 2017年08月15日
 */
public interface UserSignService extends CommonService<UserSign> {

    UserSign addUserSign(String userId, Double amt, SignType signType, String remark);

    public Boolean sign(String signId) throws Exception;

    Double readSignCount(String userId);

    Double readSumFrozenCount(String userId);

    Boolean  splitSign(String id,Double availableAmt);

}
