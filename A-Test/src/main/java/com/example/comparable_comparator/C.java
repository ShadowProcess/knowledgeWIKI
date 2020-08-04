package com.example.comparable_comparator;

import java.util.Comparator;

//public class C implements Comparator<PkgActivCoupon> {
//
//    @Override
//    public int compare(PkgActivCoupon o1, PkgActivCoupon o2) {

//TODO 用于null排序在前边
//TODO		Comparator.comparing(PkgActivCoupon::getActivity,Comparator.nullsFirst(null))
//TODO				.thenComparing(PkgActivCoupon::getUserCoupon,Comparator.nullsFirst(null))
//TODO				.thenComparing(x -> x.getFlowPackage().getAmount()).reversed();


//        if (o1.getActivity() != null && o2.getActivity() == null) {
//            return -1;
//        } else if (o1.getActivity() == null && o2.getActivity() != null) {
//            return 1;
//        } else {
//            if (o1.getUserCoupon() != null && o2.getUserCoupon() == null) {
//                return -1;
//            } else if (o1.getUserCoupon() == null && o2.getUserCoupon() != null) {
//                return 1;
//            } else {
//                // return 0;
//                // 活动类商品优先显示，其他产品按照价格从低到高排序
//                // return
//                // o1.getFlowPackage().getPrice().compareTo(o2.getFlowPackage().getPrice());
//                // TODO 默认优先显示活动类产品，按照流量包额度由高到低显示；
//                return o2.getFlowPackage().getAmount().compareTo(o1.getFlowPackage().getAmount());
//            }
//        }
//    }
//}
