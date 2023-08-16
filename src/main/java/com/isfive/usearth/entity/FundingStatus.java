package com.isfive.usearth.entity;

public enum FundingStatus {
    ORDER,              // 주문완료
    PAYMENT,            // 결제완료
    REJECT,             // 거절
    REFUNDED,           // 환불신청
    REFUND_COMPLETED,   // 환불완료
    CANCEL              // 취소
}
