package pfms.repository.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvZzsZfhxExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public InvZzsZfhxExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
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
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
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

        public Criteria andFlagIsNull() {
            addCriterion("FLAG is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("FLAG is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(String value) {
            addCriterion("FLAG =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(String value) {
            addCriterion("FLAG <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(String value) {
            addCriterion("FLAG >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(String value) {
            addCriterion("FLAG >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(String value) {
            addCriterion("FLAG <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(String value) {
            addCriterion("FLAG <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLike(String value) {
            addCriterion("FLAG like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotLike(String value) {
            addCriterion("FLAG not like", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<String> values) {
            addCriterion("FLAG in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<String> values) {
            addCriterion("FLAG not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(String value1, String value2) {
            addCriterion("FLAG between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(String value1, String value2) {
            addCriterion("FLAG not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andKprqIsNull() {
            addCriterion("KPRQ is null");
            return (Criteria) this;
        }

        public Criteria andKprqIsNotNull() {
            addCriterion("KPRQ is not null");
            return (Criteria) this;
        }

        public Criteria andKprqEqualTo(Date value) {
            addCriterion("KPRQ =", value, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqNotEqualTo(Date value) {
            addCriterion("KPRQ <>", value, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqGreaterThan(Date value) {
            addCriterion("KPRQ >", value, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqGreaterThanOrEqualTo(Date value) {
            addCriterion("KPRQ >=", value, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqLessThan(Date value) {
            addCriterion("KPRQ <", value, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqLessThanOrEqualTo(Date value) {
            addCriterion("KPRQ <=", value, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqIn(List<Date> values) {
            addCriterion("KPRQ in", values, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqNotIn(List<Date> values) {
            addCriterion("KPRQ not in", values, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqBetween(Date value1, Date value2) {
            addCriterion("KPRQ between", value1, value2, "kprq");
            return (Criteria) this;
        }

        public Criteria andKprqNotBetween(Date value1, Date value2) {
            addCriterion("KPRQ not between", value1, value2, "kprq");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeIsNull() {
            addCriterion("RELATEDCODE is null");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeIsNotNull() {
            addCriterion("RELATEDCODE is not null");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeEqualTo(String value) {
            addCriterion("RELATEDCODE =", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeNotEqualTo(String value) {
            addCriterion("RELATEDCODE <>", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeGreaterThan(String value) {
            addCriterion("RELATEDCODE >", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeGreaterThanOrEqualTo(String value) {
            addCriterion("RELATEDCODE >=", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeLessThan(String value) {
            addCriterion("RELATEDCODE <", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeLessThanOrEqualTo(String value) {
            addCriterion("RELATEDCODE <=", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeLike(String value) {
            addCriterion("RELATEDCODE like", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeNotLike(String value) {
            addCriterion("RELATEDCODE not like", value, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeIn(List<String> values) {
            addCriterion("RELATEDCODE in", values, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeNotIn(List<String> values) {
            addCriterion("RELATEDCODE not in", values, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeBetween(String value1, String value2) {
            addCriterion("RELATEDCODE between", value1, value2, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andRelatedcodeNotBetween(String value1, String value2) {
            addCriterion("RELATEDCODE not between", value1, value2, "relatedcode");
            return (Criteria) this;
        }

        public Criteria andViewurlIsNull() {
            addCriterion("VIEWURL is null");
            return (Criteria) this;
        }

        public Criteria andViewurlIsNotNull() {
            addCriterion("VIEWURL is not null");
            return (Criteria) this;
        }

        public Criteria andViewurlEqualTo(String value) {
            addCriterion("VIEWURL =", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlNotEqualTo(String value) {
            addCriterion("VIEWURL <>", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlGreaterThan(String value) {
            addCriterion("VIEWURL >", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlGreaterThanOrEqualTo(String value) {
            addCriterion("VIEWURL >=", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlLessThan(String value) {
            addCriterion("VIEWURL <", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlLessThanOrEqualTo(String value) {
            addCriterion("VIEWURL <=", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlLike(String value) {
            addCriterion("VIEWURL like", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlNotLike(String value) {
            addCriterion("VIEWURL not like", value, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlIn(List<String> values) {
            addCriterion("VIEWURL in", values, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlNotIn(List<String> values) {
            addCriterion("VIEWURL not in", values, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlBetween(String value1, String value2) {
            addCriterion("VIEWURL between", value1, value2, "viewurl");
            return (Criteria) this;
        }

        public Criteria andViewurlNotBetween(String value1, String value2) {
            addCriterion("VIEWURL not between", value1, value2, "viewurl");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated do_not_delete_during_merge Sun Apr 24 16:20:34 CST 2016
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table INV_ZZS_ZFHX
     *
     * @mbggenerated Sun Apr 24 16:20:34 CST 2016
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