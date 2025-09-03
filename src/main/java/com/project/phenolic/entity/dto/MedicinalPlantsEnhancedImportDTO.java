package com.project.phenolic.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 药用植物增强版Excel导入DTO
 * 支持所有字段的动态映射导入
 *
 * @author lhy
 * @since 2025-07-21
 */
@Data
public class MedicinalPlantsEnhancedImportDTO {

    /**
     * 样品名称
     */
    @NotBlank(message = "样品名称不能为空")
    private String name;

    /**
     * 值
     */
    private Double value;

    /**
     * 批次
     */
    private Integer batch;

    /**
     * 类别
     */
    private String type;

    // 时间字段
    private Integer time1;
    private Integer time2;
    private Integer time3;
    private Integer time4;
    private Integer time5;
    private Integer time6;
    private Integer time7;
    private Integer time8;
    private Integer time9;
    private Integer time10;
    private Integer time11;
    private Integer time12;
    private Integer time13;
    private Integer time14;
    private Integer time15;
    private Integer time16;
    private Integer time17;
    private Integer time18;
    private Integer time19;
    private Integer time20;
    private Integer time21;
    private Integer time22;
    private Integer time23;
    private Integer time24;
    private Integer time25;
    private Integer time26;
    private Integer time27;
    private Integer time28;
    private Integer time29;
    private Integer time30;
    private Integer time31;
    private Integer time32;
    private Integer time33;
    private Integer time34;
    private Integer time35;
    private Integer time36;
    private Integer time37;
    private Integer time38;
    private Integer time39;
    private Integer time40;
    private Integer time41;
    private Integer time42;
    private Integer time43;
    private Integer time44;
    private Integer time45;
    private Integer time46;
    private Integer time47;
    private Integer time48;
    private Integer time49;
    private Integer time50;
    private Integer time51;
    private Integer time52;
    private Integer time53;
    private Integer time54;
    private Integer time55;
    private Integer time56;
    private Integer time57;
    private Integer time58;
    private Integer time59;
    private Integer time60;
    private Integer time61;
    private Integer time62;
    private Integer time63;
    private Integer time64;
    private Integer time65;
    private Integer time66;
    private Integer time67;
    private Integer time68;
    private Integer time69;
    private Integer time70;
    private Integer time71;
    private Integer time72;
    private Integer time73;
    private Integer time74;
    private Integer time75;
    private Integer time76;
    private Integer time77;
    private Integer time78;
    private Integer time79;
    private Integer time80;
    private Integer time81;
    private Integer time82;
    private Integer time83;
    private Integer time84;
    private Integer time85;
    private Integer time86;
    private Integer time87;
    private Integer time88;
    private Integer time89;
    private Integer time90;
    private Integer time91;
    private Integer time92;
    private Integer time93;
    private Integer time94;
    private Integer time95;
    private Integer time96;
    private Integer time97;
    private Integer time98;
    private Integer time99;
    private Integer time100;
    private Integer time101;
    private Integer time102;
    private Integer time103;
    private Integer time104;
    private Integer time105;
    private Integer time106;
    private Integer time107;
    private Integer time108;
    private Integer time109;
    private Integer time110;
    private Integer time111;
    private Integer time112;
    private Integer time113;
    private Integer time114;
    private Integer time115;
    private Integer time116;
    private Integer time117;
    private Integer time118;
    private Integer time119;
    private Integer time120;
    private Integer time121;
    private Integer time122;
    private Integer time123;
    private Integer time124;
    private Integer time125;
    private Integer time126;
    private Integer time127;
    private Integer time128;
    private Integer time129;
    private Integer time130;
    private Integer time131;
    private Integer time132;
    private Integer time133;
    private Integer time134;
    private Integer time135;
    private Integer time136;
    private Integer time137;
    private Integer time138;
    private Integer time139;
    private Integer time140;
    private Integer time141;
    private Integer time142;
    private Integer time143;
    private Integer time144;
    private Integer time145;
    private Integer time146;
    private Integer time147;
    private Integer time148;
    private Integer time149;
    private Integer time150;
    private Integer time151;
    private Integer time152;
    private Integer time153;
    private Integer time154;
    private Integer time155;
    private Integer time156;
    private Integer time157;
    private Integer time158;
    private Integer time159;
    private Integer time160;
    private Integer time161;
    private Integer time162;
    private Integer time163;
    private Integer time164;
    private Integer time165;
    private Integer time166;
    private Integer time167;
    private Integer time168;
    private Integer time169;
    private Integer time170;
    private Integer time171;
    private Integer time172;
    private Integer time173;
    private Integer time174;
    private Integer time175;
    private Integer time176;
    private Integer time177;
    private Integer time178;
    private Integer time179;
    private Integer time180;
    private Integer time181;
    private Integer time182;
    private Integer time183;
    private Integer time184;
    private Integer time185;
    private Integer time186;
    private Integer time187;
    private Integer time188;
    private Integer time189;
    private Integer time190;
    private Integer time191;
    private Integer time192;
    private Integer time193;
    private Integer time194;
    private Integer time195;
    private Integer time196;
    private Integer time197;
    private Integer time198;
    private Integer time199;
    private Integer time200;
    private Integer time201;
    private Integer time202;

    /**
     * 验证必填字段
     *
     * @return 验证结果
     */
    public String validate() {
        if (name == null || name.trim().isEmpty()) {
            return "样品名称不能为空";
        }
//        if (batch == null) {
//            return "批次不能为空";
//        }
        return null;
    }

    /**
     * 检查是否有有效数据
     *
     * @return 是否有有效数据
     */
    public boolean hasValidData() {
        // 至少要有名称和批次
        return name != null && !name.trim().isEmpty();
    }

    /**
     * 获取非空时间字段数量
     *
     * @return 非空时间字段数量
     */
    public int getNonNullTimeFieldCount() {
        int count = 0;
        Integer[] timeFields = {
            time1, time2, time3, time4, time5, time6, time7, time8, time9, time10,
            time11, time12, time13, time14, time15, time16, time17, time18, time19, time20,
            time21, time22, time23, time24, time25, time26, time27, time28, time29, time30,
            time31, time32, time33, time34, time35, time36, time37, time38, time39, time40,
            time41, time42, time43, time44, time45, time46, time47, time48, time49, time50,
            time51, time52, time53, time54, time55, time56, time57, time58, time59, time60,
            time61, time62, time63, time64, time65, time66, time67, time68, time69, time70,
            time71, time72, time73, time74, time75, time76, time77, time78, time79, time80,
            time81, time82, time83, time84, time85, time86, time87, time88, time89, time90,
            time91, time92, time93, time94, time95, time96, time97, time98, time99, time100,
            time101, time102, time103, time104, time105, time106, time107, time108, time109, time110,
            time111, time112, time113, time114, time115, time116, time117, time118, time119, time120,
            time121, time122, time123, time124, time125,
                time126, time127, time128, time129, time130, time131, time132, time133, time134, time135,
            time136, time137, time138, time139, time140, time141, time142, time143, time144, time145,
            time146, time147, time148, time149, time150,
                time151, time152, time153, time154, time155, time156, time157, time158, time159, time160,
            time161, time162, time163, time164, time165, time166, time167, time168, time169, time170,
            time171, time172, time173, time174, time175, time176, time177, time178, time179, time180,
            time181, time182, time183, time184, time185,
                time186, time187, time188, time189, time190, time191, time192, time193, time194, time195,
            time196, time197, time198, time199, time200,
                time201, time202
        };
        
        for (Integer timeField : timeFields) {
            if (timeField != null) {
                count++;
            }
        }
        return count;
    }
}
