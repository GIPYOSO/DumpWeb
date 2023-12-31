<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="layerMask dis-n pop-wrap" id="pop-caraddform" tabindex="0">
  <div class="layerMask__inner" style="height: 75vh; width: 95%">
    <section class="layer-popup" style="height: 100%;">
      <div class="pop-header modal-title" style="padding: 20px 15px;">
        <h3 id="helpMsgTitle"><%--도움말 (코드)--%>차량등록</h3>

        <button type="button" onclick="closePopUpTest(this);" class="pop-close">
          <i class="ico-close-b"></i>
          <span class="offscreen">닫기버튼</span>
        </button>
      </div>
      <div class="popSearch-listWrap unspecifiedFromArea" style="padding-bottom: 60px">
        <form name="cardatafrm">
          <ul class="mtable__ul">
            <li><label >차량번호</label>
              <span class="content" style="padding-left: 100px;">
                <input type="text" style="width: 100%;" name="carNoKey" autocomplete="off">
              </span>
            </li>
            <li><label>전체 차량번호</label>
              <span class="content" style="padding-left: 100px;">
                  <div class="input-group select">
                    <input type="text" class="trn" style="width: 100%;" name="carNoFull" id="carNoFull" autocomplete="off">
                    <span class="input-group-clear dis-n" id="carNoFull_clear">x</span>
                    <select class="carNoFull" id="carNoFullBox" onchange="$.selectBoxChange(this.value, 'carNoFull');">
                    <option value="">직접입력</option>
                      <c:forEach var="item" items="${carNoList}">
                        <option value="${item}">${item}</option>
                      </c:forEach>
                    </select>
              </div>
              </span>
            </li>
            <li><label>휴대폰 번호</label>
              <span class="content" style="padding-left: 100px;">
                <input type="text" style="width: 100%;" name="carNoHp" autocomplete="off">
              </span></li>
            <li><label>기사명</label>
              <span class="content" style="padding-left: 100px;">
                <input type="text" style="width: 100%;" name="carNoName" autocomplete="off">
              </span></li>
          </ul>
        </form>
        <div class="btn-area">
            <input type="button" class="btn btn-blue" value="등록" onclick="saveCarData();">
            <input type="button" class="btn btn-white" value="닫기" onclick="closePopUpTest(this);">
        </div>
      </div>


    </section>
  </div>
</div>
