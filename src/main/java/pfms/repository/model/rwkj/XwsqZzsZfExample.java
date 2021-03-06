package pfms.repository.model.rwkj;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class XwsqZzsZfExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public XwsqZzsZfExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
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
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
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

        public Criteria andInvoicecodeIsNull() {
            addCriterion("INVOICECODE is null");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeIsNotNull() {
            addCriterion("INVOICECODE is not null");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeEqualTo(String value) {
            addCriterion("INVOICECODE =", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeNotEqualTo(String value) {
            addCriterion("INVOICECODE <>", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeGreaterThan(String value) {
            addCriterion("INVOICECODE >", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeGreaterThanOrEqualTo(String value) {
            addCriterion("INVOICECODE >=", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeLessThan(String value) {
            addCriterion("INVOICECODE <", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeLessThanOrEqualTo(String value) {
            addCriterion("INVOICECODE <=", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeLike(String value) {
            addCriterion("INVOICECODE like", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeNotLike(String value) {
            addCriterion("INVOICECODE not like", value, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeIn(List<String> values) {
            addCriterion("INVOICECODE in", values, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeNotIn(List<String> values) {
            addCriterion("INVOICECODE not in", values, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeBetween(String value1, String value2) {
            addCriterion("INVOICECODE between", value1, value2, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andInvoicecodeNotBetween(String value1, String value2) {
            addCriterion("INVOICECODE not between", value1, value2, "invoicecode");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("TYPE is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("TYPE =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("TYPE <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("TYPE >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("TYPE >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("TYPE <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("TYPE <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("TYPE like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("TYPE not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("TYPE in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("TYPE not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("TYPE between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("TYPE not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTag1IsNull() {
            addCriterion("TAG1 is null");
            return (Criteria) this;
        }

        public Criteria andTag1IsNotNull() {
            addCriterion("TAG1 is not null");
            return (Criteria) this;
        }

        public Criteria andTag1EqualTo(String value) {
            addCriterion("TAG1 =", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1NotEqualTo(String value) {
            addCriterion("TAG1 <>", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1GreaterThan(String value) {
            addCriterion("TAG1 >", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1GreaterThanOrEqualTo(String value) {
            addCriterion("TAG1 >=", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1LessThan(String value) {
            addCriterion("TAG1 <", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1LessThanOrEqualTo(String value) {
            addCriterion("TAG1 <=", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1Like(String value) {
            addCriterion("TAG1 like", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1NotLike(String value) {
            addCriterion("TAG1 not like", value, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1In(List<String> values) {
            addCriterion("TAG1 in", values, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1NotIn(List<String> values) {
            addCriterion("TAG1 not in", values, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1Between(String value1, String value2) {
            addCriterion("TAG1 between", value1, value2, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag1NotBetween(String value1, String value2) {
            addCriterion("TAG1 not between", value1, value2, "tag1");
            return (Criteria) this;
        }

        public Criteria andTag2IsNull() {
            addCriterion("TAG2 is null");
            return (Criteria) this;
        }

        public Criteria andTag2IsNotNull() {
            addCriterion("TAG2 is not null");
            return (Criteria) this;
        }

        public Criteria andTag2EqualTo(String value) {
            addCriterion("TAG2 =", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2NotEqualTo(String value) {
            addCriterion("TAG2 <>", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2GreaterThan(String value) {
            addCriterion("TAG2 >", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2GreaterThanOrEqualTo(String value) {
            addCriterion("TAG2 >=", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2LessThan(String value) {
            addCriterion("TAG2 <", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2LessThanOrEqualTo(String value) {
            addCriterion("TAG2 <=", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2Like(String value) {
            addCriterion("TAG2 like", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2NotLike(String value) {
            addCriterion("TAG2 not like", value, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2In(List<String> values) {
            addCriterion("TAG2 in", values, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2NotIn(List<String> values) {
            addCriterion("TAG2 not in", values, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2Between(String value1, String value2) {
            addCriterion("TAG2 between", value1, value2, "tag2");
            return (Criteria) this;
        }

        public Criteria andTag2NotBetween(String value1, String value2) {
            addCriterion("TAG2 not between", value1, value2, "tag2");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("MSG is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("MSG is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("MSG =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("MSG <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("MSG >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("MSG >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("MSG <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("MSG <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("MSG like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("MSG not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("MSG in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("MSG not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("MSG between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("MSG not between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andClbzIsNull() {
            addCriterion("CLBZ is null");
            return (Criteria) this;
        }

        public Criteria andClbzIsNotNull() {
            addCriterion("CLBZ is not null");
            return (Criteria) this;
        }

        public Criteria andClbzEqualTo(String value) {
            addCriterion("CLBZ =", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzNotEqualTo(String value) {
            addCriterion("CLBZ <>", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzGreaterThan(String value) {
            addCriterion("CLBZ >", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzGreaterThanOrEqualTo(String value) {
            addCriterion("CLBZ >=", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzLessThan(String value) {
            addCriterion("CLBZ <", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzLessThanOrEqualTo(String value) {
            addCriterion("CLBZ <=", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzLike(String value) {
            addCriterion("CLBZ like", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzNotLike(String value) {
            addCriterion("CLBZ not like", value, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzIn(List<String> values) {
            addCriterion("CLBZ in", values, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzNotIn(List<String> values) {
            addCriterion("CLBZ not in", values, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzBetween(String value1, String value2) {
            addCriterion("CLBZ between", value1, value2, "clbz");
            return (Criteria) this;
        }

        public Criteria andClbzNotBetween(String value1, String value2) {
            addCriterion("CLBZ not between", value1, value2, "clbz");
            return (Criteria) this;
        }

        public Criteria andXrrqIsNull() {
            addCriterion("XRRQ is null");
            return (Criteria) this;
        }

        public Criteria andXrrqIsNotNull() {
            addCriterion("XRRQ is not null");
            return (Criteria) this;
        }

        public Criteria andXrrqEqualTo(Date value) {
            addCriterion("XRRQ =", value, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqNotEqualTo(Date value) {
            addCriterion("XRRQ <>", value, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqGreaterThan(Date value) {
            addCriterion("XRRQ >", value, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqGreaterThanOrEqualTo(Date value) {
            addCriterion("XRRQ >=", value, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqLessThan(Date value) {
            addCriterion("XRRQ <", value, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqLessThanOrEqualTo(Date value) {
            addCriterion("XRRQ <=", value, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqIn(List<Date> values) {
            addCriterion("XRRQ in", values, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqNotIn(List<Date> values) {
            addCriterion("XRRQ not in", values, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqBetween(Date value1, Date value2) {
            addCriterion("XRRQ between", value1, value2, "xrrq");
            return (Criteria) this;
        }

        public Criteria andXrrqNotBetween(Date value1, Date value2) {
            addCriterion("XRRQ not between", value1, value2, "xrrq");
            return (Criteria) this;
        }

        public Criteria andSkrqIsNull() {
            addCriterion("SKRQ is null");
            return (Criteria) this;
        }

        public Criteria andSkrqIsNotNull() {
            addCriterion("SKRQ is not null");
            return (Criteria) this;
        }

        public Criteria andSkrqEqualTo(Date value) {
            addCriterion("SKRQ =", value, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqNotEqualTo(Date value) {
            addCriterion("SKRQ <>", value, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqGreaterThan(Date value) {
            addCriterion("SKRQ >", value, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqGreaterThanOrEqualTo(Date value) {
            addCriterion("SKRQ >=", value, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqLessThan(Date value) {
            addCriterion("SKRQ <", value, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqLessThanOrEqualTo(Date value) {
            addCriterion("SKRQ <=", value, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqIn(List<Date> values) {
            addCriterion("SKRQ in", values, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqNotIn(List<Date> values) {
            addCriterion("SKRQ not in", values, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqBetween(Date value1, Date value2) {
            addCriterion("SKRQ between", value1, value2, "skrq");
            return (Criteria) this;
        }

        public Criteria andSkrqNotBetween(Date value1, Date value2) {
            addCriterion("SKRQ not between", value1, value2, "skrq");
            return (Criteria) this;
        }

        public Criteria andClcsIsNull() {
            addCriterion("CLCS is null");
            return (Criteria) this;
        }

        public Criteria andClcsIsNotNull() {
            addCriterion("CLCS is not null");
            return (Criteria) this;
        }

        public Criteria andClcsEqualTo(Short value) {
            addCriterion("CLCS =", value, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsNotEqualTo(Short value) {
            addCriterion("CLCS <>", value, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsGreaterThan(Short value) {
            addCriterion("CLCS >", value, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsGreaterThanOrEqualTo(Short value) {
            addCriterion("CLCS >=", value, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsLessThan(Short value) {
            addCriterion("CLCS <", value, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsLessThanOrEqualTo(Short value) {
            addCriterion("CLCS <=", value, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsIn(List<Short> values) {
            addCriterion("CLCS in", values, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsNotIn(List<Short> values) {
            addCriterion("CLCS not in", values, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsBetween(Short value1, Short value2) {
            addCriterion("CLCS between", value1, value2, "clcs");
            return (Criteria) this;
        }

        public Criteria andClcsNotBetween(Short value1, Short value2) {
            addCriterion("CLCS not between", value1, value2, "clcs");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated do_not_delete_during_merge Mon Apr 25 09:11:23 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table RWKJ.XWSQ_ZZS_ZF
     *
     * @mbggenerated Mon Apr 25 09:11:23 CST 2016
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