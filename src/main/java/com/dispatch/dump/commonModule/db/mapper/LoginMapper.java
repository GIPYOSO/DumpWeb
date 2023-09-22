package com.dispatch.dump.commonModule.db.mapper;

import com.dispatch.dump.commonModule.db.dto.Login;

public interface LoginMapper {
     Login userIdValidChk(Login login);
     Login findUserInfo(Login login);

     Login findAdvUserInfo(Login login);
     int userIdDuplicateChk(Login login);

     int insertUserInfo(Login login);

     int updateUserPassword(Login login);

     int insertDefaultCarData(Login login);

     int countDefaultCarData(Login login);

     int updateSheetSsByUserId(Login login);

     int updateCarNoSsByUserId(Login login);

     int updateSheetSubSsByUserId(Login login);
}
