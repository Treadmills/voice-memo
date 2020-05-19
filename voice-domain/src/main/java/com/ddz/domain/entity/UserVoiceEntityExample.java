package com.ddz.domain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserVoiceEntityExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public UserVoiceEntityExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserVoiceIdIsNull() {
            addCriterion("F_USER_VOICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdIsNotNull() {
            addCriterion("F_USER_VOICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdEqualTo(Long value) {
            addCriterion("F_USER_VOICE_ID =", value, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdNotEqualTo(Long value) {
            addCriterion("F_USER_VOICE_ID <>", value, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdGreaterThan(Long value) {
            addCriterion("F_USER_VOICE_ID >", value, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("F_USER_VOICE_ID >=", value, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdLessThan(Long value) {
            addCriterion("F_USER_VOICE_ID <", value, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdLessThanOrEqualTo(Long value) {
            addCriterion("F_USER_VOICE_ID <=", value, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdIn(List<Long> values) {
            addCriterion("F_USER_VOICE_ID in", values, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdNotIn(List<Long> values) {
            addCriterion("F_USER_VOICE_ID not in", values, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdBetween(Long value1, Long value2) {
            addCriterion("F_USER_VOICE_ID between", value1, value2, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserVoiceIdNotBetween(Long value1, Long value2) {
            addCriterion("F_USER_VOICE_ID not between", value1, value2, "userVoiceId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("F_USER_ID is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("F_USER_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Long value) {
            addCriterion("F_USER_ID =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Long value) {
            addCriterion("F_USER_ID <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Long value) {
            addCriterion("F_USER_ID >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("F_USER_ID >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Long value) {
            addCriterion("F_USER_ID <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("F_USER_ID <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Long> values) {
            addCriterion("F_USER_ID in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Long> values) {
            addCriterion("F_USER_ID not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("F_USER_ID between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("F_USER_ID not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andVoicePathIsNull() {
            addCriterion("F_VOICE_PATH is null");
            return (Criteria) this;
        }

        public Criteria andVoicePathIsNotNull() {
            addCriterion("F_VOICE_PATH is not null");
            return (Criteria) this;
        }

        public Criteria andVoicePathEqualTo(String value) {
            addCriterion("F_VOICE_PATH =", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathNotEqualTo(String value) {
            addCriterion("F_VOICE_PATH <>", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathGreaterThan(String value) {
            addCriterion("F_VOICE_PATH >", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathGreaterThanOrEqualTo(String value) {
            addCriterion("F_VOICE_PATH >=", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathLessThan(String value) {
            addCriterion("F_VOICE_PATH <", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathLessThanOrEqualTo(String value) {
            addCriterion("F_VOICE_PATH <=", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathLike(String value) {
            addCriterion("F_VOICE_PATH like", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathNotLike(String value) {
            addCriterion("F_VOICE_PATH not like", value, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathIn(List<String> values) {
            addCriterion("F_VOICE_PATH in", values, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathNotIn(List<String> values) {
            addCriterion("F_VOICE_PATH not in", values, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathBetween(String value1, String value2) {
            addCriterion("F_VOICE_PATH between", value1, value2, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoicePathNotBetween(String value1, String value2) {
            addCriterion("F_VOICE_PATH not between", value1, value2, "voicePath");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageIsNull() {
            addCriterion("F_VOICE_MESSAGE is null");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageIsNotNull() {
            addCriterion("F_VOICE_MESSAGE is not null");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageEqualTo(String value) {
            addCriterion("F_VOICE_MESSAGE =", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageNotEqualTo(String value) {
            addCriterion("F_VOICE_MESSAGE <>", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageGreaterThan(String value) {
            addCriterion("F_VOICE_MESSAGE >", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageGreaterThanOrEqualTo(String value) {
            addCriterion("F_VOICE_MESSAGE >=", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageLessThan(String value) {
            addCriterion("F_VOICE_MESSAGE <", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageLessThanOrEqualTo(String value) {
            addCriterion("F_VOICE_MESSAGE <=", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageLike(String value) {
            addCriterion("F_VOICE_MESSAGE like", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageNotLike(String value) {
            addCriterion("F_VOICE_MESSAGE not like", value, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageIn(List<String> values) {
            addCriterion("F_VOICE_MESSAGE in", values, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageNotIn(List<String> values) {
            addCriterion("F_VOICE_MESSAGE not in", values, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageBetween(String value1, String value2) {
            addCriterion("F_VOICE_MESSAGE between", value1, value2, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andVoiceMessageNotBetween(String value1, String value2) {
            addCriterion("F_VOICE_MESSAGE not between", value1, value2, "voiceMessage");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNull() {
            addCriterion("F_IS_DELETE is null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIsNotNull() {
            addCriterion("F_IS_DELETE is not null");
            return (Criteria) this;
        }

        public Criteria andIsDeleteEqualTo(Integer value) {
            addCriterion("F_IS_DELETE =", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotEqualTo(Integer value) {
            addCriterion("F_IS_DELETE <>", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThan(Integer value) {
            addCriterion("F_IS_DELETE >", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
            addCriterion("F_IS_DELETE >=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThan(Integer value) {
            addCriterion("F_IS_DELETE <", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
            addCriterion("F_IS_DELETE <=", value, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteIn(List<Integer> values) {
            addCriterion("F_IS_DELETE in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotIn(List<Integer> values) {
            addCriterion("F_IS_DELETE not in", values, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
            addCriterion("F_IS_DELETE between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
            addCriterion("F_IS_DELETE not between", value1, value2, "isDelete");
            return (Criteria) this;
        }

        public Criteria andIndateIsNull() {
            addCriterion("F_INDATE is null");
            return (Criteria) this;
        }

        public Criteria andIndateIsNotNull() {
            addCriterion("F_INDATE is not null");
            return (Criteria) this;
        }

        public Criteria andIndateEqualTo(Date value) {
            addCriterion("F_INDATE =", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotEqualTo(Date value) {
            addCriterion("F_INDATE <>", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThan(Date value) {
            addCriterion("F_INDATE >", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateGreaterThanOrEqualTo(Date value) {
            addCriterion("F_INDATE >=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThan(Date value) {
            addCriterion("F_INDATE <", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateLessThanOrEqualTo(Date value) {
            addCriterion("F_INDATE <=", value, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateIn(List<Date> values) {
            addCriterion("F_INDATE in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotIn(List<Date> values) {
            addCriterion("F_INDATE not in", values, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateBetween(Date value1, Date value2) {
            addCriterion("F_INDATE between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andIndateNotBetween(Date value1, Date value2) {
            addCriterion("F_INDATE not between", value1, value2, "indate");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("F_UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("F_UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("F_UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("F_UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("F_UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("F_UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("F_UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("F_UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("F_UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("F_UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("F_UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("F_UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_user_voice
     *
     * @mbg.generated do_not_delete_during_merge Sat Apr 04 11:02:05 CST 2020
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_user_voice
     *
     * @mbg.generated Sat Apr 04 11:02:05 CST 2020
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}