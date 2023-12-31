// 일괄결재
function approveReceiptsList() {
    $.ajax({
        url: "/dailyReport/receipts/ajax/approve",
        type: "POST",
        data: $("[name=search_frm]").serialize(),
        success: function (data) {
            alert("일괄결재가 완료되었습니다.");
        },
        error: function (error) {
            console.error("오류 발생: " + error);
            alert("오류가 발생했습니다.");
        }
    })
}

//일괄 취소
function cancelApproval() {
    $.ajax({
        url: "/dailyReport/receipts/ajax/cancel",
        type: "POST",
        data: $("[name=search_frm]").serialize(),
        success: function (data) {
            alert("일괄취소가 완료되었습니다.");
        },
        error: function (error) {
            console.error("오류 발생: " + error);
            alert("오류가 발생했습니다.");
        }
    })
}

// 이전화면 버튼 클릭 시 실행되는 함수
function goBack(){
    window.history.back();
};

/* js를 통해 search_btn이 눌릴 수 있도록 처리 */
function clickSearchButton(){
    document.querySelector(".search_btn").onclick();
}

/* 리스트의 행 클릭시, 상태값에 따라 파라미터와 함께 step3 or step7로 이동하도록 처리. */
function clickListThAndRedirect(){
    const listRow = document.querySelector("table tbody");

    listRow.addEventListener("click", (event) => {
        // const currStatus = event.target.parentElement.querySelector(".currStatus"); // 상태 열
        const currStatus = event.target.parentElement.getAttribute("receipt-status");

        if (currStatus) {
            // const status = currStatus.textContent;
            console.log("Clicked Status:", currStatus);
            let subID = event.target.parentElement.getAttribute("receipt-subID")
            let sheetID = event.target.parentElement.getAttribute("receipt-sheetID")
            let writerIDX = event.target.parentElement.getAttribute("receipt-writerIDX")
            let sheetSS2 = event.target.parentElement.getAttribute("receipt-sheetSS2")
            console.log("writerIDX?" + writerIDX)
            console.log("sheetSS2?" + sheetSS2)

            if (currStatus === "제출") {  // "제출" 상태일 때는 무조건 step3로 이동
                window.location.href = "/dailyReport/form" + "?sheetID=" +  sheetID;
            } else if (currStatus === "배차" && writerIDX === sheetSS2) { // "배차" 상태일 때 작성자가 나자신이면 step7로 이동
                window.location.href = "/dailyReport/orderform" + "?subID=" +  subID + "&sheetID=" + sheetID;
            }else if (currStatus ==="배차" && writerIDX != sheetSS2) { // "배차" 상태일 때 작성자가 기사면 알림 띄우고 이동은 x
                console.log("기사가 등록한 전표입니다.")
                alert("기사가 등록한 전표입니다.")
            }
        }
    });
}

/* 선택한 옵션을 통해 데이터를 받아올 수 있도록 ajax POST 처리. */
function bindList() {
    $.ajax({
        url: "/dailyReport/receipts/ajax/list",
        type: "POST",
        data: $("[name=search_frm]").serialize(),
        success: function (data) {
            printList(data);
            printSummary(data);
        }
    })
}



