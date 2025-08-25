package com.project.phenolic.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author lhy
 * @since 2025-08-22
 */
@Getter
@Setter
@TableName("unknown_plants")
public class UnknownPlants implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelIgnore
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 样品名称
     */
    @ExcelProperty(value = "name", index = 0)
    private String name;

    /**
     * 值
     */
    @ExcelIgnore
    private Double value;

    /**
     * 批次
     */
    @ExcelIgnore
    private Integer batch;

    /**
     * 类别
     */
    @ExcelIgnore
    private String type;

    @ExcelIgnore
    private String top1;

    @ExcelIgnore
    private String top2;

    @ExcelIgnore
    private String top3;

    @ExcelIgnore
    private String top4;

    @ExcelIgnore
    private String top5;

    @ExcelIgnore
    private String top6;

    @ExcelProperty(value = "3.70", index = 1)
    private Integer time1;

    @ExcelProperty(value = "3.89", index = 2)
    private Integer time2;

    @ExcelProperty(value = "4.09", index = 3)
    private Integer time3;

    @ExcelProperty(value = "4.26", index = 4)
    private Integer time4;

    @ExcelProperty(value = "4.49", index = 5)
    private Integer time5;

    @ExcelProperty(value = "4.71", index = 6)
    private Integer time6;

    @ExcelProperty(value = "4.87", index = 7)
    private Integer time7;

    @ExcelProperty(value = "5.10", index = 8)
    private Integer time8;

    @ExcelProperty(value = "5.30", index = 9)
    private Integer time9;

    @ExcelProperty(value = "5.51", index = 10)
    private Integer time10;

    @ExcelProperty(value = "5.72", index = 11)
    private Integer time11;

    @ExcelProperty(value = "5.86", index = 12)
    private Integer time12;

    @ExcelProperty(value = "6.09", index = 13)
    private Integer time13;

    @ExcelProperty(value = "6.30", index = 14)
    private Integer time14;

    @ExcelProperty(value = "6.55", index = 15)
    private Integer time15;

    @ExcelProperty(value = "6.69", index = 16)
    private Integer time16;

    @ExcelProperty(value = "6.86", index = 17)
    private Integer time17;

    @ExcelProperty(value = "7.14", index = 18)
    private Integer time18;

    @ExcelProperty(value = "7.25", index = 19)
    private Integer time19;

    @ExcelProperty(value = "7.47", index = 20)
    private Integer time20;

    @ExcelProperty(value = "7.73", index = 21)
    private Integer time21;

    @ExcelProperty(value = "7.86", index = 22)
    private Integer time22;

    @ExcelProperty(value = "8.07", index = 23)
    private Integer time23;

    @ExcelProperty(value = "8.16", index = 24)
    private Integer time24;

    @ExcelProperty(value = "8.30", index = 25)
    private Integer time25;

    @ExcelProperty(value = "8.48", index = 26)
    private Integer time26;

    @ExcelProperty(value = "8.68", index = 27)
    private Integer time27;

    @ExcelProperty(value = "8.93", index = 28)
    private Integer time28;

    @ExcelProperty(value = "9.09", index = 29)
    private Integer time29;

    @ExcelProperty(value = "9.29", index = 30)
    private Integer time30;

    @ExcelProperty(value = "9.44", index = 31)
    private Integer time31;

    @ExcelProperty(value = "9.54", index = 32)
    private Integer time32;

    @ExcelProperty(value = "9.71", index = 33)
    private Integer time33;

    @ExcelProperty(value = "9.88", index = 34)
    private Integer time34;

    @ExcelProperty(value = "10.05", index = 35)
    private Integer time35;

    @ExcelProperty(value = "10.17", index = 36)
    private Integer time36;

    @ExcelProperty(value = "10.29", index = 37)
    private Integer time37;

    @ExcelProperty(value = "10.46", index = 38)
    private Integer time38;

    @ExcelProperty(value = "10.68", index = 39)
    private Integer time39;

    @ExcelProperty(value = "10.91", index = 40)
    private Integer time40;

    @ExcelProperty(value = "11.10", index = 41)
    private Integer time41;

    @ExcelProperty(value = "11.22", index = 42)
    private Integer time42;

    @ExcelProperty(value = "11.37", index = 43)
    private Integer time43;

    @ExcelProperty(value = "11.52", index = 44)
    private Integer time44;

    @ExcelProperty(value = "11.64", index = 45)
    private Integer time45;

    @ExcelProperty(value = "11.88", index = 46)
    private Integer time46;

    @ExcelProperty(value = "12.13", index = 47)
    private Integer time47;

    @ExcelProperty(value = "12.28", index = 48)
    private Integer time48;

    @ExcelProperty(value = "12.49", index = 49)
    private Integer time49;

    @ExcelProperty(value = "12.69", index = 50)
    private Integer time50;

    @ExcelProperty(value = "12.90", index = 51)
    private Integer time51;

    @ExcelProperty(value = "13.09", index = 52)
    private Integer time52;

    @ExcelProperty(value = "13.33", index = 53)
    private Integer time53;

    @ExcelProperty(value = "13.44", index = 54)
    private Integer time54;

    @ExcelProperty(value = "13.55", index = 55)
    private Integer time55;

    @ExcelProperty(value = "13.69", index = 56)
    private Integer time56;

    @ExcelProperty(value = "13.94", index = 57)
    private Integer time57;

    @ExcelProperty(value = "14.06", index = 58)
    private Integer time58;

    @ExcelProperty(value = "14.30", index = 59)
    private Integer time59;

    @ExcelProperty(value = "14.48", index = 60)
    private Integer time60;

    @ExcelProperty(value = "14.70", index = 61)
    private Integer time61;

    @ExcelProperty(value = "14.84", index = 62)
    private Integer time62;

    @ExcelProperty(value = "14.96", index = 63)
    private Integer time63;

    @ExcelProperty(value = "15.10", index = 64)
    private Integer time64;

    @ExcelProperty(value = "15.30", index = 65)
    private Integer time65;

    @ExcelProperty(value = "15.51", index = 66)
    private Integer time66;

    @ExcelProperty(value = "15.70", index = 67)
    private Integer time67;

    @ExcelProperty(value = "15.90", index = 68)
    private Integer time68;

    @ExcelProperty(value = "16.10", index = 69)
    private Integer time69;

    @ExcelProperty(value = "16.26", index = 70)
    private Integer time70;

    @ExcelProperty(value = "16.38", index = 71)
    private Integer time71;

    @ExcelProperty(value = "16.51", index = 72)
    private Integer time72;

    @ExcelProperty(value = "16.71", index = 73)
    private Integer time73;

    @ExcelProperty(value = "16.88", index = 74)
    private Integer time74;

    @ExcelProperty(value = "17.05", index = 75)
    private Integer time75;

    @ExcelProperty(value = "17.13", index = 76)
    private Integer time76;

    @ExcelProperty(value = "17.28", index = 77)
    private Integer time77;

    @ExcelProperty(value = "17.45", index = 78)
    private Integer time78;

    @ExcelProperty(value = "17.55", index = 79)
    private Integer time79;

    @ExcelProperty(value = "17.69", index = 80)
    private Integer time80;

    @ExcelProperty(value = "17.90", index = 81)
    private Integer time81;

    @ExcelProperty(value = "18.10", index = 82)
    private Integer time82;

    @ExcelProperty(value = "18.25", index = 83)
    private Integer time83;

    @ExcelProperty(value = "18.36", index = 84)
    private Integer time84;

    @ExcelProperty(value = "18.48", index = 85)
    private Integer time85;

    @ExcelProperty(value = "18.71", index = 86)
    private Integer time86;

    @ExcelProperty(value = "18.90", index = 87)
    private Integer time87;

    @ExcelProperty(value = "19.13", index = 88)
    private Integer time88;

    @ExcelProperty(value = "19.32", index = 89)
    private Integer time89;

    @ExcelProperty(value = "19.49", index = 90)
    private Integer time90;

    @ExcelProperty(value = "19.70", index = 91)
    private Integer time91;

    @ExcelProperty(value = "19.89", index = 92)
    private Integer time92;

    @ExcelProperty(value = "20.10", index = 93)
    private Integer time93;

    @ExcelProperty(value = "20.23", index = 94)
    private Integer time94;

    @ExcelProperty(value = "20.37", index = 95)
    private Integer time95;

    @ExcelProperty(value = "20.46", index = 96)
    private Integer time96;

    @ExcelProperty(value = "20.66", index = 97)
    private Integer time97;

    @ExcelProperty(value = "20.75", index = 98)
    private Integer time98;

    @ExcelProperty(value = "20.91", index = 99)
    private Integer time99;

    @ExcelProperty(value = "21.09", index = 100)
    private Integer time100;

    @ExcelProperty(value = "21.30", index = 101)
    private Integer time101;

    @ExcelProperty(value = "21.46", index = 102)
    private Integer time102;

    @ExcelProperty(value = "21.55", index = 103)
    private Integer time103;

    @ExcelProperty(value = "21.71", index = 104)
    private Integer time104;

    @ExcelProperty(value = "21.90", index = 105)
    private Integer time105;

    @ExcelProperty(value = "22.06", index = 106)
    private Integer time106;

    @ExcelProperty(value = "22.14", index = 107)
    private Integer time107;

    @ExcelProperty(value = "22.29", index = 108)
    private Integer time108;

    @ExcelProperty(value = "22.49", index = 109)
    private Integer time109;

    @ExcelProperty(value = "22.72", index = 110)
    private Integer time110;

    @ExcelProperty(value = "22.88", index = 111)
    private Integer time111;

    @ExcelProperty(value = "23.05", index = 112)
    private Integer time112;

    @ExcelProperty(value = "23.16", index = 113)
    private Integer time113;

    @ExcelProperty(value = "23.29", index = 114)
    private Integer time114;

    @ExcelProperty(value = "23.49", index = 115)
    private Integer time115;

    @ExcelProperty(value = "23.70", index = 116)
    private Integer time116;

    @ExcelProperty(value = "23.83", index = 117)
    private Integer time117;

    @ExcelProperty(value = "23.95", index = 118)
    private Integer time118;

    @ExcelProperty(value = "24.10", index = 119)
    private Integer time119;

    @ExcelProperty(value = "24.28", index = 120)
    private Integer time120;

    @ExcelProperty(value = "24.51", index = 121)
    private Integer time121;

    @ExcelProperty(value = "24.67", index = 122)
    private Integer time122;

    @ExcelProperty(value = "24.92", index = 123)
    private Integer time123;

    @ExcelProperty(value = "25.11", index = 124)
    private Integer time124;

    @ExcelProperty(value = "25.26", index = 125)
    private Integer time125;

    @ExcelProperty(value = "25.52", index = 126)
    private Integer time126;

    @ExcelProperty(value = "25.72", index = 127)
    private Integer time127;

    @ExcelProperty(value = "25.88", index = 128)
    private Integer time128;

    @ExcelProperty(value = "26.06", index = 129)
    private Integer time129;

    @ExcelProperty(value = "26.14", index = 130)
    private Integer time130;

    @ExcelProperty(value = "26.32", index = 131)
    private Integer time131;

    @ExcelProperty(value = "26.52", index = 132)
    private Integer time132;

    @ExcelProperty(value = "26.70", index = 133)
    private Integer time133;

    @ExcelProperty(value = "26.89", index = 134)
    private Integer time134;

    @ExcelProperty(value = "27.10", index = 135)
    private Integer time135;

    @ExcelProperty(value = "27.29", index = 136)
    private Integer time136;

    @ExcelProperty(value = "27.50", index = 137)
    private Integer time137;

    @ExcelProperty(value = "27.70", index = 138)
    private Integer time138;

    @ExcelProperty(value = "27.90", index = 139)
    private Integer time139;

    @ExcelProperty(value = "28.08", index = 140)
    private Integer time140;

    @ExcelProperty(value = "28.32", index = 141)
    private Integer time141;

    @ExcelProperty(value = "28.50", index = 142)
    private Integer time142;

    @ExcelProperty(value = "28.69", index = 143)
    private Integer time143;

    @ExcelProperty(value = "28.89", index = 144)
    private Integer time144;

    @ExcelProperty(value = "29.04", index = 145)
    private Integer time145;

    @ExcelProperty(value = "29.14", index = 146)
    private Integer time146;

    @ExcelProperty(value = "29.31", index = 147)
    private Integer time147;

    @ExcelProperty(value = "29.46", index = 148)
    private Integer time148;

    @ExcelProperty(value = "29.70", index = 149)
    private Integer time149;

    @ExcelProperty(value = "29.89", index = 150)
    private Integer time150;

    @ExcelProperty(value = "30.13", index = 151)
    private Integer time151;

    @ExcelProperty(value = "30.29", index = 152)
    private Integer time152;

    @ExcelProperty(value = "30.45", index = 153)
    private Integer time153;

    @ExcelProperty(value = "30.54", index = 154)
    private Integer time154;

    @ExcelProperty(value = "30.69", index = 155)
    private Integer time155;

    @ExcelProperty(value = "30.90", index = 156)
    private Integer time156;

    @ExcelProperty(value = "31.10", index = 157)
    private Integer time157;

    @ExcelProperty(value = "31.34", index = 158)
    private Integer time158;

    @ExcelProperty(value = "31.47", index = 159)
    private Integer time159;

    @ExcelProperty(value = "31.71", index = 160)
    private Integer time160;

    @ExcelProperty(value = "31.92", index = 161)
    private Integer time161;

    @ExcelProperty(value = "32.09", index = 162)
    private Integer time162;

    @ExcelProperty(value = "32.28", index = 163)
    private Integer time163;

    @ExcelProperty(value = "32.48", index = 164)
    private Integer time164;

    @ExcelProperty(value = "32.68", index = 165)
    private Integer time165;

    @ExcelProperty(value = "32.89", index = 166)
    private Integer time166;

    @ExcelProperty(value = "33.12", index = 167)
    private Integer time167;

    @ExcelProperty(value = "33.30", index = 168)
    private Integer time168;

    @ExcelProperty(value = "33.52", index = 169)
    private Integer time169;

    @ExcelProperty(value = "33.68", index = 170)
    private Integer time170;

    @ExcelProperty(value = "33.89", index = 171)
    private Integer time171;

    @ExcelProperty(value = "34.08", index = 172)
    private Integer time172;

    @ExcelProperty(value = "34.28", index = 173)
    private Integer time173;

    @ExcelProperty(value = "34.53", index = 174)
    private Integer time174;

    @ExcelProperty(value = "34.67", index = 175)
    private Integer time175;

    @ExcelProperty(value = "34.92", index = 176)
    private Integer time176;

    @ExcelProperty(value = "35.07", index = 177)
    private Integer time177;

    @ExcelProperty(value = "35.27", index = 178)
    private Integer time178;

    @ExcelProperty(value = "35.49", index = 179)
    private Integer time179;

    @ExcelProperty(value = "35.56", index = 180)
    private Integer time180;

    @ExcelProperty(value = "35.70", index = 181)
    private Integer time181;

    @ExcelProperty(value = "35.83", index = 182)
    private Integer time182;

    @ExcelProperty(value = "35.95", index = 183)
    private Integer time183;

    @ExcelProperty(value = "36.14", index = 184)
    private Integer time184;

    @ExcelProperty(value = "36.31", index = 185)
    private Integer time185;

    @ExcelProperty(value = "36.51", index = 186)
    private Integer time186;

    @ExcelProperty(value = "36.68", index = 187)
    private Integer time187;

    @ExcelProperty(value = "36.90", index = 188)
    private Integer time188;

    @ExcelProperty(value = "37.08", index = 189)
    private Integer time189;

    @ExcelProperty(value = "37.30", index = 190)
    private Integer time190;

    @ExcelProperty(value = "37.50", index = 191)
    private Integer time191;

    @ExcelProperty(value = "37.67", index = 192)
    private Integer time192;

    @ExcelProperty(value = "37.87", index = 193)
    private Integer time193;

    @ExcelProperty(value = "38.11", index = 194)
    private Integer time194;

    @ExcelProperty(value = "38.33", index = 195)
    private Integer time195;

    @ExcelProperty(value = "38.50", index = 196)
    private Integer time196;

    @ExcelProperty(value = "38.70", index = 197)
    private Integer time197;

    @ExcelProperty(value = "38.93", index = 198)
    private Integer time198;

    @ExcelProperty(value = "39.09", index = 199)
    private Integer time199;

    @ExcelProperty(value = "39.31", index = 200)
    private Integer time200;

    @ExcelProperty(value = "39.51", index = 201)
    private Integer time201;

    @ExcelProperty(value = "39.69", index = 202)
    private Integer time202;

}
