//package com.example.springbootjpa.ruler;
//
//import com.google.common.collect.Sets;
//import com.zouqi.dataflow.jpa.repos.G5RootPkgConfigRepository;
//import com.zouqi.dataflow.service.FlowTagService;
//import com.zouqi.dataflow.util.BizUtils;
//import com.zouqi.dataflow.util.DTF;
//import lombok.RequiredArgsConstructor;
//import lombok.val;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDateTime;
//import java.util.Arrays;
//import java.util.stream.Collectors;
//
//@Configuration
//@RequiredArgsConstructor
//public class RulerConfiguration {
//
//    private final FlowTagService flowTagService;
//    private final G5RootPkgConfigRepository g5RootPkgConfigRepository;
//
//    @Bean // 用户是政企用户
//    public Ruler isCustTypeZQ() {
//        return Rulers.newRuler("is_cust_type_zq", BizUtils::isCustTypeZQ);
//    }
//
//    @Bean//用户是否在主套餐配置表中 ，是否可以升级5G
//    public Ruler inG5RootPkgConfig() {
//        return Rulers.newRuler("in_g5_root_pkg_config", () -> {
//            val offerIdSet = BizUtils.getOfferIdSet();  //获取用户的offerIdSet
//            return g5RootPkgConfigRepository.spec() // 查询用户是否在主套餐配置表中
//                    .in("rootOfferId", offerIdSet)
//                    .count() > 0;
//        });
//    }
//
//    @Bean // 用户offerId列表包含参数中任何一个offerId
//    public Ruler offerIdContainsOneOf() {
//        return Rulers.newRuler("offer_id_contains_one_of", offerIds -> {
//            val idSet = Arrays.stream(offerIds.split(",")).collect(Collectors.toSet());
//            val offerIdSet = BizUtils.getOfferIdSet();  //获取用户的offerIdSet
//            val intersectionEmpty = Sets.intersection(offerIdSet, idSet) // 比较便宜的主套餐
//                    .isEmpty();// 交集为空
//            return !intersectionEmpty; // 返回交集非空
//        });
//    }
//
//    @Bean// 用户标签列表包含参数中任意一个标签
//    public Ruler hasTagOneOf() {
//        return Rulers.newRuler("has_tag_one_of", tags -> {
//            val mobileNum = BizUtils.getMobileNum();
//            return Arrays.stream(tags.split(","))
//                    .anyMatch(x -> flowTagService.getUserTag(mobileNum, x) != null);
//        });
//    }
//
//    @Bean // 用户月租费用大于等于参数
//    public Ruler monthFeeGe() {
//        return Rulers.newRuler("month_fee_ge", bound -> {
//            double fee = Double.parseDouble(bound);
//            val offerIdSet = BizUtils.getOfferIdSet();  //获取用户的offerIdSet
//            return g5RootPkgConfigRepository.spec()
//                    .in("rootOfferId", offerIdSet)                 // 月租费用
//                    .ge("monthlyFee", fee)
//                    .findOne().isPresent();
//        });
//    }
//
//    @Bean // 是否是当日的第一次登录
//    public Ruler dailyFirstLogin() {
//        return Rulers.newRuler("daily_first_login", BizUtils::isFirstLoginDaily);
//    }
//
//    @Bean // 月租费用等于参数中任意一个值
//    public Ruler monthFeeEqOneOf() {
//        return Rulers.newRuler("month_fee_eq_one_of", fees -> {
//            val feeSet = Arrays.stream(fees.split(","))
//                    .map(Double::parseDouble)
//                    .collect(Collectors.toSet());
//            val offerIdSet = BizUtils.getOfferIdSet();  //获取用户的offerIdSet
//            return g5RootPkgConfigRepository.spec()
//                    .in("rootOfferId", offerIdSet) // 月租费用
//                    .in("monthlyFee", feeSet)
//                    .findOne().isPresent();
//        });
//    }
//
//    @Bean // 当前时间在参数时间闭区间内
//    public Ruler dateBetween() {
//        return Rulers.newRuler("date_between", dateRange -> {
//            val now = DTF.yyyyMMddHHmmss.format(LocalDateTime.now());
//            val rangeArray = dateRange.split(",");
//            return now.compareTo(rangeArray[0]) >= 0 && now.compareTo(rangeArray[1]) <= 0;
//        });
//    }
//}
