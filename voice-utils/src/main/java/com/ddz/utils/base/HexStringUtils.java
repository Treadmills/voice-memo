package com.ddz.utils.base;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2018/10/15.
 */
public class HexStringUtils {
    private static final char[] DIGITS_HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final static Logger logger = LoggerFactory.getLogger(HexStringUtils.class);

    protected static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = DIGITS_HEX[(0xF0 & data[i]) >>> 4];
            out[j++] = DIGITS_HEX[0x0F & data[i]];
        }
        return out;
    }

    protected static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 0x01) != 0) {
            throw new RuntimeException("字符个数应该为偶数");
        }
        byte[] out = new byte[len >> 1];
        for (int i = 0, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f |= toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }
        return out;
    }

    protected static int toDigit(char ch, int index) {
        int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public static String toHexString(byte[] bs) {
        return new String(encodeHex(bs));
    }

    public static String hexString2Bytes(String hex) {
        return new String(decodeHex(hex.toCharArray()));
    }

    public static byte[] chars2Bytes(char[] bs) {
        return decodeHex(bs);
    }

    public static void printlnByte(byte[] bytes) throws Exception {
        int length = bytes.length;
        int n = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            n = bytes[i] & 0xFF;
            if (n < 16)
                builder.append(0);
            builder.append(Integer.toHexString(n).toUpperCase());
        }
        logger.info("receive data:" + builder.toString() + "......[" + new Timestamp(System.currentTimeMillis()) + "]");
    }

    public static void printlnByteSend(byte[] bytes) throws Exception {
        int length = bytes.length;
        int n = 0;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            n = bytes[i] & 0xFF;
            if (n < 16)
                builder.append(0);
            builder.append(Integer.toHexString(n).toUpperCase());
        }
        logger.info("Send data:" + builder.toString() + "......[" + new Timestamp(System.currentTimeMillis()) + "]");
    }

    /**
     * 16字节数据补充对齐
     *
     * @param buf    原始数据
     * @param length 补充后数据长度
     * @return
     */
    public static byte[] alignmentHex(byte[] buf, int length) {
        if (buf == null || buf.length >= length) {
            return buf;
        }
        byte[] buf1 = new byte[length];
        System.arraycopy(buf, 0, buf1, 0, buf.length);
        for (int i = 0; i < (length - buf.length); i++) {
            buf1[buf.length + 1] = (byte) 0x00;
        }
        return buf1;
    }

    /**
     * To byte array byte [ ].
     *
     * @param hexString the hex string
     * @return the byte [ ]
     */
    public static byte[] hexStringToByteArray(String hexString) {
        if (StringUtils.isEmpty(hexString)) {
            return null;
        }
        hexString = hexString.toLowerCase();
        final byte[] byteArray = new byte[hexString.length() >> 1];
        int index = 0;
        for (int i = 0; i < hexString.length(); i++) {
            if (index > hexString.length() - 1) {
                return byteArray;
            }
            byte highDit = (byte) (Character.digit(hexString.charAt(index), 16) & 0xFF);
            byte lowDit = (byte) (Character.digit(hexString.charAt(index + 1), 16) & 0xFF);
            byteArray[i] = (byte) (highDit << 4 | lowDit);
            index += 2;
        }
        return byteArray;
    }

    /**
     * 16字节数据补充对齐
     *
     * @param buf       原始数据
     * @param length    补充后数据长度
     * @param signature 签名, MessageId低8位
     * @return
     */
    public static byte[] alignmentHex(byte[] buf, int length, byte signature) {
        if (buf == null || buf.length == 0) {
            return buf;
        }
        int count = buf.length / 16;

        if (buf.length % 16 != 0) {
            count++;
        }
        byte[] buf1 = new byte[count * 16];
        System.arraycopy(buf, 0, buf1, 0, buf.length);
        /*for (int i=0;i<(count-buf.length-1);i++){
            buf1[buf.length+i] = (byte)0x00;
        }*/
        buf1[count * 16 - 1] = signature;
        return buf1;
    }

    /**
     * 解析洗车机故障信息
     *
     * @param bufs 原始故障数据
     *
     * @return
     */

    public static String parsingData(byte[] bufs) {
        String error = BitOperator.bytesToBit(bufs);
        logger.info(error);
        char[] errors = error.toCharArray();
        return parsingData(errors);
    }



    /**
     * 解析洗车机故障信息
     *
     * @param errors 原始故障数据
     *              //00000000000000000000000000000110
     * @return
     */

    public static String parsingData(char[] errors) {
        //AA0021104400060000000000FD2E36865D0B4C09
        // 00000001
        // 000000000000000691
        String error_msg = "";
        //00000000 00000000 00000000 00000110
        if (errors[32 - 1] == '1') {
            error_msg += "PLC通讯失败,";
        }
        if (errors[32 - 2] == '1') {
            error_msg += "轮刷限位报警,";
        }
        if (errors[32 - 3] == '1') {
            error_msg += "轮刷过载报警,";
        }
        if (errors[32 - 4] == '1') {
            error_msg += "顶刷电机过载报警,";
        }
        if (errors[32 - 5] == '1') {
            error_msg += "侧刷旋转电机过载报警,";
        }
        if (errors[32 - 6] == '1') {
            error_msg += "侧刷行走电机过载报警,";
        }
        if (errors[32 - 7] == '1') {
            error_msg += "风机系统过载报警,";
        }
        if (errors[32 - 8] == '1') {
            error_msg += "行走电机过载报警,";
        }
        if (errors[32 - 9] == '1') {
            error_msg += "水泵过载报警,";
        }
        if (errors[32 - 10] == '1') {
            error_msg += "顶刷上下限位报警,";
        }
        if (errors[32 - 11] == '1') {
            error_msg += "防撞防护报警,";
        }
        if (errors[32 - 12] == '1') {
            error_msg += "左侧刷内外限位异常报警,";
        }
        if (errors[32 - 13] == '1') {
            error_msg += "右侧刷内外限位异常报警,";
        }
        if (errors[32 - 14] == '1') {
            error_msg += "一期,";
        }
        if (errors[32 - 15] == '1') {
            error_msg += "二期,";
        }
        if (errors[32 - 16] == '1') {
            error_msg += "三期,";
        }
        if (errors[32 - 17] == '1') {
            error_msg += "四期,";
        }
        if (errors[32 - 18] == '1') {
            error_msg += "试使用期限已到,";
        }
        if (errors[32 - 19] == '1') {
            error_msg += "行走限位异常,";
        }
        if (errors[32 - 20] == '1') {
            error_msg += "顶刷限位异常,";
        }
        if (errors[32 - 21] == '1') {
            error_msg += "气源异常,";
        }
        if (errors[32 - 22] == '1') {
            error_msg += "相序异常,";
        }
        if (errors[32 - 23] == '1') {
            error_msg += "五期,";
        }
        if (errors[32 - 24] == '1') {
            error_msg += "洗车量设定提醒,";
        }
        if (errors[32 - 25] == '1') {
            error_msg += "顶刷大电流保护,";
        }
        if (errors[32 - 26] == '1') {
            error_msg += "顶刷无电流保护,";
        }
        if (errors[32 - 27] == '1') {
            error_msg += "清水不足,";
        }
        if (errors[32 - 28] == '1') {
            error_msg += "中水不足,";
        }
        if (errors[32 - 29] == '1') {
            error_msg += "泥土疏松剂不足,";
        }
        if (errors[32 - 30] == '1') {
            error_msg += "泡沫剂不足,";
        }
        if (errors[32 - 31] == '1') {
            error_msg += "水蜡剂不足,";
        }
        if (errors[32 - 32] == '1') {
            error_msg += "干燥剂不足,";
        }
        if (error_msg != "") {
            error_msg = error_msg.substring(0, error_msg.length() - 1);
        }
        return error_msg;
    }

    public static String generateDebugCmd(int[] cmds){
        if(cmds==null|| cmds.length!=25){
            return null;
        }
        String debugs = "00000000" +
                "00000000" +
                "00000000" +
                "00000000" +
                "00000000" +
                "00000000" +
                "00000000";

        char[] debugcmds = debugs.toCharArray();
        if(cmds[0]==1){
            logger.info("-->> 手动/行走正传");
            debugcmds[debugcmds.length-1] = '1';
        }else if(cmds[0]==2){
            logger.info("-->> 手动/行走反转");
            debugcmds[debugcmds.length-2] = '1';
        }
        if(cmds[1]==1){
            logger.info("-->> 手动/行走中速");
            debugcmds[debugcmds.length-3] = '1';
        }else if(cmds[1]==2){
            logger.info("-->> 手动/行走低速");
            debugcmds[debugcmds.length-4] = '1';
        }
        if(cmds[2]==1){
            logger.info("-->> 手动/顶刷正传");
            debugcmds[debugcmds.length-5] = '1';
        }else if(cmds[2]==2){
            logger.info("-->> 手动/顶刷反转");
            debugcmds[debugcmds.length-6] = '1';
        }
        if(cmds[3]==1){
            logger.info("-->> 手动/侧刷正传");
            debugcmds[debugcmds.length-7] = '1';
        }else if(cmds[3]==2){
            logger.info("-->> 手动/侧刷反转");
            debugcmds[debugcmds.length-8] = '1';
        }
        if(cmds[4]==1){
            logger.info("-->> 手动/顶刷升");
            debugcmds[debugcmds.length-9] = '1';
        }else if(cmds[4]==2){
            logger.info("-->> 手动/顶刷降");
            debugcmds[debugcmds.length-10] = '1';
        }
        if(cmds[5]==1){
            logger.info("-->> 手动/左侧刷开");
            debugcmds[debugcmds.length-13] = '1';
        }else if(cmds[5]==2){
            logger.info("-->> 手动/左侧刷合");
            debugcmds[debugcmds.length-14] = '1';
        }
        if(cmds[6]==1){
            logger.info("-->> 手动/右侧刷开");
            debugcmds[debugcmds.length-15] = '1';
        }else if(cmds[6]==2){
            logger.info("-->> 手动/右侧刷合");
            debugcmds[debugcmds.length-16] = '1';
        }
        if(cmds[7]==1){
            logger.info("-->> 手动/顶风1");
            debugcmds[debugcmds.length-19] = '1';
        }
        if(cmds[8]==1){
            logger.info("-->> 手动/顶风2");
            debugcmds[debugcmds.length-20] = '1';
        }
        if(cmds[9]==1){
            logger.info("-->> 手动/顶风3");
            debugcmds[debugcmds.length-21] = '1';
        }
        if(cmds[10]==1){
            logger.info("-->> 手动/顶风4");
            debugcmds[debugcmds.length-22] = '1';
        }
        if(cmds[11]==1){
            logger.info("-->> 手动/轮刷正转");
            debugcmds[debugcmds.length-23] = '1';
        }else if(cmds[11]==2){
            logger.info("-->> 手动/轮刷反转");
            debugcmds[debugcmds.length-24] = '1';
        }

        if(cmds[12]==1){
            logger.info("-->> 手动/清水水泵");
            debugcmds[debugcmds.length-25] = '1';
        }
        if(cmds[13]==1){
            logger.info("-->> 手动/泥土疏松剂泵");
            debugcmds[debugcmds.length-26] = '1';
        }
        if(cmds[14]==1){
            logger.info("-->> 手动/防冻排水");
            debugcmds[debugcmds.length-27] = '1';
        }
        if(cmds[15]==1){
            logger.info("-->> 手动/自动切换");
            debugcmds[debugcmds.length-28] = '1';
        }
        if(cmds[16]==1){
            logger.info("-->> 手动/顶刷水阀");
            debugcmds[debugcmds.length-29] = '1';
        }
        if(cmds[17]==1){
            logger.info("-->> 手动/侧刷水阀");
            debugcmds[debugcmds.length-30] = '1';
        }
        if(cmds[18]==1){
            logger.info("-->> 手动/轮刷水阀");
            debugcmds[debugcmds.length-31] = '1';
        }
        if(cmds[19]==1){
            logger.info("-->> 手动/泡沫水阀");
            debugcmds[debugcmds.length-32] = '1';
        }
        if(cmds[20]==1){
            logger.info("-->> 手动/水蜡水阀");
            debugcmds[debugcmds.length-33] = '1';
        }
        if(cmds[21]==1){
            logger.info("-->> 手动/喷淋水阀");
            debugcmds[debugcmds.length-34] = '1';
        }
        if(cmds[22]==1){
            logger.info("-->> 手动/清、污水转向阀");
            debugcmds[debugcmds.length-35] = '1';
        }
        if(cmds[23]==1){
            logger.info("-->> 手动/排水气阀");
            debugcmds[debugcmds.length-36] = '1';
        }
        if(cmds[24]==1){
            logger.info("-->> 手动/测量安全行程");
            debugcmds[debugcmds.length-37] = '1';
        }
        return new String(debugcmds);
    }


    public static void main(String[] args) {
        String s = "abc你好";
        String hex = toHexString(s.getBytes());
        String decode = hexString2Bytes(hex);
        System.out.println("原字符串:" + s);
        System.out.println("十六进制字符串:" + hex);
        System.out.println("还原:" + decode);
    }

}
