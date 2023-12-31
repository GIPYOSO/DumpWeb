package com.dispatch.dump.dailyReportModule.service;

import com.dispatch.dump.commonModule.db.dto.*;
import com.dispatch.dump.commonModule.db.mapper.DailyReportStep7Mapper;
import com.dispatch.dump.commonModule.db.mapper.DailyReportStep7MainMapper;
import com.dispatch.dump.commonModule.db.mapper.DailyReportStep7SubMapper;
import com.dispatch.dump.commonModule.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Step7Service {

    private final CommonUtil commonUtil;
    private final DailyReportStep7MainMapper dailyReportStep7MainMapper;
    private final DailyReportStep7SubMapper dailyReportStep7SubMapper;
    private final DailyReportStep7Mapper step7Mapper;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public Login getSessionLoginData() {
        return (Login) commonUtil.getSession().getAttribute("loginInfo");
    }

    //제출처 주문 정보 저장
    public String saveOrder(DailyReportStep7Main dailyReportStep7Main, DailyReportStep7Sub dailyReportStep7Sub){

        Map<String, Object> rtnMap = commonUtil.returnMap();
        HttpSession session = commonUtil.getSession();

        try {

            System.out.println(dailyReportStep7Main);
            System.out.println(dailyReportStep7Sub);
            Login loginData = (Login) session.getAttribute("loginInfo");

            //System.out.println(loginData);
            dailyReportStep7Main.setCarSubmit(String.valueOf(getSessionLoginData().getUserSS()));
            dailyReportStep7Main.setCarSubmitTel(String.valueOf(getSessionLoginData().getUserTel()));
            dailyReportStep7Main.setSalesman(String.valueOf(getSessionLoginData().getUserName()));
            //결재 체크 값
//            dailyReportStep7Main.setSheetSS(Integer.parseInt(loginData.getUserId()));
            dailyReportStep7Main.setSheetSS2(Integer.parseInt(loginData.getUuserID()));
            dailyReportStep7Main.setWriterIDX(Integer.parseInt(loginData.getUuserID()));
            //writerIDX

            dailyReportStep7MainMapper.insertDailyReportStep7(dailyReportStep7Main);

            dailyReportStep7Sub.setSheetID2(dailyReportStep7Main.getSheetID());
            dailyReportStep7Sub.setSheetsubSS2(Integer.parseInt(loginData.getUuserID()));
            dailyReportStep7Sub.setWriteridx2(Integer.parseInt(loginData.getUuserID()));

            dailyReportStep7SubMapper.insertDailyReportStep7sub(dailyReportStep7Sub);
            rtnMap.put("httpCode", 200);

        } catch (Exception e) {
            log.error("Exception["+ e.getMessage() +"]");
        }
        return commonUtil.jsonFormatTransfer(rtnMap);

    }

    //오늘 날짜 받기
    public String getToday(){
        //SimpleDateFormat 객체 생성하여 날짜 포맷 설정
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //현재 시스템 시간을 기반으로 Date 객체 생성
        Date date = new Date(System.currentTimeMillis());


        //Date 객체를 SimpleDateFormat을 사용하여
        //"yyyy-MM-dd" 형식의 문자열로 변환하여 반환
        return formatter.format(date);
    }

    //제출처 주문 정보 조회
    public List<DailyReportStep7Sub> searchOrderList(DailyReportStep7Main dailyReportStep7Main){
        System.out.println(dailyReportStep7Main);
        System.out.println(getSessionLoginData().getUuserID());
        System.out.println(dailyReportStep7MainMapper.selectReceptionList(getSessionLoginData().getUuserID(),dailyReportStep7Main.getDate()));
        return dailyReportStep7MainMapper.selectReceptionList(getSessionLoginData().getUuserID(),dailyReportStep7Main.getDate());
    }

    //제출처 주문 정보 삭제
    public String delete(DailyReportStep7Sub dailyReportStep7Sub){
        Map<String, Object> rtnMap = commonUtil.returnMap();

        int sheetID = dailyReportStep7SubMapper.findBySheetsubID(dailyReportStep7Sub.getSheetsubID());
        System.out.println("sheetID는?"+sheetID);
        boolean chk1 = dailyReportStep7MainMapper.findBySheetID(sheetID);
        System.out.println("chk1은?"+chk1);

        if(chk1==false){
            System.out.println("도달");
            dailyReportStep7Sub.setWriteridx2(Integer.parseInt(getSessionLoginData().getUuserID()));
            dailyReportStep7SubMapper.deleteByOne(dailyReportStep7Sub);
            rtnMap.put("httpCode",200);
        }else{
            rtnMap.put("httpCode",422);
        }
        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public String saveCarData(DailyReportStep7CarNo dailyReportStep7CarNo) {
        Map<String, Object> rtnMap = commonUtil.returnMap();
        try {
            HttpSession session = commonUtil.getSession();
            Login loginData = (Login) session.getAttribute("loginInfo");

            dailyReportStep7CarNo.setCarNoSS2(Integer.parseInt(loginData.getUuserID()));

            step7Mapper.insertCarData(dailyReportStep7CarNo);

            rtnMap.put("httpCode", 200);
        } catch (Exception e) {
            log.error("Exception [" + e.getMessage() +"]");
        }


        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public void carNoList(Model model) {
        model.addAttribute("carNoList", step7Mapper.findCarNoList());
    }

    public String driverList() {
        Map<String, Object> rtnMap = commonUtil.returnMap();
        try {
            HttpSession session = commonUtil.getSession();
            Login loginData = (Login) session.getAttribute("loginInfo");
            DailyReportStep7CarNo dailyReportStep7CarNo = new DailyReportStep7CarNo();
            dailyReportStep7CarNo.setCarNoSS2(Integer.parseInt(loginData.getUuserID()));
            rtnMap.put("driverList", step7Mapper.findCarNoByLoginData(dailyReportStep7CarNo));
            rtnMap.put("httpCode", 200);
        } catch (Exception e) {
            log.error("Exception [" + e.getMessage() +"]");
        }
        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    public String subInfo(int sheetsubID) {
        Map<String, Object> rtnMap = commonUtil.returnMap();
        try {
            DailyReportStep7Sub subData = dailyReportStep7SubMapper.findSubInfoBySheetSubID(sheetsubID);
            rtnMap.put("view", subData);
            rtnMap.put("httpCode", 200);
        } catch (Exception e) {
            log.error("Exception [" + e.getMessage() +"]");
        }

        return commonUtil.jsonFormatTransfer(rtnMap);
    }

    @Transactional
    public String saveDispatchData(int parentID, String dispatchDataList) {
        Map<String, Object> rtnMap = commonUtil.returnMap();
        try {
            DailyReportStep7Sub dailyReportStep7Sub = dailyReportStep7SubMapper.findSubInfoBySheetSubID(parentID);
            int dispatchQty = 0;


            if (dispatchDataList != null && !dispatchDataList.equals("")) {
                JSONArray jsonArray = new JSONArray(dispatchDataList);

                for (Object o : jsonArray) {
                    DailyReportStep7CarNo dispatchData = commonUtil.jsonToObject(o.toString(), DailyReportStep7CarNo.class);
                    dispatchData.setParentID(dailyReportStep7Sub.getSheetID2());
                    dispatchData.setSubID(dailyReportStep7Sub.getSheetsubID());
                    step7Mapper.autoDispatchData(dispatchData);
                    step7Mapper.autoDispatchSubDataSub(dispatchData);
                    log.info(dispatchData.toString());
                    dispatchQty += dispatchData.getCarQty();

                }

                log.info("total Dispatch: " + dispatchQty);

                dailyReportStep7Sub.setQty(dispatchQty);
                step7Mapper.updateQtyParentsData(dailyReportStep7Sub);

                rtnMap.put("httpCode", 200);
            }
        } catch (Exception e) {
            log.error("Exception [" + e.getMessage() +"]");
        }
        return commonUtil.jsonFormatTransfer(rtnMap);
    }

}
