<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<div id="popup" class="popup">
    <div class="popup-content">
        <div class="mt10">
            <form method="post" name="frm">
                <input type="hidden" name="sheetsubID" id="sheetsubID" value="${!empty view ? view.sheetSubID : 0}">

                <div class="mtable">
                    <ul class="mtable__ul" style="border-bottom: 1px solid transparent !important;">

                        <li>
                            <label style="font-size : var( --main-font-size);">상차지</label>
                            <img src="/resources/image/icons/ico_mic.png" alt="마이크" class="icon_mic">
                            <span class="content">
                                    <input type="text" class="wp100 voice input" name="fromsite" id="fromsite" placeholder="상차지" value="${!empty view ? view.fromsite : ''}">
                                </span>
                        </li>

                        <li>
                            <label style="font-size : var( --main-font-size);">하차지</label>
                            <img src="/resources/image/icons/ico_mic.png" alt="마이크" class="icon_mic">
                            <span class="content">
                                    <input type="text" class="wp100 voice input" name="tosite" id="tosite" value="${!empty view ? view.tosite : ''}"
                                           placeholder="하차지" autocomplete="off">
                                </span>
                        </li>

                        <li>
                            <label style="font-size : var( --main-font-size);">품목</label>
                            <img src="/resources/image/icons/ico_mic.png" alt="마이크" class="icon_mic">
                            <span class="content">
                                    <input type="text" class="wp100 voice input" name="item" id="item" value="${!empty view ? view.item : ''}"
                                           placeholder="품목" autocomplete="off">
                                </span>
                        </li>

                        <li>
                            <label style="font-size : var( --main-font-size);">대수</label>
                            <span class="content">
                                     <input type="number" class="wp100 ml75 input" name="Qty" id="Qty" placeholder="대수" autocomplete="off">
                                </span>
                        </li>

                        <li>
                            <label style="font-size : var( --main-font-size);">비고</label>
                            <span class="content">
                                <textarea class="Rem ml75 input" name="Rem" id="Rem" cols="40" rows="1" autocomplete="off"
                                          style="height: auto; resize:none; overflow-y: auto;"></textarea>
                        </li>

                        <div style="display: block; height: 30px; padding-top: 3px;">
                            <label for="checkbox" id="checkboxLabel" style="height: 15px; font-size: var( --main-font-size);">운반 금액 표시:</label>
                            <input type="checkbox" id="showHideCheckbox" onchange="showOrHide()" style="margin-left: 95px; width: 14px; height: 14px;">
                        </div>
                        <div class="" id="hiddenPart">
                            <form method="post" name="frm">
                                <div>
                                    <ul>
                                        <li>
                                            <label style="font-size : var( --main-font-size);">운반 단가</label>
                                            <span class="content">
                                                 <input type="number" class="wp70 ml75 input" name="Qtyup" id="Qtyup" placeholder="운반 단가" autocomplete="off">
                                            </span>
                                        </li>
                                        <li>
                                            <label style="font-size : var( --main-font-size);">운반 총액</label>
                                            <span class="content">
                                                <input type="number" class="wp70 ml75 input" name="totalAmount" id="totalAmount"
                                                      placeholder="합계" readonly autocomplete="off">
                                            </span>
                                        </li>
                                    </ul>
                                </div>
                            </form>
                        </div>
                    </ul>
                </div>
            </form>
        </div>
        <div class="btn-area">

            <%-- 신규저장 --%>

            <button class="btn btn-white btn-popup unsaved" type="button" onclick="$.emptyRow();" >
                취소
            </button>
            <button class="btn btn-blue btn-popup unsaved" type="button" onclick="$.save();" onkeydown="if(event.keyCode == 13) return false;">
                저장
            </button>
            <%-- 불러온 데이터 일때  --%>
            <button class="btn btn-white btn-popup saved" type="button" onclick="$.confirmRemoval();">
                삭제
            </button>
            <button class="btn btn-blue btn-popup saved" type="button" onclick="$.editRow();" >
                수정
            </button>
            <%-- 공통 --%>
            <button id="closePopupButton" class="btn btn-white btn-popup" type="button" onclick="closePop();">
                닫기
            </button>
        </div>
    </div>
</div>