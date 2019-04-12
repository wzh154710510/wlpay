package org.wlpay.dal.dao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotificationExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    private Integer limit;

    private Integer offset;

    public NotificationExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andIdIsNull() {
            addCriterion("Id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("Id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("Id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("Id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("Id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("Id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("Id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("Id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("Id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("Id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("Id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("Id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andMchIDIsNull() {
            addCriterion("MchID is null");
            return (Criteria) this;
        }

        public Criteria andMchIDIsNotNull() {
            addCriterion("MchID is not null");
            return (Criteria) this;
        }

        public Criteria andMchIDEqualTo(String value) {
            addCriterion("MchID =", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDNotEqualTo(String value) {
            addCriterion("MchID <>", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDGreaterThan(String value) {
            addCriterion("MchID >", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDGreaterThanOrEqualTo(String value) {
            addCriterion("MchID >=", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDLessThan(String value) {
            addCriterion("MchID <", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDLessThanOrEqualTo(String value) {
            addCriterion("MchID <=", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDLike(String value) {
            addCriterion("MchID like", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDNotLike(String value) {
            addCriterion("MchID not like", value, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDIn(List<String> values) {
            addCriterion("MchID in", values, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDNotIn(List<String> values) {
            addCriterion("MchID not in", values, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDBetween(String value1, String value2) {
            addCriterion("MchID between", value1, value2, "mchID");
            return (Criteria) this;
        }

        public Criteria andMchIDNotBetween(String value1, String value2) {
            addCriterion("MchID not between", value1, value2, "mchID");
            return (Criteria) this;
        }

        public Criteria andChannelIsNull() {
            addCriterion("Channel is null");
            return (Criteria) this;
        }

        public Criteria andChannelIsNotNull() {
            addCriterion("Channel is not null");
            return (Criteria) this;
        }

        public Criteria andChannelEqualTo(String value) {
            addCriterion("Channel =", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotEqualTo(String value) {
            addCriterion("Channel <>", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThan(String value) {
            addCriterion("Channel >", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelGreaterThanOrEqualTo(String value) {
            addCriterion("Channel >=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThan(String value) {
            addCriterion("Channel <", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLessThanOrEqualTo(String value) {
            addCriterion("Channel <=", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelLike(String value) {
            addCriterion("Channel like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotLike(String value) {
            addCriterion("Channel not like", value, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelIn(List<String> values) {
            addCriterion("Channel in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotIn(List<String> values) {
            addCriterion("Channel not in", values, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelBetween(String value1, String value2) {
            addCriterion("Channel between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andChannelNotBetween(String value1, String value2) {
            addCriterion("Channel not between", value1, value2, "channel");
            return (Criteria) this;
        }

        public Criteria andUserPayNameIsNull() {
            addCriterion("UserPayName is null");
            return (Criteria) this;
        }

        public Criteria andUserPayNameIsNotNull() {
            addCriterion("UserPayName is not null");
            return (Criteria) this;
        }

        public Criteria andUserPayNameEqualTo(String value) {
            addCriterion("UserPayName =", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameNotEqualTo(String value) {
            addCriterion("UserPayName <>", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameGreaterThan(String value) {
            addCriterion("UserPayName >", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameGreaterThanOrEqualTo(String value) {
            addCriterion("UserPayName >=", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameLessThan(String value) {
            addCriterion("UserPayName <", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameLessThanOrEqualTo(String value) {
            addCriterion("UserPayName <=", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameLike(String value) {
            addCriterion("UserPayName like", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameNotLike(String value) {
            addCriterion("UserPayName not like", value, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameIn(List<String> values) {
            addCriterion("UserPayName in", values, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameNotIn(List<String> values) {
            addCriterion("UserPayName not in", values, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameBetween(String value1, String value2) {
            addCriterion("UserPayName between", value1, value2, "userPayName");
            return (Criteria) this;
        }

        public Criteria andUserPayNameNotBetween(String value1, String value2) {
            addCriterion("UserPayName not between", value1, value2, "userPayName");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("Amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("Amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(String value) {
            addCriterion("Amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(String value) {
            addCriterion("Amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(String value) {
            addCriterion("Amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(String value) {
            addCriterion("Amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(String value) {
            addCriterion("Amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(String value) {
            addCriterion("Amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLike(String value) {
            addCriterion("Amount like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotLike(String value) {
            addCriterion("Amount not like", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<String> values) {
            addCriterion("Amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<String> values) {
            addCriterion("Amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(String value1, String value2) {
            addCriterion("Amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(String value1, String value2) {
            addCriterion("Amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andListenerTimeIsNull() {
            addCriterion("ListenerTime is null");
            return (Criteria) this;
        }

        public Criteria andListenerTimeIsNotNull() {
            addCriterion("ListenerTime is not null");
            return (Criteria) this;
        }

        public Criteria andListenerTimeEqualTo(Date value) {
            addCriterion("ListenerTime =", value, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeNotEqualTo(Date value) {
            addCriterion("ListenerTime <>", value, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeGreaterThan(Date value) {
            addCriterion("ListenerTime >", value, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ListenerTime >=", value, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeLessThan(Date value) {
            addCriterion("ListenerTime <", value, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeLessThanOrEqualTo(Date value) {
            addCriterion("ListenerTime <=", value, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeIn(List<Date> values) {
            addCriterion("ListenerTime in", values, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeNotIn(List<Date> values) {
            addCriterion("ListenerTime not in", values, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeBetween(Date value1, Date value2) {
            addCriterion("ListenerTime between", value1, value2, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andListenerTimeNotBetween(Date value1, Date value2) {
            addCriterion("ListenerTime not between", value1, value2, "listenerTime");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleIsNull() {
            addCriterion("NotifiTiTle is null");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleIsNotNull() {
            addCriterion("NotifiTiTle is not null");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleEqualTo(String value) {
            addCriterion("NotifiTiTle =", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleNotEqualTo(String value) {
            addCriterion("NotifiTiTle <>", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleGreaterThan(String value) {
            addCriterion("NotifiTiTle >", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleGreaterThanOrEqualTo(String value) {
            addCriterion("NotifiTiTle >=", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleLessThan(String value) {
            addCriterion("NotifiTiTle <", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleLessThanOrEqualTo(String value) {
            addCriterion("NotifiTiTle <=", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleLike(String value) {
            addCriterion("NotifiTiTle like", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleNotLike(String value) {
            addCriterion("NotifiTiTle not like", value, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleIn(List<String> values) {
            addCriterion("NotifiTiTle in", values, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleNotIn(List<String> values) {
            addCriterion("NotifiTiTle not in", values, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleBetween(String value1, String value2) {
            addCriterion("NotifiTiTle between", value1, value2, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTiTleNotBetween(String value1, String value2) {
            addCriterion("NotifiTiTle not between", value1, value2, "notifiTiTle");
            return (Criteria) this;
        }

        public Criteria andNotifiTextIsNull() {
            addCriterion("NotifiText is null");
            return (Criteria) this;
        }

        public Criteria andNotifiTextIsNotNull() {
            addCriterion("NotifiText is not null");
            return (Criteria) this;
        }

        public Criteria andNotifiTextEqualTo(String value) {
            addCriterion("NotifiText =", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextNotEqualTo(String value) {
            addCriterion("NotifiText <>", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextGreaterThan(String value) {
            addCriterion("NotifiText >", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextGreaterThanOrEqualTo(String value) {
            addCriterion("NotifiText >=", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextLessThan(String value) {
            addCriterion("NotifiText <", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextLessThanOrEqualTo(String value) {
            addCriterion("NotifiText <=", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextLike(String value) {
            addCriterion("NotifiText like", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextNotLike(String value) {
            addCriterion("NotifiText not like", value, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextIn(List<String> values) {
            addCriterion("NotifiText in", values, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextNotIn(List<String> values) {
            addCriterion("NotifiText not in", values, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextBetween(String value1, String value2) {
            addCriterion("NotifiText between", value1, value2, "notifiText");
            return (Criteria) this;
        }

        public Criteria andNotifiTextNotBetween(String value1, String value2) {
            addCriterion("NotifiText not between", value1, value2, "notifiText");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CreateTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CreateTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CreateTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CreateTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CreateTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CreateTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CreateTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CreateTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CreateTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CreateTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CreateTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CreateTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
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